import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Main{


	static int bfs(HashMap<Integer,LinkedList<Integer>> graph, int src, int to) {
		final int INF = 1000000;
		boolean visited[] = new boolean[graph.size()];
		int dist[] = new int[graph.size()];
		for(int i = 0; i < graph.size(); i++){
			visited[i] = false;
			dist[i] = INF;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(src);
		visited[src] = true;
		dist[src] = 0;
		while(!q.isEmpty()){
			int u = q.poll();
			for(Integer e: graph.get(u)){
				if(visited[e] == false){
					visited[e] = true;
					dist[e] = dist[u] + 1;
					q.offer(e);
				}
			}
		}
		return dist[to]-1;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.parseInt(br.readLine());
		for(int t = 0 ; t < testCases; ++t) {
			br.readLine();
			int numV = Integer.parseInt(br.readLine());
			HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
			for(int i = 0; i < numV; i++) {
				graph.put(i, new LinkedList<>());
				String[] line = br.readLine().split(" ");
				for(int j = 0; j < line.length; j++) {
					int v = Integer.parseInt(line[j]);
					if(v != i) {
						graph.get(i).add(v);
					}

				}
			}
			String[] from_to = br.readLine().split(" ");
			int from = Integer.parseInt(from_to[0]);
			int to = Integer.parseInt(from_to[1]);
			System.out.println(from + " " + to + " " + bfs(graph,from,to));
			if(t != testCases-1){
				System.out.println();
			}
		}
	}
}
