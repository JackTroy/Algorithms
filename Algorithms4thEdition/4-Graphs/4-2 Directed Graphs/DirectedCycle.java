import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DirectedCycle {
	private boolean[] marked;
	private boolean[] onStack;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	public DirectedCycle(Digraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for(int v=0;v<G.V();v++)
			if(!marked[v])
				dfs(G, v);
	}
	public void dfs(Digraph G,int v){
		marked[v]=true;
		onStack[v]=true;
		for(int w:G.adj(v)){
			if(this.hasCycle()) return;
			else if(!marked[w]){
				edgeTo[w]=v;
				dfs(G, w);
			}
			else if(onStack[w]){
				cycle = new Stack<Integer>();
				for(int x=v;x!=w;x=edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v]=false;
	}
	public boolean hasCycle() {
		return cycle!=null;
	}
	public Iterable<Integer> cycle(){
		return cycle;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Digraph G = new Digraph(new In(args[0]));
		DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("Graph is acyclic");
        }
	}

}
