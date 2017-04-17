import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class UniqueSnowflakes {
//Unique Snowflakes UVA - 11572 
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();

	public HashSet<Long> check;
	public void solve() throws NumberFormatException, IOException{
		int rnd = Integer.parseInt(in.readLine());
		while(rnd-- > 0){
			check = new HashSet<Long>();
			int n = Integer.parseInt(in.readLine());
			ArrayList<Long> a = new ArrayList<Long>();
			for(int i = 0; i < n; i++)	a.add(Long.parseLong(in.readLine()));
			int maxLen = 0;
			int low = 0, high = 1;
			check.add(a.get(low));
			while(high < n){
				Long cur = a.get(high);
				if(!check.contains(cur)){
					high++;
					check.add(cur);
					if(high - low > maxLen)	maxLen = high - low;
				}
				else{
					check.remove(a.get(low));
					low++;
				}
			}
			out.append(maxLen+"\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		UniqueSnowflakes a = new UniqueSnowflakes();
		a.solve();
	}

}
