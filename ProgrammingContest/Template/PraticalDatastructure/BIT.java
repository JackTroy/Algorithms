
public class BIT {
	
	public static int n, c[];
	public static int lowbit(int x){
		return x & -x;
	}
	public static int sum(int x){
		int ret = 0;
		while(x > 0){
			ret += c[x];
			x -= lowbit(x);
		}
		return ret;
	}
	public static void add(int x, int d){
		while(x <= n){
			c[x] += d;
			x += lowbit(x);
		}
	}
	public static void main(String[] args) {
		n = 27;
		c = new int[n + 1];
		for(int i = 1; i < n + 1; i++){
			int a = (int) (Math.random() * 100);
			System.out.print(a + " ");
			add(i, a);
		}
	}
}
