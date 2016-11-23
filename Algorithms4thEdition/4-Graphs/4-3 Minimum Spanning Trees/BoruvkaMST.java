import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class BoruvkaMST {
	private Bag<Edge> mst = new Bag<Edge>();
	private double weight;
	public BoruvkaMST(EdgeWeightedGraph G) {
		// TODO Auto-generated constructor stub
		UF uf = new UF(G.V());
		// repeat at most log V times or until we have V-1 edges
		for(int t=1;t<G.V()&&mst.size()<G.V()-1;t=t+t){
			// foreach tree in forest, find closest edge
            // if edge weights are equal, 
			// ties are broken in favor of first edge in G.edges()
			Edge[] closest = new Edge[G.V()];
			for(Edge e:G.edges()){
                int v = e.either(), w = e.other(v);
                int i = uf.find(v), j = uf.find(w);
                if (i == j) continue;   // same tree
                if (closest[i] == null || less(e, closest[i])) closest[i] = e;
                if (closest[j] == null || less(e, closest[j])) closest[j] = e;
			}
			
			// add newly discovered edges to MST
			for(int i=0;i<G.V();i++){
				Edge edge=closest[i];
				if(edge!=null){
					int v = edge.either(),w=edge.other(v);
					// don't add the same edge twice
					if(!uf.connected(v, w)){
						mst.add(edge);
						weight+=edge.weight();
						uf.union(v, w);
					}
				}
			}
		}
	}
    public Iterable<Edge> edges() {
        return mst;
    }
    public double weight() {
        return weight;
    }
    private static boolean less(Edge e, Edge f) {
        return e.weight() < f.weight();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        BoruvkaMST mst = new BoruvkaMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
