/**
 * @author jnzastrow
 * 
 * Class to represent a single directed edge of a graph.
 */
package graph;

import java.util.HashSet;
import java.util.Iterator;

import processing.core.PApplet;

/**
 * Tracks the edge's weight, records the start and end node, and 
 * draws a line for the visualization.
 */
public class Edge {
	private double flow;
//	private Vertex start;
//	private Vertex end;
	private PApplet parent;
	private HashSet<Vertex> ends;
	
	/**
	 * Constructs an edge.
	 * 
	 * @param start Vertex that starts the edge.
	 * @param finish Vertex that ends the edge.
	 * @param parent PApplet that will draw the graph.
	 */
	public Edge(Vertex start, Vertex finish, PApplet parent) {
//		this.start = start;
//		this.end = finish;
		flow = 0.0d;
		this.parent = parent;
		ends = new HashSet<Vertex>();
		ends.add(start);
		ends.add(finish);
	}
	
	/**
	 * adds one to the weight of this edge
	 */
	public void incrementFlow(double amount) {
		flow += amount;
	}
	
	public double getFlow() {
		return flow;
	}

//	/**
//	 * Gets the class variable start 
//	 * @return Vertex that starts this edge
//	 */
//	public Vertex getStart() {
//		return start;
//	}
//	
//	/**
//	 * Gets the class variable end
//	 * @return Vertex that ends this edge
//	 */
//	public Vertex getEnd() {
//		return end;
//	}
//	
//	/**
//	 * Gets the class variable weight
//	 * @return Double that represents the weight of this edge
//	 */
//	public double getWeight() {
//		return weight;
//	}
	
	public HashSet<Vertex> getEnds() {
		return ends;
	}
	
	/**
	 * Draws a line from the start Vertex to the end Vertex
	 */
	public void draw() {
		Iterator<Vertex> e = ends.iterator();
		Vertex start = e.next();
		Vertex end = e.next();
		parent.line(start.x(), start.y(), end.x(), end.y());
	}	
	
	/**
	 * A string representation of this edge
	 */
	public String toString() {
		Iterator<Vertex> e = ends.iterator();
		Vertex start = e.next();
		Vertex end = e.next();
		String ret = start.name() + "<-" + flow + "->"+ end.name();
		return ret;
	}

	/**
	 * Return the Vertex that is on the other end of the provided vertex
	 * 
	 * @param vertex
	 * @return The other vertex
	 */
	public Vertex getOther(Vertex vertex) {
		Iterator<Vertex> e = ends.iterator();
		Vertex start = e.next();
		Vertex end = e.next();
		if(vertex.equals(start)) {
			return end;
		} else {
			return start;
		}
	}


}
