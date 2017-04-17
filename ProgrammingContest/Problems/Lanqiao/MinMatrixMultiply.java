import java.util.Scanner;

public class MinMatrixMultiply {
	
	public static int n, m[][];
	public static long d[][];
	public static long dp(int i, int j){
		if(i == j)	return (long) 0;
		if(d[i][j] > 0)	return d[i][j];
		long ans = Long.MAX_VALUE;
		for(int k = i; k < j; k++){
			long tmp = dp(i, k) + dp(k + 1, j) + m[i][0] * m[k][1] * m[j][1];
			ans = Math.min(ans, tmp);
		}
		return ans;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			n = in.nextInt();
			m = new int[n][2];
			d = new long[n][n];
			int a = in.nextInt(), b;
			for(int i = 0; i < n; i++){
				b = in.nextInt();
				m[i][0] = a;
				m[i][1] = b;
				a = b;
			}
			long ans = dp(0, n - 1);
			System.out.println(ans);
		}
		in.close();
	}

}
