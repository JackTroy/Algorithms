import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EditingaBook {
//Editing a Book UVA - 11212
	public int[] a = new int[9];
	public int n;
	public void solve() throws IOException{
		int rnd = 0;
		StringBuilder out = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			n = Integer.parseInt(in.readLine());	if(n == 0)	break;
			Arrays.fill(a, -1);
			String[] nums = in.readLine().split(" ");
			for(int i = 0; i < n; i++)	a[i] = Integer.parseInt(nums[i]);
			int ans = 0;
			if(!isSorted()){
				ans = 8;
				for(int d = 1; d < 8; d++)
					if(dfs(0,d)){
						ans = d;	
						break;
					}
			}
			out.append(String.format("Case %d: %d\n", ++rnd,ans));
		}
		System.out.print(out.toString());
	}
	public boolean dfs(int d,int maxd){
		//if(d >= maxd) return false;
		if(d*3 + h() > maxd*3)	return false;
		if(isSorted())	return true;
		
		int[] b = new int[9];
		int[] olda = new int[9];
		olda = a.clone();
		for(int i = 0; i < n; i++)
			for(int j = i; j < n; j++) {
				int cnt = 0;
			    for(int k = 0; k < n; k++)
			       if(k < i || k > j) b[cnt++] = a[k];
			    for(int k = 0; k <= cnt; k++) {
			    	int cnt2 = 0;
			    	for(int p = 0; p < k; p++) a[cnt2++] = b[p];
			    	for(int p = i; p <= j; p++) a[cnt2++] = olda[p];
			    	for(int p = k; p < cnt; p++) a[cnt2++] = b[p];

			    	if(dfs(d+1, maxd)) return true;
			    	a = olda.clone();
			    }
		   }
		return false;		
	}
	public int h(){
		int cnt = 0;
		for(int i = 0; i < n-1; i++){
			if(a[i] + 1 != a[i+1])	cnt++;
		}
		if(a[n-1] != n)	cnt++;
		return cnt;
	}
	public boolean isSorted(){
		for(int i = 0; i < n; i++)
			if(a[i] != i + 1)
				return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		EditingaBook a = new EditingaBook();
		a.solve();
	}

}
