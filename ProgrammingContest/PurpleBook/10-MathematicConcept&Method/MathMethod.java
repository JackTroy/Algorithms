
public class MathMethod {
	//Gcd
	public static int gcd(int a, int b){
		return b == 0 ? a : gcd(b, a % b);
	}
	
	//expand gcd
	public static int x, y, d;
	public static void exgcd(int a, int b){
		if(b == 0)	{d = a; x = 1; y = 0;}
		else{
			exgcd(b, a % b);
			//x1=y2; y1=x2-(a/b)*y2;
			int t = x;// t = x2
			x = y;
			y = t - a / b * y; 
		}
	}
	
	//prime
	public static int n, is[];
	public static void isPrime(int n, int[] is){
		//0 as true, 1 as false;
		int m = (int)Math.sqrt(n + 0.5);
		for(int i = 2; i <= m; i++)
			if(is[i] == 0)
				for(int j = i * i; j <= n; j += i)
					is[j] = 1;
	}
	//mod for big integer
	public static Long mod(char[] n, int m){
		Long ans = new Long(0);
		for(int i = 0; i < n.length; i++)
			ans = (ans * 10 + n[i] - '0') % m;
		return ans;
	}
	
	//mod of pow number
	public static Long modOfPow(int a, int n, int m){
		if(n == 0)	return (long) 1;
		Long x = modOfPow(a, n / 2, m);
		Long ans = (x * x) % m;
		if(n % 2 == 1)	ans = (ans * a) % m;
		return ans;
	}
	//A upper script m lower script n
	public static Long permutation(int n, int m){
		Long ans = new Long(1);
		for(int i = 0; i < m; i++)
			ans *= new Long(n - i);
		return ans;
	}
	//(n,m) or C upper script m lower script n
	public static Long[] combination(int n){
		Long[] ans = new Long[n + 1];//from 0 to n
		ans[0] = (long) 1;
		for(int i = 1; i <= n; i++){
			ans[i] = ans[i - 1] * (n - i + 1) / i;
		}
		return ans;
	}
	
	//find all combinations
	public static void combinations(int n, int m, int cur, int lastpos, int[] cbs){
		//System.out.println("cur : " + cur);
		if(cur == m){
			for(int i = 0; i < n; i++)	System.out.print(cbs[i]);
			System.out.println();
			return;
		}
		//from 0 to n - 1
		//lastpos 
		for(int i = lastpos + 1; i < n - (m - cur) + 1; i++){
			cbs[i] = 1;
			combinations(n, m, cur + 1, i, cbs);
			cbs[i] = 0;
		}
	}
	
	//compute phi(x)
	public static int eulerPhi(int n){
		int ans = n, m = (int)(Math.sqrt(n) + 0.5);
		for(int i = 2; i <= m; i++){
			if(n % i == 0)	ans = ans / i * (i - 1);
			while(n % i == 0)	n /= i;
		}
		if(n > 1)	ans = ans / n * (n - 1);
		return ans;
	}
	//compute phi(x)  1 < x <= n
	public static int[] phiTable(int n){
		int[] phi = new int[n + 1];
		//for(int i = 2; i <= n; i++)	phi[i] = 0;
		phi[1] = 1;
		for(int i = 2; i <= n; i++){
			if(phi[i] != 0){
				
			}
		}
		return phi;
	}
	
	//compute upper bound with choice = {a,b,c,d}
	//ans = {a1,b1,~}, i as decision level, j as decision
	public static int upperbound(int i, int j, int[] ans, int[] choice){
		int ub = 0;
		for(int level = 0; level < i; level++){
			int tmp = ans[level];
			for(int k = level + 1; k < 5; k++){
				tmp *= choice[k]; 
			}
			ub += tmp;
		}
		int tmp = j + 1;
		for(int k = i + 1; k < 5; k++)
			tmp *= choice[k];
		ub += tmp;
		return ub;
	}
	
	public static void main(String[] args) {
		System.out.println(modOfPow(7, 2, 4));
	}
}
