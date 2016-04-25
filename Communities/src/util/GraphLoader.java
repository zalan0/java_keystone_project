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
	 * Loads a graph with data from a file.
	 * 
	 * The file can be in one of two formats:
	 * 1) a list of edges, such that each line contains two numbers
	 * separated by a space.  This file can have any name and extension.
	 * 
	 * 2) An adjacency list of band students.  Format must be thus: each line
	 * starts with a number to represent the student's name, followed by a grade number,
	 * followed by the name of the instrument that they play, then a list of their 
	 * friends.  All data points are seperated by a space and the filename must end
	 * with the extension ".gve"
	 * 
	 * @param g Graph to load data into
	 * @param filename what the file is called.
	 */
	public static void loadGraph(graph.Graph g, String filename) {
		if(filename.contains(".gve")){
			System.out.println("loading adjacency list");
			loadAdjacencyListGraph(g, filename);
		} else {
			System.out.println("loading edge/edge list");
			loadEdgeListGraph(g, filename);
		}
		System.out.println("Graph loaded");
		System.out.println("Vertices: " + g.getVertexKeys().size() + 
						 "  Edges: " + g.getEdges().size());
	}
	
	/**
     * Loads graph with data from a file.
     * The file should consist of lines with 2 integers each, corresponding
     * to a "from" vertex and a "to" vertex.
     */ 
    private static void loadEdgeListGraph(graph.Graph g, String filename) {
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

    /**
     * Helper method for loadEdgeListGraph
     */
	private static void makeVerticesEdges(graph.Graph g, Set<Integer> seen, 
			int vName1, int vName2) {
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
    
	/**
	 * Reads a file that represents a graph as an adjacency list.  The file must have the
	 * following columns; name (int), gender (string 'M'/'F'), grade (int), and instrument
	 * (string).  These are followed by any neighbors that the node has.
	 * 
	 * @param graph empty Graph file.
	 * @param filename File that holds the graph
	 */
    private static void loadAdjacencyListGraph(graph.Graph graph, String filename){
    	HashMap<Integer, TempNode> adjacencyList = new HashMap<Integer, TempNode>();
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
    		if(lineScanner.hasNext() && !lineScanner.hasNextInt()) {
    			instrument += " " + lineScanner.next();
    		}
    		Vertex v = new Vertex(name, gender, grade, instrument, graph.getParent());
    		
    		HashSet<Integer> neighbors = new HashSet<Integer>();
    		while(lineScanner.hasNextInt()) {
    			int n = lineScanner.nextInt();
    			neighbors.add(n);
    		}
    		TempNode tn = new TempNode(v, neighbors);
    		adjacencyList.put(name, tn);
    		lineScanner.close();
    		
    	}
    	
		Iterator<Integer> keys = adjacencyList.keySet().iterator();
    	while(keys.hasNext()) {
    		int name1 = keys.next();
    		TempNode currTempNode = adjacencyList.get(name1);
    		Vertex v1 = currTempNode.getVertex();
    		graph.addVertex(v1);
    		Iterator<Integer> neighbors = currTempNode.getNeighbors().iterator();
    		while(neighbors.hasNext()) {
    			int name2 = neighbors.next();
    			Vertex v2 = adjacencyList.get(name2).getVertex();
    			graph.addEdge(v1, v2);
    		}
    		
    	}
    	
    	fileScanner.close();
    }
    
}
