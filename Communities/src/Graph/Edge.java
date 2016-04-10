/**
 * @author jnzastrow
 * 
 * Class to represent a single directed edge of a graph.
 */
package graph;

import processing.core.PApplet;

/**
 * Tracks the edge's weight, records the start and end node, and 
 * draws a line for the visualization.
 */
public class Edge {
	private double weight;
	private Vertex start;
	private Vertex end;
	PApplet parent;
	
	/**
	 * Constructs an edge.
	 * 
	 * @param start Vertex that starts the edge.
	 * @param finish Vertex that ends the edge.
	 * @param parent PApplet that will draw the graph.
	 */
	public Edge(Vertex start, Vertex finish, PApplet parent) {
		this.start = start;
		this.end = finish;
		weight = 0;
		this.parent = parent;
	}
	
	/**
	 * adds one to the weight of this edge
	 */
	public void incrementWeight() {
		weight++;
	}

	/**
	 * Gets the class variable start 
	 * @return Vertex that starts this edge
	 */
	public Vertex getStart() {
		return start;
	}
	
	/**
	 * Gets the class variable end
	 * @return Vertex that ends this edge
	 */
	public Vertex getEnd() {
		return end;
	}
	
	/**
	 * Gets the class variable weight
	 * @return Double that represents the weight of this edge
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Draws a line from the start Vertex to the end Vertex
	 */
	public void draw() {
		parent.line(start.x(), start.y(), end.x(), end.y());
	}	
	
	/**
	 * A string representation of this edge
	 */
	public String toString() {
		String ret = start.name() + "->" + end.name();
		return ret;
	}


}
