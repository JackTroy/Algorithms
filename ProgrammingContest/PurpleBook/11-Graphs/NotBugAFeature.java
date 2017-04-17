import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class NotBugAFeature {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public class Vertex implements Comparable<Vertex>{
		public int state;
		public Long dist;
		public Vertex(int s, Long d){
			state = s;	dist = d;
		}
		public int compareTo(Vertex o) {
			if(dist < o.dist)		return -1;
			else if(dist > o.dist)	return 1;
			return 0;
		}
	}
	public int bugs, patches;
	public int[] Cost;
	public String[][] bfat;
	public HashMap<Integer, Long> dist;
	public void solve() throws IOException{
		int curT = 0;
		while(true){
			String[] nm = in.readLine().split(" ");
			bugs = Integer.parseInt(nm[0]);
			patches = Integer.parseInt(nm[1]);
			if(bugs == 0 && patches == 0)	break;
			
			Cost = new int[patches];
			bfat = new String[patches][2];
			dist = new HashMap<Integer, Long>();
			for(int i = 0; i < patches; i++){
				String[] patchData = in.readLine().split(" ");
				Cost[i] = Integer.parseInt(patchData[0]);
				bfat[i][0] = patchData[1];
				bfat[i][1] = patchData[2];
			}
			Long ans = dijkstra((1 << bugs) - 1);
			out.append("Product " + (++curT) + '\n');
			if(ans == -1)	out.append("Bugs cannot be fixed.\n");
			else			out.append("Fastest sequence takes " + ans + " seconds.\n");
			out.append("\n");
		}
		System.out.print(out.toString());
	}
	public Long dijkstra(int s){
		dist.put(s, new Long(0));
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		q.add(new Vertex(s, new Long(0)));
		
		while(!q.isEmpty()){
			Vertex cur = q.remove();
			int state = cur.state;
			Long distance = cur.dist;
			for(int i = 0; i < patches; i++){
				int nextState = fix(state, i);
				if(nextState == -1)	continue;
				if(dist.containsKey(nextState)){
					Long oldDist = dist.get(nextState);
					if(oldDist > distance + Cost[i]){
						dist.remove(nextState);
						dist.put(nextState, distance + Cost[i]);
						q.add(new Vertex(nextState, distance + Cost[i]));
					}
				}
				else{
					dist.put(nextState, distance + Cost[i]);
					q.add(new Vertex(nextState, distance + Cost[i]));
				}
			}
		}
		if(dist.containsKey(0))	return dist.get(0);
		else					return (long) -1;
	}
	public int fix(int state, int pn){
		int ans = state;
		for(int i = 0; i < bugs; i++){
			char b = bfat[pn][0].charAt(bugs - i - 1), a = bfat[pn][1].charAt(bugs - i - 1);
			boolean f1 = b == '0' ;
			boolean f2 = (b == '-') && ((state & (1 << i)) == 0);
			boolean f3 = (b == '+') && ((state & (1 << i)) > 0);
			if(f1 || f2 || f3){
				if(a == '0')	continue;
				else if(a == '-'){
					if((ans & (1 << i)) > 0)	ans = ans ^ (1 << i);
				}
				else{
					if((ans & (1 << i)) == 0)	ans = ans ^ (1 << i);
				}
			}
			else return -1;
		}
		return ans;
	}
	public static void main(String[] args) throws IOException {
		NotBugAFeature a = new NotBugAFeature();
		a.solve();
	}

}
