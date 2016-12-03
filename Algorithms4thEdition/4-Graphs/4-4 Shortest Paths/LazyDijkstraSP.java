import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class LazyDijkstraSP {
	private boolean[] marked;  
	private DirectedEdge edgeTo[];
	private double distTo[];
	private MinPQ<DirectedEdge> pq;
	
    private class ByDistanceFromSource implements Comparator<DirectedEdge> {
        public int compare(DirectedEdge e, DirectedEdge f) {
            double dist1 = distTo[e.from()] + e.weight();
            double dist2 = distTo[f.from()] + f.weight();
            return Double.compare(dist1, dist2);
        }
    }
    
	public LazyDijkstraSP(EdgeWeightedDigraph G,int s){
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		pq = new MinPQ<DirectedEdge>(new ByDistanceFromSource());
		marked = new boolean[G.V()];
		
		for(int v=0;v<G.V();v++)
			distTo[v]=Double.POSITIVE_INFINITY;
		
		distTo[s]=0.0;
		relax(G,s);

        while (!pq.isEmpty()) {
            DirectedEdge e = pq.delMin();
            int w = e.to();
            if (!marked[w]) relax(G, w);   // lazy, so w might already have been relaxed
        }
	}
	private void relax(EdgeWeightedDigraph G,int v){
		marked[v] = true;
		for(DirectedEdge e:G.adj(v)){
			int w = e.to();
			if(distTo[w]>distTo[v]+e.weight()){
				distTo[w]=distTo[v]+e.weight();
				edgeTo[w]=e;
				pq.insert(e);
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
        LazyDijkstraSP sp = new LazyDijkstraSP(G, s);


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
