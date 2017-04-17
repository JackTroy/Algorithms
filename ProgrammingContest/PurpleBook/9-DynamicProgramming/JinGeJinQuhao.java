import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JinGeJinQuhao {
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();

	public void solve() throws IOException{
		int rnd = Integer.parseInt(in.readLine()), acRnd = 0;
		while(acRnd++ < rnd){
			int n, t;
			String[] nt = in.readLine().split(" ");
			n = Integer.parseInt(nt[0]);
			t = Integer.parseInt(nt[1]);
			int lens[] = new int[n + 1];
			String[] slens = in.readLine().split(" ");
			for(int i = 1; i <= n; i++)	lens[i] = Integer.parseInt(slens[i - 1]);
			int[][] dn = new int[55][50 * 180 + 5];
			int[][] dt = new int[55][50 * 180 + 5];
			for(int i = n; i >= 1; i--){
				for(int j = 1; j <= t; j++){
					dt[i][j] = dt[i + 1][j];
					dn[i][j] = dn[i + 1][j];
					if(j > lens[i]){
						if(dn[i][j] < dn[i + 1][j - lens[i]] + 1){
							dn[i][j] = dn[i + 1][j - lens[i]] + 1;
							dt[i][j] = dt[i + 1][j - lens[i]] + lens[i];
						}
						else if(dn[i][j] == dn[i + 1][j - lens[i]] + 1){
							dt[i][j] = Math.max(dt[i][j], dt[i + 1][j - lens[i]] + lens[i]);
						}
					}
				}
			}
			System.out.println(String.format("Case %d: %d %d", acRnd, dn[1][t] + 1, dt[1][t] + 678));
		}
	}
	public static void main(String[] args) throws IOException {
		JinGeJinQuhao a = new JinGeJinQuhao();
		a.solve();
	}

}
