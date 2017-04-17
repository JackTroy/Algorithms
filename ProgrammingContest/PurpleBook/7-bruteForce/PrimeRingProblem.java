import java.util.Scanner;

public class PrimeRingProblem {
	public boolean isp[];
	public boolean marked[];
	public StringBuilder out;
	public boolean isPrime(int n){
		for(int i = 2; i * i <= n; i++)
			if(n % i == 0)	return false;
		return true;
	}
	public void dfs(int[] a,int cur,int n){
		if(cur == n && isp[a[cur-1]+a[0]]){
			for(int i = 0;i<n;i++){
				if(i>0)	out.append(' ');
				out.append(a[i]);
			}	
			out.append('\n');
		}
		else{
			for(int i = 2;i<=n;i++){
				if(!marked[i]&&isp[a[cur-1]+i]){
					a[cur] = i;
					marked[i] = true;
					dfs(a, cur+1, n);
					marked[i] = false;
				}
			}
		}
			
	}
	public void solve(){
		isp = new boolean[32];
		for(int i = 2;i <= 31;i++)
			if(isPrime(i))	isp[i] = true;
		Scanner in = new Scanner(System.in);
		out = new StringBuilder();
		int rnd = 0;
		while(in.hasNext()){
			int n = in.nextInt();
			int a[] = new int[n];
			a[0] = 1;
			marked = new boolean[n+1];
			if(rnd>0)	out.append('\n');
			out.append("Case "+(++rnd)+":\n");
			dfs(a,1, n);
		}
		System.out.print(out.toString());
		in.close();
	}
	public static void main(String[] args) {
		PrimeRingProblem a = new PrimeRingProblem();
		a.solve();
	}
}
