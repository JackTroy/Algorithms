import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Chooseanddivide {
	
	public static ArrayList<Integer> primes;
	public static void main(String[] args) {
		int maxn = 10000, m = (int) Math.sqrt(maxn + 0.5);
		int[] is = new int[maxn + 5];
		primes = new ArrayList<Integer>();
		for(int i = 2; i < m; i++){
			if(is[i] == 0){
				for(int j = i * i; j <= maxn; j += i){
					is[j] = 1;
				}
			}
		}
		for(int i = 2; i <= maxn; i++)
			if(is[i] == 0)
				primes.add(i);
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int p = in.nextInt(), q = in.nextInt(), r = in.nextInt(), s = in.nextInt();
			int[] e = new int[primes.size()];
			factorial(e, p, 1);
			factorial(e, q, -1);
			factorial(e, p - q, -1);
			factorial(e, r, 1);
			factorial(e, s, -1);
			factorial(e, r - s, -1);
			double ans = 1;
			for(int i = 0; i < primes.size(); i++){
				ans *= Math.pow(primes.get(i), e[i]);
			}
			System.out.println(ans);
		}
		in.close();
	}
	public static void factorial(int[] e, int d, int flag){
		for(int i = 1; i <= d; i++)
			calculate(e, i, flag);
	}
	public static void calculate(int[] e, int n, int flag){
		for(int i = 0; i < primes.size(); i++){
			int prime = primes.get(i);
			while(n % prime == 0){
				e[i] += flag;
				n /= prime;
			}
			if(n == 1)	break;
		}
	}
}
