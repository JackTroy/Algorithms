import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ASpyintheMetro {
//A Spy in the Metro UVA - 1025
	
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int n, t, m1, m2;
	public int[] tc, m1t, m2t;
	public int[][] dp;
	public int[][][] hasTrain; // time station direction
	public static final int INF = 1000000000;
	public void solve() throws NumberFormatException, IOException{
		int rnd = 0;
		while(true){
			n = Integer.parseInt(in.readLine()); if(n == 0)	break;
			t = Integer.parseInt(in.readLine());
			String[] nums;
			
			tc = new int[n];
			nums = in.readLine().split(" ");
			for(int i = 1; i < n; i++)	tc[i] = Integer.parseInt(nums[i - 1]);
			
			m1 = Integer.parseInt(in.readLine());
			m1t = new int[m1];
			nums = in.readLine().split(" ");
			for(int i = 0; i < m1; i++)	m1t[i] = Integer.parseInt(nums[i]);
			
			m2 = Integer.parseInt(in.readLine());
			m2t = new int[m2];
			nums = in.readLine().split(" ");
			for(int i = 0; i < m2; i++)	m2t[i] = Integer.parseInt(nums[i]);
			computeTrain();
			compute();
			System.out.print(String.format("Case Number %d: ", ++rnd));
			if(dp[0][1] >= INF)	System.out.print("impossible\n");
			else				System.out.print(dp[0][1] + "\n");
		}
	}
	public void compute(){
		dp = new int[t + 1][n + 1];
		for(int i = 1; i < n; i++)	dp[t][i] = INF;
		dp[t][n] = 0;
		
		for(int i = t - 1; i >= 0; i--){
			for(int j = 1; j <= n; j++){
				dp[i][j] = dp[i + 1][j] + 1;
				if(j < n && hasTrain[i][j][0] == 1 && i + tc[j] <= t)
					dp[i][j] = Math.min(dp[i][j], dp[i + tc[j]][j + 1]);
				if(j > 1 && hasTrain[i][j][1] == 1 && i + tc[j - 1] <= t)
					dp[i][j] = Math.min(dp[i][j], dp[i + tc[j - 1]][j - 1]);
			}
		}
	}
	public void computeTrain(){
		hasTrain = new int[t + 1][n + 1][2]; // time station direction
		for(int j = 0; j < m1; j++){
			int acT = m1t[j];
			for(int i = 1; i <= n; i++){
				if(acT <= t)	hasTrain[acT][i][0] = 1;
				if(i == n) break;
				acT += tc[i];
			}
		}
		for(int j = 0; j < m2; j++){
			int acT = m2t[j];
			for(int i = n - 1; i >= 1; i--){
				if(acT <= t)	hasTrain[acT][i + 1][1] = 1;
				acT += tc[i];
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		ASpyintheMetro a = new ASpyintheMetro();
		a.solve();
	}

}
