import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Fill {
	public StringBuilder out = new StringBuilder();
	public final int max = 201;
	public boolean vis[][];
	public int cap[];
	public int goal;
	public int ans[];
	public class Node implements Comparable<Node>{
		public int v[];
		public int dist;
		public Node(int a,int b,int c,int dist){
			v = new int[3];
			v[0] = a; v[1] = b; v[2] = c; this.dist = dist;
		}
		public Node(Node o){
			v = new int[3];
			v[0] = o.v[0]; 
			v[1] = o.v[1]; 
			v[2] = o.v[2];
			this.dist = o.dist;
		}
		public int compareTo(Node o) {
			if(this.dist < o.dist )	return -1;
			if(this.dist > o.dist)	return 1;
			return 0;
		}
	}
	public void solve() throws NumberFormatException, IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int cases = Integer.parseInt(reader.readLine());
		while(cases-->0){
			String[] nums = reader.readLine().split(" ");
			cap = new int[3];
			for(int i=0;i<3;i++)
				cap[i] = Integer.parseInt(nums[i]);
			goal = Integer.parseInt(nums[3]);
			vis = new boolean[max][max];
			ans = new int[max];
			Arrays.fill(ans, -1);
			bfs();
		}
		System.out.print(out.toString());
	}
	public void bfs(){
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(0, 0, cap[2], 0));
		vis[0][0] = true;
		while(!q.isEmpty()){
			Node v = q.remove();
			for(int i=0;i<3;i++){
				int tvol = v.v[i];
				int td = v.dist;
				if(ans[tvol]<0||td<ans[tvol])	ans[tvol] = td;
			}
			if(ans[goal]>0)	break;
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++){
					if(i==j)	continue;
					if(v.v[i]==0||v.v[j]==cap[j])	continue;
					int amount;
					if(v.v[i]<cap[j]-v.v[j])	amount = v.v[i];
					else						amount = cap[j] - v.v[j];
					Node w = new Node(v);
					w.dist += amount;
					w.v[i] -= amount;
					w.v[j] += amount;
					int a = w.v[0],b = w.v[1];
					if(!vis[a][b])	{q.add(w);vis[a][b] = true;}
				}
		}
		int g = goal;
		while(g>=0&&ans[g]==-1)	g--;
		out.append(String.format("%d %d", ans[g],g));
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Fill a = new Fill();
		a.solve();
	}

}
