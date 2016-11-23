import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {
	private final int V;//overall number of vertices
	private int E;//overall number of edges;
	private Bag<Edge>[] adj;
	private static final String NEWLINE = System.getProperty("line.separator");

	public EdgeWeightedGraph(int V) {
		// TODO Auto-generated constructor stub
		this.V=V;
		this.E=0;
		adj =(Bag<Edge>[]) new Bag[V];
		for(int v=0;v<V;v++)
			adj[v]=new Bag<Edge>();
	}
	public EdgeWeightedGraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i=0;i<E;i++){
			int v=in.readInt();
			int w=in.readInt();
			double weight = in.readDouble();
			Edge edge = new Edge(v, w, weight);
			addEdge(edge);
		}
	}
	public void addEdge(Edge e){
		int v=e.either(),w=e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	//use the fact that finally it must come to balance
	public Iterable<Edge> edges(){
		Bag<Edge> bag=new Bag<Edge>();
		for(int v=0;v<V;v++){
			for(Edge e:adj(v)){
				if(e.other(v)>v)
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
            for (Edge e : adj[v]) {
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
