import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HighestPriceinSupplyChain {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	int n, root;
	double p, r;
	ArrayList<Integer>[] kids;
	int[] depth;
	
	public void solve() throws IOException{
		while(true){
			String str = in.readLine();	if(str == null)	break;
			StringTokenizer tk = new StringTokenizer(str);
			n = Integer.parseInt(tk.nextToken());
			p = Double.parseDouble(tk.nextToken());
			r = Double.parseDouble(tk.nextToken());
			kids = (ArrayList<Integer>[]) new ArrayList[n];
			for(int i = 0; i < n; i++)	kids[i] = new ArrayList<Integer>();
			tk = new StringTokenizer(in.readLine());
			for(int i = 0; i < n; i++){
				int sup = Integer.parseInt(tk.nextToken());
				if(sup == -1)	root = i;
				else{
					kids[sup].add(i);
				}
			}
			depth = new int[n];
			dfs(root, 0);
			int cnt = 0, maxdep = 0;
			for(int i = 0; i < n; i++){
				if(depth[i] > maxdep){
					maxdep = depth[i];
					cnt = 1;
				}
				else if(depth[i] == maxdep) cnt++;
			}
			out.append(String.format("%.2f %d\n",p * Math.pow(r / 100.0 + 1, maxdep), cnt));
		}
		System.out.print(out.toString());
	}
	public void dfs(int u, int dep){
		depth[u] = dep;
		for(int next:kids[u])
			dfs(next, dep + 1);
	}
	public static void main(String[] args) throws IOException {
		HighestPriceinSupplyChain a = new HighestPriceinSupplyChain();
		a.solve();
	}

}
