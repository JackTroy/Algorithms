import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PageHopping {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public int maxn;
	public int[][] dist;
	public void solve() throws IOException{
		int curT = 0;
		while(true){
			String[] ss = in.readLine().split(" ");	if(ss.length == 2)	break;
			dist = new int[100][100];
			for(int i = 0; i < 100; i++)
				for(int j = 0; j < 100; j++){
					if(i == j)	dist[i][j] = 0;
					else		dist[i][j] = 105;
				}
					
			int cnt = 0;
			int[] map = new int[101];
			Arrays.fill(map, -1);
			for(int i = 0; i < ss.length / 2 - 1; i++){
				int a = Integer.parseInt(ss[2 * i]);
				int b = Integer.parseInt(ss[2 * i + 1]);
				if(map[a] == -1)	map[a] = cnt++;
				if(map[b] == -1)	map[b] = cnt++;
				dist[map[a]][map[b]] = 1;
			}
			maxn = cnt;
			floyd();
			double ans = 0.0;
			for(int i = 0; i < maxn; i++)
				for(int j = 0; j < maxn; j++){
					ans += dist[i][j];
				}
			ans /= maxn * (maxn - 1);
			out.append(String.format("Case %d: average length between pages = %.3f clicks\n", ++curT, ans));
		}
		System.out.print(out.toString());
	}
	public void floyd(){
		for(int k = 0; k < maxn; k++)
			for(int i = 0; i < maxn; i++)
				for(int j = 0; j < maxn; j++){
					if(dist[i][k] < 105 && dist[k][j] < 105){
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
						//System.out.println(dist[i][j]);
					}
				}
	}
	public static void main(String[] args) throws IOException {
		PageHopping a = new PageHopping();
		a.solve();
	}

}
