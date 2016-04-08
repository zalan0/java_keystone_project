import graph.Graph;
import graph.Vertex;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph();
		g.printGraph();
		
		int[] vertices = {0,1,2,3,4,5,6,7,8,9};
//		System.out.println(vertices.toString());
		for(int v: vertices) {
//			System.out.println(v);
			g.addVertex(v);
		}
		
		for(int v: vertices) {
			System.out.println("new edge adding:" + v + "->" + (v+1)%10);
			Vertex v1 = g.getVertex(v);
			Vertex v2 = g.getVertex((v+1)%10);
			System.out.println(v1.name() + ", " + v2.name());
			g.addEdge(v1, v2);
		}
		
//		System.out.println(9%10);
		g.printGraph();
		
		g.printEdges();
	}

}
