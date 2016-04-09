package graph;

import processing.core.PApplet;

public class Edge {
	private double weight;
	private Vertex start;
	private Vertex end;
	PApplet parent;
	
	public Edge(Vertex start, Vertex finish, PApplet parent) {
		this.start = start;
		this.end = finish;
		weight = 0;
		this.parent = parent;
	}
	
	public void incrementWeight() {
		weight++;
	}

	public Vertex getStart() {
		return start;
	}
	
	public String toString() {
		String ret = start.name() + "->" + end.name();
		return ret;
	}

	public Vertex getEnd() {
		return end;
	}
	
	public void draw() {
		parent.line(start.x(), start.y(), end.x(), end.y());
	}
}
