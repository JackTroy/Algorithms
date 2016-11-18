import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TwoColor {
	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColor;
	public TwoColor(Graph G){
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		for(int s=0;s<G.V();s++)
			if(!marked[s])
				dfs(G,s);
	}
	private void dfs(Graph G,int v) {
		marked[v]=true;
		for(int w:G.adj(v)){
			if(!marked[w]){
				color[w]=!color[v];
				dfs(G, w);
			}
			else if(color[w]==color[v])
				isTwoColor=true;
		}
	}
	public boolean isTwoColor(){
		return isTwoColor;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph G = new Graph(new In(args[0]));
		TwoColor finder = new TwoColor(G);
		StdOut.println(finder.isTwoColor);
	}

}
