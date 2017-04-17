import java.util.Scanner;

public class TSP {
	public static int n;
	public static int dist[][], d[][];
	public static int dp(int i,int s){
		if(s == 0)	return dist[0][i];
		if(d[i][s] > 0)	return d[i][s];
		int tmp = Integer.MAX_VALUE;
		for(int j = 0; j < n; j++){
			if(i == j) continue;
			if((s & (1 << j)) == 0)	continue;
			int tmp2 = dp(j, s ^ (1 << j)) + dist[i][j];
			tmp = Math.min(tmp, tmp2);
			if(i == 0)	System.out.println(tmp2);
		}
		d[i][s] = tmp;
		return tmp;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		dist = new int[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				dist[i][j] = in.nextInt();
		d = new int[n][1 << n];
		System.out.println(dp(0, ((1 << n) - 1) ^ 1));
		in.close();
	}

}
