package graph;

import java.util.HashSet;
import java.util.Iterator;
import processing.core.*;
import visualization.Coordinate;

/**
 * Represents a node of the graph.
 * @author jnzastrow
 * 
 * Tracks the node's position on the visualization and contains a set of
 * edges that connect it to other nodes.  Includes a draw method to show
 * this vertex on the visualization.
 */
public class Vertex {
	private int name;
	private PApplet parent;
//	private float positionX;
//	private float positionY;
	private Coordinate position;
	private HashSet<Edge> edges;
	private final static float RADIUS = 5;  // size of circle to draw
	private final static float VELOCITY = 1;  // how fast the circle moves
	private int direction; // direction that the circle moves
	
	private String gender;
	private int grade;
	private String instrument;
	
	/**
	 * Constructs a vertex.
	 * 
	 * @param name Integer representing the name of the node.
	 * @param parent PApplet that the node will be drawn on.
	 */
	public Vertex(int name, PApplet parent) {
		this(name, "", -1, "", parent);
	}
	
	public Vertex(int name, String gender, int grade, String instrument, PApplet parent) {
		this.name = name;
		this.parent = parent;
		edges = new HashSet<Edge>();
		float x = parent.random(RADIUS, parent.width - RADIUS);
		float y = parent.random(RADIUS, parent.height - RADIUS);
		direction = Math.round(parent.random((float) 0.5, (float) 8.5));
		position = new Coordinate(x, y);
		
		this.gender = gender;
		this.grade = grade;
		this.instrument = instrument;
	}
	
	/**
	 * Gets the name of the Vertex.
	 * @return Integer representing the name of this node.
	 */
	public int name() {
		return name;
	}
	
	/**
	 * Gets the x value of the coordinates for this node.
	 * @return Float representing the horizontal coordinate to draw this node
	 */
	public float getX() {
		return position.getX();
	}
	
	/**
	 * Gets the y value of the coordinates for this node.
	 * @return Float representing the vertical coordinate to draw this node
	 */
	public float getY() {
		return position.getY();
	}
	
	/**
	 * Adds an edge to the HashSet containing connected edges.
	 * @param e The edge to add.
	 */
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public void removeEdge(Edge e) {
		edges.remove(e);
	}
	
	/**
	 * Gets the edges connected to this node.
	 * @return HashSet of Edges
	 */
	public HashSet<Edge> getEdges() {
		return edges;
	}
	
	/**
	 * Returns an array of neighbor nodes
	 * @return Vertex array of neighbors.
	 */
	public Vertex[] getNeighbors() {
		Vertex[] ret = new Vertex[edges.size()];
		if(edges.size()>0) {
			Iterator<Edge> i = edges.iterator();
			int index = 0;
			while(i.hasNext()) {
				Edge e = i.next();
				ret[index] = (e.getOther(this));
				index++;
			}
		}
		return ret;
	}
	
	/**
	 * For testing purposes.  Lists names of neighbor nodes.
	 * @return String containing list of neighbor nodes.
	 */
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
	
	/**
	 * Draws a circle on the PApplet with the name of the node to it's
	 * upper right.
	 */
	public void draw() {
		parent.ellipse(position.getX(), position.getY(), RADIUS, RADIUS);
		
		parent.textSize(RADIUS * 2);
		parent.text(name, position.getX() + RADIUS, position.getY() - RADIUS);
//		String coords = "" + positionX + ", " + positionY;
//		parent.text(coords, positionX + RADIUS, positionY - RADIUS * 4);
	}

	/**
	 * Updates the position of this node in relation to its velocity
	 * and direction.
	 */
	public void update() {
//		int direction = Math.round(parent.random((float) 0.5, (float) 8.5));
		switch(direction) {
			// right
			case 1: position.setX(position.getX() + VELOCITY);
					break;
			// right and down		
			case 2: position.setX(position.getX() + VELOCITY);
					position.setY(position.getY() + VELOCITY);
					break;
			// down
			case 3: position.setY(position.getY() + VELOCITY);
					break;
			// left and down
			case 4: position.setX(position.getX() - VELOCITY);
					position.setY(position.getY() + VELOCITY);
					break;
			// left
			case 5: position.setX(position.getX() - VELOCITY);
					break;
			// left and up
			case 6: position.setX(position.getX() - VELOCITY);
					position.setY(position.getY() - VELOCITY);
					break;
			// up
			case 7: position.setY(position.getY() - VELOCITY);
					break;
			// right and up
			case 8: position.setX(position.getX() + VELOCITY);
					position.setY(position.getY() - VELOCITY);
					break;
			default: break;
		}
		if(position.getY() > parent.height - RADIUS) position.setY(RADIUS);
		if(position.getY() < RADIUS) position.setY(parent.height - RADIUS);
		if(position.getX() > parent.width - RADIUS) position.setX(RADIUS);
		if(position.getX() < RADIUS) position.setX(parent.width - RADIUS);
	}
	
	public String toString() {
		String ret = "";
//		ret += "Vertex[";
//		ret += "name: "
		ret += name;
//		ret += ", gender: " + gender;
//		ret += ", grade: " + grade;
//		ret += ", instrument: " + instrument;
//		ret += "]";
		return ret;
	}
}
