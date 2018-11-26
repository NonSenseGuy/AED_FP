package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import genericGraphs.Graph;
import genericGraphs.GraphAdjList;
import genericGraphs.GraphMatrix;

class GraphTheMysteriousXTest {
	
	private Graph<String> g;
	
	void setupAdjListGraph() {
		g = new GraphAdjList<>(false, false);
	}
	
	void setupMatrixGraph(int n ) {
		g = new GraphMatrix<>(n, false, false);
	}

	@Test
	void testAdjList1() {
		setupAdjListGraph();
		for(int i = 0; i < 4; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("0"),g.getVertex("3"));	
		g.addEdge(g.getVertex("0"),g.getVertex("1"));
		g.addEdge(g.getVertex("0"),g.getVertex("2"));
		g.addEdge(g.getVertex("2"),g.getVertex("3"));
		
		g.bfs(g.getVertex("1"));
		int d = g.getVertex("2").getD()-1;
		assert d == 1: "Distancia incorrecta";
		
	}
		
	@Test
	void testAdjMatrix1() {
		setupMatrixGraph(4);
		for(int i = 0; i < 4; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("0"),g.getVertex("3"));	
		g.addEdge(g.getVertex("0"),g.getVertex("1"));
		g.addEdge(g.getVertex("0"),g.getVertex("2"));
		g.addEdge(g.getVertex("2"),g.getVertex("3"));
		
		g.bfs(g.getVertex("1"));
		int d = g.getVertex("2").getD()-1;
		assert d == 1: "Distancia incorrecta " + d;
	}
	
	@Test
	void testAdjList2 () {
		setupAdjListGraph();
		for(int i = 0; i < 4; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("0"),g.getVertex("2"));	
		g.addEdge(g.getVertex("0"),g.getVertex("1"));
		g.addEdge(g.getVertex("0"),g.getVertex("3"));
		g.addEdge(g.getVertex("2"),g.getVertex("3"));
		
		g.bfs(g.getVertex("1"));
		int d = g.getVertex("2").getD()-1;
		assert d == 1: "Distancia incorrecta";
	}
	
	@Test
	void testAdjMatrix2() {
		setupMatrixGraph(4);
		for(int i = 0; i < 4; i++) {
			g.addVertex("" + i);
		}
		g.addEdge(g.getVertex("0"),g.getVertex("2"));	
		g.addEdge(g.getVertex("0"),g.getVertex("1"));
		g.addEdge(g.getVertex("0"),g.getVertex("3"));
		g.addEdge(g.getVertex("2"),g.getVertex("3"));
		
		g.bfs(g.getVertex("1"));
		int d = g.getVertex("2").getD()-1;
		assert d == 1: "Distancia incorrecta";
	}


}
