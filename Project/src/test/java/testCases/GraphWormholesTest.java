package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import genericGraphs.Graph;
import genericGraphs.GraphAdjList;
import genericGraphs.GraphMatrix;

class GraphWormholesTest {
	
	private Graph<String> g;
	
	void setupWormholesListGraph() {
		g = new GraphAdjList<>(true, true);
	}
	void setupWormholesMatrixGraph(int n) {
		g = new GraphMatrix<>(n,true,true);
	}
	
	@Test
	void testAdjListWormholes1() {
		setupWormholesListGraph();
		for(int i = 0; i < 3; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("0"),g.getVertex("1"), 1000);
		g.addEdge(g.getVertex("1"),g.getVertex("2"), 15);
		g.addEdge(g.getVertex("2"),g.getVertex("1"), -42);
		assertFalse(g.bellmanford(g.getVertex("0"))); //possible 
	}
	
	@Test
	void testAdjMatrixWormholes1() {
		setupWormholesMatrixGraph(3);
		for(int i = 0; i < 3; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("0"),g.getVertex("1"), 1000);
		g.addEdge(g.getVertex("1"),g.getVertex("2"), 15);
		g.addEdge(g.getVertex("2"),g.getVertex("1"), -42);
		assertFalse(g.bellmanford(g.getVertex("0"))); //possible 
	}
	
	@Test
	void testAdjListWormholes2() {
		setupWormholesListGraph();
		for(int i = 0; i < 4; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("0"),g.getVertex("1"), 10);
		g.addEdge(g.getVertex("1"),g.getVertex("2"), 20);
		g.addEdge(g.getVertex("2"),g.getVertex("3"), 30);
		g.addEdge(g.getVertex("3"),g.getVertex("0"), -60);
		assertTrue(g.bellmanford(g.getVertex("0"))); //not possible
	}
	
	@Test
	void testAdjMatrixWormholes2() {
		setupWormholesMatrixGraph(4);
		for(int i = 0; i < 4; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("0"),g.getVertex("1"), 10);
		g.addEdge(g.getVertex("1"),g.getVertex("2"), 20);
		g.addEdge(g.getVertex("2"),g.getVertex("3"), 30);
		g.addEdge(g.getVertex("3"),g.getVertex("0"), -60);
		assertTrue(g.bellmanford(g.getVertex("0"))); //not possible
	}
	
	@Test
	void testAdjListWormholes3() {
		setupWormholesListGraph();
		for(int i = 0; i < 6; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("5"),g.getVertex("2"), 1357);	
		g.addEdge(g.getVertex("1"),g.getVertex("3"), 1256);
		g.addEdge(g.getVertex("3"),g.getVertex("4"), 262);
		g.addEdge(g.getVertex("5"),g.getVertex("0"), 1271);
		g.addEdge(g.getVertex("2"),g.getVertex("0"), 189);
		g.addEdge(g.getVertex("1"),g.getVertex("2"), -189);
		g.addEdge(g.getVertex("1"),g.getVertex("4"), 530);
		g.addEdge(g.getVertex("0"),g.getVertex("1"), -447);
		g.addEdge(g.getVertex("1"),g.getVertex("0"), -470);
		g.addEdge(g.getVertex("4"),g.getVertex("5"), 1168);
		
		assertFalse(g.bellmanford(g.getVertex("0"))); //possible

	}
	
	@Test
	void testAdjMatrixWormholes3() {
		setupWormholesMatrixGraph(6);
		for(int i = 0; i < 6; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("5"),g.getVertex("2"), 1357);	
		g.addEdge(g.getVertex("1"),g.getVertex("3"), 1256);
		g.addEdge(g.getVertex("3"),g.getVertex("4"), 262);
		g.addEdge(g.getVertex("5"),g.getVertex("0"), 1271);
		g.addEdge(g.getVertex("2"),g.getVertex("0"), 189);
		g.addEdge(g.getVertex("1"),g.getVertex("2"), -189);
		g.addEdge(g.getVertex("1"),g.getVertex("4"), 530);
		g.addEdge(g.getVertex("0"),g.getVertex("1"), -447);
		g.addEdge(g.getVertex("1"),g.getVertex("0"), -470);
		g.addEdge(g.getVertex("4"),g.getVertex("5"), 1168);
		
		assertFalse(g.bellmanford(g.getVertex("0"))); //possible
		
	}
	
	@Test
	void testAdjMatrixWormholes4() {

		setupWormholesMatrixGraph(6);
		for(int i = 0; i < 6; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("3"),g.getVertex("4"), 50);	
		g.addEdge(g.getVertex("0"),g.getVertex("2"), 1483);
		g.addEdge(g.getVertex("4"),g.getVertex("1"), 238);
		g.addEdge(g.getVertex("1"),g.getVertex("2"), -125);
		g.addEdge(g.getVertex("4"),g.getVertex("5"), -473);
		g.addEdge(g.getVertex("1"),g.getVertex("4"), 1447);
		g.addEdge(g.getVertex("5"),g.getVertex("3"), -496);
		g.addEdge(g.getVertex("5"),g.getVertex("4"), -317);
		g.addEdge(g.getVertex("0"),g.getVertex("5"), 908);
		g.addEdge(g.getVertex("0"),g.getVertex("3"), 250);
		
		assertFalse(g.bellmanford(g.getVertex("0"))); //possible
	}
	
	@Test
	void testAdjListWormholes() {
		setupWormholesListGraph();
		for(int i = 0; i < 6; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("3"),g.getVertex("4"), 50);	
		g.addEdge(g.getVertex("0"),g.getVertex("2"), 1483);
		g.addEdge(g.getVertex("4"),g.getVertex("1"), 238);
		g.addEdge(g.getVertex("1"),g.getVertex("2"), -125);
		g.addEdge(g.getVertex("4"),g.getVertex("5"), -473);
		g.addEdge(g.getVertex("1"),g.getVertex("4"), 1447);
		g.addEdge(g.getVertex("5"),g.getVertex("3"), -496);
		g.addEdge(g.getVertex("5"),g.getVertex("4"), -317);
		g.addEdge(g.getVertex("0"),g.getVertex("5"), 908);
		g.addEdge(g.getVertex("0"),g.getVertex("3"), 250);
		
		assertFalse(g.bellmanford(g.getVertex("0"))); //possible
	}
	
	

}
