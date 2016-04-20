package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Utility class of methods for graphs.
 * @author zalan0
 *
 * Class containing methods to manipulate graphs.
 */
public class Graphs {
	
//	Graph graph;
//	
//	public Graphs(Graph g) {
//		graph = g;
//	}
	
	/**
	 * Breadth First Search implementation that produces a pairing of nodes
	 * that can be reversed to find a shortest route.
	 * 
	 * @param start
	 * @param goal
	 * @return
	 */
	public static HashMap<Vertex, Vertex> shortestPathBFS(Vertex start, Vertex goal) {
		
		// initialize queue, visited, and parent
		Queue<Vertex> queue = new LinkedList<Vertex>();
		HashSet<Vertex> visited = new HashSet<Vertex>();
		HashMap<Vertex, Vertex> parent = new HashMap<Vertex, Vertex>();
		
		// enqueue start onto queue and add to visited
		queue.add(start);
		visited.add(start);
		
		//while queue is not empty, do stuff...
		while(!queue.isEmpty()) {
			//dequeue node curr from front of queue
			Vertex curr = queue.poll();
			
			// if curr is goal then return parent map
			if(curr.equals(goal)) return parent;
			
			// for each of curr's neighbors, n, not in visited set
			Vertex[] neighbors = curr.getNeighbors();
			for(Vertex n: neighbors) {
				if(!visited.contains(n)) {
					// add n to visited set
					visited.add(n);
					
					//add curr as n's parent in parent
					parent.put(n, curr);
					
					// engueue n onto queue
					queue.add(n);
				}
			}
		}
		return null;
	}
	
	/**
	 * Breadth First Search implementation that visits all of the nodes that are connected 
	 * to the start node.
	 * 
	 * @param start Vertex to start the search
	 * @return HashSet of all nodes connected to the start node
	 */
	public static HashSet<Vertex> visitBFS(Vertex start) {
		
		// initialize queue, visited, and parent
		Queue<Vertex> queue = new LinkedList<Vertex>();
		HashSet<Vertex> visited = new HashSet<Vertex>();
		
		// enqueue start onto queue and add to visited
		queue.add(start);
		visited.add(start);
		
		//while queue is not empty, do stuff...
		while(!queue.isEmpty()) {
			//dequeue node curr from front of queue
			Vertex curr = queue.poll();
			
			// for each of curr's neighbors, n, not in visited set
			Vertex[] neighbors = curr.getNeighbors();
			for(Vertex n: neighbors) {
				if(!visited.contains(n)) {
					// add n to visited set
					visited.add(n);
					
					// engueue n onto queue
					queue.add(n);
				}
			}
		}
		return visited;
	}
	
	/**
	 * An implementation of BFS that returns the number of all possible shortest
	 * routes from start to goal.
	 * 
	 * @param start Vertex to start the search
	 * @return An ArrayList of HashSets of Vertices that show distance from start
	 * by their index within the ArrayList
	 */
	public static ArrayList<HashSet<Vertex>> shortestRoutesBFS(Vertex start) {
		// initialize queue, visited, and levels array
		ArrayList<HashSet<Vertex>> levels = new ArrayList<HashSet<Vertex>>();
		Queue<Vertex> currentLevel = new LinkedList<Vertex>();
		Queue<Vertex> nextLevel = new LinkedList<Vertex>();
		HashSet<Vertex> visited = new HashSet<Vertex>();
		
		// enqueue start onto queue and add to visited and levels
		currentLevel.add(start);
		visited.add(start);
		levels.add(new HashSet<Vertex>(Arrays.asList(start)));
		
		//while queue is not empty, do stuff...
		while(!currentLevel.isEmpty()) {
			//dequeue node curr from front of queue and increment level
			Vertex curr = currentLevel.poll();
			
			// for each of curr's neighbors, n, not in visited set
			Vertex[] neighbors = curr.getNeighbors();
			for(Vertex n: neighbors) {
				
				if(!visited.contains(n)) {
					
					// add n to visited set
					visited.add(n);
					
					// engueue n onto queue
					nextLevel.add(n);
				}
			}
			
			// when the currentLevel queue is empty, swap it with the nextLevel 
			// queue and at that to the levels array
			if(currentLevel.isEmpty()) {
				if(!nextLevel.isEmpty()) 
					levels.add(new HashSet<Vertex>(nextLevel));
				currentLevel.addAll(nextLevel);
				nextLevel.clear();
			}
		}
		return levels;
	}
	
	/**
	 * Method that takes the output from the shortestRoutesBFS method and finds the number of
	 * paths that travel through each node.
	 * 
	 * @param levels ArrayList of HashSets - output from BFS search
	 * @return a map of nodes and the amount of paths that travel through them.
	 */
	public static HashMap<Vertex, Integer> countShortestPaths(ArrayList<HashSet<Vertex>> levels) {
		
		HashMap<Vertex, Integer> paths = new HashMap<Vertex, Integer>();
		
		// set shortest paths at node in index 0 to be 1
		Vertex start = (Vertex) levels.get(0).toArray()[0];
		paths.put(start, 1);
		
		// iterate through all levels starting with index 1
		for(int level = 1; level < levels.size(); level++) {
			
			// iterate through all nodes within current level
			Iterator<Vertex> nodes = levels.get(level).iterator();
			while(nodes.hasNext()) {
				Vertex currentNode = nodes.next(); 
				
				// add paths through current node's neighbors together
				int tempPaths = 0;
				for(Vertex n: currentNode.getNeighbors()) {
					if(levels.get(level - 1).contains(n)) {
						tempPaths += paths.get(n);
					}
				}
				// assign that number to current node.
				paths.put(currentNode, tempPaths);
			}
		}
		return paths;
	}
	
	/**
	 * Method that takes the output from both the shortestRoutesBFS and numberOfShortestPaths
	 * methods and finds the betweeness or flow for all edges in the graph.  This value is stored
	 * within the Edge Class.
	 * 
	 * @param levels ArrayList of HashSets - output from BFS search
	 * @param paths a map of nodes and the amount of paths that travel through them - output from
	 * 			numberOfShortestPaths.
	 */
	public static void calculateEdgeFlow(ArrayList<HashSet<Vertex>> levels,
			HashMap<Vertex, Integer> paths) {  
		//TODO not returning correct value on edges from start to levels index 1 
		// see notes for week 4 for a new algorithm
		
		// Decrement through levels
		for(int level = levels.size() - 1; level > 0; level--) {
			//iterate through current level
			HashSet<Vertex> aboveLevel = levels.get(level - 1);
			HashSet<Vertex> belowLevel = new HashSet<Vertex>();
			if(level != levels.size() - 1) {
				belowLevel = levels.get(level + 1);
			}
			Iterator<Vertex> currentLevel = levels.get(level).iterator();
			while(currentLevel.hasNext()) {
				Vertex currentNode = currentLevel.next();
				
				// find max flow (all flow arriving to this node from below + 1 for itself)
				double maxFlow = 0.0d;
				if(level != levels.size() - 1) {
					// add flows from all incoming edges
					Iterator<Edge> edges = currentNode.getEdges().iterator();
					while(edges.hasNext()) {
						Edge edge = edges.next();
						Vertex other = edge.getOther(currentNode);
						if(belowLevel.contains(other)) {
							maxFlow += edge.getFlow();
						}
					}
				} 
				maxFlow += 1;
				
				// compare level above to neighbors of current node
				// find total paths
				int totalPaths = 0;
				HashMap<Vertex, Edge> neighborsAbove = new HashMap<Vertex, Edge>();
				Iterator<Edge> edges = currentNode.getEdges().iterator();
				while(edges.hasNext()) {
					Edge edge = edges.next();
					Vertex other = edge.getOther(currentNode);
					if(aboveLevel.contains(other)) {
						totalPaths += paths.get(other);
						neighborsAbove.put(other, edge);
					}
				}
				// flow for edge will be the ratio of total paths to neighbor's path
				Iterator<Vertex> i = neighborsAbove.keySet().iterator();
				while(i.hasNext()) {
					Vertex neighbor = i.next();
					System.out.println("setting flow from " + neighbor.name() + "to " + currentNode.name());
					double ratio = paths.get(neighbor)/ (double) totalPaths;
					double flow = ((ratio) * maxFlow) / 2.0d;
					System.out.println("    ratio: " + ratio + "  flow: " + flow);
					neighborsAbove.get(neighbor).incrementFlow(flow);
				}
			}
		}
	}
	
	public static void calculateEdgeFlow2
	(ArrayList<HashSet<Vertex>> levels, HashMap<Vertex, Integer> paths) {
		HashMap<Edge, Double> flowMap = new HashMap<Edge, Double>(); 
//		HashMap<Vertex, Double> incomingFlows = new HashMap<Vertex, Double>();
		
		// initialize bottom level w/ total flow of 1
		// divided by the ratio of paths to neighbors
		int level = levels.size()-1;
		HashSet<Vertex> currentLevel = levels.get(level);
		HashSet<Vertex> aboveCurrentLevel = levels.get(level-1);
		int totalPaths = 0;
		Iterator<Vertex> it = currentLevel.iterator();
		
		// find total paths through this level
		while(it.hasNext()) {
			Vertex currentNode = it.next();
			totalPaths += paths.get(currentNode);
		}
		
		// assign flows to edges
		it = currentLevel.iterator();
		while(it.hasNext()) {
			Vertex currentNode = it.next();
			double incomingFlow = paths.get(currentNode) / (double) totalPaths;
			HashSet<Edge> edges = currentNode.getEdges();
			Iterator<Edge> edgeIt = edges.iterator();
			while(edgeIt.hasNext()) {
				Edge edge = edgeIt.next();
				if(!aboveCurrentLevel.contains(edge.getOther(currentNode))) {
					edges.remove(edge);
				}
			}
			edgeIt = edges.iterator();
			while(edgeIt.hasNext()) {
				Edge edge = edgeIt.next();
				double flow = incomingFlow / (double) edges.size();
				flowMap.put(edge, flow);
			}
		}
		/*
		 * Iterate back wards through array starting with
		 * second to lowest level.
		 */
		
			/*
			 * for each node at this level divide it's flow
			 * into the neighbors above
			 */
	}
	
	/**
	 * Computes the betweeness of all edges in a graph.
	 * 
	 * @param graph
	 */
	public static void computeFlow(Graph graph) {
		Iterator<Integer> i = graph.getVerticeIterator();
		while(i.hasNext()) {
			int nodeName = i.next();
			Vertex currentNode = graph.getVertex(nodeName);
			ArrayList<HashSet<Vertex>> levels = shortestRoutesBFS(currentNode);
			HashMap<Vertex, Integer> paths = countShortestPaths(levels);
			calculateEdgeFlow(levels, paths);
		}
	}
	
	/**
	 * Checks for unconnected portions of the graph.
	 * 
	 * @param graph
	 * @return a Set of sets of connected nodes.
	 */
	public static HashSet<HashSet<Vertex>> findConnectedness(Graph graph) {
		HashSet<HashSet<Vertex>> connected = new HashSet<HashSet<Vertex>>();
		HashSet<Vertex> visited = new HashSet<Vertex>();
		
		// iterate every node in graph, skip if already visited
		Iterator<Integer> i = graph.getVerticeIterator();
		while(i.hasNext()) {
			int currentNodeName = i.next();
			Vertex currentNode = graph.getVertex(currentNodeName);
			if(!visited.contains(currentNode)) {
				// run BFS that returns it's visited
				HashSet<Vertex> group = visitBFS(currentNode);
				
				// add that to connected set
				connected.add(group);
				
				// add all vertices in group to visited
				visited.addAll(group);
			}
		}
		return connected;
	}
	
	/**
	 * Set x and y coordinates for where the node will be displayed.
	 * @param connected a set of sets where each subset is connected.
	 */
	public static void setLocations(HashSet<HashSet<Vertex>> connected) {
		//TODO!!!
		// iterate through sets
		
		// for each sub-set of vertices calculate percent of screen that will be used
		// by ratio of size.
	}
}

















