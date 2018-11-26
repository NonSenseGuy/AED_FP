package genericGraphs;

public class Edge<T> implements Comparable<Edge<T>>{
	private Vertex<T> v1;
	private Vertex<T> v2;
	private int w;

	
	public Edge(Vertex<T> v1, Vertex<T> v2, int w) {
		this.v1 = v1; 
		this.v2 = v2;
		this.w = w;
	}
	
	
	public Vertex<T> initVertex(){
		return v1;
	}
	
	public Vertex<T> endVertex(){
		return v2;
	}
	
	public int getWeight() {
		return w;
	}
	
	public void setWeight(int w) {
		this.w = w;
	}


	@Override
	public String toString() {
		return "(" + v2 + "," + w + ")";
	}

	@Override
	public int compareTo(Edge<T> o) {
		if(w > o.getWeight()) {
			return 1;
		}else if(w == o.getWeight()) {
			return 0;
		}else {
			return -1;
		}
	}
	
}

