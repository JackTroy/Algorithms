import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GokigenNaname {
//Gokigen Naname UVA - 11694 
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public int[][][] connected;//y x 4 neighbours
	public int[][] required, real;
	public int[][] slashes;
	public static int[][] nco= {
			{0,0,1,1},// \ 
			{1,0,0,1},// /
	};
	public static int[][] neighbour = {
			{1,1},
			{-1,-1},
			{-1,1},
			{1,-1},
	};
	public int n;
	public boolean[][] marked;
	public void solve() throws IOException{
		int rnd = Integer.parseInt(in.readLine());
		while(rnd-->0){
			String s = in.readLine();
			n = Integer.parseInt(s);
			required = new int[n+1][n+1];
			real = new int[n+1][n+1];
			connected = new int[n+1][n+1][4];
			slashes = new int[n][n];
			for(int i = 0; i <= n; i++){
				char[] line = in.readLine().toCharArray();
				for(int j = 0; j <= n; j++){
					if(line[j] == '.')	required[i][j] = -1;
					else				required[i][j] = line[j] - '0';
				}
			}
			dfs(0,0);
		}
		System.out.print(out.toString());
	}
	public boolean dfs(int y, int x){
		//0 - \  1 - /
		//System.out.println(x+" "+y);

		for(int i = 0; i < 2; i++){
			int x1 = x + nco[i][0];
			int y1 = y + nco[i][1];
			int x2 = x + nco[i][2];
			int y2 = y + nco[i][3];
			if(required[y1][x1] != -1 && real[y1][x1] + 1 > required[y1][x1])	continue;
			if(required[y2][x2] != -1 && real[y2][x2] + 1 > required[y2][x2])	continue;
			real[y1][x1]++;	real[y2][x2]++;
			connected[y1][x1][i * 2] = 1;
			connected[y2][x2][i * 2 + 1] = 1;
			slashes[y][x] = i;
			if(x < n - 1){
				if(dfs(y, x + 1))	
					return true;
			}
			else if(x == n - 1 && scan(y)){
				if(y == n-1 && scan(y + 1)){
					if(check())	return true;
				}
				else if(y < n -1)	if(dfs(y + 1, 0))	return true;		
			}					
			real[y1][x1]--;	real[y2][x2]--;
			connected[y1][x1][i * 2] = 0;
			connected[y2][x2][i * 2 + 1] = 0;
		}
		return false;
	}
	public boolean scan(int y){
		for(int i = 0; i <= n; i++){
			if(required[y][i] == -1)	continue;
			if(real[y][i] != required[y][i])	return false;
		}
		return  true;
	}
	public boolean check(){
		marked = new boolean[n+1][n+1];
		/*
		 * System.out.println("here");
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(slashes[i][j] == 0)	System.out.print("\\");
				else					System.out.print("/");		
			}
			System.out.println();
		}
		 */
		for(int i = 0; i < n + 1; i++){
			for(int j = 0; j < n + 1; j++){
				if(!marked[i][j]){
					if(cycle(i,j,-1,-1))	return false;
				}
			}
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(slashes[i][j] == 0)	out.append("\\");
				else					out.append("/");		
			}
			out.append("\n");
		}
		return true;
	}
	public boolean cycle(int y, int x, int ly, int lx){
		marked[y][x] = true;
		for(int i = 0; i < 4; i++){
			if(connected[y][x][i] == 0)	continue;
			int ny = y + neighbour[i][1];
			int nx = x + neighbour[i][0];
			if(nx == lx && ny == ly)	continue;
			if(marked[ny][nx])	return true;
			else{
				if(cycle(ny, nx, y ,x))	return true;
			}
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		GokigenNaname a = new GokigenNaname();
		a.solve();
	}
}
