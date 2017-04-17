import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
//uva 1599
public class IdealPath {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public class Edge{
		public int from,to,c;
		public Edge(int from,int to,int c){
			this.from=from;this.to=to;this.c=c;
		}
	}
	public ArrayList<Edge>[] G;
	public int n,m;
	public void solve() throws IOException{
		while(true){
			if(!read())	break;
			bfs();
		}
		System.out.print(out.toString());
	}
	public void bfs(){
		int[] distToN = new int[n+1];
		Arrays.fill(distToN, -1);
		distToN[n] = 0;
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(n);
		while(!q.isEmpty()){
			int pos = q.removeFirst();
			for(int i = 0;i<G[pos].size();i++){
				int to = G[pos].get(i).to;
				if(distToN[to]==-1){
					distToN[to] = distToN[pos]+1;
					q.add(to);
				}		
			}
		}
		boolean[] marked = new boolean[n+1];
		ArrayList<Integer> paths = new ArrayList<Integer>();
		ArrayList<Integer> next = new ArrayList<Integer>();
		next.add(1);
		marked[1] = true;
		for(int i = 0;i<distToN[1];i++){
			int min = Integer.MAX_VALUE;
			for(int k=0;k<next.size();k++){
				int pos = next.get(k);
				for(int j = 0;j<G[pos].size();j++){
					int to = G[pos].get(j).to,c = G[pos].get(j).c;
					if(distToN[to]+1 == distToN[pos]&&c<min)
						min = c;
				}
			}
			paths.add(min);
			ArrayList<Integer> buf = new ArrayList<Integer>();
			for(int k=0;k<next.size();k++){
				int pos = next.get(k);
				for(int j = 0;j<G[pos].size();j++){
					int to = G[pos].get(j).to,c = G[pos].get(j).c;
					if(distToN[to]+1 == distToN[pos]&&c==min)
						buf.add(to);
				}
			}
			next = buf;
		}
		out.append(paths.size());
		out.append(paths.get(0));
		for(int i=1;i<paths.size();i++)
			out.append(" " + paths.get(i));
		out.append("\n");
	}
	@SuppressWarnings("unchecked")
	public boolean read() throws IOException{
		String s = in.readLine(); if(s == null) return false;
		String[] ss = s.split("\\s+");
		n = Integer.parseInt(ss[0]);m = Integer.parseInt(ss[1]);
		G = (ArrayList<Edge>[]) new ArrayList[n+1];
		for(int i=1;i<=n;i++)	G[i] = new ArrayList<Edge>();
		for(int i=0;i<m;i++){
			s = in.readLine();
			ss = s.split("\\s+");
			int a = Integer.parseInt(ss[0]),b = Integer.parseInt(ss[1]),c = Integer.parseInt(ss[2]);
			G[a].add(new Edge(a, b, c));
			G[b].add(new Edge(b, a, c));
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		IdealPath a = new IdealPath();
		a.solve();
	}

}
