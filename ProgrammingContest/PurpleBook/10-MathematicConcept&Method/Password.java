import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Password {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public int[] choice, ans;
	
	public void solve() throws NumberFormatException, IOException{
		int T = Integer.parseInt(in.readLine());
		while(T-- > 0){
			int k = Integer.parseInt(in.readLine());
			char[][] a = new char[6][5],b = new char[6][5];
			for(int i = 0; i < 6; i++)
				a[i] = in.readLine().toCharArray();
			for(int i = 0; i < 6; i++)
				b[i] = in.readLine().toCharArray();
			
			ArrayList<Integer>[] chars = (ArrayList<Integer>[]) new ArrayList[5];
			for(int i = 0; i < 5; i++){
				chars[i] = new ArrayList<Integer>();
				int[] cnt = new int[26];
				for(int j = 0; j < 6; j++){
					cnt[a[j][i] - 'A']++;
				}
				for(int j = 0; j < 6; j++){
					if(cnt[b[j][i] - 'A'] >= 1 && !chars[i].contains(b[j][i] - 'A'))	chars[i].add(b[j][i] - 'A');
				}
				Collections.sort(chars[i]);
			}
			
			choice = new int[5];
			int maxK = 1;
			for(int i = 0; i < 5; i++)	{ choice[i] = chars[i].size(); maxK *= choice[i]; }
			if(maxK < k)	{ out.append("NO\n");	continue;}
			ans = new int[5];
			for(int i = 0; i < 5; i++){
				//if(i == 4)	System.out.println("final");
				for(int j = 0; j < choice[i]; j++){
					int ub = upperbound(i,j);
					if(k <= ub){ 
						ans[i] = j;	
						break; 
					}
				}
			}
			for(int i = 0; i < 5; i++)	out.append((char)(chars[i].get(ans[i]) + 'A'));
			out.append('\n');
		}
		System.out.print(out.toString());
	}
	public int upperbound(int i, int j){
		int ub = 0;
		for(int level = 0; level < i; level++){
			int tmp = ans[level];
			for(int k = level + 1; k < 5; k++){
				tmp *= choice[k]; 
			}
			ub += tmp;
		}
		int tmp = j + 1;
		for(int k = i + 1; k < 5; k++)
			tmp *= choice[k];
		ub += tmp;
		return ub;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Password a = new Password();
		a.solve();
	}

}
