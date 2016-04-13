import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import graph.Edge;
import graph.Graph;
import graph.Graphs;
import graph.Vertex;
import util.GraphLoader;

public class test {
	static String filename = "data/small_test_graph.txt";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph();
//		g.printGraph();
		
		GraphLoader.loadGraph(g, filename);
		
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
//		g.printGraph();
//		
//		
//		System.out.println(g.getVertex(1).getNeighbors());
//		
//		Vertex v0 = g.getVertex(1);
//		Vertex[] neighbors = v0.getNeighbors();
//		System.out.println(neighbors.length);
//		
//		HashSet<Edge> edges = v0.getEdges();
//		System.out.println(edges.size());
		
		
//		for(Vertex n: neighbors) {
//			System.out.println(n.name());
//		}
		
//		g.printEdges();
		
		Set<Integer> vertNames = g.getVertices().keySet();
		System.out.println(vertNames.size() + " nodes");
		System.out.println(g.getEdges().size() + " edges");
		
//		g.printGraph();
//		g.printEdges();
		HashMap<Vertex, Vertex> parentMap = Graphs.BFS(g.getVertex(1), g.getVertex(5));
		
		Iterator<Vertex> i = parentMap.keySet().iterator();
		while(i.hasNext()) {
			Vertex key = i.next();
			System.out.println(key.name() + ", " + parentMap.get(key).name());
		}
		
//		parentMap.printGraph();
//		System.out.println(parentMap.getVertices().keySet().size());
//		System.out.println(parentMap.getEdges().size());
		
//		Iterator<Integer> i = vertNames.iterator();
//		int step = 0;
//		int check = i.next();
//		
//		while(i.hasNext()) {
//			if(check != step) {
//				System.out.println("did not find number " + step);
//				step++;
//			} else {
//				step++;
//				check = i.next();
//			}
//		}
	}

}
