import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class MonotonicSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private IndexMinPQ<Double> pq;
	public MonotonicSP(EdgeWeightedDigraph G,int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for(int v=0;v<G.V();v++)
			distTo[v]=Double.POSITIVE_INFINITY;
		
		distTo[s]=0.0;
		edgeTo[s]=null;
		
		pq.insert(s, distTo[s]);
		while(!pq.isEmpty())
			relax(G, pq.delMin());
	}
	private void relax(EdgeWeightedDigraph G,int v){
		MinPQ<DirectedEdge> minpq = new MinPQ<DirectedEdge>();
		for(DirectedEdge e:G.adj(v))
			minpq.insert(e);
		while(!minpq.isEmpty()){
			DirectedEdge e = minpq.delMin();
			int w = e.to();
			if(edgeTo[v].weight()<e.weight()){
				if(distTo[w]>distTo[v]+e.weight()){
					distTo[w]=distTo[v]+e.weight();
					edgeTo[w]=e;
					
					if(pq.contains(w)) 	pq.changeKey(w, distTo[w]);
					else				pq.insert(w, distTo[w]);	
				}
			}
		}
	}
	public double distTo(int v){
		return distTo[v];
	}
	public boolean hasPathTo(int v) {
		return distTo[v]<Double.POSITIVE_INFINITY;
	}
	public Iterable<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v))	return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()])
			path.push(e);
		return path;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        int s = Integer.parseInt(args[1]);

        // compute shortest paths
        DijkstraSP sp = new DijkstraSP(G, s);


        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
	}

}
