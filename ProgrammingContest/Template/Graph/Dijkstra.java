import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public void createGraph() throws NumberFormatException, IOException{
		int v = Integer.parseInt(in.readLine());
		int e = Integer.parseInt(in.readLine());
		Graph graph = new Graph(v, e);
		for(int i = 0; i < e; i++){
			String[] nums = in.readLine().split(" ");
			int u = Integer.parseInt(nums[0]);
			int next = Integer.parseInt(nums[1]);
			int w = Integer.parseInt(nums[2]);
			graph.addEdge(u, next, w);
		}
	}
	/*
	 * public Integer[] vertex;
	public double[] dist;
	public class DistComparator implements Comparator<Integer>{
		public int compare(Integer o1, Integer o2) {
			if(dist[o1] < dist[o2])			return -1;
			else if(dist[o1] > dist[o2])	return 1;
			return 0;
		}
	}
	 */
	public class Vertex implements Comparable<Vertex>{
		public int v;
		public double dist;
		public Vertex(int v, double dist){
			this.v = v;
			this.dist = dist;
		}
		public int compareTo(Vertex o) {
			if(dist < o.dist)		return -1;
			else if(dist > o.dist)	return 1;
			return 0;
		}
	}
	public void dijkstra(Graph graph, int s){
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		int[] dist = new int[graph.v];
		q.add(new Vertex(s, 0.0));
		boolean[] relaxed = new boolean[graph.v];
		
		while(!q.isEmpty()){
			int u = q.poll().v;
			if(relaxed[u])	continue;
			relaxed[u] = true;
			for(int e:graph.adj[u]){
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
	public static void main(String[] args) throws NumberFormatException, IOException {
		
	}
}
class Edge{
	int from, to, weight;
	public Edge(int f, int t, int w){
		from = f;	to = t;
		weight = w;
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
		adj[edge.from].add(index);
	}
}