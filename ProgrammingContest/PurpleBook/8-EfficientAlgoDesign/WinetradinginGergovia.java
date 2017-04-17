import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WinetradinginGergovia {
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public void solve() throws NumberFormatException, IOException{
		while(true){
			int n = Integer.parseInt(in.readLine()); if(n == 0)	break;
			long ans = 0, a = 0, last = 0;
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int i = 0; i < n; i++){
				a = Integer.parseInt(st.nextToken());
				last += a;
				ans += Math.abs(last);
			}
			out.append(ans+"\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		WinetradinginGergovia a = new WinetradinginGergovia();
		a.solve();
	}

}
