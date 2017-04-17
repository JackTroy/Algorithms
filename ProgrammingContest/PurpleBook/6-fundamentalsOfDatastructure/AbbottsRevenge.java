import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
//uva 816
public class AbbottsRevenge {
	public class Node{
		public int r,c;
		public int dir;
		public Node(int r,int c,int dir){
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	public Scanner in;
	public boolean[][][][] hasEdge;
	public int[][][] dist;
	public Node[][][] nodeTo;
	public final String dirs = "NESW";
	public final String turns = "FLR";
	public final int[] dr = {-1,0,1,0};
	public final int[] dc = {0,1,0,-1};
	public int sr,sc,sd,gr,gc;
	public String title;
	public AbbottsRevenge(){}
	public Node walk(Node u,int turn){
		int dir = u.dir;
		//left n-w w-s s-e e-n
		//right n-e e-s s-w w-n
		if(turn==1)	dir = (dir+3) % 4;
		if(turn==2) dir = (dir+1) % 4;
		return new Node(u.r+dr[dir],u.c+dc[dir],dir);
	}
	public int Idir(char c){
		return dirs.indexOf(c);
	}
	public int Iturn(char c){
		return turns.indexOf(c);
	}
	public boolean legal(int r,int c){
		return r >= 1 && r <= 9 && c >= 1 && c <= 9;
	}
	public boolean read(){
		title = in.next();
		if(title.equals("END"))	return false;
		sr = in.nextInt();sc = in.nextInt();
		sd = Idir(in.next().charAt(0));
		gr = in.nextInt();gc = in.nextInt();
		while(true){
			int r = in.nextInt();	if(r==0) break;
			int c = in.nextInt();
			while(true){
				String s = in.next();
				if(s.equals("*"))	break;
				for(int i=1;i<s.length();i++)
					hasEdge[r][c][Idir(s.charAt(0))][Iturn(s.charAt(i))] = true;
			}
		}
		return true;
	}
	public void print(Node u){
		LinkedList<Node> nodes = new LinkedList<Node>();
		while(true){
			nodes.add(u);
			if(dist[u.r][u.c][u.dir]==0)	break;
			u = nodeTo[u.r][u.c][u.dir];
		}
		nodes.add(new Node(sr, sc, sd));
		
		int cnt = 0;
		System.out.println(title);
		for(int i = nodes.size()-1;i>=0;i--){
			if(cnt%10==0)	System.out.print(' ');
			System.out.print(String.format(" (%d,%d)", nodes.get(i).r, nodes.get(i).c));
			if(++cnt%10==0) System.out.println();
		}
		if(nodes.size()%10!=0)	System.out.println();
	}
	public void solve(){
		in = new Scanner(System.in);
		while(true){
			hasEdge = new boolean[10][10][4][3];
			dist = new int[10][10][4];
			nodeTo = new Node[10][10][4];
			for(int i=0;i<10;i++)	for(int j=0;j<10;j++)	Arrays.fill(dist[i][j], -1);
			if(!read())	break;
			Node u = new Node(sr+dr[sd], sc+dc[sd], sd);
			dist[u.r][u.c][u.dir] = 0;
			if(!bfs(u))	System.out.print(title+"\n  No Solution Possible\n");
		}
		in.close();
	}
	public boolean bfs(Node u){
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(u);
		while(!q.isEmpty()){
			u = q.pollFirst();
			if(u.r==gr&&u.c==gc)	{print(u);return true;}
			for(int i=0;i<3;i++){
				Node v = walk(u, i);
				if(legal(v.r, v.c)&&hasEdge[u.r][u.c][u.dir][i]&&dist[v.r][v.c][v.dir]<0){
					dist[v.r][v.c][v.dir] = dist[u.r][u.c][u.dir] + 1;
					nodeTo[v.r][v.c][v.dir] = u;
					q.push(v);
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		AbbottsRevenge a = new AbbottsRevenge();
		a.solve();
	}
}
