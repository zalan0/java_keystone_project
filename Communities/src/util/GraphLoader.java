/**
 * @author UCSD MOOC development team
 * modified by jnzastrow
 * 
 * Utility class to add vertices and edges to a graph
 *
 */
package util;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import graph.Vertex;

public class GraphLoader {

	/**
     * Loads graph with data from a file.
     * The file should consist of lines with 2 integers each, corresponding
     * to a "from" vertex and a "to" vertex.
     */ 
    public static void loadEdgeListGraph(graph.Graph g, String filename) {
        Set<Integer> seen = new HashSet<Integer>();
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // Iterate over the lines in the file, adding new
        // vertices as they are found and connecting them with edges.
        while (sc.hasNextInt()) {
            int vName1 = sc.nextInt();
            int vName2 = sc.nextInt();
            
            // here is the main modification
            makeVerticesEdges(g, seen, vName1, vName2);
        }
        
        sc.close();
    }

	private static void makeVerticesEdges(graph.Graph g, Set<Integer> seen, int vName1, int vName2) {
		Vertex v1;
		Vertex v2;
		
		// as the vertices are created we need to pass along the PApplet
		// Instantiation so that the vertices know about our drawing.
		if (!seen.contains(vName1)) {
			v1 = new Vertex(vName1, g.getParent());
		    g.addVertex(v1);
		    seen.add(vName1);
		} else {
			v1 = g.getVertex(vName1);
		}
		if (!seen.contains(vName2)) {
			v2 = new Vertex(vName2, g.getParent());
		    g.addVertex(v2);
		    seen.add(vName2);
		} else {
			v2 = g.getVertex(vName2);
		}
		g.addEdge(v1, v2);
	}
    
    public static void loadAdjacencyListGraph(graph.Graph g, String filename){
    	HashMap<Integer, Set<Integer>> adjacencyList = new HashMap<Integer, Set<Integer>>();
    	Scanner fileScanner;
    	try {
    		fileScanner = new Scanner(new File(filename));
    	} catch (Exception e) {
    		e.printStackTrace();
    		return;
    	}
    	
    	while(fileScanner.hasNextLine()){
    		String line = fileScanner.nextLine();
    		Scanner lineScanner = new Scanner(line);
    		
    		int name = lineScanner.nextInt();
    		String gender = lineScanner.next();
    		int grade = lineScanner.nextInt();
    		String instrument = lineScanner.next();
//    		Vertex v = new Vertex(name, g.getParent());
    		
    		Set<Integer> neighbors = new HashSet<Integer>();
    		while(lineScanner.hasNextInt()) {
    			int n = lineScanner.nextInt();
    			neighbors.add(n);
    		}
    		
    		adjacencyList.put(name, neighbors);
    		lineScanner.close();
    	}
    	Set<Integer> seen = new HashSet<Integer>();
    	Iterator<Integer> i = adjacencyList.keySet().iterator();
    	while(i.hasNext()) {
    		int name = i.next();
    		Iterator<Integer> j = adjacencyList.get(i).iterator();
    		while(j.hasNext()) {
    			int neighbor = j.next();
    			makeVerticesEdges(g, seen, name, neighbor);
    		}
    	}
    	
    	fileScanner.close();
    }
    
}
