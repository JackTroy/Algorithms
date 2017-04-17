import java.util.Scanner;

public class ColossalFibonacciNumbers {
	public static Long modOfPow(Long a, Long n, int m){
		if(n == 0)	return (long) 1;
		Long x = modOfPow(a, n / 2, m);
		Long ans = (x * x) % m;
		if(n % 2 == 1)	ans = (ans * a) % m;
		return ans;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int rnd = in.nextInt();
		int maxn = 1000 + 5;
		int[][] f = new int[maxn][maxn * 6];
		int[] c = new int[maxn];
		for(int n = 2; n <= 1000; n++){
			f[n][0] = 1; f[n][1] = 1;
			//1 1 2 3 5 ```
			int i = 2;
			while(true){
				f[n][i] = (f[n][i - 1] + f[n][i - 2]) % n;
				if(f[n][i] == 1 && f[n][i - 1] == 1){
					c[n] = i - 1;
					break;
				}
				i++;
			}
		}
		while(rnd-- > 0){
			Long a = in.nextLong(), b = in.nextLong();
			int n = in.nextInt();
			int cycle = c[n];
			if(a == 0 || n == 1)	{System.out.println(0);	continue;}
			Long ans = modOfPow(a, b, cycle);
			if(ans == 0)	System.out.println(f[n][cycle - 1]);
			else			System.out.println(f[n][(int) (ans - 1)]);
			//System.out.println(f[n][(int) ans]);
		}
		in.close();
	}

}
