import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HeadmastersHeadache {
//Headmaster's Headache UVA - 10817
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int s, m, n;
	public int tc[], ts[], d[][][];
	public final int INF = 6000000;
	public void solve() throws IOException{
		while(true){
			String[] nums = in.readLine().split(" ");	if(nums[0].equals("0"))	break;
			s = Integer.parseInt(nums[0]);
			m = Integer.parseInt(nums[1]);
			n = Integer.parseInt(nums[2]);
			tc = new int[m + n];
			ts = new int[m + n];
			for(int i = 0; i < m + n; i++){
				StringTokenizer st = new StringTokenizer(in.readLine());
				tc[i] = Integer.parseInt(st.nextToken());
				while(st.hasMoreTokens())
					ts[i] |= 1 << (Integer.parseInt(st.nextToken()) - 1);
			}
			d = new int[m + n][1 << s][1 << s];
			int ans = dp(0, (1 << s) - 1, 0, 0);
			out.append(ans + "\n");
		}
		System.out.print(out.toString());
	}
	public int dp(int i, int s0, int s1, int s2){
		if(i == m + n) return s2 == (1 << s) - 1 ? 0 : INF;
		if(d[i][s1][s2] > 0)	return d[i][s1][s2];
		d[i][s1][s2] = INF;
		if(i >= m)	d[i][s1][s2] = dp(i + 1, s0, s1, s2);
		int m0 = ts[i] & s0, m1 = ts[i] & s1;
		int ns0 = s0^m0; int ns1 = (s1 ^ m1) | m0; int ns2 = s2 | m1;
		d[i][s1][s2] = Math.min(d[i][s1][s2], dp(i + 1, ns0, ns1, ns2) + tc[i]);
		return	d[i][s1][s2];
	}
	public static void main(String[] args) throws IOException {
		HeadmastersHeadache a = new HeadmastersHeadache();
		a.solve();
	}
}
