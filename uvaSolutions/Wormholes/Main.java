import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


class Main{
  static final int INF = 1000000;
  static boolean bellmanford(int[][] graph,int vertices, int edges){
    int V = vertices, E = edges;
    int dist[] = new int[V];

    for(int i = 1; i < V; ++i)
      dist[i] = INF;
    dist[0] = 0;

    for(int i = 1; i < V; ++i){
      for(int j = 0; j < E; ++j){
        int u = graph[j][0];
        int v = graph[j][1];
        int w = graph[j][2];
        if(dist[u] != INF &&  dist[u] + w < dist[v]){
          dist[v] = dist[u] + w;
        }
      }
    }
    for(int j = 0; j < E; ++j){
      int u = graph[j][0];
      int v = graph[j][1];
      int w = graph[j][2];
      if(dist[u] != INF &&  dist[u] + w < dist[v]){
        return false;
      }
    }

    return true;
  }
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCases = Integer.parseInt(br.readLine());
    for(int t = 0; t < testCases; ++t){
      String[] line = br.readLine().split(" ");
      int n = Integer.parseInt(line[0]), m = Integer.parseInt(line[1]);
      int[][] graph = new int[m][3];

      for(int i = 0; i < m ; ++i){
        String[] l = br.readLine().split(" ");
        int v1 = Integer.parseInt(l[0]);
        int v2 = Integer.parseInt(l[1]);
        int w = Integer.parseInt(l[2]);
        graph[i][0] = v1;
        graph[i][1] = v2;
        graph[i][2] = w;
      }

      if(bellmanford(graph, n, m)){
        System.out.println("not possible");
      }else{
        System.out.println("possible");
      }


    }
  }
}
