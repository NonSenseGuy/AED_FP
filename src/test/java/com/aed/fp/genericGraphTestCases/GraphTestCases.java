package com.aed.fp.genericGraphTestCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.aed.fp.genericGraphs.Edge;
import com.aed.fp.genericGraphs.GraphAdjList;
import com.aed.fp.genericGraphs.Vertex;



class GraphTestCases {
	
	GraphAdjList<Integer> graph;
	
	void setup() {
		graph = new GraphAdjList<>(false, false);
	}
	
	void setupBellmanford() {
		setupDirectedWeightedGraph();
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.addEdge(v1, v2, 10);
		graph.addEdge(v2, v3, -13);
		graph.addEdge(v2, v4, 4);
		graph.addEdge(v3, v1, 2);


	}
	
	void setupDirectedGraph() {
		graph = new GraphAdjList<>(true, false);
	}
	
	void setupDirectedWeightedGraph() {
		graph = new GraphAdjList<>(true,true);
	}
	
	void setupDirectedWeightedGraph2() {
		setupDirectedWeightedGraph();
		Vertex<Integer> v1 = new Vertex<>(1);
		Vertex<Integer> v2 = new Vertex<>(2);
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addEdge(v1, v2, 5);
	}
	void setupDirectedGraph2() {
		setupDirectedGraph();
		Vertex<Integer> v1 = new Vertex<>(1);
		Vertex<Integer> v2 = new Vertex<>(2);
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addEdge(v1, v2);
		
	}
	
	void setup2() {
		setup();
		graph.addVertex(new Vertex<Integer>(1));
		graph.addVertex(new Vertex<Integer>(2));
	}
	
	void setupMoreVertex() {
		setup2();
		Vertex<Integer> v1 = new Vertex<>(6);
		Vertex<Integer> v2 = new Vertex<>(16);
		Vertex<Integer> v3 = new Vertex<>(26);
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addEdge(v1, v3);
		graph.addEdge(v2, v1);
		graph.addEdge(v3, v2);
		graph.addEdge(graph.getVertex(1), v3);
		graph.addEdge(graph.getVertex(2),graph.getVertex(1));
	}
	void setupMoreVertexWeighted() {
		setup2();
		Vertex<Integer> v1 = new Vertex<>(6);
		Vertex<Integer> v2 = new Vertex<>(16);
		Vertex<Integer> v3 = new Vertex<>(26);
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addEdge(v1, v3, 3);
		graph.addEdge(v2, v1, 10);
		graph.addEdge(v3, v2, 5);
		graph.addEdge(graph.getVertex(1), v3, 20);
		graph.addEdge(graph.getVertex(1), graph.getVertex(2), 2);
		graph.addEdge(graph.getVertex(1), graph.getVertex(6));
	}
	
	void setup3() {
		setup2();
		Vertex<Integer> v1 = graph.getVertex(1);
		Vertex<Integer> v2 = graph.getVertex(2);
		graph.addEdge(v1,v2);
	}
	
	@Test
	void simpleAddVertexTest() {
		setup();
		Vertex<Integer> v = new Vertex<Integer>(4);
		graph.addVertex(v);
		assertTrue(graph.getAdjList().keySet().contains(v));
	}
	
	@Test
	void simpleAddVertexTest2() {
		setup();
		graph.addVertex(10);
		graph.addVertex(2);
		graph.addVertex(5);
		graph.addVertex(6);
		assertTrue(graph.getAdjList().size() == 4 && graph.getAdjList().containsKey(graph.getVertex(6)));
	}
	
	@Test
	void getVertexTest() {
		setup();
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		v1.setColor(Vertex.BLACK);
		v1.setD(4);
		v1.setPred(null);
		graph.addVertex(v1);
		Vertex<Integer> v = graph.getVertex(1);
		assertTrue(v.getColor() == Vertex.BLACK && v.getD() == 4 && v.getPred() == null && v.equals(v1));
	}
	
	@Test
	void simpleRemoveVertexText() {
		setup();
		graph.addVertex(4);
		graph.removeVertex(graph.getVertex(4));
		assertTrue(graph.getAdjList().isEmpty());
	}
	@Test
	void simpleRemoveVertexTest2() {
		setup3();
		Vertex<Integer> v1 = graph.getVertex(1);
		Vertex<Integer> v2 = graph.getVertex(2);
		Edge<Integer> e1 = graph.getEdge(v1, v2);
		Edge<Integer> e2 = graph.getEdge(v2, v1);
		Set<Edge<Integer>> edgesV1 = graph.getAdjList().get(v1);
		Set<Edge<Integer>> edgesV2 = graph.getAdjList().get(v2);
		graph.removeVertex(v1);
		boolean cond = edgesV1.contains(e1) || edgesV2.contains(e2);
		assertFalse(graph.getAdjList().containsKey(v1) || cond);
		
	}
	
	@Test
	void simpleAddEdgeTest() {
		setup2();
		Vertex<Integer> v1 = graph.getVertex(1);
		Vertex<Integer> v2 = graph.getVertex(2);
		graph.addEdge(v1, v2);
		Edge<Integer> e = graph.getEdge(v1, v2);
		assertTrue(e.endVertex().getValue() == 2);
	}
	
	@Test
	void simpleGetEdgeTest() {
		setup3();
		Vertex<Integer> v1 = graph.getVertex(1);
		Vertex<Integer> v2 = graph.getVertex(2);
		Edge<Integer> edge = graph.getEdge(v1, v2);
		assertTrue(edge.endVertex().equals(v2) && edge.initVertex().equals(v1) && edge.getWeight() == 1.0);
	}
	
	@Test
	void simpleRemoveEdgeTest() {
		setup3();
		Vertex<Integer> v1 = graph.getVertex(1);
		Vertex<Integer> v2 = graph.getVertex(2);
		Edge<Integer> edge = graph.getEdge(v1, v2);
		graph.removeEdge(v1, v2);
	
		assertFalse(graph.getAdjList().get(v1).contains(edge));
	}
	
	@Test
	void directedAddEdgeTest() {
		setupDirectedGraph2();
		Vertex<Integer> v1 = graph.getVertex(1);
		Vertex<Integer> v2 = graph.getVertex(2);
		Edge<Integer> e = graph.getEdge(v1, v2);
		assertTrue(graph.getAdjList().get(v2).isEmpty() && e.endVertex().getValue() == 2 && e.initVertex().getValue() == 1);
	}
	
	@Test
	void directedWeightedAddEdgeTest() {
		setupDirectedWeightedGraph2();
		Vertex<Integer> v1 = graph.getVertex(1);
		Vertex<Integer> v2 = graph.getVertex(2);
		Edge<Integer> e = graph.getEdge(v1, v2);
		assertTrue(graph.getAdjList().get(v2).isEmpty() && e.endVertex().getValue() == 2 && e.initVertex().getValue() == 1 && e.getWeight() == 5);
	}
	
	@Test
	void bellmanfordTest() {
		setupBellmanford();
		assertFalse(graph.bellmanford(graph.getVertex(1)));
	}
	
	@Test
	void bellmanfordTest2() {
		setupBellmanford();
		Vertex<Integer> v = graph.getVertex(2);
		graph.removeVertex(v);
		assertTrue(graph.bellmanford(graph.getVertex(4)));
	}
	
	@Test 
	void dijkstraTest(){
		setupMoreVertexWeighted();
		graph.dijkstra(graph.getVertex(2));
		assertTrue(graph.getVertex(16).getPred() == graph.getVertex(26) && graph.getVertex(6).getPred() == graph.getVertex(1) && graph.getVertex(26).getPred() == graph.getVertex(6) && graph.getVertex(1).getPred() == graph.getVertex(2));	
	}
	
	@Test
	void dijkstraTest2() {
		setupMoreVertexWeighted();
		graph.setWeightEdge(graph.getVertex(26), graph.getVertex(1), 3);
		graph.dijkstra(graph.getVertex(2));
		assertTrue(graph.getVertex(26).getPred() == graph.getVertex(1));
	}
	
	@Test
	void prim() {
		setupMoreVertexWeighted();
		graph.prim(graph.getVertex(2));
		Vertex<Integer> v1 = graph.getVertex(1);
		Vertex<Integer> v2 = graph.getVertex(2);
		Vertex<Integer> v3 = graph.getVertex(6);
		Vertex<Integer> v4 = graph.getVertex(26);
		Vertex<Integer> v5 = graph.getVertex(16);
		
		assertTrue(v1.getPred() == v2 && v2.getPred() == null && v3.getPred() == v1 && v4.getPred() == v3 && v5.getPred() == v4);
	}
	
	
	
	

}
