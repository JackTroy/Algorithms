import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
public class CubicEightPuzzle {
	//Cubic Eight-Puzzle UVA - 1604
	/*
	 *  top w front r side b 1
	 *  top w front b side r 2
	 *  top r front w side b 3
	 *  top r front b side w 4
	 *  top b front r side w 5
	 *  top b front w side r 6
	 *  empty				 7
	 *  code row-first 111111711
	 *  
	 *  w-1 r-2 b-3 e-4
	 */
	//tle
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public class Node{
		int[] state;
		int dist;
		int ex,ey;
		public Node(int[] a, int d, int x,int y){
			this.state = a.clone();
			dist = d;
			ex = x;
			ey = y;
		}
	}
	
	public int x, y, ans, fx, fy;
	public int[] des;
	public static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
	public static int[][] move = {
			{0, 0, 0, 0},
			{5, 5, 3, 3},
			{4, 4, 6, 6},
			{6, 6, 1, 1},
			{2, 2, 5, 5},
			{1, 1, 4, 4},
			{3, 3, 2, 2},
	};
	public void solve() throws IOException{
		while(true){
			String[] nums = in.readLine().split(" "); if(nums[0].equals("0")) break;
			x = Integer.parseInt(nums[0]); y = Integer.parseInt(nums[1]);
			des = new int[9];
			for(int i = 0; i < 3; i++){
				String[] chars = in.readLine().split(" ");
				for(int j = 0; j < 3; j++){
					if(chars[j].equals("W"))		des[i * 3 + j] = 1;
					else if(chars[j].equals("R"))	des[i * 3 + j] = 2;
					else if(chars[j].equals("B"))	des[i * 3 + j] = 3;
					else							{des[i * 3 + j] = 4; fx = j; fy = i;}
				}
			}
			ans = 31;
			bfs();
			if(ans == 31)	out.append("-1\n");
			else			out.append(ans+"\n");
		}
		System.out.print(out.toString());
	}
	public void bfs(){
		LinkedList<Node>[] queue = (LinkedList<Node>[]) new LinkedList[2];
		HashMap<Integer,Integer>[] marked = (HashMap<Integer,Integer>[])new HashMap[2];
		for(int i = 0; i < 2; i++){
			queue[i] = new LinkedList<Node>();
			marked[i] = new HashMap<Integer,Integer>();
		}
		
		int[] start = new int[9];
		Arrays.fill(start, 1);
		start[(y - 1) * 3 + x - 1] = 7;
		queue[0].add(new Node(start, 0, x - 1, y - 1));
		marked[0].put(code(start), 0);
		for(int i = 0; i < 1<<8; i++){
			int[] state = new int[9];
			for(int j = 0; j < 8; j++){
				int k;
				if(j >= fy * 3 + fx)	k = j + 1;
				else					k = j;
				if((i & (1<<j)) > 0)	state[k] = des[k] * 2;
				else					state[k] = des[k] * 2 - 1;
			}
			state[fy * 3 + fx] = 7;
			queue[1].add(new Node(state, 0, fx, fy));
			marked[1].put(code(state),0);
		}
		if(marked[1].containsKey(code(start)))	{ans = 0; return;}
		for(int d = 1; d < 22; d++){
			//System.out.println(d);
			while(!queue[0].isEmpty()){
				Node cur = queue[0].remove();
				if(cur.dist > d)	break;
				if(cur.dist > 21)	{queue[0].clear();	continue;}
				
				for(int i = 0; i < 4; i++){
					int curE = cur.ey * 3 + cur.ex ;
					int nx = cur.ex + dx[i], ny = cur.ey + dy[i];
					if(!(nx >= 0 && nx <= 2 && ny >=0 && ny <= 2))	continue;
					int nextE = ny * 3 + nx; 
					
					int[] nextState = cur.state.clone();
					int cubic = cur.state[nextE];
					nextState[curE] = move[cubic][i];
					nextState[nextE] = 7;
					int code = code(nextState);
					if(marked[1].containsKey(code))	{
						ans = marked[1].get(code) + cur.dist + 1; 
						return;}
					if(!marked[0].containsKey(code))	{
						queue[0].add(new Node(nextState, cur.dist + 1,nx, ny));
						marked[0].put(code, marked[0].get(code(cur.state)) + 1);
					}
				}
			}
			while(!queue[1].isEmpty()){
				Node cur = queue[1].remove();
				if(cur.dist > d)	break;
				if(cur.dist > 9)	{queue[1].clear();	continue;}
				
				for(int i = 0; i < 4; i++){
					int curE = cur.ey * 3 + cur.ex ;
					int nx = cur.ex + dx[i], ny = cur.ey + dy[i];
					if(!(nx >= 0 && nx <= 2 && ny >=0 && ny <= 2))	continue;
					int nextE = ny * 3 + nx; 
					
					int[] nextState = cur.state.clone();
					int cubic = cur.state[nextE];
					nextState[curE] = move[cubic][i];
					nextState[nextE] = 7;
					int code = code(nextState);
					if(marked[0].containsKey(code))	{
						ans = marked[0].get(code) + cur.dist + 1; 
						return;
						}
					if(!marked[1].containsKey(code))	{
						queue[1].add(new Node(nextState, cur.dist + 1,nx, ny));
						marked[1].put(code, marked[1].get(code(cur.state)) + 1);
					}
				}
			}
		}
	}
	public int code(int[] cur){
		int code = 0;
		for(int i = 0; i < 9; i++)
			code = code * 10 + cur[i];
		return code;
	}
	public static void main(String[] args) throws IOException {
		CubicEightPuzzle aCubicEightPuzzle = new CubicEightPuzzle();
		aCubicEightPuzzle.solve();
	}

}
