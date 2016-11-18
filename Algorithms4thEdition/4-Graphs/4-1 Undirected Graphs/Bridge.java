import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Bridge {
	private int[] low;
	private int[] pre;
	private int count,bridges;
	public Bridge(Graph G){
		low = new int[G.V()];
		pre = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            low[v] = -1;
        for (int v = 0; v < G.V(); v++)
            pre[v] = -1;
		for(int i=0;i<G.V();i++){
			if(pre[i]==-1)
				dfs(G, i, i);
		}
	}
	private void dfs(Graph G,int u,int v){
		pre[v]=count++;
		low[v]=pre[v];
		for(int w:G.adj(v)){
			if(pre[w]==-1){
				dfs(G, v, w);
				low[v]=Math.min(low[v], low[w]);
				if(low[w]==pre[w]){
					bridges++;
					StdOut.println(v+" to "+w);
				}	
			}
			else if(w!=u)
				low[v]=Math.min(low[v], pre[w]);
		}
	}
	public int components() {
		return bridges+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph G = new Graph(new In(args[0]));
	    StdOut.println(G);

	    Bridge bridge = new Bridge(G);
	    StdOut.println("Edge connected components = " + bridge.components());		 
	}

}
