import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class MaxFlow {
	public class Edge{
		int from, to, cap, flow, cost;
		public Edge(int f, int t, int c, int fl, int cs){
			from = f;	to = t;		cap = c;	flow = fl;
			cost = cs;
		}
	}
	public class Graph{
		int v, e;
		LinkedList<Integer>[] adj;
		ArrayList<Edge> edges;
		public Graph(int v, int e){
			this.v = v;
			this.e = e;
			edges = new ArrayList<Edge>();
			adj = (LinkedList<Integer>[]) new LinkedList[v];
			for(int i = 0; i < v; i++)
				adj[i] = new LinkedList<Integer>();
		}
	}

	public class EdmondsKarp {
		public int flow;
		public int[] a, edgeTo;

		public int maxflow(Graph G, int s, int t) {
			flow = 0;
			while (true) {
				a = new int[G.v];
				edgeTo = new int[G.v];
				a[0] = Integer.MAX_VALUE;
				LinkedList<Integer> queue = new LinkedList<Integer>();
				queue.add(0);
				while (!queue.isEmpty()) {
					int cur = queue.remove();
					for (Integer e : G.adj[cur]) {
						Edge edge = G.edges.get(e);
						if (a[edge.to] == 0 && edge.cap > edge.flow) {
							edgeTo[edge.to] = e;
							a[edge.to] = Math.min(a[cur], edge.cap - edge.flow);
							queue.add(edge.to);
						}
					}
					if (a[t] != 0)
						break;
				}
				if (a[t] == 0)
					break;
				for (int u = t; u != s; u = G.edges.get(edgeTo[u]).from) {
					G.edges.get(edgeTo[u]).flow += a[t];
					G.edges.get(edgeTo[u] ^ 1).flow -= a[t];
				}
				flow += a[t];
			}
			return flow;
		}
	}
	public class MinCostMaxFlow{
		//use bellman ford to find augmenting path
		public int flow;
		public Long cost;
		
		public int  mcmf(Graph G, int s, int t){
			flow = 0; cost = new Long(0);
			while(bellmanford(G, s, t));
			return flow;
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
	public static void main(String[] args) {

	}
}
