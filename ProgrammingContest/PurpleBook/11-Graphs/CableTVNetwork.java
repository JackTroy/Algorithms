import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CableTVNetwork {
	public class Edge{
		int from, to, cap, flow;
		public Edge(int f, int t, int c, int fl){
			from = f;	to = t;		cap = c;	flow = fl;
		}
		public Edge(Edge o){
			from = o.from;
			to= o.to;
			cap = o.cap;
			flow = o.flow;
		}
	}
	public class Graph{
		int v;
		LinkedList<Integer>[] adj;
		ArrayList<Edge> edges;
		public Graph(int v){
			this.v = v;
			adj = (LinkedList<Integer>[]) new LinkedList[v];
			for(int i = 0; i < v; i++)	adj[i] = new LinkedList<Integer>();
			edges = new ArrayList<Edge>();
		}
		public Graph(Graph o){
			this.v = o.v;
			adj = (LinkedList<Integer>[]) new LinkedList[v];
			for(int i = 0; i < adj.length; i++){
				adj[i] = new LinkedList<Integer>();
				adj[i].addAll(o.adj[i]);
			}
			edges = new ArrayList<Edge>();
			for(Edge edge:o.edges){
				edges.add(new Edge(edge));
			}
		}
		public int addEdge(int u, int v, int cap){
			int index = edges.size();
			edges.add(new Edge(u, v, cap, 0));
			edges.add(new Edge(v, u, 0, 0));
			adj[u].add(index);
			adj[v].add(index + 1);
			return index;
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
	public void solve(){
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int v = in.nextInt(), e = in.nextInt();
			if(e == 0)	{ 
				if(v == 1)	System.out.println(1);
				else		System.out.println(0);
				continue; 
			}
			Graph graph = new Graph(2 * v);
			//u-u`,2*i-2*i+1 self edge
			//u-v,
			int[] selfEdges = new int[v];
			for(int i = 0; i < v; i++){
				selfEdges[i] = graph.addEdge(2 * i, 2 * i + 1, 1);
			}
			for(int i = 0; i < e; i++){
				String edge = in.next();
				int comma = edge.indexOf(',');
				int a = Integer.parseInt(edge.substring(1, comma));
				int b = Integer.parseInt(edge.substring(comma + 1, edge.length() - 1));
				//System.out.println(u + " " + a);
				graph.addEdge(2 * a + 1, 2 * b, Integer.MAX_VALUE);
				graph.addEdge(2 * b + 1, 2 * a, Integer.MAX_VALUE);
			}
			
			int ans = Integer.MAX_VALUE;
			for(int t = 1; t < v; t++){
				Graph copy = new Graph(graph);
				copy.edges.get(selfEdges[0]).cap = Integer.MAX_VALUE;
				copy.edges.get(selfEdges[t]).cap = Integer.MAX_VALUE;
				EdmondsKarp ek = new EdmondsKarp();
				ans = Math.min(ans, ek.maxflow(copy, 0, 2 * t + 1));
				copy.edges.get(selfEdges[t]).cap = 1;
			}
			if(ans == Integer.MAX_VALUE)	System.out.println(v);
			else							System.out.println(ans);
		}
		in.close();
	}
	public static void main(String[] args) {
		CableTVNetwork a = new CableTVNetwork();
		a.solve();
	}

}
