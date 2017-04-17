import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinPacking {
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int n, len;
	public int[] lens;
	public void solve() throws NumberFormatException, IOException{
		int rnd = Integer.parseInt(in.readLine());
		while(rnd-- > 0){
			in.readLine();
			n = Integer.parseInt(in.readLine());
			len = Integer.parseInt(in.readLine());
			lens = new int[n];
			for(int i = 0; i < n; i++)	lens[i] = Integer.parseInt(in.readLine());
			Arrays.sort(lens);
			out.append(compute()+"\n");
			if(rnd != 0)	out.append("\n");
		}
		System.out.print(out.toString());
	}
	public int compute(){
		int l = 0, h = n - 1, cnt = 0;
		while(l <= h){
			if(lens[h] + lens[l] > len)	h--;
			else						{h--; l++;}
			cnt++;
		}
		return cnt;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BinPacking a = new BinPacking();
		a.solve();
	}

}
