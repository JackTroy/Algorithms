import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class InternetBandwidth {
	
	public class Edge{
		int from, to, cap, flow;
		public Edge(int f, int t, int c, int fl){
			from = f;	to = t;		cap = c;	flow = fl;
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
		public void addEdge(int u, int v, int cap){
			int index = edges.size();
			edges.add(new Edge(u, v, cap, 0));
			edges.add(new Edge(v, u, 0, 0));
			adj[u].add(index);
			adj[v].add(index + 1);
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
				a[s] = Integer.MAX_VALUE;
				LinkedList<Integer> queue = new LinkedList<Integer>();
				queue.add(s);
				while (!queue.isEmpty()) {
					int cur = queue.remove();
					for (Integer e : G.adj[cur]) {
						Edge edge = G.edges.get(e);
						if(cur == 2 && edge.to == 3){
							System.out.println(a[edge.to] + "-" + edge.cap + "-" + edge.flow);
						}
						int next = edge.to;
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
		int curT = 0;
		while(in.hasNext()){
			int n = in.nextInt();	if(n == 0)	break;
			int s = in.nextInt(), t = in.nextInt(), c = in.nextInt();
			Graph graph = new Graph(101, 200);
			
			for(int i = 0; i < c; i++){
				int u = in.nextInt(), v = in.nextInt(), cap = in.nextInt();
				graph.addEdge(u, v, cap);
				graph.addEdge(v, u, cap);
			}
			EdmondsKarp ek = new EdmondsKarp();
			int ans = ek.maxflow(graph, s, t);
			System.out.print(String.format("Network %d\nThe bandwidth is %d.\n\n", ++curT, ans));
		}
		in.close();
	}
	public static void main(String[] args) {
		InternetBandwidth a = new InternetBandwidth();
		a.solve();
	}
}
