package visualization;

import java.util.Iterator;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import processing.core.PApplet;
import util.GraphLoader;

public class GraphVisualize extends PApplet{
	
	private Graph graph;
	final static String graphFile = "data/small_test_graph.txt";
	
	public static void main(String args[] ) {
		PApplet.main(new String[] {"--present", "visualization.GraphVisualize"});
		
	}
	
	public void settings() {
		fullScreen();
//		size(600,600);
	}
	
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
	
	public void draw() {
//		stroke(255);
//		if(mousePressed) {
//			line(mouseX, mouseY, pmouseX, pmouseY);
//		}
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
			v.update();
		}
	}
}
