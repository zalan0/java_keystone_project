import java.util.HashSet;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import util.GraphLoader;

public class test {
	String filename = "data/facebook_100.txt";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph();
		g.printGraph();
		
		GraphLoader.loadGraph(g, "data/facebook_1000.txt");
		
//		int[] vertices = {0,1,2,3,4,5,6,7,8,9};
////		System.out.println(vertices.toString());
//		for(int v: vertices) {
////			System.out.println(v);
//			g.addVertex(v);
//		}
//		
//		for(int v: vertices) {
//			System.out.println("new edge adding:" + v + "->" + (v+1)%10);
//			Vertex v1 = g.getVertex(v);
//			Vertex v2 = g.getVertex((v+1)%10);
//			System.out.println(v1.name() + ", " + v2.name());
//			g.addEdge(v1, v2);
//		}
		
		
		
//		System.out.println(9%10);
		g.printGraph();
		
		
		System.out.println(g.getVertex(1).getNeighbors());
		
		Vertex v0 = g.getVertex(1);
		Vertex[] neighbors = v0.getNeighbors();
		System.out.println(neighbors.length);
		
		HashSet<Edge> edges = v0.getEdges();
		System.out.println(edges.size());
		
		
		for(Vertex n: neighbors) {
			System.out.println(n.name());
		}
		
		g.printEdges();
	}

}
