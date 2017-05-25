
public class RandomCombination {
	static int ans = 0, a[] = {2,3,5,8}, b[] = {1,4,6,7};
	static boolean used[];
	public static void dfs(int d, int[] c){
		if(d == 4){
			int num1 = 0, num2 = 0;
			for(int i = 0; i < 4; i++){
				num1 += (a[i] * 10 + c[i]) * (a[i] * 10 + c[i]);
				num2 += (c[i] * 10 + a[i]) * (c[i] * 10 + a[i]);
			}
			if(num1 == num2){
				ans++;
				System.out.println(String.format("%d %d %d %d", c[0], c[1], c[2], c[3]));
			}
			return;
		}
		for(int i = 0; i < 4; i++){
			if(!used[i]){
				c[d] = b[i];
				used[i] = true;
				dfs(d + 1, c);
				used[i] = false;
			}
		}
	}
	public static void main(String[] args){
		used = new boolean[4];
		dfs(0, new int[4]);
		System.out.println(ans);
	}
}
