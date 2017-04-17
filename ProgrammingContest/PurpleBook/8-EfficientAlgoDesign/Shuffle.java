import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shuffle {
//Shuffle UVA - 12174
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int s, n;
	public int[] list;
	public boolean[] ok;
	public void solve() throws IOException{
		int rnd = Integer.parseInt(in.readLine());
		while(rnd-- > 0){
			String[] sn = in.readLine().split(" ");
			s = Integer.parseInt(sn[0]); n = Integer.parseInt(sn[1]);
			list = new int[n];
			String[] nums = in.readLine().split(" ");
			for(int i = 0; i < n; i++)	list[i] = Integer.parseInt(nums[i]);
			int ans = check();
			out.append(ans+"\n");
		}
		System.out.print(out.toString());
	}
	public int check(){
		int[] cnt = new int[s];
		ok = new boolean[n + s - 1];
		int tot = 0;
		for(int i = -s + 1; i < n; i++){
			int lastStart = i - 1;
			int end = i + s - 1;
			if(end < n && cnt[list[end] - 1]++ == 0)				tot++;
			if(lastStart >= 0 && --cnt[list[lastStart] - 1] == 0)	tot--;
			if(tot == s) 							ok[i + s - 1] = true;
			else if(i < 0 && end > n - 1 && tot == n)ok[i + s - 1] = true;
			else if(i < 0 && s + i == tot)			ok[i + s - 1] = true;
			else if(end > n - 1 && tot == n - i) 	ok[i + s - 1] = true;
		}
		int ans = 0;
		for(int start = - s + 1; start <= 0; start++){
			int i;
			for(i = start; i < n; i += s){
				if(!ok[i + s - 1])	break;
			}
			if(i >= n)	ans++;
		}
		return ans;
	}
	public boolean judge(int start){
		int end = start + s;
		if(start < 0)	start = 0;
		if(end > n)		end = n;
		boolean[] a = new boolean[s];
		for(int i = start; i < end; i++){
			if(a[list[i] - 1])	return false;
			else			a[list[i] - 1] = true;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		Shuffle a = new Shuffle();
		a.solve();
	}

}
