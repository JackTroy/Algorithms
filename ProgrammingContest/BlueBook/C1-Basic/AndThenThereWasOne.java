import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AndThenThereWasOne {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	void solve() throws NumberFormatException, IOException{
		for(;;){
			String str = in.readLine();	if(str.equals("0 0 0"))	break;
			StringTokenizer tk = new StringTokenizer(str);
			int n = Integer.parseInt(tk.nextToken());
			int k = Integer.parseInt(tk.nextToken());
			int m = Integer.parseInt(tk.nextToken());
			int ans = 0;
			for(int i = 2; i <= n; i++)	ans = (ans + k) % i;
			ans = (m - k + 1 + ans) % n;
			ans = ans <= 0 ? ans + n : ans;
			out.append(ans + "\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		AndThenThereWasOne a = new AndThenThereWasOne();
		a.solve();
	}

}
