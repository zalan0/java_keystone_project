import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import graph.CapGraph;
import graph.Graph;
import processing.core.PApplet;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create a bidirectional graph
		
		int[] vertices = {32, 50, 23, 25, 44, 18, 65};
		int[][] edges = {
				{50, 44}, 
				{23, 44, 32},
				{50, 25, 18, 65},
				{23, 18, 65},
				{32, 50, 18},
				{23, 25, 44},
				{25, 23}
		};
		
//		CapGraph biGraph = createGraph(vertices, edges);
		
		// test bidirectional graph construction and egonet
//		System.out.println("ORIGINAL GRAPH");
//		biGraph.printGraph();
//		
//		System.out.println();
//		System.out.println("Create egonet for 50...");
//		CapGraph egonet1 = (CapGraph) biGraph.getEgonet(50);
//		egonet1.printGraph();
//		
//		System.out.println();
//		System.out.println("Create egonet for 25...");
//		CapGraph egonet2 = (CapGraph) biGraph.getEgonet(25);
//		egonet2.printGraph();
		
		
		
		// create a directional graph
		int[] diVertices = {32, 50, 23, 28, 44, 18, 65};
		int[][] diEdges = {
				{50, 44}, 
				{},
				{18, 28},
				{23, 18, 65},
				{50},
				{23, 44},
				{23}
		};
		
		CapGraph diGraph = createGraph(diVertices, diEdges);
		
		// test directional graph construction and SCC
		System.out.println();
		System.out.println("DIRECTIONAL GRAPH");
		diGraph.printGraph();
		System.out.println("running getSCCs");
		List<Graph> allSCCs = diGraph.getSCCs();
		for(Graph graph: allSCCs){
			((CapGraph) graph).printGraph();
		}
		
		
		//check to see if original graphs are unchanged
//		System.out.println();
//		System.out.println("ORIGINAL BI GRAPH");
//		biGraph.printGraph();
		System.out.println();
		System.out.println("ORIGINAL DI GRAPH");
		diGraph.printGraph();
	}

	private static CapGraph createGraph(int[] vertices, int[][] edges) {
		CapGraph graph = new CapGraph();
		int index = 0;
		for (int v: vertices) {
			graph.addVertex(v);
			for(int n: edges[index]) {
				graph.addEdge(v, n);
			}
			index += 1;
		}
		return graph;
	}

}
