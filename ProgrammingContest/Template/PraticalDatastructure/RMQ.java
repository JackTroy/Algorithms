
public class RMQ {
	
	public static int n = 1024;
	public static int dMin[][] = new int[n][10], dMax[][] = new int[n][10];
	public static void InitMin(int[] a){
		for(int i = 0; i < a.length; i++)	dMin[i][0] = a[i];
		for(int j = 1; (1<<j) <= a.length; j++)
			for(int i = 0; i + (1<<j) - 1 < a.length; i++)
				dMin[i][j] = Math.min(dMin[i][j - 1], dMin[i + (1<<(j - 1))][j - 1]);
	}
	public static void InitMax(int[] a){
		for(int i = 0; i < a.length; i++)	dMax[i][0] = a[i];
		for(int j = 1; (1<<j) <= a.length; j++)
			for(int i = 0; i + (1<<j) - 1 < a.length; i++)
				dMax[i][j] = Math.max(dMax[i][j - 1], dMax[i + (1<<(j - 1))][j - 1]);
	}
	public static int queryMin(int l, int r){
		int k = 0;
		while((1<<(k + 1) <= r - l + 1))	k++;
		return Math.min(dMin[l][k], dMin[r - (1<<k) + 1][k]);
	}
	public static int queryMax(int l, int r){
		int k = 0;
		while((1<<(k + 1) <= r - l + 1))	k++;
		return Math.max(dMax[l][k], dMax[r - (1<<k) + 1][k]);
	}
	public static void main(String[] args) {
		int[] a = new int[15];
		for(int i = 0; i < 15; i++){
			a[i] = (int) (Math.random() * 100);
			System.out.print(i + ":" + a[i] + " ");
		}
		InitMin(a);
		System.out.println();
		System.out.println(String.format("query min", queryMin(2, 11)));
	}	

}
