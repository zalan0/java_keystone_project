package visualization;

import graph.Graph;
import processing.core.PApplet;

public class GraphVisualize extends PApplet{
	
	Graph graph;
	
	public static void main(String args[] ) {
		PApplet.main(new String[] {"visualization.GraphVisualize"});
		
	}
	
	public void settings() {
//		fullScreen();
		size(600,600);
	}
	
	public void setup() {
		
		background(0);
		
		graph = new Graph(this);
	}
	
	public void draw() {
		stroke(255);
		if(mousePressed) {
			line(mouseX, mouseY, pmouseX, pmouseY);
		}
	}
}
