import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	public BreadthFirstPaths(Graph G,int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G,this.s);
	}
	private void bfs(Graph G,int s) {
		Queue<Integer> queue = new Queue<Integer>();
		marked[s]=true;
		queue.enqueue(s);
		while(!queue.isEmpty()){
			int v = queue.dequeue();
			for(int w:G.adj(v))
				if(!marked[w]){
					edgeTo[w]=v;
					marked[w]=true;
					queue.enqueue(w);
				}
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
