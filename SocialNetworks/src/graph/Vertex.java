package graph;

import processing.core.*;

public class Vertex {
	private int name;
	private PApplet parent;
	private float positionX;
	private float positionY;
	
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
}