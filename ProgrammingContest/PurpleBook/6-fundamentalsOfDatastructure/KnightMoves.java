import java.util.LinkedList;
import java.util.Scanner;

public class KnightMoves {
	public class Cdn{
		public int r,c;
		public Cdn(int r,int c){
			this.r = r; this.c = c;
		}
	}
	public Scanner in;
	public int[] rs = {-1,-2,1,2},cs = {-1,-2,1,2};
	public KnightMoves(){}
	public void solve(){
		in = new Scanner(System.in);
		while(in.hasNext()){
			int[][] steps = new int[9][9];
			String s = in.next(),e = in.next();
			int sr = s.charAt(0)-'a'+1,sc = s.charAt(1)-'0';
			int gr = e.charAt(0)-'a'+1,gc = e.charAt(1)-'0';
			LinkedList<Cdn> q = new LinkedList<Cdn>();
			q.add(new Cdn(sr, sc));
			steps[sr][sc] = 1;
			while(!q.isEmpty()){
				Cdn cur = q.removeFirst();
				int curr = cur.r,curc = cur.c;
				if(curr==gr&&curc==gc)	break;
				for(int i=0;i<4;i++){
					int nr = rs[i]+curr;
					int nc1 = cs[(i+1)%4]+curc,nc2 = cs[(i+3)%4]+curc;
					if(legal(nr, nc1)&&steps[nr][nc1]==0){
						steps[nr][nc1] = steps[curr][curc]+1;
						q.add(new Cdn(nr, nc1));
					}
					if(legal(nr, nc2)&&steps[nr][nc2]==0){
						steps[nr][nc2] = steps[curr][curc]+1;
						q.add(new Cdn(nr, nc2));
					}
				}
			}
			//System.out.println(String.format("gr:%d gc:%d", gr,gc));
			System.out.println(String.format("To get from %s to %s takes %d knight moves.", s,e,steps[gr][gc]-1));
		}
	}
	public boolean legal(int r,int c){
		return r>=1&&r<=8&&c>=1&&c<=8;
	}
	public static void main(String[] args) {
		KnightMoves a = new KnightMoves();
		a.solve();

	}

}
