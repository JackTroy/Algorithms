import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreathFirstPathsDirected {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	//private final int s;
	public BreathFirstPathsDirected(Digraph G,int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		bfs(G,s);
	}
	private void bfs(Digraph G,int s) {
		Queue<Integer> queue = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
		marked[s]=true;
		distTo[s]=0;
		queue.enqueue(s);
		while(!queue.isEmpty()){
			int v = queue.dequeue();
			for(int w:G.adj(v))
				if(!marked[w]){
					edgeTo[w]=v;
					marked[w]=true;
					distTo[w]=distTo[v]+1;
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
		int x;
		for(x=v;distTo[x]!=0;x=edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}
	public int distTo(int v){
		return distTo[v];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
