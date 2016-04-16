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
	
	Graph graph;
	
	public Graphs(Graph g) {
		graph = g;
	}
	
	public static HashMap<Vertex, Vertex> BFS(Vertex start, Vertex goal) {
		// TODO implement BFS (Algorithm in Course 3, Week 2)
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
	
}
