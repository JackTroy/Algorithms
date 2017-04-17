import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FunnyCarRacing {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public class Edge{
		public int u, v, a, b, t;
		public Edge(int u, int v, int a, int b, int t){
			this.u = u;
			this.v = v;
			this.a = a;
			this.b = b;
			this.t = t;
		}
	}
	public class Graph{
		public int v, e;
		public ArrayList<Edge> edges;
		public ArrayList<Integer>[] adj;
		public Graph(int v){
			this.v = v;
			edges = new ArrayList<Edge>();
			adj = (ArrayList<Integer>[]) new ArrayList[v];
			for(int i = 0; i < v; i++)
				adj[i] = new ArrayList<Integer>();
		}
		public void addEdge(int u, int v, int a, int b, int t){
			int index = edges.size();
			edges.add(new Edge(u, v, a, b, t));
			adj[u].add(index);
		}
	}
	public class Vertex implements Comparable<Vertex>{
		public int v;
		public int dist;
		public Vertex(int v, int dist){
			this.v = v;
			this.dist = dist;
		}
		public int compareTo(Vertex o) {
			if(dist < o.dist)		return -1;
			else if(dist > o.dist)	return 1;
			return 0;
		}
	}
	public int[] dist;
	public final int INF = Integer.MAX_VALUE / 2;
	public int dijkstra(Graph graph, int s, int t){
		boolean[] vis = new boolean[graph.v];
		dist = new int[graph.v];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		q.add(new Vertex(s, 0));
		while(!q.isEmpty()){
			int u = q.remove().v;
			if(vis[u]) continue;
			vis[u] = true;
			for(Integer e:graph.adj[u]){
				Edge edge = graph.edges.get(e);
				int v = edge.v;
				int r = dist[u] % (edge.a + edge.b);
				//r from 0 to a + b - 1
				int tmp = dist[u];
				if(edge.t > edge.a)	tmp = INF;
				else if(edge.t + r> edge.a)	tmp = tmp + edge.a + edge.b - r + edge.t;
				else	tmp = tmp + edge.t;
				if(dist[v] > tmp){
					dist[v] = tmp;
					q.add(new Vertex(v, dist[v]));
				}
			}
		}
		return dist[t];
	}
	public void solve() throws IOException{
		String line;
		int curT = 0;
		while(true){
			line = in.readLine();	if(line == null)	break;
			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken()) - 1;
			Graph graph = new Graph(n);
			for(int i = 0; i < m; i++){
				st = new StringTokenizer(in.readLine());
				graph.addEdge(parseInt(st.nextToken()) - 1, parseInt(st.nextToken()) - 1, 
						parseInt(st.nextToken()), parseInt(st.nextToken()), 
						parseInt(st.nextToken()));
			}
			out.append("Case " + (++curT) + ": " + dijkstra(graph, s, t) + "\n");
		}
		System.out.print(out.toString());
	}
	public int parseInt(String s){
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException {
		FunnyCarRacing a = new FunnyCarRacing();
		a.solve();
	}

}
