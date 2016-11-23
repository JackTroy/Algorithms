import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] marked;
	private IndexMinPQ<Double> pq;
	
	public PrimMST(EdgeWeightedGraph G){
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for(int v=0;v<G.V();v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		//Compute a minimum spanning tree (or forest) of an edge-weighted graph.
		for(int v=0;v<G.V();v++)
			if(!marked[v])
				prim(G, v);
	}
	private void prim(EdgeWeightedGraph G,int s){
		distTo[s]=0.0;
		pq.insert(s, distTo[s]);
		while(!pq.isEmpty())
			visit(G,pq.delMin());
	}
	private void visit(EdgeWeightedGraph G,int v) {
		marked[v]=true;
		for(Edge e:G.adj(v)){
			int w=e.other(v);
			
			if(marked[w]) continue;
			if(e.weight()<distTo[w]){
				edgeTo[w]=e;
				
				distTo[w]=e.weight();
				if(pq.contains(w))	pq.changeKey(w, distTo[w]);
				else				pq.insert(w, distTo[w]);
			}
		}
	}
	public Iterable<Edge> edges(){
		Bag<Edge> mst = new Bag<Edge>();
		for(int v=1;v<edgeTo.length;v++)
			mst.add(edgeTo[v]);
		return mst;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
