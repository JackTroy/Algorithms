import java.util.Scanner;

public class STrees {
//uva 712
	public static char solve(String results,int[] idx,String q){
		int k = 1;
		for(int i=0;i<idx.length;i++){
			char c = q.charAt(idx[i]-1);
			if(c=='0')	k = k*2;
			else		k = k*2+1;
		}
		return results.charAt((int) (k-Math.pow(2, idx.length)));
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int rnd = 0;
		while(true){
			int d = in.nextInt();	if(d==0) break;
			int[] idx = new int[d];
			for(int i=0;i<d;i++){
				String var = in.next();
				idx[i] = Integer.parseInt(var.substring(1, var.length()));
			}
			String results = in.next();
			int qs = in.nextInt();
			System.out.println(String.format("S-Tree #%d:", ++rnd));
			for(int i=0;i<qs;i++){
				String q = in.next();
				System.out.print(solve(results,idx,q));
			}
			System.out.print("\n\n");
		}
		in.close();
	}

}
