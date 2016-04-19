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
	public static HashMap<Vertex, Vertex> BFS(Vertex start, Vertex goal) {
//		ArrayList<Vertex> path = new ArrayList<Vertex>();
		
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
	 * An implementation of BFS that returns the number of all possible shortest
	 * routes from start to goal.
	 * 
	 * @param start Vertex to start the search
	 * @return An ArrayList of HashSets of Vertices that show distance from start
	 * by their index within the ArrayList
	 */
	public static ArrayList<HashSet<Vertex>> shortestRoutesBFS (Vertex start) {
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
	
	public static HashMap<Vertex, Integer> numberOfShortestPaths
	(ArrayList<HashSet<Vertex>> levels) {
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
		paths.remove(start);
		return paths;
	}
	
	public static void incrementEdgeFlow(ArrayList<HashSet<Vertex>> levels,
			HashMap<Vertex, Integer> paths) {
		
		// Decrement through levels
		for(int level = levels.size(); level < 0; level--) {
			
			
			
			//iterate through current level
			Iterator<Vertex> currentLevel = levels.get(level).iterator();
			while(currentLevel.hasNext()) {
				Vertex currentNode = currentLevel.next();
				
				// find max flow (all flow arriving to this node from below + 1 for itself)
				double maxFlow = 0.0d;
				if(level != levels.size()) {
					// add flows from all incoming edges
					Iterator<Edge> edges = currentNode.getEdges().iterator();
					while(edges.hasNext()) {
						Edge edge = edges.next();
						HashSet<Vertex> intersection = 
								new HashSet<Vertex>(levels.get(level + 1));
						intersection.retainAll(edge.getEnds());
						if(!intersection.isEmpty()) {
							maxFlow += edge.getFlow();
						}
					}
					
				} 
				maxFlow += 1;
				
				// TODO
				// compare level above to neighbors of current node
				// find total paths
				
				// flow for edge will be the ratio of total paths to neighbor's path
				
				
			}
			
			
		}
	}
}

















