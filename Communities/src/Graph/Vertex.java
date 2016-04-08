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
		edges = new HashSet<Edge>();
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
	
	public Vertex[] getNeighbors() {
		Vertex[] ret = new Vertex[edges.size()];
		if(edges.size()>0) {
			Iterator<Edge> i = edges.iterator();
			int index = 0;
			while(i.hasNext()) {
				Edge e = i.next();
				ret[index] = (e.getFinish());
				index++;
			}
		}
		return ret;
	}
	
	public String printNeighbors() {
		String ret = "[";
		if(edges.size() > 0) {
			for(Vertex v: this.getNeighbors()) {
				ret += (v.name() + ", ");
			}
			ret = ret.substring(0, ret.length() - 2);
		}
		ret += "]";
		return ret;
	}
}
