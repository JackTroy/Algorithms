import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Teamthemup {
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int n;
	public int[][] G;
	public boolean[] marked, color;
	public int cnt;
	public int[] d;
	public int[][] dp;
	public LinkedList<Integer>[][] a;
	public void solve() throws IOException{
		int rnd = Integer.parseInt(in.readLine()), curRnd = 0;
		while(curRnd++ < rnd){
			in.readLine();
			n = Integer.parseInt(in.readLine());
			G = new int[n + 1][n + 1];
			for(int i = 1; i <= n; i++){
				StringTokenizer st = new StringTokenizer(in.readLine());
				while(true){
					int j = Integer.parseInt(st.nextToken());	if(j == 0)	break;
					G[i][j]++;
				}
			}
			marked = new boolean[n + 1];
			color = new boolean[n + 1];
			boolean ok = false;
			cnt = 0;
			d = new int[n + 1];
			a = (LinkedList<Integer>[][]) new LinkedList[n][2];
			for(int i = 1; i <= n; i++){
				if(!marked[i]){
					a[cnt][0] = new LinkedList<Integer>();
					a[cnt][1] = new LinkedList<Integer>();
					if(!dfs(i))	{ok = true; break;}
					cnt++;
				}	
			}

			if(curRnd != 1)	out.append("\n");
			if(n == 1||ok)	{out.append("No solution\n"); continue;}
			dp = new int[cnt + 1][2 * n + 1];
			dp[0][0 + n] = 1;
			for(int i = 0; i < cnt; i++){
				for(int j = -n; j <= n; j++){
					int tj = j + n;
					if(dp[i][tj] == 1){
						dp[i + 1][tj + d[i]] = 1;
						dp[i + 1][tj - d[i]] = 1;
					}
				}
			}
			for(int ans = 0; ans < n; ans++){
				if(dp[cnt][ans + n] == 1){
					print(ans);
					break;
				}
				if(dp[cnt][-ans + n] == 1){
					print(-ans);
					break;
				}
			}
		}
		System.out.print(out.toString());
	}
	public void print(int ans){
		LinkedList<Integer> t1 = new LinkedList<Integer>(), t2 = new LinkedList<Integer>();
		int cur = ans;
		for(int i = cnt; i > 0; i--){
			if(dp[i - 1][cur + d[i - 1] + n] == 1){
				t1.addAll(a[i - 1][1]);
				t2.addAll(a[i - 1][0]);
				cur += d[i - 1];
			}
			else{
				t1.addAll(a[i - 1][0]);
				t2.addAll(a[i - 1][1]);
				cur -= d[i - 1];
			}
		}
		out.append(t1.size());
		for(Integer i:t1)	out.append(" " + i);
		out.append("\n");
		out.append(t2.size());
		for(Integer i:t2)	out.append(" " + i);
		out.append("\n");
	}
	public boolean dfs(int u){
		if(color[u])	{d[cnt]++;	a[cnt][1].add(u);}
		else			{d[cnt]--;	a[cnt][0].add(u);}
		marked[u] = true;
		for(int v = 1; v <= n; v++){
			if(u == v||(G[u][v] == 1 && G[v][u] == 1))	continue;
			if(marked[v]){
				if(color[u] == color[v])	return false;
				else						continue;
			}
			//notice the color
			color[v] = !color[u];
			if(!dfs(v))		return false;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		Teamthemup	a = new Teamthemup();
		a.solve();
	}

}
