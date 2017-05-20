import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GameofSum {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	void solve() throws NumberFormatException, IOException{
		int a[] = new int[101], sum[] = new int[101];
		for(;;){
			int n = Integer.parseInt(in.readLine());	if(n == 0)	break;
			StringTokenizer tk = new StringTokenizer(in.readLine());
			for(int i = 0; i < n; i++){
				a[i + 1] = Integer.parseInt(tk.nextToken());
				sum[i + 1] = sum[i] + a[i + 1];
			}
			
			int d[][] = new int[n + 1][n + 1];
			int f[][] = new int[n + 1][n + 1];
			int g[][] = new int[n + 1][n + 1];
			
			for(int i = 1; i <= n; i++)	d[i][i] = f[i][i] = g[i][i] = a[i];
			for(int l = 1; l < n; l++){
				for(int i = 1; i + l <= n; i++){
					int j = i + l;
					int m = 0;
					m = Math.min(m, f[i + 1][j]);
					m = Math.min(m, g[i][j - 1]);
					d[i][j] = sum[j] - sum[i - 1] - m;
					f[i][j] = Math.min(d[i][j], f[i + 1][j]);
					g[i][j] = Math.min(d[i][j], g[i][j - 1]);
				}
			}
			out.append(2 * d[1][n] - sum[n] + "\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		GameofSum a = new GameofSum();
		a.solve();
	}

}
