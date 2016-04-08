package graph;

public class Edge {
	private double weight;
	private Vertex start;
	private Vertex finish;
	
	public Edge(Vertex start, Vertex finish) {
		this.start = start;
		this.finish = finish;
		weight = 0;
	}
	
	public void incrementWeight() {
		weight++;
	}

	public Vertex getStart() {
		return start;
	}
	
	public String toString() {
		String ret = start.name() + "->" + finish.name();
		return ret;
	}

	public Vertex getFinish() {
		return finish;
	}
}
