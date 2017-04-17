import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Crane {
//Main UVA - 1611
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int n;
	public int a[];
	public int swaps[][];
	public void swap(int s1,int s2){
		for(int i = s1, j = s2; i < s2 ; i++, j++){
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}
	}
	public void solve() throws NumberFormatException, IOException{
		int rnd = Integer.parseInt(in.readLine());
		while(rnd-- > 0){
			n = Integer.parseInt(in.readLine());
			String[] nums = in.readLine().split(" ");
			a = new int[n];
			for(int i = 0; i < n; i++)	a[i] = Integer.parseInt(nums[i]);
			int cnt = 0;
			swaps = new int[2 * n][2];
			for(int i = 0; i < n; i++){
				int pos;
				for(pos = 0; pos < n; pos++)	
					if(a[pos] == i + 1)	break;
				if(i == pos)	continue;
				if((pos - i) * 2 + i <= n){
					swap(i, pos);
					swaps[cnt][0] = i;
					swaps[cnt][1] = 2 * pos - i - 1;
					cnt++;
				}
				else{
					if((pos - i - 1) % 2 == 0){
						swap(i, i + (pos - i + 1) / 2);
						swaps[cnt][0] = i;
						swaps[cnt][1] = pos;
						cnt++;
						pos = (pos + i - 1) / 2;
						swap(i, pos);
						swaps[cnt][0] = i;
						swaps[cnt][1] = 2 * pos - i - 1;
						cnt++;
					}
					else{
						swap(i+1, (pos + 2 + i) / 2);
						swaps[cnt][0] = i + 1;
						swaps[cnt][1] = pos;
						cnt++;
						pos = (pos + i) / 2;
						swap(i, pos);
						swaps[cnt][0] = i;
						swaps[cnt][1] = 2 * pos - i - 1;
						cnt++;
					}
					
				}
			}
			System.out.println(cnt);
			for(int i = 0; i < cnt; i++)
				System.out.print(String.format("%d %d\n", swaps[i][0] + 1, swaps[i][1] + 1));
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Crane a = new Crane();
		a.solve();
	}

}
