import java.io.IOException;
import java.util.Scanner;

public class JustFinishitup {
//Just Finish it up UVA - 11093 
	
	public static int[] p;
	public static int[] q;
	public static int n;
	public static int solve(int start){
		int pl = p[start] - q[start];
		for(int i = (start + 1) % n; i != start; i = (i + 1) % n){
			if(pl < 0)	return i;
			pl += p[i] - q[i];
		}
		if(pl < 0)	return -1;
		return start;
	}
	public static int compute(){
		int start = 0;
		while(true){
			int finish = solve(start);
			if(finish < start)	return -1;
			if(finish == start)	return start;
			start = finish;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int rnd = in.nextInt();
		for(int curRnd = 1; curRnd <= rnd; curRnd++){
			n = in.nextInt();
			p = new int[n]; q = new int[n];
			for(int i = 0; i < n; i++)	p[i] = in.nextInt();
			for(int i = 0; i < n; i++)	q[i] = in.nextInt();
			int ans = compute();
			if(ans >= 0) 	out.append(String.format("Case %d: Possible from station %d\n", curRnd, ans + 1));
			else			out.append(String.format("Case %d: Not possible\n", curRnd));
		}
		in.close();
		System.out.print(out.toString());
	}

}
