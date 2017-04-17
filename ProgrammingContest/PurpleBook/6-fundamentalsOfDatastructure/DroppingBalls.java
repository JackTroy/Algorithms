import java.util.Scanner;

public class DroppingBalls {
//uva 679
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int c = in.nextInt();
		for(int i=0;i<c;i++){
			int d = in.nextInt(),no = in.nextInt();
			int k=1;
			for(int cd=0;cd<d-1;cd++){
				if(no%2==1)	{k = 2*k;no=(no+1)/2;}
				else		{k = 2*k+1;no=no/2;}
			}
			System.out.println(k);
		}
		in.nextInt();
		in.close();
	}

}
