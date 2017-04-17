import java.util.Scanner;

public class SumofDifferentPrimes {
	public static int n, is[];
	public static void isPrime(int n, int[] is){
		//0 as true, 1 as false;
		int m = (int)Math.sqrt(n + 0.5);
		for(int i = 2; i <= m; i++)
			if(is[i] == 0)
				for(int j = i * i; j <= n; j += i)
					is[j] = 1;
	}
	public static void main(String[] args) {
		int[] is = new int[1120 + 5];
		isPrime(1120, is);
		int[][] d = new int[1120 + 5][14 + 5];
		d[0][0] = 1;
		for (int i = 2; i <= 1120; i++){
			if(is[i] == 0){
				for (int k = 14; k >= 1; k--)
					for (int n = i; n <= 1120;n++){
						d[n][k] += d[n - i][k - 1];  
					}
				        
			}
		}
		Scanner in = new Scanner(System.in);
		while(true){
			int n = in.nextInt(), k = in.nextInt();
			if(n == 0 && k == 0)	break;
			System.out.println(d[n][k]);
		}
		in.close();
	}

}
