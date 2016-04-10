package visualization;

import java.util.Iterator;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import processing.core.PApplet;
import util.GraphLoader;

/**
 * Runs the visualization of the graph.
 * @author jnzastrow
 *
 * Extends PApplet.  Instantiates a graph and load that graph.  
 */
public class GraphVisualize extends PApplet{
	
	private Graph graph;
	final static String graphFile = "data/small_test_graph.txt"; // graph to load
	
	/**
	 * Main method to run the applet as an application.
	 */
	public static void main(String args[] ) {
		PApplet.main(new String[] {"--present", "visualization.GraphVisualize"});
		
	}
	
	/**
	 * Overrides settings method from PApplet
	 * 
	 * Sets size of canvas
	 */
	public void settings() {
		fullScreen();
//		size(600,600);
	}
	
	/**
	 * Overrides setup method from PApplet
	 * 
	 * Instantiates and loads the graph.
	 */
	public void setup() {
		
//		background(0);
		
		graph = new Graph(this);
		GraphLoader.loadGraph(graph, graphFile);
		
//		print(height, "x", width);
		
//		graph.printGraph();
		
//		Iterator<Integer> i = graph.getVerticeIterator();
//		while(i.hasNext()) {
//			int vName = i.next();
//			System.out.println(graph.getVertex(vName).name());
//		}
	}
	
	/**
	 * draw method from PApplet that continually draws the graph.
	 */
	public void draw() {
		background(128);
		Iterator<Integer> i = graph.getVerticeIterator();
		while(i.hasNext()) {
			int vName = i.next();
			Vertex v = graph.getVertex(vName);
			v.draw();
			Iterator<Edge> e = v.getEdges().iterator();
			while(e.hasNext()) {
				Edge edge = e.next();
				edge.draw();
			}
			v.update(); // added to make the graph move!
		}
	}
}
