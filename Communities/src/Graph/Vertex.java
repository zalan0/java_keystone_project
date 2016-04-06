package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import processing.core.*;

public class Vertex {
	private int name;
	private PApplet parent;
	private float positionX;
	private float positionY;
	private HashSet<Edge> edges;
	
	public Vertex(int name, PApplet parent) {
		this.name = name;
		this.parent = parent;
		positionX = parent.random(parent.height);
		positionY = parent.random(parent.width);
	}
	
	public int name() {
		return name;
	}
	
	public float x() {
		return positionX;
	}
	
	public float y() {
		return positionY;
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public ArrayList<Vertex> getNeighbors() {
		ArrayList<Vertex> ret = new ArrayList<Vertex>();
		Iterator<Edge> i = edges.iterator();
		while(i.hasNext()) {
			Edge e = i.next();
			ret.add(e.getStart());
		}
		return ret;
	}
}
