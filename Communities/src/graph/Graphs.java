package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	 */
	public static ArrayList<HashSet<Vertex>> allShortestPaths (Vertex start) {
		// TODO implement BFS so that the level (distance from start is tracked
		// This should be done as the neighbors are being visited.
		
		// initialize queue, visited, and levels array
		ArrayList<HashSet<Vertex>> levels = new ArrayList<HashSet<Vertex>>();
		Queue<Vertex> queue = new LinkedList<Vertex>();
		HashSet<Vertex> visited = new HashSet<Vertex>();
//		HashMap<Vertex, Vertex> parent = new HashMap<Vertex, Vertex>();
		
		// enqueue start onto queue and add to visited
		queue.add(start);
		visited.add(start);
//		int level = 0;
		
		HashSet<Vertex> initialSet = new HashSet<Vertex>();
		initialSet.add(start);
		levels.add(initialSet);
		
		//while queue is not empty, do stuff...
		while(!queue.isEmpty()) {
			//dequeue node curr from front of queue and increment level
			Vertex curr = queue.poll();
//			level++;  //TODO level incremented here creates problems
			HashSet<Vertex> currSet = new HashSet<Vertex>();
			
//			// if curr is goal then return parent map
//			if(curr.equals(goal)) return parent;
			
			// for each of curr's neighbors, n, not in visited set
			Vertex[] neighbors = curr.getNeighbors();
			for(Vertex n: neighbors) {
				
				if(!visited.contains(n)) {
					// increment level if currSet is empty TODO
//					if(currSet.isEmpty()) {
//						level++;
//						levels.put(level, currSet);
//					}
					
					// add n to visited set
					visited.add(n);
					
//					//add curr as n's parent in parent
//					parent.put(n, curr);
					
					// add curr to it's level in levels map
					currSet.add(n);
					
					// engueue n onto queue
					queue.add(n);
				}
				
			}
			if(!currSet.isEmpty()) levels.add(currSet);
		}
		
		
		return levels;
	}
}
