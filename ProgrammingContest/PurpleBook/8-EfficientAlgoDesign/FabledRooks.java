import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FabledRooks {
//Fabled Rooks UVA - 11134
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public class Rook implements Comparable<Rook>{
		int no, low, high;
		public Rook(int no,int low,int high){
			this.no = no;	this.low = low;	this.high = high;
		}
		public int compareTo(Rook o) {
			if(low < o.low)			return -1;
			else if(low == o.low)	return 0;
			else 					return 1;
		}
		public boolean contain(int n){
			return low <= n && n <= high;
		}
	}
	public class HighComparator implements Comparator<Rook>{

		public int compare(Rook arg0, Rook arg1) {
			if (arg0.high < arg1.high)		return -1;
			else if(arg0.high > arg1.high)	return 1;
			else							return 0;
		}
	}
	public int n;
	public Rook[][] rooks; // for x & y
	public int[][] ans;
	public void solve() throws NumberFormatException, IOException{
		while(true){
			n = Integer.parseInt(in.readLine());	if(n == 0) break;
			rooks = new Rook[2][n];
			ans = new int[n][2];
			for(int i = 0; i < n; i++){
				StringTokenizer st = new StringTokenizer(in.readLine());
				int xl, yl, xr, yr;
				xl = Integer.parseInt(st.nextToken());
				yl = Integer.parseInt(st.nextToken());
				xr = Integer.parseInt(st.nextToken());
				yr = Integer.parseInt(st.nextToken());
				rooks[0][i] = new Rook(i, xl, xr);
				rooks[1][i] = new Rook(i, yl, yr);
			}
			if(check(0) && check(1)){
				for(int i = 0; i < n; i++){
					out.append(String.format("%d %d\n", ans[i][0],ans[i][1]));
				}
			}
			else	out.append("IMPOSSIBLE\n");
		}
		System.out.print(out.toString());
	}
	public boolean check(int d){
		Arrays.sort(rooks[d]);
		int cnt = 0;
		PriorityQueue<Rook> q = new PriorityQueue<Rook>(new HighComparator());
		for(int i = 1; i <= n; i++){
			while(cnt < n && rooks[d][cnt].low <= i)
				q.add(rooks[d][cnt++]);
			if(q.isEmpty())	return false;
			Rook tmp = q.remove();
			if(!tmp.contain(i))	return false;
			else	ans[tmp.no][d] = i;
		}
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		FabledRooks a = new FabledRooks();
		a.solve(); 
	}

}
