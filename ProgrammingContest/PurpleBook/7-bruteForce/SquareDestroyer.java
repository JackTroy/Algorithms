import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SquareDestroyer {
//Square Destroyer UVA - 1603 
	public int n,ans,s;
	public int matches[],contain[][];
	public int[] fullsize = new int[53],size = new int[53];
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	public void solve() throws NumberFormatException, IOException{
		int rnd = Integer.parseInt(in.readLine());
		while(rnd-->0){
			init();
			/*
			 * for(int maxd = 1; maxd < 60; maxd++){
				if(dfs(0,maxd)){
					out.append(maxd+"\n");
					break;
				}
			}
			 */
			ans = 60;
			dfs(0);
			out.append(ans+"\n");
			ans = 60;
		}
		System.out.print(out.toString());
	}
	public void init() throws NumberFormatException, IOException{
		matches = new int[61];
		contain = new int[53][61];
		fullsize = new int[53];size = new int[53];
		n = Integer.parseInt(in.readLine());
		String nums[] = in.readLine().split(" ");
		for(int i = 1; i <= 2*n*(n+1); i++)	matches[i] = 1;
		int mn = Integer.parseInt(nums[0]);
		for(int i = 0; i < mn; i++)
			matches[Integer.parseInt(nums[i+1])] = 0;
		s = 0;
		for(int i = 1; i <= n; i++){
			for(int y = 0; y <= n - i; y++)
				for(int x = 0; x <= n - i; x++){
					//size[s] = 0;
					fullsize[s] = 4 * i;
					for(int j = 0; j < i; j++){
						int up = (1 + 2 * n) * y + 1 + x + j;
						int left = n + (1 + 2 * n) * y + x + 1 + (2 * n + 1) * j;
						int right = left + i;
						int down = up + (2 * n + 1) * i;
						contain[s][up] = 1;
						contain[s][left] = 1;
						contain[s][right] = 1;
						contain[s][down] = 1;
						size[s] += matches[up] + matches[left] + matches[right] + matches[down];
						//System.out.println(s);
						//System.out.println("up"+up+" left"+left+" right"+right+" down"+down);
					}
					s++;
				}
		}
		//System.out.println("finish");
	}
	public void dfs(int dep){
		if(dep>=ans)	return;
		int k = allBroken();
		if(k == -1)	{
			ans = dep;
			return;
		}
		
		for(int i = 1; i <= 2*n*(n+1); i++){
			if(contain[k][i] == 1){
				//System.out.println("i"+i+" k"+k);
				for(int j = 0; j < s; j++)
					if(contain[j][i] == 1)	size[j]--;
				dfs(dep+1);
				for(int j = 0; j < s; j++)
					if(contain[j][i] == 1)	size[j]++;
			}
		}
	}
	public int allBroken(){
		for(int i = 0; i < s; i++)
			if(size[i] == fullsize[i])
				return i;
		return -1;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		SquareDestroyer a = new SquareDestroyer();
		a.solve();
	}

}
