import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PublicBikeManagement {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public int chalfmax, n, sp, m, cn[];
	public int ansOut, ansIn;
	public ArrayList<Integer> ans;
	public boolean[] reachable;
	public boolean[] marked;
	public Graph graph;
	public void solve() throws IOException{
		while(true){
			String line = in.readLine();	if(line == null)	break;
			StringTokenizer st = new StringTokenizer(line);
			chalfmax = Integer.parseInt(st.nextToken()) / 2;
			n = Integer.parseInt(st.nextToken());
			sp = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(in.readLine());
			cn = new int[n + 1];
			for(int i = 1; i <= n; i++)
				cn[i] = Integer.parseInt(st.nextToken());
			graph = new Graph(n + 1, m);
			for(int i = 0; i < m; i++){
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				graph.addEdge(from, to, weight);
			}
			marked = new boolean[n + 1];
			reachable = new boolean[m];
			dfsReach(sp);
			dijkstra(graph, 0);
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(sp);
			ansOut = Integer.MAX_VALUE;
			ansIn = Integer.MAX_VALUE;
			dfsSearch(sp, tmp);
			out.append(ansOut + " ");
			out.append(0);
			for(int i = ans.size() - 2; i >= 0; i--)
				out.append("->" + ans.get(i));
			out.append(" " + ansIn + "\n");
		}
		System.out.print(out.toString());
	}
	public void dfsSearch(int u, ArrayList<Integer> tmp){
		if(u == 0){
			int out = 0, cur = 0;
			for(int i = tmp.size() - 2; i >= 0; i--){
				if(cn[tmp.get(i)] > chalfmax)	cur += cn[tmp.get(i)] - chalfmax;
				else if(cn[tmp.get(i)] < chalfmax){
					int need = chalfmax - cn[tmp.get(i)];
					if(cur >= need)	cur -= need;
					else{
						out += need - cur;
						cur = 0;
					}
				}
			}
			if(out < ansOut || (out == ansOut && cur < ansIn)){
				ansOut = out;
				ansIn = cur;
				ans = new ArrayList<Integer>();
				ans.addAll(tmp);
			}
			return;
		}
		for(int e:graph.adj[u]){
			Edge edge = graph.edges.get(e);
			int v = edge.other(u);
			if(dist[v] + edge.weight == dist[u]){
				tmp.add(v);
				dfsSearch(v, tmp);
				tmp.remove(tmp.size() - 1);
			}
		}
		return;
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
	public int dist[];
	public void dijkstra(Graph graph, int s){
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		dist = new int[graph.v];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s] = 0;
		q.add(new Vertex(s, 0));
		boolean[] relaxed = new boolean[graph.v];
		
		while(!q.isEmpty()){
			int u = q.poll().v;
			if(relaxed[u])	continue;
			relaxed[u] = true;
			for(int e:graph.adj[u]){
				if(!reachable[e])	continue;
				Edge edge = graph.edges.get(e);
				int to = edge.to;
				int weight = edge.weight;
				if(dist[to] > dist[u] + weight){ 
					dist[to] = dist[u] + weight;
					q.add(new Vertex(to	, dist[to]));
				}
			}
		}
	}
	public void dfsReach(int u){
		marked[u] = true;
		for(int e:graph.adj[u]){
			reachable[e] = true;
			int v = graph.edges.get(e).other(u);
			if(!marked[v])	dfsReach(v);
		}
	}
	public static void main(String[] args) throws IOException {
		PublicBikeManagement a = new PublicBikeManagement();
		a.solve();
	}
	class Edge{
		int from, to, weight;
		public Edge(int f, int t, int w){
			from = f;	to = t;
			weight = w;
		}
		public int other(int u){
			if(u == from)	return to;
			else			return from;
		}
	}
	class Graph{
		int v, e;
		ArrayList<Integer>[] adj;
		ArrayList<Edge> edges;
		public Graph(int v, int e){
			this.v = v;
			this.e = e;
			edges = new ArrayList<Edge>();
			adj = (ArrayList<Integer>[]) new ArrayList[v];
			for(int i = 0; i < v; i++)
				adj[i] = new ArrayList<Integer>();
		}
		public void addEdge(int f, int t, int w){
			Edge edge = new Edge(f, t, w);
			int index = edges.size();
			edges.add(edge);
			adj[f].add(index);
			adj[t].add(index);
		}
	}
}
