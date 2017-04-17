import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class CallingCircles {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public HashMap<String, Integer>	map;
	public int v, e;
	public boolean[][] edge;
	boolean[] vis;
	public HashSet<Integer> set;
	public String[] index2names;
	public void solve() throws IOException{
		int curT = 0;
		while(true){
			String[] ve = in.readLine().split(" ");
			v = Integer.parseInt(ve[0]);
			e = Integer.parseInt(ve[1]);
			if(v == 0 && e == 0)	break;
			map = new HashMap<String, Integer>();
			index2names = new String[v];
			edge = new boolean[v][v];
			for(int i = 0, cnt = 0; i < e; i++){
				String[] names = in.readLine().split(" ");
				int u, v;
				if(map.containsKey(names[0]))	u = map.get(names[0]);
				else{
					map.put(names[0], cnt);
					index2names[cnt] = names[0];
					u = cnt++;
				}
				if(map.containsKey(names[1]))	v = map.get(names[1]);
				else{
					map.put(names[1], cnt);
					index2names[cnt] = names[1];
					v = cnt++;
				}
				edge[u][v] = true;
			}
			for(int i = 0; i < v; i++)	edge[i][i] = true;
			for(int k = 0; k < v; k++)
				for(int i = 0; i < v; i++)
					for(int j = 0; j < v; j++)
						edge[i][j] = edge[i][j] || (edge[i][k] && edge[k][j]);
			vis = new boolean[v];
			if(curT++ != 0)	System.out.println();
			System.out.println(String.format("Calling circles for data set %d:", curT));
			if(e == 0)	continue;
			for(int i = 0; i < v; i++){
				if(!vis[i]){
					set = new HashSet<Integer>();
					set.add(i);
					dfs(i);
					boolean first = true;
					for(Integer e:set){
						if(first)	first =false;
						else		System.out.print(", ");
						System.out.print(index2names[e]);
					}
					System.out.println();
				}
			}
		}
	}
	public void dfs(int u){
		vis[u] = true;
		for(int i = 0; i < v; i++){
			if(i == u || vis[i])	continue;
			if(edge[u][i] && edge[i][u]){
				set.add(i);
				dfs(i);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		CallingCircles a = new CallingCircles();
		a.solve();
	}

}
