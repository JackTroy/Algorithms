import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PingPong {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	int TreeArray[], size;
	int lowbit(int x){
		return x & -x;
	}
	int sum(int x){
		int ret = 0;
		while(x > 0){
			ret += TreeArray[x];
			x -= lowbit(x);
		}
		return ret;
	}
	void update(int x, int right){
		while(x <= size){
			TreeArray[x] += right;
			x += lowbit(x);
		}
	}
	
	int n, a[], left[], right[];
	void solve() throws NumberFormatException, IOException{
		int t = Integer.parseInt(in.readLine());
		StringTokenizer tk;
		TreeArray = new int[100001];
		size = 100000;
		while(t-- > 0){
			tk = new StringTokenizer(in.readLine());
			n = Integer.parseInt(tk.nextToken());
			a = new int[n + 1];
			for(int i = 1; i <= n; i++){
				a[i] = Integer.parseInt(tk.nextToken());
				size = Math.max(size, a[i]);
			}
			left = new int[n + 1];
			right = new int[n + 1];
			Arrays.fill(TreeArray, 0);
			for(int i = 1; i <= n; i++){
				update(a[i], 1);
				left[i] = sum(a[i] - 1);
			}
			Arrays.fill(TreeArray, 0);
			for(int i = n; i >= 1; i--){
				update(a[i], 1);
				right[i] = sum(a[i] - 1);
			}
			Long ans = new Long(0);
			for(int i = 2; i < n; i++){
				ans += left[i] * (n - i - right[i]);
				ans += (i - 1 - left[i]) * right[i];
			}
			out.append(ans + "\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		PingPong a = new PingPong();
		a.solve();
	}
}
