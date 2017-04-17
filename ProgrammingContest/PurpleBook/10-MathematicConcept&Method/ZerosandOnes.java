import java.util.Scanner;

public class ZerosandOnes {
	public static Long modOfPow(int a, int n, int m){
		if(n == 0)	return (long) 1;
		Long x = modOfPow(a, n / 2, m);
		Long ans = (x * x) % m;
		if(n % 2 == 1)	ans = (ans * a) % m;
		return ans;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt(), curT = 0;
		while(curT++ < T){
			int n = in.nextInt(), k = in.nextInt();
			if((n & 1) == 1 || k == 0) {
				System.out.println(String.format("Case %d: %d", curT, 0));
				continue;
			}
			Long[][][] d = new Long[n + 1][n + 1][k];
			for(int i = 0; i <= n; i++)
				for(int j = 0; j <= i; j++)
					for(int r = 0; r < k; r++)
						d[i][j][r] = new Long(0);
			d[0][0][0] = (long) 1;
			
			for(int i = 0; i < n; i++)
				for(int j = 0; j <= i; j++)
					for(int r = 0; r < k; r++)
						if(d[i][j][r] > 0){
							if(i + 1 != n)	d[i + 1][j][r] += d[i][j][r];
							d[i + 1][j + 1][(r + modOfPow(2, i, k).intValue()) % k] += d[i][j][r];
						}
			System.out.println(String.format("Case %d: %d", curT, d[n][n/2][0]));
		}
		in.close();
	}

}
