import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {
	private Queue<Edge> mst;
	public KruskalMST(EdgeWeightedGraph G){
		mst = new Queue<Edge>();
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e:G.edges())
			pq.insert(e);
		//use union-find to skip edge which connects 
		//two vertex already in mst
		UF uf = new UF(G.V());
		
		//note that this can handle both tree and forest 
		while(!pq.isEmpty()&&mst.size()<G.V()-1){
			Edge e = pq.delMin();
			int v = e.either(),w=e.other(v);
			
			//if they are already in mst,skip then;
			if(uf.connected(v, w)) continue;
			
			uf.union(v, w);
			mst.enqueue(e);
		}
		check(G);
	}
	public Iterable<Edge> edges(){
		return mst;
	}
	private boolean check(EdgeWeightedGraph G) {
		// just a copy 
        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            UF uf = new UF(G.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }
            
            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
