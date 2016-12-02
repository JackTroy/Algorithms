import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedCycleFinder {
	private boolean[] marked;
	private boolean[] onStack;
	private DirectedEdge[] edgeTo;
	private Stack<DirectedEdge> cycle;
	public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for(int v=0;v<G.V();v++)
			if(!marked[v])
				dfs(G, v);
	}
	public void dfs(EdgeWeightedDigraph G,int v){
		marked[v]=true;
		onStack[v]=true;
		for(DirectedEdge e:G.adj(v)){
			int w = e.to();
			if(this.hasCycle()) return;
			else if(!marked[w]){
				edgeTo[w]=e;
				dfs(G, w);
			}
			else if(onStack[w]){
				cycle = new Stack<DirectedEdge>();
				DirectedEdge x;
				for(x=e;x.from()!=w;x=edgeTo[x.from()])
					cycle.push(x);
				cycle.push(x);
			}
		}
		onStack[v]=false;
	}
	public boolean hasCycle() {
		return cycle!=null;
	}
	public Iterable<DirectedEdge> cycle(){
		return cycle;
	}

}
