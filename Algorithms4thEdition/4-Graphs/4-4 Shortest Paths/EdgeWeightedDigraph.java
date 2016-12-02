import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {
	private final int V;//overall number of vertices
	private int E;//overall number of edges;
	private Bag<DirectedEdge>[] adj;
	private static final String NEWLINE = System.getProperty("line.separator");

	public EdgeWeightedDigraph(int V) {
		// TODO Auto-generated constructor stub
		this.V=V;
		this.E=0;
		adj =(Bag<DirectedEdge>[]) new Bag[V];
		for(int v=0;v<V;v++)
			adj[v]=new Bag<DirectedEdge>();
	}
	public EdgeWeightedDigraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i=0;i<E;i++){
			int v=in.readInt();
			int w=in.readInt();
			double weight = in.readDouble();
			DirectedEdge edge = new DirectedEdge(v, w, weight);
			addEdge(edge);
		}
	}
	public void addEdge(DirectedEdge e){
		adj[e.from()].add(e);
		E++;
	}
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	//use the fact that finally it must come to balance
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> bag=new Bag<DirectedEdge>();
		for(int v=0;v<V;v++){
			for(DirectedEdge e:adj(v)){
					bag.add(e);
			}
		}
		return bag;
	}
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
