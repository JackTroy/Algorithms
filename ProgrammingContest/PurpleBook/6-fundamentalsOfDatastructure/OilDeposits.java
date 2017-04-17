import java.util.Scanner;

public class OilDeposits {
	public Scanner in;
	public char[][] grids;
	public int[][] idx;
	public int r,c;
	public OilDeposits(){}
	public void solve(){
		in = new Scanner(System.in);
		while(true){
			r = in.nextInt();c =in.nextInt();
			if(r==0) break;
			grids = new char[r][c];
			idx = new int[r][c];
			for(int i=0;i<r;i++)
				grids[i]= in.next().toCharArray();
			int cnt = 0;
			for(int i=0;i<r;i++)
				for(int j=0;j<c;j++)
					if(idx[i][j]==0&grids[i][j]=='@')
						dfs(i, j,++cnt);
			System.out.println(cnt);
		}
	}
	public void dfs(int i,int j,int id){
		if(i<0||i>=r||j<0||j>=c)			return;
		if(idx[i][j]>0||grids[i][j]!='@')	return;
		idx[i][j] = id;
		for(int dr = -1;dr<=1;dr++)
			for(int dc = -1;dc<=1;dc++)
				if(dr!=0||dc!=0)
					dfs(i+dr, j+dc, id);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OilDeposits o = new OilDeposits();
		o.solve();
	}
}
