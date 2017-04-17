import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PipelineScheduling {
//Pipeline Scheduling UVA - 690
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int[] rt,jump;
	public int n, units = 5, ans, c;
	public boolean insert(int[] cur,int t){
		for(int i = 0; i < units; i++)
			if((rt[i] & (cur[i] >> t)) != 0)	return false;
		return true;
	}
	public void solve() throws NumberFormatException, IOException{
		while(true){
			n = Integer.parseInt(in.readLine());	if(n == 0)	break;
			rt = new int[5];
			jump = new int[20];
			ans = 10 * n;
			for(int i = 0; i < units; i++){
				String line = in.readLine();
				for(int j = 0; j < n; j++){
					if(line.charAt(j) == 'X')	rt[i] |= 1 << (n - j - 1);
				}
			}
			c = 0;
			for(int i = 1; i <= n; i++){
				if(insert(rt, i))
					jump[c++] = i;
			}
			dfs(1,rt,n);
			out.append(ans+"\n");
		}
		//System.out.println("print something");
		System.out.print(out.toString());
	}
	public void dfs(int d,int[] cur,int sum){
		if(sum + (10 - d) * jump[0] > ans)	return;
		if(d == 10){
			//System.out.println("atleasthere");
			ans = Math.min(ans, sum);
			return;
		}
		for(int i = 0; i < c; i++){
			if(insert(cur, jump[i])){
				int[] tmp = new int[5];
				for(int j = 0; j < units; j++){
					tmp[j] = (cur[j] >> jump[i]) ^ rt[j];
				}
				dfs(d+1, tmp, sum + jump[i]);
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		PipelineScheduling a = new PipelineScheduling();
		a.solve();
	}

}
