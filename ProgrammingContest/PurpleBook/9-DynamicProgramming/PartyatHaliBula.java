import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PartyatHaliBula{
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int[][] d, f;//point state
	public ArrayList<Integer>[] s;
	
	public void solve() throws NumberFormatException, IOException{
		while(true){
			int n = Integer.parseInt(in.readLine()); if(n == 0)	break;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			String boss = in.readLine();
			map.put(boss, 0);
			s = (ArrayList<Integer>[])new ArrayList[n];
			s[0] = new ArrayList<Integer>();
			for(int i = 1; i < n; i++){
				String[] names = in.readLine().split(" ");
				map.put(names[0], i);
				s[i] = new ArrayList<Integer>();
				int db = map.get(names[1]);
				s[db].add(i);
			}
			d = new int[n][2]; f = new int[n][2];
			int ans = Math.max(dp(0, 1), dp(0, 0));//start from boss;
			boolean unique = false;
			if(d[0][1] > d[0][0] && f[0][1] == 1)	unique = true;
			if(d[0][1] < d[0][0] && f[0][0] == 1)	unique = true;
			if(unique)	out.append(ans + " Yes\n");
			else		out.append(ans + " No\n");
		}
		System.out.print(out.toString());
	}
	public int dp(int u, int k){
		f[u][k] = 1;
		d[u][k] = k;
		for(int i = 0; i < s[u].size(); i++){
			int v = s[u].get(i);
			if(k == 1){
				d[u][k] += dp(v, 0);
				if(f[v][0] == 0)	f[u][k] = 0;
			}
			else{
				d[u][k] += Math.max(dp(v, 0), dp(v, 1));
				if(d[v][0] == d[v][1])						f[u][k] = 0;
				else if(d[v][0] > d[v][1] && f[v][0] == 0)	f[u][k] = 0;
				else if(d[v][0] < d[v][1] && f[v][1] == 0)	f[u][k] = 0;
			}
		}
		return d[u][k];
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		PartyatHaliBula a = new PartyatHaliBula();
		a.solve();
	}

}
