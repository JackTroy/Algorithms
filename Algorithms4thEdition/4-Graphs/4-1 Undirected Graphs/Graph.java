import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");
	private final int V;//number of vertex
	private int E;//number of edge
	private Bag<Integer>[] adj;
	@SuppressWarnings("unchecked")
	public Graph(int V){
		this.V=V;
		this.E=0;
		//generic array is banned in java,pity
		//remember this form to create generic array
		adj=(Bag<Integer>[]) new Bag[V];
		for(int v=0;v<V;v++)
			adj[v]=new Bag<Integer>();
	}
	public Graph(In in){
		this(in.readInt());
		int E = in.readInt();
		for(int i=0;i<E;i++){
			int v=in.readInt();
			int w=in.readInt();
			addEdge(v,w);
		}
	}
	public Graph(Graph G){
		this(G.V());
		this.E = G.E();
		for(int v=0;v<V;v++){
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
		}
	}
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	public void addEdge(int v,int w){
		validateVertex(v);
	    validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}
	private void validateVertex(int v){
		if(v<0||v>=V)
			throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
	}
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
	}

}
