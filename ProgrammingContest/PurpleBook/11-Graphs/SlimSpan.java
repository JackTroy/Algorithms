import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class SlimSpan {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public class WeightComparator implements Comparator<Integer>{
		public int compare(Integer o1, Integer o2) {
			if(edgeweight[o1] < edgeweight[o2])			return -1;
			else if(edgeweight[o1] > edgeweight[o2])	return 1;
			return 0;
		}
	}	
	
	public int v, e;
	public int[][] edges;
	public Integer[] edgeindex;
	public int[] edgeweight;
	public void solve() throws NumberFormatException, IOException{
		while(true){
			String[] nm = in.readLine().split(" ");
			v = Integer.parseInt(nm[0]);
			e = Integer.parseInt(nm[1]);
			if(v == 0 && e == 0)	break;
			edges = new int[e][2];
			edgeindex = new Integer[e];
			edgeweight = new int[e];
			for(int i = 0; i < e; i++){
				String[] nums = in.readLine().split(" ");
				int u = Integer.parseInt(nums[0]) - 1;
				int v = Integer.parseInt(nums[1]) - 1;
				int weight = Integer.parseInt(nums[2]);
				edgeindex[i] = i;
				edges[i][0] = u;	edges[i][1] = v;
				edgeweight[i] = weight;
			}
			int ans = iterate();
			System.out.println(ans);
		}
	}
	public static int[] ufs;
	public static int find(int n){
		if(ufs[n] == n)	return n;
		else{
			ufs[n] = find(ufs[n]);
			return ufs[n];
		}
	}
	public int iterate(){
		Arrays.sort(edgeindex, new WeightComparator());
		int min = Integer.MAX_VALUE;
		int l,r;
		boolean flag = false;
		for(l = 0; l + (v - 1) <= e; l++){
			ufs = new int[v];
			for(int i = 0; i < v; i++)	ufs[i] = i;
			int cnt = v;
			for(r = l; r < e; r++){
				int e = edgeindex[r],u = edges[e][0], v = edges[e][1], rootu = find(u), rootv = find(v);
				if(rootu == rootv)	continue;
				ufs[rootu] = rootv;
				cnt--;
				if(cnt == 1){
					if(!flag)	flag = true;
					int tmp = edgeweight[edgeindex[r]] - edgeweight[edgeindex[l]];
					min = Math.min(min, tmp);
					break;
				}
			}
		}
		if(!flag)	return -1;
		else		return min;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		SlimSpan a = new SlimSpan();
		a.solve();
	}

}
