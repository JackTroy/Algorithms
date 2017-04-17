import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class UnidirectionalTSP {
	//public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public Scanner in = new Scanner(System.in);
	
	public int r, c;
	public int[][] m, d, next;
	public void solve() throws IOException{
		while(in.hasNext()){	
			r = in.nextInt();	c = in.nextInt();
			m = new int[r][c];
			for(int i = 0; i < r; i++){
				for(int j = 0; j < c; j++){
					m[i][j] = in.nextInt();
				}
			}
			d = new int[r][c];
			next = new int[r][c];
			int ans = Integer.MAX_VALUE, first = 0;
			for(int i = c - 1; i >= 0; i--){
				for(int j = 0; j < r; j++){
					if(i == c - 1)	d[j][i] = m[j][i];
					else{
						int[] nrs = {j, j - 1, j + 1};
						if(j == 0)		nrs[1] = r - 1;
						if(j == r - 1)	nrs[2] = 0;
						Arrays.sort(nrs);
						d[j][i] = Integer.MAX_VALUE;
						for(int k = 0; k < 3; k++){
							int v = d[nrs[k]][i + 1] + m[j][i];
							if(v < d[j][i])	{d[j][i] = v;	next[j][i] = nrs[k];}
						}
					}
					if(i == 0 && d[j][i] < ans)	{ans = d[j][i]; first = j;}
				}
			}
			System.out.print(first + 1);
			int cur = next[first][0];
			for(int i = 1; i < c; i++){
				System.out.print(" " + (cur + 1));
				cur = next[cur][i];
			}
			System.out.print("\n" + d[first][0] + "\n");
		}
	}
	public static void main(String[] args) throws IOException {
		UnidirectionalTSP a = new UnidirectionalTSP();
		a.solve();
	}

}
