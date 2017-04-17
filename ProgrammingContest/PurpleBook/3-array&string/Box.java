import java.util.Arrays;
import java.util.Scanner;
//uva 1587
public class Box {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			Square[] sqs = new Square[6];
			for(int i = 0; i < 6; i++)
				sqs[i] = new Square(in.nextInt(), in.nextInt());
			Arrays.sort(sqs);
			boolean possible = true;
			for(int i = 0; i < 3; i++)
				if(sqs[2 * i].compareTo(sqs[2 * i + 1]) != 0)	possible = false;
			if(!possible){
				System.out.println("IMPOSSIBLE");
				continue;
			}
			Square s0 = sqs[0], s1 = sqs[2], s2 = sqs[4];
			if((s0.h == s1.h) && (s0.w == s2.h) && (s1.w == s2.w))
				System.out.println("POSSIBLE");
			else
				System.out.println("IMPOSSIBLE");
			
		}
		in.close();
	}

}
class Square implements Comparable<Square>{
	public int w, h;// w >= h
	public Square(int a, int b){
		if(a > b) { w = a; h = b; }
		else	{ w = b; h = a; }
	}
	public int compareTo(Square o) {
		if(h < o.h)			return -1;
		else if(h > o.h)	return 1;
		else{
			if(w < o.w)			return -1;
			else if(w > o.w)	return 1;
			else				return 0;
		}
	}
	
}