import java.util.Scanner;

public class MinimumSumLCM {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int rnd = 0;
		while(true){
			int n = in.nextInt();	if(n == 0)	break;
			if(n == 1)	{System.out.println("Case " + (++rnd) + ": " + 2);	continue;}
			int cnt = 0, m = (int) Math.sqrt(n + 0.5);
			Long ans = new Long(0);
			for(int i = 2; i <= m; i++){
				if(n % i == 0){
					cnt++;
					int tmp = 1;
					while(n % i == 0){
						n /= i;
						tmp *= i;
					}
					ans += tmp;
				}
			}
			if(n > 1) {
				cnt++;
				ans += n;
			}
			System.out.print("Case " + (++rnd) + ": ");
			if(cnt == 1)	System.out.println(ans + 1);
			else			System.out.println(ans);
		}
		in.close();
	}
}
