package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import processing.core.PApplet;
import visualization.Coordinate;

/**
 * Utility class of methods for graphs.
 * @author jnzastrow
 *
 * Class containing methods to manipulate graphs.
 */
public class Graphs {
	
	private final static float PUSH = 1;
	private final static float PULL = 1;
	private final static float INNER_BOUND = 15;
	private final static float OUTER_BOUND = 25;
	
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
			for(Vertex n: curr.getNeighbors()) {
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
			for(Vertex n: curr.getNeighbors()) {
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
			for(Vertex n: curr.getNeighbors()) {
				
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
	 * Method that takes the output from both the shortestRoutesBFS and 
	 * numberOfShortestPaths methods and finds the betweenness or flow for all 
	 * edges in the graph.  This value is stored within the Edge Class.
	 * 
	 * @param levels ArrayList of HashSets - output from BFS search
	 * @param paths a map of nodes and the amount of paths that travel through 
	 *        them - output from numberOfShortestPaths.
	 */
	public static void calculateEdgeFlow
	(ArrayList<HashSet<Vertex>> levels, HashMap<Vertex, Integer> paths) {

		HashMap<Edge, Double> flowMap = new HashMap<Edge, Double>(); 

		// Iterate back wards through array starting with
		// lowest level.
		
		for(int level = levels.size()-1; level > 0; level--) {
			HashSet<Vertex> currentLevel = levels.get(level);
			HashSet<Vertex> aboveCurrentLevel = levels.get(level-1);
			HashSet<Vertex> belowCurrentLevel = new HashSet<Vertex>();
			if(level < levels.size()-1) {
				belowCurrentLevel= levels.get(level+1);
			}
			// calculate incoming flow for each node of current level
			Iterator<Vertex> it = currentLevel.iterator();
			while(it.hasNext()) {
				double incomingFlow = 0.0d;
				Vertex currentNode = it.next();
				
				// get all neighbor edges that point below
				if(level < levels.size()-1){
					HashSet<Edge> belowEdges = new HashSet<Edge>();
					Iterator<Edge> edgeIt = currentNode.getEdges().iterator();
					while(edgeIt.hasNext()) {
						Edge edge = edgeIt.next();
						if(belowCurrentLevel.contains(edge.getOther(currentNode))) {
							belowEdges.add(edge);
						}
					}
				
					// sum flow from incoming edges
					Iterator<Edge> e = belowEdges.iterator();
					while(e.hasNext()) {
						Edge edge = e.next();
						incomingFlow += flowMap.get(edge);
					}
				} 
				incomingFlow += 1;
				// assign flow
				// first find all up flowing edges and total the other node's paths
				HashSet<Edge> aboveEdges = new HashSet<Edge>();
				Iterator<Edge> edgeIt = currentNode.getEdges().iterator();
				int totalPaths = 0;
				while(edgeIt.hasNext()) {
					Edge edge = edgeIt.next();
					Vertex other = edge.getOther(currentNode);
					if(aboveCurrentLevel.contains(other)) {
						totalPaths += paths.get(other);
						aboveEdges.add(edge);
					}
				}
				// assign flow to each edge
				edgeIt = aboveEdges.iterator();
				while(edgeIt.hasNext()) {
					Edge edge = edgeIt.next();
					Vertex other = edge.getOther(currentNode);
					double flow = (paths.get(other)/ (double) totalPaths) * incomingFlow;
					flowMap.put(edge, flow);
				}
				
				
			}
		}
		// increment each edge's flow
		Iterator<Edge> edgeIt = flowMap.keySet().iterator();
		while(edgeIt.hasNext()) {
			Edge edge = edgeIt.next();
			edge.incrementFlow(flowMap.get(edge)/2.0d);
		}
	}
	
	/**
	 * Computes the betweenness of all edges in a graph.
	 * 
	 * @param graph
	 */
	public static void computeFlow(Graph graph) {
		Iterator<Integer> i = graph.getVertexIterator();
		while(i.hasNext()) {
			int nodeName = i.next();
			Vertex currentNode = graph.getVertex(nodeName);
			ArrayList<HashSet<Vertex>> levels = shortestRoutesBFS(currentNode);
			HashMap<Vertex, Integer> paths = countShortestPaths(levels);
			calculateEdgeFlow(levels, paths);
		}
	}

	/**
	 * Removes the edge that has been shown to be the traveled  by
	 * calculating betweenness or flow. If there are edges with equal
	 * flows, the method will return the first one that is found.
	 * 
	 * @param graph
	 * @return The edge with highest betweenness
	 */
	public static Edge removeHighestBetweenness(Graph graph) {
		Iterator<Edge> eIt = graph.getEdges().iterator();
		Edge highestBetweenness = eIt.next();
		while(eIt.hasNext()) {
			Edge edge = eIt.next();
			if(edge.getFlow() > highestBetweenness.getFlow()) {
				highestBetweenness = edge;
			}
		}
		HashSet<Vertex> ends = highestBetweenness.getEnds();
		Iterator<Vertex> vIt = ends.iterator();
		vIt.next().removeEdge(highestBetweenness);
		vIt.next().removeEdge(highestBetweenness);
		return highestBetweenness;
	}
	
	/**
	 * Groups connected portions of the graph.
	 * 
	 * @param graph
	 * @return a Set of sets of connected nodes.
	 */
	public static HashSet<HashSet<Vertex>> findConnectedness(Graph graph) {
		HashSet<HashSet<Vertex>> connected = new HashSet<HashSet<Vertex>>();
		HashSet<Vertex> visited = new HashSet<Vertex>();
		
		// iterate every node in graph, skip if already visited
		Iterator<Integer> i = graph.getVertexIterator();
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
	 * Set x and y coordinates for where the node will be displayed using the idea
	 * of force-directed graph drawing.  No centering node.
	 * 
	 * @param graph Graph with vertices to move.
	 */
	public static void setLocations(Graph graph) {
		PApplet parent = graph.getParent();
		
		// iterate through all vertices
		HashSet<Vertex> vertices = new HashSet<Vertex>(graph.getVertices().values());
		Iterator<Vertex> itI = vertices.iterator();
		while(itI.hasNext()) {
			Vertex currentNode = itI.next();
			// and iterate again
			Iterator<Vertex> itJ = vertices.iterator();
			while(itJ.hasNext()) {
				Vertex compared = itJ.next();
				if(!currentNode.equals(compared)){
					pushPull(parent, currentNode, compared);
				}
			}
		}
	}
	
	/**
	 * Set x and y coordinates for where the node will be displayed using the idea
	 * of force-directed graph drawing centered on the node that has the most connections
	 * within the largest community.
	 * 
	 * @param graph Graph with vertices to move.
	 */
	public static void setLocationsMaxNode(Graph graph) {
		PApplet parent = graph.getParent();
		
		
		// find most connected node  
		int maxName = findMaxNode(graph);
		
		// set that in the middle
		Vertex maxNode = graph.getVertex(maxName);
		maxNode.setX(parent.width / 2.0f);
		maxNode.setY(parent.height / 2.0f);
		Vertex currentNode = maxNode;
		
		// iterate through all vertices
		HashSet<Vertex> vertices = new HashSet<Vertex>(graph.getVertices().values());
		Iterator<Vertex> itI = vertices.iterator();
		while(itI.hasNext()) {
			
			// and iterate again
			Iterator<Vertex> itJ = vertices.iterator();
			while(itJ.hasNext()) {
				Vertex compared = itJ.next();
				if(!currentNode.equals(compared) && !compared.equals(maxNode)){
					pushPull(parent, currentNode, compared);
				}
			}
			if(itI.hasNext()) currentNode = itI.next();
		}
	}

	/**
	 * Helper method that calculates whether the current point will push or pull the 
	 * compared point, if it moves it at all.  The new position is then calculated by
	 * the newPoint() method.
	 * 
	 * @param parent  PApplet object that is passed to newPoint()
	 * @param currentNode
	 * @param compared
	 */
	private static void pushPull(PApplet parent, Vertex currentNode, Vertex compared) {
		float currentNodeX = currentNode.getX();
		float currentNodeY = currentNode.getY();
		float comparedX = compared.getX();
		float comparedY = compared.getY();
		
		// find distance between
		float deltaX = currentNodeX - comparedX;
		float deltaY = currentNodeY - comparedY;
		float distance = (float) (Math.sqrt((deltaX*deltaX) + (deltaY*deltaY)));
		
		// pull neighbors closer in, up to a certain distance
		if(currentNode.getNeighbors().contains(compared)){
			if(distance < INNER_BOUND  || distance < PULL){
				// push to INNER_BOUND distance
				Coordinate newComparedCoords = newPoint(currentNodeX, currentNodeY,
						comparedX, comparedY, INNER_BOUND, distance, parent);
				compared.setX(newComparedCoords.getX());
				compared.setY(newComparedCoords.getY());
			} else {
				// pull closer by PULL
				Coordinate newComparedCoords = newPoint(comparedX, comparedY, 
						currentNodeX, currentNodeY, PULL, distance, parent);
				compared.setX(newComparedCoords.getX());
				compared.setY(newComparedCoords.getY());
			}
			
		} else { // push away all non-neighbors to a certain point
			if(distance < OUTER_BOUND) {
				// push away by PUSH
				Coordinate newComparedCoords = newPoint(currentNodeX, currentNodeY, 
						comparedX, comparedY, PUSH + distance, distance, parent);
				compared.setX(newComparedCoords.getX());
				compared.setY(newComparedCoords.getY());
			}
		}
	}

	/**
	 * Finds the node that has the most neighbors in the largest sub-group
	 * of the graph.
	 * 
	 * @param graph
	 * @return An integer that represents the name of a node.
	 */
	private static int findMaxNode(Graph graph) {
		int maxName = -1;
		int maxNeighbors = -1;
		HashSet<Vertex> largestGroup = new HashSet<Vertex>();
		
		// find largest group
		HashSet<HashSet<Vertex>> connections = findConnectedness(graph);
		for(HashSet<Vertex> connected: connections) {
			if(connected.size() > largestGroup.size()) largestGroup = connected;
		}
		
		// find most connected node in largest group
		for(Vertex current: largestGroup) {
			if(current.getEdges().size() > maxNeighbors) {
				maxName = current.name();
				maxNeighbors = current.getEdges().size();
			}
		}
		return maxName;
	}
	
	/**
	 * Helper method that computes a point to move a vertex. It use the other point to move in a 
	 * straight line either towards or away.
	 * 
	 * @param x1 X coordinate for origin point
	 * @param y1 Y coordinate for origin point
	 * @param x2 X Coordinate for second point
	 * @param y2 Y Coordinate for second point
	 * @param distanceToMove
	 * @param distanceBetweenPoints
	 * @param parent PApplet object, used to find height and width of canvas.
	 * @return a Coordinate object containing the x and y of the points new position.
	 */
	private static Coordinate newPoint(float x1, float y1, float x2, float y2, 
			float distanceToMove, float distanceBetweenPoints, PApplet parent) {
		
		float ratioOfDistances = distanceToMove/distanceBetweenPoints;
		float newX = ((1 - ratioOfDistances) * x1 + ratioOfDistances * x2);
		float newY = ((1 - ratioOfDistances) * y1 + ratioOfDistances * y2);
		
		// sanity check
		if(newX < 0) newX = 10;
		else if(newX > parent.width) newX = parent.width - 10;
		if(newY < 0) newY = 10;
		else if(newY > parent.height) newY = parent.height - 10;
		
		return new Coordinate(newX, newY);
	}
	
	/**
	 * Finds communities within graphs.
	 * 
	 * @param graph
	 * @param iterations Times to run through and separate edges
	 */
	public static void girvanNewman(Graph graph, int iterations) {
		for(int iteration = 0; iteration < iterations; iteration++) {
			System.out.println("Girvan-Newman iteration: " + iteration);
			computeFlow(graph);
			removeHighestBetweenness(graph);
			resetEdgeFlow(graph);
		}
	}

	/**
	 * Reset each edges' float value to zero.
	 * @param graph
	 */
	private static void resetEdgeFlow(Graph graph) {
		Iterator<Edge> it = graph.getEdges().iterator();
		while(it.hasNext()) {
			Edge edge = it.next();
			edge.resetFlow();
		}
	}
}
