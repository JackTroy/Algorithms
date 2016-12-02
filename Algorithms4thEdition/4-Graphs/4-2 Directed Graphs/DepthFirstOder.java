import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOder {
	private boolean[] marked;
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	public DepthFirstOder(Digraph G) {
		marked = new boolean[G.V()];
		pre = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		for(int v=0;v<G.V();v++)
			if(!marked[v])
				dfs(G,v);
	}
	public DepthFirstOder(EdgeWeightedDigraph G) {
		marked = new boolean[G.V()];
		pre = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		for(int v=0;v<G.V();v++)
			if(!marked[v])
				dfs(G,v);
	}
	private void dfs(Digraph G,int v) {
		marked[v]=true;
		pre.enqueue(v);
		for(int w:G.adj(v)){
			if(!marked[w])
				dfs(G, w);
		}
		post.enqueue(v);
		reversePost.push(v);
	}
	private void dfs(EdgeWeightedDigraph G,int v) {
		marked[v]=true;
		pre.enqueue(v);
		for(DirectedEdge e:G.adj(v)){
			int w = e.to();
			if(!marked[w])
				dfs(G, w);
		}
		post.enqueue(v);
		reversePost.push(v);
	}
	public Iterable<Integer> pre(){
		return pre;
	}
	public Iterable<Integer> post(){
		return post;
	}
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
