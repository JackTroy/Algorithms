import java.util.Scanner;
//uva 839
public class NotsoMobile {
	public static int weight(Scanner in){
		int wl = in.nextInt(),dl = in.nextInt();
		int wr = in.nextInt(),dr = in.nextInt();
		if(wl==0)	wl = weight(in);
		if(wr==0)	wr = weight(in);
		if(wl==0||wr==0)	return 0;
		if(wl*dl==wr*dr)	return wl+wr;
		else				return 0;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int c = in.nextInt();
		in.nextLine();
		in.nextLine();
		while(c-->0){
			if(weight(in)>0)	System.out.print("YES\n");
			else				System.out.print("NO\n");
			if(c>0)				System.out.print("\n");
			in.nextLine();
		}
		in.close();
	}
}
