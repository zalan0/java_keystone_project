package graph;

import java.util.HashSet;
import java.util.Iterator;
import processing.core.*;

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
	private float positionX;
	private float positionY;
	private HashSet<Edge> edges;
	private final static int RADIUS = 5;  // size of circle to draw
	private final static float VELOCITY = 1;  // how fast the circle moves
	private int direction; // direction that the circle moves
	
	/**
	 * Constructs a vertex.
	 * 
	 * @param name Integer representing the name of the node.
	 * @param parent PApplet that the node will be drawn on.
	 */
	public Vertex(int name, PApplet parent) {
		this.name = name;
		this.parent = parent;
		edges = new HashSet<Edge>();
		positionX = parent.random(RADIUS, parent.width - RADIUS);
		positionY = parent.random(RADIUS, parent.height - RADIUS);
		direction = Math.round(parent.random((float) 0.5, (float) 8.5));
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
	public float x() {
		return positionX;
	}
	
	/**
	 * Gets the y value of the coordinates for this node.
	 * @return Float representing the vertical coordinate to draw this node
	 */
	public float y() {
		return positionY;
	}
	
	/**
	 * Adds an edge to the HashSet containing connected edges.
	 * @param e The edge to add.
	 */
	public void addEdge(Edge e) {
		edges.add(e);
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
				ret[index] = (e.getEnd());
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
		parent.ellipse(positionX, positionY, RADIUS, RADIUS);
		
		parent.textSize(RADIUS * 2);
		parent.text(name, positionX + RADIUS, positionY - RADIUS);
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
