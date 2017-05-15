import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Frequentvalues {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	void solve() throws NumberFormatException, IOException{
		for(;;){
			String str = in.readLine();	if(str.equals("0"))	break;
			StringTokenizer tk = new StringTokenizer(str);
			int n = Integer.parseInt(tk.nextToken()), q = Integer.parseInt(tk.nextToken());
			tk = new StringTokenizer(in.readLine());
			int count = 0, last, a[] = new int[n], groupCount[] = new int[n];
			int groupNumOfA[] = new int[n], groupLeft[] = new int[n], groupRight[] = new int[n];
			//compute first 
			last = Integer.parseInt(tk.nextToken());
			a[0] = last;
			groupCount[count]++;
			groupNumOfA[0] = count;
			groupLeft[count] = 0;
			for(int i = 1; i < n; i++){
				a[i] = Integer.parseInt(tk.nextToken());
				if(a[i] != last){
					count++;
					last = a[i];
					groupRight[count - 1] = i - 1;
					groupLeft[count] = i;
				}
				groupNumOfA[i] = count;
				groupCount[count]++;
			}
			groupRight[count] = n - 1;
			count++;
			int dMax[][] = new int[count][(int)(Math.log(count)/Math.log(2)) + 1];
			for(int i = 0; i < count; i++)	dMax[i][0] = groupCount[i];
			for(int j = 1; (1<<j) <= count; j++)
				for(int i = 0; i + (1<<j) - 1 < count; i++){
					//System.out.println(i + " " + j);
					dMax[i][j] = Math.max(dMax[i][j - 1], dMax[i + (1<<(j - 1))][j - 1]);
				}
					
			
			for(int i = 0; i < q; i++){
				//out.append("this is " + i + "\n");
				tk = new StringTokenizer(in.readLine());
				int queryLeft = Integer.parseInt(tk.nextToken()) - 1;
				int queryRight = Integer.parseInt(tk.nextToken()) - 1;
				int l = groupNumOfA[queryLeft];
				int r = groupNumOfA[queryRight];
				if(l == r)	out.append(queryRight - queryLeft + 1);
				else{
					int ans1 = Math.max(groupRight[l] - queryLeft + 1, queryRight - groupLeft[r] + 1);
					if(l + 1 == r)	out.append(ans1);
					else{
						l++;
						r--;
						int k = 0;
						while((1<<(k + 1) <= r - l + 1))	k++;
						int ans2 = Math.max(dMax[l][k], dMax[r - (1<<k) + 1][k]);
						out.append(Math.max(ans1, ans2));
					}
				}
				out.append("\n");
			}
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		Frequentvalues a = new Frequentvalues();
		a.solve();
	}

}
