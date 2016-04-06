/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import processing.core.PApplet;
import sun.security.provider.certpath.Vertex;

/**
 * @author Joe Zastrow.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {
	private HashSet<Integer> vertices;
	private HashMap<Integer, HashSet<Integer>> edges;
	private PApplet parent;  // director to visualizer
	
	/**
	 * Constructor that sets up an empty graph.
	 */
	public CapGraph() {
		vertices = new HashSet<Integer>();
		edges = new HashMap<Integer, HashSet<Integer>>();
		this.parent = parent;
	}
	
	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		vertices.add(num);
		edges.put(num, new HashSet<Integer>());
	}

	/**
	 * Takes a set of vertices and adds them to the graph all at once.
	 * @param vertices2 A HashSet of Integers representing vertices.
	 */
	public void addVertices(HashSet<Integer> vertices2) {
		Iterator<Integer> i = vertices2.iterator();
		while(i.hasNext()) {
			int vertex = i.next();
			addVertex(vertex);
		}
	}


	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		if(edges.containsKey(from)) {
			edges.get(from).add(to);
		} 
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// create a new graph with vertices of center and neighbors
		CapGraph egonet = new CapGraph();
		egonet.addVertex(center);
		egonet.addVertices(edges.get(center));
		
		// recreate neighbors within neighbor/center set
		subGraphEdges(egonet);
		
		return egonet;
	}

	private void subGraphEdges(CapGraph graph) {
		Iterator<Integer> vertexIterator = graph.getVertices().iterator();
		while(vertexIterator.hasNext()) {
			int vertex = vertexIterator.next();
			Iterator<Integer> neighborIterator = edges.get(vertex).iterator();
			while(neighborIterator.hasNext()) {
				int neighbor = neighborIterator.next();
				if(graph.hasVertex(neighbor)) {
					graph.addEdge(vertex, neighbor);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		// 1. create a finished stack through a DFS of graph
		Stack<Integer> vertexStack = getVertexStack();
		Stack<Integer> finished = DFS(vertexStack);
		
		// 2. transpose (flip edge directions) of graph
		CapGraph transGraph = transposeGraph();
		
		// 3. run DFS with the transposed graph and the finished stack to create a list of graphs
		List<Graph> ret = DFS(transGraph, finished);
		return ret;
	}
	
	/**
	 * A method to setup the Depth First Search implemented in DFS_Visit.
	 * This method is called as the third step in getSCCs. It returns a list of graphs,
	 * which is it's main difference from the other DFS called as step one of getSCCs
	 * 
	 * Consider a way to implement both in same method...
	 * 
	 * @param transGraph A graph with all of the directed edges backwards.  See 
	 * transposeGraph().
	 * 
	 * @param vertices A stack of nodes that is returned from the other DFS method.
	 * 
	 * @return A list of Graphs that are Strongly Connected Components.
	 */
	private List<Graph> DFS(CapGraph transGraph, Stack<Integer> vertices) {
		// initialize set visited and stack finished and List of Graphs ret
		HashSet<Integer> visited = new HashSet<Integer>();
		Stack<Integer> finished = new Stack<Integer>();
		List<Graph> ret = new ArrayList<Graph>();
		
		//run DFS_Visit on each vertex
		while(!vertices.empty()) {
			int vertex = vertices.pop();
			if(!visited.contains(vertex)){
				HashSet<Integer> newVertices = new HashSet<Integer>();
				DFS_Visit(transGraph, vertex, visited, finished, newVertices);
				CapGraph newGraph = new CapGraph();
				newGraph.addVertices(newVertices);
				subGraphEdges(newGraph);
				ret.add(newGraph);
			}
		}
		return ret;
	}

	/**
	 * A method to transpose a directional graph.
	 * @return A graph with all of the directed edges backwards.
	 */
	private CapGraph transposeGraph() {
		CapGraph newGraph = new CapGraph();
		newGraph.addVertices(vertices);
		Iterator<Integer> vertIter = newGraph.getVertices().iterator();
		while(vertIter.hasNext()) {
			int vertex = vertIter.next();
			Iterator<Integer> neighborIter = edges.get(vertex).iterator();
			while(neighborIter.hasNext()) {
				int neighbor = neighborIter.next();
				newGraph.addEdge(neighbor, vertex);
			}
		}
		return newGraph;
	}

	/**
	 * The first step for getSCCs().
	 * @param vertices A Stack of vertices in this graph.
	 * @return A Stack of vertices formed in the order visited.
	 */
	private Stack<Integer> DFS(Stack<Integer> vertices) {
		// initialize set visited and stack finished
		HashSet<Integer> visited = new HashSet<Integer>();
		Stack<Integer> finished = new Stack<Integer>();
		
		// run DFS-Visit on each vertex
		while(!vertices.empty()) {
			int vertex = vertices.pop();
			if(!visited.contains(vertex)) {
				HashSet<Integer> group = new HashSet<Integer>();
				DFS_Visit(this, vertex, visited, finished, group);
			}
		}
		return finished;
	}

	/**
	 * Depth First Search implemented to be used in the getSCCs() method.
	 * 
	 * @param graph CapGraph to be searched
	 * @param vertex Starting vertex
	 * @param visited HashSet of visited vertices
	 * @param finished Stack to track finished vertices
	 * @param group A HashSet which is a subset of visited and will form the answer
	 * to getSCCs().
	 */
	private void DFS_Visit(CapGraph graph, int vertex, HashSet<Integer> visited,
			Stack<Integer> finished, HashSet<Integer> group) {
		visited.add(vertex);
		Iterator<Integer> neighbors = graph.edges.get(vertex).iterator();
		while(neighbors.hasNext()) {
			int neighbor = neighbors.next();
			if(!visited.contains(neighbor)){
				DFS_Visit(graph, neighbor, visited, finished, group);
			}
		}
		finished.push(vertex);
		group.add(vertex);
	}

	/**
	 * A helper method to change the set of vertices into a Stack.  
	 * @return A Stack of vertices.
	 */
	private Stack<Integer> getVertexStack() {
		Stack<Integer> vertexStack = new Stack<Integer>();
		Iterator<Integer> i = vertices.iterator();
		while(i.hasNext()) {
			Integer v = i.next();
			vertexStack.add(v);
		}
		return vertexStack;
	}
	
	/**
	 * Getter for vertices in graph
	 * @return HashSet of vertices
	 */
	public HashSet<Integer> getVertices() {
		return vertices;
	}

	/**
	 * Checks for vertex in graph.
	 * @param vertex Vertex to check.
	 * @return True if vertex exists in graph
	 */
	public boolean hasVertex(int vertex) {
		return this.vertices.contains(vertex);
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		return edges;
	}
	
	/**
	 * A testing method to print a representation of the graph to the console.
	 */
	public void printGraph() {
		System.out.println("-------------------");
		System.out.println("Printing graph...");
		System.out.println("Vertex=>[Neighbors]");
		Iterator<Integer> i = vertices.iterator();
		while(i.hasNext()) {
			Integer vertex = i.next();
			System.out.println(vertex + "=>" + edges.get(vertex));
		}
		System.out.println("-------------------");
	}

}
