import java.util.Scanner;
import java.util.Stack;

public class Rails {
//uva 514
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true){
			int n = in.nextInt();
			if(n==0)	break;
			while(true){
				int[] target = new int[n+1];
				target[1] = in.nextInt();
				if(target[1]==0)	break;
				for(int i=2;i<=n;i++)
					target[i]=in.nextInt();
				int A=1,B=1;
				Stack<Integer> s = new Stack<Integer>();
				while(B<=n){
					//if(A==target[B])	{A++;B++;}
					if(!s.isEmpty()&&s.peek()==target[B])	{B++;s.pop();}
					else if(A<=n)	{s.push(A);A++;}
					else			{break;}
				}
				if(B<=n)	System.out.println("No");
				else		System.out.println("Yes");
			}
			System.out.println();
		}
		in.close();
		
	}

}
