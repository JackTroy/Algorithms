import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class AllRoadsLeadtoRome {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	int n, k, start = 0, end, happiness[];
	HashMap<String, Integer> map;
	String[] CitiesName;
	
	ArrayList<Integer>[] adj;
	int[] us, vs, costs;
	boolean[] reachable;
	
	boolean[] marked;
	Stack<Integer> bestpath;
	int[] vertexTo;
	int mincost, maxhappines, cities, MincostRoute;
	
	public void solve() throws IOException{
		while(true){
			String line = in.readLine();	if(line == null)	break;
			map = new HashMap<String, Integer>();
			StringTokenizer tk = new StringTokenizer(line);
			n = Integer.parseInt(tk.nextToken());
			k = Integer.parseInt(tk.nextToken());
			CitiesName = new String[n];
			String city = tk.nextToken();
			map.put(city, 0);
			CitiesName[0] = city;
			happiness = new int[n];
			for(int i = 1; i < n; i++){
				tk = new StringTokenizer(in.readLine());
				city = tk.nextToken();
				if(city.equals("ROM"))	end = i;
				map.put(city, i);
				CitiesName[i] = city;
				happiness[i] = Integer.parseInt(tk.nextToken());
			}
			adj = (ArrayList<Integer>[]) new ArrayList[n];
			us = new int[k];
			vs = new int[k];
			costs = new int[k];
			for(int i = 0; i < n; i++)	adj[i] = new ArrayList<Integer>();
			for(int i = 0; i < k; i++){
				tk = new StringTokenizer(in.readLine());
				int c1 = map.get(tk.nextToken());
				int c2 = map.get(tk.nextToken());
				int cost = Integer.parseInt(tk.nextToken());
				adj[c1].add(i);
				adj[c2].add(i);
				vs[i] = c1;
				us[i] = c2;
				costs[i] = cost;
			}
			reachable = new boolean[n];
			dfsDetect(end);
			
			marked = new boolean[n];
			mincost = Integer.MAX_VALUE;
			maxhappines = Integer.MIN_VALUE;
			cities = 0;
			vertexTo = new int[n];
			dfs(0, 0);
			out.append(String.format("%d %d %d %d\n", MincostRoute, 
					mincost, maxhappines, maxhappines / cities));
			out.append(CitiesName[bestpath.pop()]);
			while(!bestpath.isEmpty())	out.append("->" + CitiesName[bestpath.pop()]);
			out.append("\n");
		}
		System.out.print(out.toString());
	}
	public void dfsDetect(int u){
		reachable[u] = true;
		if(u == start)	return;
		for(int edge:adj[u]){
			int v = us[edge] == u ? vs[edge] : us[edge];
			if(!reachable[v]) dfsDetect(v);
		}
	}
	public void dfs(int u, int curCost){
		marked[u] = true;
		if(u == end && curCost <= mincost){
			int acchappiness = 0, acccities = 0;
			Stack<Integer> tmp = new Stack<Integer>();
			for(int s = end; s != start; s = vertexTo[s]){
				tmp.add(s);
				acccities++;
				acchappiness += happiness[s];
			}
			tmp.add(start);
			if(curCost < mincost){
				mincost = curCost;
				MincostRoute = 1;
				maxhappines = acchappiness;
				cities = acccities;
				bestpath = new Stack<Integer>();
				bestpath.addAll(tmp);
			}
			else{
				MincostRoute++;
				if(acchappiness > maxhappines || (acchappiness == maxhappines && acccities < cities)){
					maxhappines = acchappiness;
					cities = acccities;
					bestpath = new Stack<Integer>();
					bestpath.addAll(tmp);
				}
			}
		}
		else{
			for(int edge:adj[u]){
				int v = us[edge] == u ? vs[edge] : us[edge];
				if(reachable[v] && !marked[v] && curCost + costs[edge] <= mincost){
					vertexTo[v] = u;
					dfs(v, curCost + costs[edge]);
				}
			}
		}
		marked[u] = false;
		return;
	}
	public static void main(String[] args) throws IOException {
		AllRoadsLeadtoRome a = new AllRoadsLeadtoRome();
		a.solve();
	}

}
