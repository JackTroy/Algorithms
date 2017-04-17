import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowerCalculus {
//Power Calculus
	public int n,maxn;
	public int[] a;
	public void solve() throws NumberFormatException, IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		while(true){
			n = Integer.parseInt(in.readLine());	if(n == 0)	break;
			a = new int[1000];
			a[0] = 1;
			maxn = 1;
			for(int maxd = 0;;maxd++){
				if(dfs(0,maxd)){
					out.append(maxd+"\n");
					break;
				}
			}
		}
		System.out.print(out.toString());
	}
	public boolean dfs(int d,int maxd){
		if(a[d] == n)	return true;
		if(d == maxd)	return false;
		if(a[d] * Math.pow(2, maxd - d) < n)	return false;
		for(int i = d; i >= 0; i--){		
			a[d+1] = a[d] + a[i];
			//if(a[d+1] > maxn) maxn = a[d+1];
			if(dfs(d+1,maxd))	return true;
			a[d+1] = a[d] - a[i];
			if(dfs(d+1,maxd))	return true;
		}
		return false;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		PowerCalculus a = new PowerCalculus();
		a.solve();
	}

}
