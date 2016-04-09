package graph;

import java.util.HashSet;
import java.util.Iterator;
import processing.core.*;

public class Vertex {
	private int name;
	private PApplet parent;
	private float positionX;
	private float positionY;
	private HashSet<Edge> edges;
	private final static int RADIUS = 5;
	private final static float VELOCITY = 1;
	private int direction;
	
	public Vertex(int name, PApplet parent) {
		this.name = name;
		this.parent = parent;
		edges = new HashSet<Edge>();
		positionX = parent.random(RADIUS, parent.width - RADIUS);
		positionY = parent.random(RADIUS, parent.height - RADIUS);
		direction = Math.round(parent.random((float) 0.5, (float) 8.5));
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
	
	public HashSet<Edge> getEdges() {
		return edges;
	}
	
	public Vertex[] getNeighbors() {
		Vertex[] ret = new Vertex[edges.size()];
		if(edges.size()>0) {
			Iterator<Edge> i = edges.iterator();
			int index = 0;
			while(i.hasNext()) {
				Edge e = i.next();
				ret[index] = (e.getEnd());
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
	
	public void draw() {
		parent.ellipse(positionX, positionY, RADIUS, RADIUS);
		
		parent.textSize(RADIUS * 2);
		parent.text(name, positionX + RADIUS, positionY - RADIUS);
//		String coords = "" + positionX + ", " + positionY;
//		parent.text(coords, positionX + RADIUS, positionY - RADIUS * 4);
	}

	public void update() {
		// TODO Auto-generated method stub
		
//		int direction = Math.round(parent.random((float) 0.5, (float) 8.5));
		switch(direction) {
			// right
			case 1: positionX += VELOCITY;
					break;
			// right and down		
			case 2: positionX += VELOCITY;
					positionY += VELOCITY;
					break;
			// down
			case 3: positionY += VELOCITY;
					break;
			// left and down
			case 4: positionX -= VELOCITY;
					positionY += VELOCITY;
					break;
			// left
			case 5: positionX -= VELOCITY;
					break;
			// left and up
			case 6: positionX -= VELOCITY;
					positionY -= VELOCITY;
					break;
			// up
			case 7: positionY -= VELOCITY;
					break;
			// right and up
			case 8: positionX += VELOCITY;
					positionY -= VELOCITY;
					break;
			default: break;
		}
		if(positionY > parent.height - RADIUS) positionY = RADIUS;
		if(positionY < RADIUS) positionY = parent.height - RADIUS;
		if(positionX > parent.width - RADIUS) positionX = RADIUS;
		if(positionX < RADIUS) positionX = parent.width - RADIUS;
	}
}
