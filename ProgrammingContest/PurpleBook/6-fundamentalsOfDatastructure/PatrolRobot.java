import java.util.LinkedList;
import java.util.Scanner;

public class PatrolRobot {
	public class Robo{
		public int r,c,p;
		public Robo(int r,int c,int p){
			this.r = r;this.c = c;this.p = p;
		}
	}
	public int[] nrs = {-1,1,0,0},ncs = {0,0,-1,1};
	public int m,n,k;
	public PatrolRobot(){}
	public void solve(){
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		while(cases-->0){
			m = in.nextInt();n = in.nextInt();k = in.nextInt();
			boolean marked[][][] = new boolean[m+1][n+1][m*n];
			boolean grids[][] = new boolean[m+1][n+1];
			for(int i=1;i<=m;i++)
				for(int j=1;j<=n;j++){
					if(in.nextInt()==0) grids[i][j] = true;
					else				grids[i][j] = false;
				}
			int[][] dist = new int[m+1][n+1];
			LinkedList<Robo> q = new LinkedList<Robo>();
			q.add(new Robo(1, 1, 0));
			while(!q.isEmpty()){
				Robo now = q.removeFirst();
				//System.out.println("current position:"+now.r+" "+now.c);
				if(now.r == m&&now.c == n)	break;
				for(int i=0;i<4;i++){
					int nr = now.r+nrs[i],nc = now.c+ncs[i];
					if(!legal(nr, nc))	continue;
					if(grids[nr][nc]){
						if(marked[nr][nc][0]) continue;
						q.add(new Robo(nr, nc, 0));
						marked[nr][nc][0] = true;
					}
					else{
						if(marked[nr][nc][now.p+1])	continue;
						if(now.p<k)	{q.add(new Robo(nr, nc, now.p+1));marked[nr][nc][now.p+1] = true;}
						else		continue;
					}
					dist[nr][nc] = dist[now.r][now.c] + 1;
				}
			}
			if(dist[m][n]==0)	System.out.println(-1);
			else				System.out.println(dist[m][n]);
		}
		in.close();
	}
	public boolean legal(int r,int c){
		return r>=1&&r<=m&&c>=1&&c<=n;
	}
	public static void main(String[] args) {
		PatrolRobot a = new PatrolRobot();
		a.solve();
	}
}
