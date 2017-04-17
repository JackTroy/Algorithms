import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class TheMorningafterHalloween {
	public int[][] G;
	public char[][] maze;
	public int cnt,c,r,n;
	public int[] start,end;
	public int[][] id;
	public int[] x,y,moves;
	public int[][][] d;
	public final int[] dx = {-1,0,1,0,0},dy = {0,1,0,-1,0};
	public void solve() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		while(true){
			String line = reader.readLine();  if(line.equals("0 0 0"))	break;
			String[] nums = line.split(" ");
			c = Integer.parseInt(nums[0]);
			r = Integer.parseInt(nums[1]);
			n = Integer.parseInt(nums[2]);
			maze = new char[r][c];
			G = new int[150][5]; 
			for(int i = 0;i<r;i++){
				String s = reader.readLine();
				//System.out.println(s);
				for(int j = 0; j < c;j++)
					maze[i][j] = s.charAt(j);
			}
			start = new int[3];
			end = new int[3];
			cnt = 0;
			id = new int[r][c];
			x = new int[150];
			y = new int[150];
			moves = new int[150];
			d = new int[150][150][150];
			for(int i = 0;i<r;i++){
				for(int j=0;j<c;j++){
					char ch = maze[i][j];
					if(ch=='#')	continue;
					x[cnt] = i; y[cnt] = j; id[i][j] = cnt;
					if(Character.isUpperCase(ch))		end[ch-'A'] = cnt;
					else if(Character.isLowerCase(ch))	start[ch-'a'] = cnt;
					++cnt;
				}
			}
			
			for(int i=0;i<cnt;i++){
				for(int j = 0;j<5;j++){
					int nx = x[i]+dx[j],ny = y[i]+dy[j];
					if(maze[nx][ny]!='#')	G[i][moves[i]++] = id[nx][ny];
				}
			}
			if(n <= 2)	{ moves[cnt] = 1; G[cnt][0] = cnt; start[2] = end[2] = cnt++; }
			if(n <= 1)	{ moves[cnt] = 1; G[cnt][0] = cnt; start[1] = end[1] = cnt++; }
			int ans = bfs();	
			if(ans != -1)	ans--;
			out.append(ans+"\n");
		}
		System.out.print(out.toString());
	}
	public int bfs(){
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(ID(start[0], start[1], start[2]));
		d[start[0]][start[1]][start[2]] = 1;
		while(!q.isEmpty()){
			int v = q.removeFirst();
			// note
			int a = (v>>16)&0xff, b = (v>>8)&0xff, c = v&0xff;
			if(a == end[0] && b == end[1] && c == end[2])	return d[a][b][c];
			for(int i=0;i<moves[a];i++){
				int a2 = G[a][i];
				for(int j = 0;j<moves[b];j++){
					int b2 = G[b][j];
					if(conflict(a, b, a2, b2))	continue;
					for(int k = 0;k<moves[c];k++){
						int c2 = G[c][k];
						if(conflict(a, c, a2, c2)) 	continue;
						if(conflict(c, b, c2, b2))	continue;
						if(d[a2][b2][c2] != 0)	continue;
						d[a2][b2][c2] = d[a][b][c] + 1;
						q.add(ID(a2, b2, c2));
					}
				}
			}
		}
		return -1;
	}
	public int ID(int a,int b,int c){
		// note 
		return (a<<16)|(b<<8)|c;
	}
	public boolean conflict(int a,int b,int a2,int b2){
		// note 
		return a2 == b2 || (a2 == b && b2 == a);
	}
	public static void main(String[] args) throws IOException {
		TheMorningafterHalloween a = new TheMorningafterHalloween();
		a.solve();
	}
}
