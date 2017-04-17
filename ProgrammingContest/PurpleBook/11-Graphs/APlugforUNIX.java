import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class APlugforUNIX {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
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
	}
	
	public Graph graph;
	public int cnt, s, t;
	public HashMap<String, Integer> map;
	public String[] receps;
	public String[][] devices;
	public String[][] adps;
	public void BuildGraph(){
		graph.edges = new ArrayList<Edge>();
		graph.adj = (LinkedList<Integer>[]) new LinkedList[cnt + 1];
		for(int i = 0; i < cnt + 1; i++)	graph.adj[i] = new LinkedList<Integer>();
		graph.v = cnt + 1;
		//s - devices
		for(int i = 0; i < devices.length; i++){
			int u = s, v = map.get(devices[i][1]);
			int index = graph.edges.size();
			graph.edges.add(new Edge(u, v, 1, 0));
			graph.edges.add(new Edge(v, u, 0, 0));
			graph.adj[u].add(index);
			graph.adj[v].add(index + 1);
		}
		//adapters
		for(int i = 0; i < adps.length; i++){
			int u = map.get(adps[i][0]), v = map.get(adps[i][1]);
			int index = graph.edges.size();
			graph.edges.add(new Edge(u, v, Integer.MAX_VALUE, 0));
			graph.edges.add(new Edge(v, u, 0, 0));
			graph.adj[u].add(index);
			graph.adj[v].add(index + 1);
		}
		//rec - t
		for(int i = 0; i < receps.length; i++){
			int u = map.get(receps[i]), v = t;
			int index = graph.edges.size();
			graph.edges.add(new Edge(u, v, 1, 0));
			graph.edges.add(new Edge(v, u, 0, 0));
			graph.adj[u].add(index);
			graph.adj[v].add(index + 1);
		}
		graph.e = graph.edges.size();
	}
	public class EdmondsKarp{
		public int flow;
		public int[] a, edgeTo;
		public int maxflow(Graph graph, int s, int t){
			flow = 0;
			while(true){
				a = new int[graph.v];
				edgeTo = new int[graph.v];
				a[0] = Integer.MAX_VALUE;
				LinkedList<Integer> queue = new LinkedList<Integer>();
				queue.add(0);
				while(!queue.isEmpty()){
					int cur = queue.remove();
					for(Integer e:graph.adj[cur]){
						Edge edge = graph.edges.get(e);
						if(a[edge.to] == 0 && edge.cap > edge.flow){
							edgeTo[edge.to] = e;
							a[edge.to] = Math.min(a[cur], edge.cap - edge.flow);
							queue.add(edge.to);
						}
					}
					if(a[t] != 0)	break;
				}
				if(a[t] == 0)	break;
				for(int u = t; u != s; u = graph.edges.get(edgeTo[u]).from){
					graph.edges.get(edgeTo[u]).flow += a[t];
					graph.edges.get(edgeTo[u] ^ 1).flow -= a[t];
				}
				flow += a[t];
			}
			return flow;
		}
	}
	public void solve() throws NumberFormatException, IOException{
		int T = Integer.parseInt(in.readLine());
		while(T-- > 0){
			in.readLine();
			graph = new Graph();
			map = new HashMap<String, Integer>();
			cnt = 1; s = 0;
			int recepN = Integer.parseInt(in.readLine());
			receps = new String[recepN];
			for(int i = 0; i < recepN; i++){
				receps[i] = in.readLine();
				map.put(receps[i], cnt++);
			}
			int deviceN = Integer.parseInt(in.readLine());
			devices = new String[deviceN][2];
			for(int i = 0; i < deviceN; i++){
				devices[i] = in.readLine().split(" ");
				if(!map.containsKey(devices[i][1]))	map.put(devices[i][1], cnt++);
			}
			int adpN = Integer.parseInt(in.readLine());
			adps = new String[adpN][2];
			for(int i = 0; i < adpN; i++){
				adps[i] = in.readLine().split(" ");
				if(!map.containsKey(adps[i][0]))	map.put(adps[i][0], cnt++);
				if(!map.containsKey(adps[i][1]))	map.put(adps[i][1], cnt++);
			}
			t = cnt;
			
			BuildGraph();
			EdmondsKarp a = new EdmondsKarp();
			out.append(deviceN - a.maxflow(graph, s, t) + "\n");
			if(T > 0) out.append("\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		APlugforUNIX a = new APlugforUNIX();
		a.solve();
	}

}
