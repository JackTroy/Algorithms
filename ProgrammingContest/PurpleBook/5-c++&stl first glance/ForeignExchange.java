import java.util.Arrays;
import java.util.Scanner;

public class ForeignExchange {
//uva 10763
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true){
			int n = in.nextInt();
			if(n==0) break;
			int[] A = new int[n];
			int[] B = new int[n];
			for(int i=0;i<n;i++){
				A[i] = in.nextInt();
				B[i] = in.nextInt();
			}
			Arrays.sort(A);
			Arrays.sort(B);
			int i;
			for(i=0;i<n;i++)
				if(A[i]!=B[i])
					break;
			if(i<n)	System.out.println("NO");
			else	System.out.println("YES");
		}
		in.close();
	}
}
