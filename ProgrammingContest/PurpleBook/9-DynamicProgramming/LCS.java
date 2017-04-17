import java.util.Scanner;

public class LCS {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int na = in.nextInt();
		int[] a = new int[na];
		for(int i = 0; i < na; i++)	a[i] = in.nextInt();
		int nb = in.nextInt();
		int[] b = new int[nb];
		for(int i = 0; i < nb; i++)	b[i] = in.nextInt();
		int[][] d = new int[na + 1][nb + 1];
		for(int i = 1; i <= na; i++)
			for(int j = 1; j <= nb; j++){
				if(a[i - 1] == b[j - 1])	d[i][j] = d[i - 1][j - 1] + 1;
				else{
					d[i][j] = Math.max(d[i][j - 1], d[i - 1][j]);
				}
			}
		System.out.println(d[na][nb]);
		in.close();
	}

}
