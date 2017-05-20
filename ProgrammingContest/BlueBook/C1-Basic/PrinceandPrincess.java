import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrinceandPrincess {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	void solve() throws NumberFormatException, IOException{
		int T = Integer.parseInt(in.readLine()), curT = 0;
		while(curT++ < T){
			String str = in.readLine();
			StringTokenizer tokenizer = new StringTokenizer(str);
			int n, p, q;
			n = Integer.parseInt(tokenizer.nextToken());
			p = Integer.parseInt(tokenizer.nextToken());
			q = Integer.parseInt(tokenizer.nextToken());
			
			int[] a = new int[n * n + 1];
			ArrayList<Integer> b = new ArrayList<>();
			str = in.readLine();
			tokenizer = new StringTokenizer(str);
			for(int i = 0; i < p + 1; i++)	a[Integer.parseInt(tokenizer.nextToken())] = i + 1;
			str = in.readLine();
			tokenizer = new StringTokenizer(str);
			for(int i = 0; i < q + 1; i++){
				int idx = a[Integer.parseInt(tokenizer.nextToken())];
				if(idx != 0)	b.add(idx);
			}
				
			int g[] = new int[b.size() + 1], max = 0;
			Arrays.fill(g, Integer.MAX_VALUE);
			g[0] = 0;
			for(int i = 0; i < b.size(); i++){
				//the reason you can use binary search is 
				//elements in b are all different
				int k = lower_bound(g, b.get(i));
				g[k] = b.get(i);
				if(k > max) max = k;
			}
			out.append(String.format("Case %d: %d\n", curT, max));
		}
		System.out.print(out.toString());
	}
	public static int lower_bound(int a[], int key){
		int l = 0, r = a.length - 1, m;
		while(l < r){
			m = (l + r) / 2;
			if(key <= a[m]) r = m;
			else			l = m + 1;
		}
		return l;
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		PrinceandPrincess a = new PrinceandPrincess();
		a.solve();
	}
}
