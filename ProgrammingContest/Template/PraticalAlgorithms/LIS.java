import java.util.Arrays;

public class LIS {
	public static int lowerBound(int a[], int key, int low, int hi){
		int l = low, r = hi, m;
		while(l < r){
			m = (l + r) / 2;
			if(key <= a[m]) r = m;
			else			l = m + 1;
		}
		return l;
	}
	public static void main(String[] args) {
		int a[] = {-2,1,6,2,3,7,5};
		int d[] = new int[a.length];
		int g[] = new int[a.length + 1], max = 0;
		Arrays.fill(g, Integer.MAX_VALUE);
		g[0] = 0;
		for(int i = 0; i < a.length; i++){
			//the reason you can use binary search is 
			//elements in b are all different
			int k = lowerBound(g, a[i], 1, a.length); 
			g[k] = a[i];
			d[i] = k;
			if(k > max) max = k;
			System.out.println(k);
		}
		System.out.println(max);
	}

}
