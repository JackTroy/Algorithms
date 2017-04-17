import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Egpyt {
	public int[] ans;
	public int[] v;
	public int maxd;
	public int getFirst(Long a,Long b){
		return (int) (b/a+1);
	}
	public void solve() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		v = new int[100];
		ans = new int[100]; 
		while(true){
			String line = in.readLine(); if(line==null)	break;
			String[] nums = line.split(" ");
			Long a = Long.parseLong(nums[0]);
			Long b = Long.parseLong(nums[1]);
			boolean found = true;
			for(maxd = 1; maxd < 100; maxd++){
				Arrays.fill(ans, -1);
				if(dfs(0, getFirst(a, b), a, b))	{ found = true; break; }
			}
			if(found){}
			else{}
		}
	}
	public boolean dfs(int d, int from, Long a1, Long b1){
		if(d == maxd){
			if(b1 % a1 == 1)	return false;
			v[d] = (int) (b1 / a1);
			if(better(d))	ans = v.clone();
			return true;	
		}
		boolean found = false;
		from = Math.max(from, getFirst(a1, b1));
		for(int i = from; ;i++){
			if(b1 * (maxd + 1 - d) <= i * a1)	break;
			v[d] = i;
			Long b2 = b1 * i;
			Long a2 = a1 * i - b1;
			Long g = gcd(a2,b2);
			if(dfs(d+1, i, a2/g, b2/g))	found = true;
 		}
		return found;
	}
	public boolean better(int d){
		for(int i = d; i>=0; i--)
			if(v[i] != ans[i])
				return ans[i] == -1 || v[i] < ans[i];
		return false;
	}
	public Long gcd(Long a, Long b){
		return b == 0 ? a : gcd(b, a % b);
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Egpyt a = new Egpyt();
		a.solve();
	}

}
