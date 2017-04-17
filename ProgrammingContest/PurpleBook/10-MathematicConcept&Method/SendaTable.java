import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SendaTable {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public void solve() throws IOException{
		int[] ans = new int[50000 + 5], phi = new int[50000 + 5];
		int N = 50000;
		for(int i = 2; i <= N; i++){
			if(phi[i] == 0)
				for(int j = i; j <= N; j += i){
					if(phi[j] == 0)	phi[j] = j;
					phi[j] = phi[j] / i * (i - 1);
				}
		}
		ans[1] = 1;
		ans[2] = phi[2];
		for(int i = 3; i <= N; i++){
			ans[i] = ans[i - 1] + phi[i];
		}
		while(true){
			int n = Integer.parseInt(in.readLine());	if(n == 0)	break;
			if(n > 1)	out.append(ans[n] * 2 + 1 + "\n");
			else		out.append(1 + "\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws IOException {
		SendaTable a = new SendaTable();
		a.solve();
	}

}
