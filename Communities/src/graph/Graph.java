package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import processing.core.PApplet;

public class Graph {
	private HashMap<Integer, Vertex> vertices;
	private HashSet<Edge> edges;
	PApplet parent;
	
	public Graph() {
		vertices = new HashMap<Integer, Vertex>();
		edges = new HashSet<Edge>();
		parent = new PApplet();
	}
	
	public Graph(PApplet p) {
		vertices = new HashMap<Integer, Vertex>();
		edges = new HashSet<Edge>();
		parent = p;
	}
	
	public void addVertex(int name) {
		Vertex v = new Vertex(name, parent);
		this.addVertex(v);
	}
	
	public void addVertex(Vertex v) {
		vertices.put(v.name(), v);
	}
	
	public void addEdge(Vertex start, Vertex finish) {
		Edge e = new Edge(start, finish);
		edges.add(e);
		start.addEdge(e);
	}
	
	public Vertex getVertex(int name) {
		return vertices.get(name);
	}
	
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
	
	public void printEdges() {
		System.out.println("Edges");
		Iterator<Edge> i = edges.iterator();
		while(i.hasNext()) {
			Edge e = i.next();
			System.out.println(e.toString());
		}
	}
}
