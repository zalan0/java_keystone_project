package util;

import java.util.HashSet;

import graph.Vertex;

public class TempNode {
	private Vertex vertex;
	private HashSet<Integer> neighbors;
	
	public TempNode(Vertex v, HashSet<Integer> neighbors) {
		vertex = v;
		this.neighbors = neighbors;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public HashSet<Integer> getNeighbors() {
		return neighbors;
	}
	
	public String toString() {
		return "" + vertex.toString() + "=>" + neighbors;
	}
}
