import java.util.Scanner;

public class GCDXOR {
	public static void main(String[] args){
		int maxN = 30000000;
		int[] ans = new int[maxN + 5];
		for(int c = 1; c < maxN; c++){
			for(int a = 2 * c; a <= maxN; a += c){
				int b = a - c;
				if((a ^ b) == c)	ans[a]++;
			}
		}
		for(int i = 1; i <= maxN; i++)
			ans[i] += ans[i - 1];
		Scanner in = new Scanner(System.in);
		int T = in.nextInt(), curT = 0;
		while(curT++ < T){
			int n = in.nextInt();
			System.out.println("Case " + curT + ": " + ans[n]);
		}
		in.close();
	}
}
