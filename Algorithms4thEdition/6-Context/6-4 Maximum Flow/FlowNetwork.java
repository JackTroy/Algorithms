import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class FlowNetwork {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int V;//overall number of vertices
	private int E;//overall number of edges;
	private Bag<FlowEdge>[] adj;

	public FlowNetwork(int V) {
		// TODO Auto-generated constructor stub
		this.V=V;
		this.E=0;
		adj =(Bag<FlowEdge>[]) new Bag[V];
		for(int v=0;v<V;v++)
			adj[v]=new Bag<FlowEdge>();
	}
	public FlowNetwork(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i=0;i<E;i++){
			int v=in.readInt();
			int w=in.readInt();
			double capacity = in.readDouble();
			FlowEdge edge = new FlowEdge(v, w, capacity);
			addEdge(edge);
		}
	}
	public void addEdge(FlowEdge e){
		adj[e.from()].add(e);
		adj[e.to()].add(e);
		E++;
	}
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	public Iterable<FlowEdge> adj(int v){
		return adj[v];
	}
	public Iterable<FlowEdge> edges(){
		Bag<FlowEdge> bag=new Bag<FlowEdge>();
		for(int v=0;v<V;v++){
			for(FlowEdge e:adj(v)){
					if(e.to()!=v)
						bag.add(e);
			}
		}
		return bag;
	}
    public String toString() {
    	StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ":  ");
            for (FlowEdge e : adj[v]) {
                if (e.to() != v) s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
