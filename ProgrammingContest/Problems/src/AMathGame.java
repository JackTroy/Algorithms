import java.util.Arrays;
import java.util.Scanner;
//acdream a math game 1726 , not solved ,on hold ,to do
public class AMathGame {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNextInt()){
			int n = in.nextInt();
			int H = in.nextInt();
			int[] a = new int[n];
			for(int i=0;i<n;i++)
				a[i]=in.nextInt();
			Arrays.sort(a);
			for(int i=0;i<n;i++){
				H-=a[i];
				if(H==0){
					System.out.println("Yes");
					break;
				}
				else if(H<0){
					System.out.println("No");
					break;
				}
				if(i==n-1&&H>0)	System.out.println("No");
			}
		}
		in.close();
	}

}
