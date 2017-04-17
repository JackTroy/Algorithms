import java.util.Scanner;

public class OptimalMatrixChainMultiplication {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] mtSize = new int[n][2];
		for(int i = 0; i < n; i++){
			mtSize[i][0] = in.nextInt();
			mtSize[i][1] = in.nextInt();
		}
		int[][] d = new int[n + 1][n + 1];
		for(int len = 2; len <= n; len++){
			for(int i = 1; i <= n; i++){
				int j = i + len - 1;
				if(j > n)	break;
				int ans = Integer.MAX_VALUE;
				for(int k = i; k < j; k++){
					ans = Math.min(ans, d[i][k] + d[k + 1][j] + mtSize[i - 1][0] * mtSize[k - 1][1] * mtSize[j - 1][1]);
				}
				d[i][j] = ans;
			}
		}
		System.out.println(d[1][n]);
		in.close();
	}

}
