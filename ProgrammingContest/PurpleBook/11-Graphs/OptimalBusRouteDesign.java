import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class OptimalBusRouteDesign {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public class Edge{
		int from, to, cap, flow, cost;
		public Edge(int f, int t, int c, int fl, int cs){
			from = f;	to = t;		cap = c;	flow = fl;
			cost = cs;
		}
	}
	public class Graph{
		int v, e;
		ArrayList<Integer>[] adj;
		ArrayList<Edge> edges;
		public Graph(int v){
			this.v = v;
			edges = new ArrayList<Edge>();
			adj = (ArrayList<Integer>[]) new ArrayList[v];
			for(int i = 0; i < v; i++)
				adj[i] = new ArrayList<Integer>();
		}
		public void addEdge(int u, int v, int cap, int cost){
			int index = edges.size();
			edges.add(new Edge(u, v, cap, 0, cost));
			edges.add(new Edge(v, u, 0, 0, -cost));
			adj[u].add(index);
			adj[v].add(index + 1);
		}
	}
	public class MinCostMaxFlow{
		//use bellman ford to find augmenting path
		public int flow;
		public Long cost;
		
		public void mcmf(Graph G, int s, int t){
			flow = 0; cost = new Long(0);
			while(bellmanford(G, s, t));
		}
		
		public boolean bellmanford(Graph graph, int s, int t){
			LinkedList<Integer> queue = new LinkedList<Integer>();
			queue.add(s);
			boolean[] inq = new boolean[graph.v];
			inq[s] = true;
			//distance here is actually cost!
			int[] dist = new int[graph.v];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[s] = 0;
			int[] a = new int[graph.v];
			a[s] = Integer.MAX_VALUE;
			int[] edgeTo = new int[graph.v];
			
			while(!queue.isEmpty()){
				int cur = queue.remove();
				inq[cur] = false;
				for(int i = 0; i < graph.adj[cur].size(); i++){
					Edge edge = graph.edges.get(graph.adj[cur].get(i));
					int to = edge.to;
					if(edge.cap > edge.flow && dist[to] > dist[cur] + edge.cost){
						dist[to] = dist[cur] + edge.cost;
						a[to] = Math.min(a[cur], edge.cap - edge.flow);
						edgeTo[to] = graph.adj[cur].get(i);
						if(!inq[to]){
							inq[to] = true;
							queue.add(to);
						}
					}
				}
			}
			if(a[t] == 0)	return false;
			for(int u = t; u != s; u = graph.edges.get(edgeTo[u]).from){
				graph.edges.get(edgeTo[u]).flow += a[t];
				graph.edges.get(edgeTo[u] ^ 1).flow -= a[t];
			}
			flow += a[t];
			cost += dist[t] * a[t];
			return true;
		}
	}
	public void solve() throws NumberFormatException, IOException{
		while(true){
			int n = Integer.parseInt(in.readLine());	if(n == 0)	break;
			Graph graph = new Graph(2 * n + 2);
			//for every location i, 2 * i - 1 as x, 2 * i as y
			int s = 0, t = 2 * n + 1;
			for(int i = 1; i <= n; i++){
				graph.addEdge(s, 2 * i - 1, 1, 0);
				graph.addEdge(2 * i, t, 1, 0);
			}
			for(int i = 1; i <= n; i++){
				StringTokenizer st = new StringTokenizer(in.readLine());
				while(true){
					int v = Integer.parseInt(st.nextToken());	if(v == 0)	break;
					int d = Integer.parseInt(st.nextToken());
					graph.addEdge(2 * i - 1, 2 * v, 1, d);
				}
			}
			MinCostMaxFlow a = new MinCostMaxFlow();
			a.mcmf(graph, s, t);
			if(a.flow < n)	out.append("N\n");
			else			out.append(a.cost + "\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		OptimalBusRouteDesign a = new OptimalBusRouteDesign();
		a.solve();
	}

}
