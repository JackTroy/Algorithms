import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {
	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst  = new Queue<Edge>();
		
		//here is the main loop process
		visit(G,0);
		while(!pq.isEmpty()){
			Edge e = pq.delMin();
			int v=e.either(),w=e.other(v);
			if(marked[v]&&marked[w]) continue;
			mst.enqueue(e);
			if(!marked[v])	visit(G, v);
			if(!marked[w])	visit(G, w);
		}
	}
	//this is the exact manipulation on vertex
	private void visit(EdgeWeightedGraph G,int v) {
		marked[v]=true;
		for(Edge e:G.adj(v))
			if(!marked[e.other(v)])
				pq.insert(e);
	}
	public Iterable<Edge> mst(int v){
		return mst;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
