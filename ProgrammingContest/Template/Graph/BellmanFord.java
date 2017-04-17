import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BellmanFord {
	public class Edge{
		int from, to, weight;
		public Edge(int f, int t, int w){
			from = f;	to = t;
			weight = w;
		}
	}
	public class Graph{
		int v, e;
		LinkedList<Integer>[] adj;
		ArrayList<Edge> edges;
		public Graph(){
			Scanner in = new Scanner(System.in);
			v = in.nextInt();	e = in.nextInt();
			adj = new LinkedList[v];
			for(int i = 0; i < v; i++)	adj[i] = new LinkedList<Integer>();
			for(int i = 0; i < e; i++){
				int v1 = in.nextInt(), v2 = in.nextInt(), weight = in.nextInt();
					adj[v1].add(i);
					edges.add(new Edge(v1, v2,weight));
			}
			in.close();
		}
	}
	public boolean negativeCycle;
	public LinkedList<Integer> q;
	public boolean[] inq;
	public int[] distTo;
	public int[] edgeTo;
	public int[] cnt;
	public void find(Graph g, int s){
		int v = g.v;
		
		q = new LinkedList<Integer>();
		inq = new boolean[v];
		distTo = new int[v];
		edgeTo = new int[v];
		cnt = new int[v];
		
		for(int i = 0; i < v; i++)	distTo[i] = Integer.MAX_VALUE;
		distTo[s] = 0;
		q.add(s);
		inq[s] = true;
		
		negativeCycle = false;
		while(!q.isEmpty() && !negativeCycle){
			int u = q.remove();
			inq[u] = false;
			if(!relax(g, u))	negativeCycle = true;
		}
	}
	public boolean relax(Graph g, int u){
		for(Integer i:g.adj[u]){
			Edge edge = g.edges.get(i);
			int v = edge.to;
			if(distTo[v] > distTo[u] + edge.weight){
				distTo[v] = distTo[u] + edge.weight;
				edgeTo[v] = u;
				if(!inq[v]){
					q.add(v);
					inq[v] = true;
					if(++cnt[v] > g.v)	return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		
	}

}
