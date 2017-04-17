import java.util.HashMap;
import java.util.Scanner;

public class LIS {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		HashMap<Integer, Integer> indexs = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++){
			a[i] = in.nextInt();
			indexs.put(a[i], i);
		}
		int[] d = new int[100];
		int max = 7, min = 1;
		//no duplicate
		int[] next = new int[100];
		int maxTail = 0, maxLen = 0;
		for(int i = min; i <= max; i++){
			if(!indexs.containsKey(i))	continue;
			int ans = 0;
			next[i] = -1;
			int idxI = indexs.get(i);
			for(int j = 0; j < idxI; j++){
				if(a[j] < i){
					ans = Math.max(ans, d[a[j]]);
					next[i] = a[j];
				}
			}
			if(ans > maxLen)	{maxLen = ans; maxTail = i;}
			d[i] = ans + 1;
		}
		int cur = maxTail;
		while(cur != -1){
			System.out.println(cur);
			cur = next[cur];
		}
		in.close();
	}

}
