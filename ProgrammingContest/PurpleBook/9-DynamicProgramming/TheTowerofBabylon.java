import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TheTowerofBabylon {
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int n, ans;
	public int[][] blocks;// n, x-y-z
	public int[][] dp;
	public int dp(int i, int j){
		if(dp[i][j] > 0)	return dp[i][j];
		
		int[] xy1 = getXY(i, j);
		for(int b = 0; b < n; b++){
			for(int side = 0; side < 3; side++){
				int[] xy2 = getXY(b, side);
				if(xy1[0] > xy2[0] && xy1[1] > xy2[1])	dp[i][j] = Math.max(dp[i][j], dp(b, side));
			}
		}
		dp[i][j] +=	blocks[i][j];
		return  dp[i][j];
	}
	public int[] getXY(int n,int side){
		int idx = 0;
		int[] xy = new int[2];
		for(int i = 0; i < 3; i++)
			if(i != side)
				xy[idx++] = blocks[n][i];
		return xy;
	}
	public void solve() throws NumberFormatException, IOException{
		int rnd = 0;
		while(true){
			n = Integer.parseInt(in.readLine());	if(n == 0)	break;
			blocks = new int[n][3];
			for(int i = 0; i < n; i++){
				String[] sides = in.readLine().split(" ");
				blocks[i][0] = Integer.parseInt(sides[0]);
				blocks[i][1] = Integer.parseInt(sides[1]);
				blocks[i][2] = Integer.parseInt(sides[2]);
				Arrays.sort(blocks[i]);
			}
			dp = new int[n][3];
			ans = 0;
			for(int i = 0; i < n; i++){
				for(int j = 0; j < 3; j++){
					ans = Math.max(ans, dp(i, j));
				}
			}
			out.append("Case " + (++rnd) + ": maximum height = " + ans + "\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		TheTowerofBabylon a = new TheTowerofBabylon();
		a.solve();
	}

}
