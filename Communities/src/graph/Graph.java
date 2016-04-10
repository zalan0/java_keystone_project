package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import processing.core.PApplet;

/**
 * Represents a network graph.
 * @author jnzastrow
 * 
 * Stores a HashMap of vertices mapped to it's name. There are methods
 * to add vertices and edges, as well as get the vertices and edges.
 */
public class Graph {
	private HashMap<Integer, Vertex> vertices;
	private HashSet<Edge> edges;
	private PApplet parent;
	
	/**
	 * Constructs a new empty graph.
	 */
	public Graph() {
		vertices = new HashMap<Integer, Vertex>();
		edges = new HashSet<Edge>();
		parent = new PApplet();
	}
	
	/**
	 * Constructs a new empty graph that connects to a PApplet.
	 * 
	 * @param p PApplet to connect to.
	 */
	public Graph(PApplet p) {
		vertices = new HashMap<Integer, Vertex>();
		edges = new HashSet<Edge>();
		parent = p;
	}
	
	/**
	 * Creates a new Vertex given only it's integer name
	 * 
	 * @param name Integer representing it's name
	 */
	public void addVertex(int name) {
		Vertex v = new Vertex(name, parent);
		this.addVertex(v);
	}
	
	/**
	 * Adds an already created Vertex to the vertices HashMap
	 * 
	 * @param v Vertex to add to HashMap
	 */
	public void addVertex(Vertex v) {
		vertices.put(v.name(), v);
	}
	
	/**
	 * Creates a new edge and adds it to both this class's set of edges
	 * as well as the start node's set of edges.
	 * 
	 * @param start Vertex at start of edge.
	 * @param finish Vertex at end of edge
	 */
	public void addEdge(Vertex start, Vertex finish) {
		Edge e = new Edge(start, finish, parent);
		edges.add(e);
		start.addEdge(e);
	}
	
	/**
	 * Gets the vertex with the provided name.
	 * 
	 * @param name Integer representing the node's name.
	 * @return Vertex with given name.
	 */
	public Vertex getVertex(int name) {
		return vertices.get(name);
	}
	
	/**
	 * Provide the PApplet used to draw graph.
	 * 
	 * @return PApplet instantiation.
	 */
	public PApplet getParent() {
		return parent;
	}

	/**
	 * Gets the Class variable vertices
	 * 
	 * @return HashMap mapping an integer representation of a node's name
	 * 		   to the node.
	 */
	public HashMap<Integer, Vertex> getVertices() {
		return vertices;
	}

	/**
	 * Gets the Class variable edges
	 * 
	 * @return HashSet of edges
	 */
	public HashSet<Edge> getEdges() {
		return edges;
	}
	
	/**
	 * Provide a means to iterate through the vertices.
	 * @return An Iterator of integers
	 */
	public Iterator<Integer> getVerticeIterator() {
		return vertices.keySet().iterator();
	}
	
	/**
	 * Prints a representation of the graph to the console for testing
	 * purposes.
	 */
	public void printGraph() {
		System.out.println("-------------------");
		System.out.println("Printing graph...");
		System.out.println("Vertex=>[Neighbors]");
		Iterator<Integer> i = vertices.keySet().iterator();
		while(i.hasNext()) {
			int name = i.next();
			System.out.println(name + "=>" + vertices.get(name).printNeighbors());
		}
		System.out.println("-------------------");
	}
	
	/**
	 * Prints all of the edges to the console for testing purposes.
	 */
	public void printEdges() {
		System.out.println("Edges");
		Iterator<Edge> i = edges.iterator();
		while(i.hasNext()) {
			Edge e = i.next();
			System.out.println(e.toString());
		}
	}
}
