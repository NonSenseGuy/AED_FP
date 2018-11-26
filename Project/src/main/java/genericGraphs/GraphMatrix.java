package genericGraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;




public class GraphMatrix<T> implements Graph<T>{
	
	private Map<T, Integer> vertices;
	private List<Vertex<T>> verticesLookUp;
	private int[][] adjMatrix;
	private int[][] distMatrix;
	private int index;
	private final boolean isDirected;
	private final boolean isWeighted;
	
	public GraphMatrix(int numVertices, boolean isDirected, boolean isWeighted) {
		adjMatrix = new int[numVertices][numVertices];
		distMatrix = new int[numVertices][numVertices];
		for(int i = 0; i < numVertices; i++) {
			for(int j = 0; j < numVertices; j++) {
				if(i == j) {
					distMatrix[i][i] = 0;
					adjMatrix[i][i] = 0;
				}else {
					adjMatrix[i][j] = INF;
					distMatrix[i][j] = INF;
				}
				
			}
			
		}
		index = 0;
		vertices = new HashMap<T, Integer>();
		verticesLookUp = new ArrayList<Vertex<T>>();
		this.isDirected = isDirected;
		this.isWeighted = isWeighted;
	}

	
	public List<Vertex<T>> getVerticesLookUp() {
		return verticesLookUp;
	}



	public void setVerticesLookUp(List<Vertex<T>> verticesLookUp) {
		this.verticesLookUp = verticesLookUp;
	}



	public int[][] getAdjMatrix() {
		return adjMatrix;
	}



	public void setAdjMatrix(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}



	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	public void setVertices(Map<T, Integer> vertices) {
		this.vertices = vertices;
	}
	@Override
	public void addVertex(T v) {
		Vertex<T> vertex = new Vertex<T>(v);
		addVertex(vertex);
	}

	@Override
	public void addVertex(Vertex<T> v) throws IllegalArgumentException,IndexOutOfBoundsException {
		if(vertices.containsKey(v.getValue())) {
			throw new IllegalArgumentException("Vertex already exists in the graph");
		}else if(vertices.size() >= adjMatrix.length) {
			throw new IndexOutOfBoundsException();
		}
		vertices.put(v.getValue(), index);
		verticesLookUp.add(v);
		index++;
	}

	@Override
	public void addEdge(Vertex<T> v1, Vertex<T> v2) throws IllegalArgumentException {
		if(!vertices.containsKey(v1.getValue()) || !vertices.containsKey(v2.getValue())) {
			throw new IllegalArgumentException("Vertex not found");
		}
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		adjMatrix[indexV1][indexV2] = 1;
		distMatrix[indexV1][indexV2] = 1;
		if(!isDirected) {
			distMatrix[indexV2][indexV1] = 1;
			adjMatrix[indexV2][indexV1] = 1;
			
		}
	}

	@Override
	public void addEdge(Vertex<T> v1, Vertex<T> v2, int w) throws IllegalArgumentException {
		if(!isWeighted) throw new IllegalArgumentException();
		if(!vertices.containsKey(v1.getValue()) || !vertices.containsKey(v2.getValue())) {
			throw new IllegalArgumentException("Vertex not found");
		}
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		adjMatrix[indexV1][indexV2] = 1;
		distMatrix[indexV1][indexV2] = w;
		if(!isDirected) {
			distMatrix[indexV2][indexV1] = w;
			adjMatrix[indexV2][indexV1] = 1;
		}
	}

	@Override
	public void removeEdge(Vertex<T> v1, Vertex<T> v2) throws IllegalArgumentException {
		if(!vertices.containsKey(v1.getValue()) || !vertices.containsKey(v2.getValue())) {
			throw new IllegalArgumentException("Vertex not found");
		}
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		adjMatrix[indexV1][indexV2] = INF;
		if(!isDirected) {
			adjMatrix[indexV2][indexV1] = INF;
		}
	}
	
	public int[][] getDistMatrix(){
		return distMatrix;
	}

	@Override
	public Vertex<T> getVertex(T valueVertex) throws IllegalArgumentException {
		if(vertices.containsKey(valueVertex)) {
			return verticesLookUp.get(vertices.get(valueVertex));
		}
		throw new IllegalArgumentException("Vertex not found");
	}

	@Override
	public Iterable<Vertex<T>> getVertices() {
		return verticesLookUp;
	}

	@Override
	public int getWeightEdge(Vertex<T> v1, Vertex<T> v2) throws IllegalArgumentException {
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		return adjMatrix[indexV1][indexV2];
	}

	@Override
	public void setWeightEdge(Vertex<T> v1, Vertex<T> v2, int w) throws IllegalArgumentException {
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		adjMatrix[indexV1][indexV2] = w;
		
	}
	@Override
	public void bfs(Vertex<T> startVertex) {
		if(!vertices.containsKey(startVertex.getValue())) {
			throw new IllegalArgumentException("Vertex not found");
		}
		for(Vertex<T> u: getVertices()) {
			if(!u.equals(startVertex)) {
				u.setColor(Vertex.WHITE);
				u.setD(INF);
				u.setPred(null);
			}
		}
		startVertex.setColor(Vertex.GRAY);
		startVertex.setD(0);
		startVertex.setPred(null);
		Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		queue.offer(startVertex);
		while(!queue.isEmpty()) {
			Vertex<T> u = queue.poll();
			int index = vertices.get(u.getValue());
			for(int i = 0; i < vertices.size(); i++) {
				if(i != index) {
					if(adjMatrix[index][i] != INF) {
						Vertex<T> v = verticesLookUp.get(i);
						if(v.getColor() == Vertex.WHITE) {
							v.setColor(Vertex.GRAY);
							v.setD(u.getD());
							v.setPred(u);
							queue.offer(v);
						}
					}
				}
			}
			u.setColor(Vertex.BLACK);
		}
		
	}
	
	@Override
	public boolean bellmanford(Vertex<T> startVertex) {
		ShortestPath sp = new ShortestPath();
		int src = vertices.get(startVertex.getValue());
		return sp.bellmanford(distMatrix, src);
	}

	@Override
	public void dfs() {
		for(Vertex<T> v : verticesLookUp) {
			v.setColor(Vertex.WHITE);
		}
		for(Vertex<T> u: verticesLookUp) {
			if(u.getColor() == Vertex.WHITE) {
				dfsVisit(u);
			}
		}
	}
	
	public void dfsVisit(Vertex<T> u) {
		u.setColor(Vertex.BLACK);
		int indexU = vertices.get(u);
		for(int i = 0; i < vertices.size(); i++) {
			if(i != indexU) {
				Vertex<T> v = verticesLookUp.get(i);
				if(adjMatrix[indexU][i] != INF && v.getColor() == Vertex.WHITE) {
					v.setPred(u);
					dfsVisit(v);
				}
			}
		}
	}

	@Override
	public boolean areAdjacent(Vertex<T> v1, Vertex<T> v2) {
		int indexV1 = vertices.get(v1);
		int indexV2 = vertices.get(v2);
		
		return adjMatrix[indexV1][indexV2] == INF ? false:true;
	}
	
	public ArrayList<Vertex<T>> getAdjacentVertices(Vertex<T> v){
		int index = vertices.get(v.getValue());
		ArrayList<Vertex<T>> result = new ArrayList<Vertex<T>>();
		for(int i = 0; i < adjMatrix[index].length; i++) {
			if(adjMatrix[index][i] != INF && adjMatrix[index][i] != 0) {
				result.add(verticesLookUp.get(i));
			}
		}
		return result;
	}


	@Override
	public void dijkstra(Vertex<T> s) {
		ShortestPath sp = new ShortestPath();
		int src = vertices.get(s.getValue());
		sp.dijkstra(distMatrix, src);
	}

	@Override
	public int getNumVertices() {
		return vertices.size();
	}

	@Override
	public int getNumEdges() {
		int numEdges = 0;
		for(int i = 0; i < vertices.size(); i++) {
			for(int j = 0; j < vertices.size(); j++) {
				if(j != i && adjMatrix[i][j] != INF) {
					numEdges++;
				}
			}
		}
		return numEdges;
	}

	@Override
	public int[][] floydWarshall(){
		int[][] weightMatrix = adjMatrix.clone();
		
		
		for(int k = 0; k < weightMatrix.length; k++) {
			for(int i = 0; i < weightMatrix.length; i++) {
				for(int j = 0; j < weightMatrix.length; j++) {
					if(weightMatrix[i][j] > weightMatrix[i][k] + weightMatrix[k][j]) {
						weightMatrix[i][j] = weightMatrix[i][k] + weightMatrix[k][j];
					}
				}
			}
		}
		distMatrix = weightMatrix;
		return weightMatrix;
	}
	
	@Override
	public void prim(Vertex<T> r){
		for(Vertex<T> v : verticesLookUp) {
			v.setD(INF);
			v.setPred(null);
			v.setColor(Vertex.WHITE);
		}
		r.setD(0);
		r.setPred(null);
		PriorityQueue<Vertex<T>> q = new PriorityQueue<>();
		q.offer(r);
		while(!q.isEmpty()) {
			Vertex<T> u = q.poll();
			ArrayList<Vertex<T>> adjVertices = getAdjacentVertices(u);
			for(Vertex<T> v : adjVertices) {
				int w = getEdgeWeight(u,v);
				if(v.getColor() == Vertex.WHITE && w < v.getD()) {
					v.setD(w);
					q.offer(v);
					v.setPred(u);
					
				}
				u.setColor(Vertex.BLACK);
			}
		}
	}
	
	public int getEdgeWeight(Vertex<T> u, Vertex<T> v) {
		int posU = vertices.get(u.getValue());
		int posV = vertices.get(v.getValue());
		return distMatrix[posU][posV];
	}
	
	@Override
	public ArrayList<Edge<T>> kruskal(){
		ArrayList<Edge<T>> res = new ArrayList<>();
		int a = 0;
		int i = 0;
		ArrayList<Edge<T>> edgesList = getEdges();
		Collections.sort(edgesList);
		UnionFind u = new UnionFind(verticesLookUp.size());
		
		while(a < verticesLookUp.size() -1 && i < edgesList.size()) {
			Edge<T> edge = edgesList.get(i);
			i++;
			int x = u.find(verticesLookUp.indexOf(edge.initVertex()));
			int y = u.find(verticesLookUp.indexOf(edge.endVertex()));
			if(x != y) {
				res.add(edge);
				u.union(x, y);
			}
		}
		return res;
	}
	
	public ArrayList<Edge<T>> getEdges(){
		ArrayList<Edge<T>> edges = new ArrayList<>();
		for(int i = 0; i < adjMatrix.length; i++) {
			for(int j = 0; j < adjMatrix[0].length; j++) {
				if(adjMatrix[i][j] == 1) {
					edges.add(new Edge<T>(verticesLookUp.get(i), verticesLookUp.get(j), distMatrix[i][j]));
				}
			}
		}
		return edges;
	}
	
	@Override
	public String toString() {
		String msg = "";
		for(int i = 0; i < vertices.size(); i++) {
			if(verticesLookUp.get(i) != null) {
				msg += verticesLookUp.get(i) + "-> ";
			}
			for(int j = 0; j < vertices.size(); j++) {
				if(j != i) {
					if(verticesLookUp.get(j) != null && adjMatrix[i][j] == 1) {
						msg += "(" + verticesLookUp.get(j)+ ", " + distMatrix[i][j] + ")";  
					}	
				}
			}
			msg+= "\n";
		}
		return msg;
		
		
	}
	
	private class ShortestPath{
		
		private int minDistance(int[] dist, boolean sptSet[]) {
			int min = INF;
			int min_index = -1;
			for(int i = 0; i < verticesLookUp.size(); i++) {
				if(sptSet[i] == false && dist[i] < min) {
					min = dist[i];
					min_index = i;
				}
			}
			
			return min_index;
		}
		
		private void dijkstra(int[][] graph, int src) {
			int[] dist = new int[verticesLookUp.size()];
			boolean[] sptSet = new boolean[verticesLookUp.size()];
			
			for(int i = 0; i < verticesLookUp.size(); i++) {
				dist[i] = INF;
				sptSet[i] = false;
			}
			
			dist[src] = 0;
			int u = -1;
			for(int count = 0; count < verticesLookUp.size() -1; count++) {
				u = minDistance(dist,sptSet);
				sptSet[u] = true;
				for(int v = 0; v < verticesLookUp.size(); v++) {
					if(!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
						dist[v] = dist[u] + graph[u][v];
						verticesLookUp.get(v).setD(dist[u] + graph[u][v]);
						verticesLookUp.get(v).setPred(verticesLookUp.get(u));
					}
				}
			}
			distMatrix[src] = dist;
			
		}
		
		private boolean bellmanford(int[][] graph, int src) {
			int[] dist = new int[verticesLookUp.size()];
			boolean[] sptSet = new boolean[verticesLookUp.size()];
			for(int i = 0; i < verticesLookUp.size(); i++) {
				dist[i] = INF;
				sptSet[i] = false;
			}
			dist[src] = 0;
			int u = -1;
			for(int count = 0; count < verticesLookUp.size() -1; count++) {
				u = minDistance(dist,sptSet);
				sptSet[u] = true;
				for(int v = 0; v < verticesLookUp.size(); v++) {
					if(!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
						dist[v] = dist[u] + graph[u][v];
						verticesLookUp.get(v).setD(dist[u] + graph[u][v]);
						verticesLookUp.get(v).setPred(verticesLookUp.get(u));
					}
				}
			}
			
			for(int e = 0; e < verticesLookUp.size(); e++) {
				for(int v = 0; v < verticesLookUp.size(); v++) {
					if(dist[e] != INF && dist[e] + graph[e][v] < dist[v]) {
						return false;
					}
				}
			}
			
			distMatrix[src] = dist;
			return true;
			
			
		}
	}

}
