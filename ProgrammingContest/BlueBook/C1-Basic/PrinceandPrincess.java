import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			for(int i = 0; i < p + 1; i++)	a[Integer.parseInt(tokenizer.nextToken())] = i;
			str = in.readLine();
			tokenizer = new StringTokenizer(str);
			for(int i = 0; i < q + 1; i++){
				int idx = a[Integer.parseInt(tokenizer.nextToken())];
				if(idx != 0)	b.add(idx);
			}
				
			int d[] = new int[b.size()], g[] = new int[b.size() + 1], max = 0;
			d[0] = 1;
			Arrays.fill(g, Integer.MAX_VALUE);
			g[0] = 0;
			for(int i = 0; i < b.size(); i++){
				/*
				 * int tmp = 0;
				for(int j = 0; j < i; j++)
					if(b.get(j) < b.get(i))
						tmp = Math.max(tmp, d[j]);
				d[i] = tmp + 1;
				max = Math.max(max, d[i]);
				 */
				int k = Arrays.binarySearch(g, 1, b.size() + 1, b.get(i));
				if(k < 0) g[-k - 1] = b.get(i);
				max = Math.max(max, -k);
			}
			out.append(String.format("Case %d: %d", curT, max));
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		PrinceandPrincess a = new PrinceandPrincess();
		a.solve();
	}
}
