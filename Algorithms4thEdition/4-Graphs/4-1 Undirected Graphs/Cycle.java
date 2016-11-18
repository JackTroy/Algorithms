import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Cycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;

	public Cycle(Graph G){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for(int v=0;v<G.V();v++)
			if(!marked[v])
				dfs(G, -1, v);
	}
	private void dfs(Graph G,int u,int v){
		marked[v]=true;
		for(int w:G.adj(v)){
			if (cycle != null) return;
			if(!marked[w]){
				//notice that changing of edgeTo must be ahead of calling function
				edgeTo[w]=v;
				dfs(G, v, w);
			}
			else if(w!=u){
				cycle = new Stack<Integer>();
				for(int x=v;x!=w;x=edgeTo[x]){
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
	}
	public boolean hasCycle(){
		return cycle!=null;
	}
	public Iterable<Integer> cycle(){
		return cycle;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph G = new Graph(new In(args[0]));
		Cycle finder = new Cycle(G);
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
