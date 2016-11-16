import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	public DepthFirstPaths(Graph G,int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G,this.s);
	}
	private void dfs(Graph G,int v) {
		marked[v]=true;
		for(int w:G.adj(v))
			if(!marked[w]){
				edgeTo[w]=v;
				dfs(G, w);
			}
	}
	public boolean hasPathTo(int v){
		return marked[v];
	}
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v))	return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x=v;x!=s;x=edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        In in = new In(args[0]);
        Graph G = new Graph(in);
        DepthFirstPaths paths = new DepthFirstPaths(G, 0);
        for(int step:paths.pathTo(10)){
        	StdOut.println(step);
        }
	}

}
