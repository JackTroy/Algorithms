import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class FordFulkerson {
	private static final double FLOATING_POINT_EPSILON = 1E-11;
	
	private boolean[] marked;
	private FlowEdge[] edgeTo;
	private double value;//max flow
	
	public FordFulkerson(FlowNetwork G,int s,int t){
		while(hasAugmentingPath(G, s, t)){
			
			double bottle = Double.POSITIVE_INFINITY;
			//compute the max bottle residual capacity on augmenting path
			for(int v=t;v!=s;v=edgeTo[v].other(v))
				bottle = Math.min(bottle, edgeTo[v].residualCapaity(v));
			//add flow along augmenting path
			for(int v=t;v!=s;v=edgeTo[v].other(v))
				edgeTo[v].addResidualFlowTo(v, bottle);
			
			value+=bottle;
		}
	}
	public double value() {
		return value;
	}
	public boolean inCut(int v) {
		return marked[v];
	}
	private boolean hasAugmentingPath(FlowNetwork G,int s,int t){
		marked = new boolean[G.V()];
		edgeTo = new FlowEdge[G.V()];
		
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(s);
		marked[s] = true;
		
		while(!queue.isEmpty()&&!marked[t]){
			int v = queue.dequeue();
			for(FlowEdge e:G.adj(v)){
				int w = e.other(v);
				//residual capacity,the key of augmenting path
				if(e.residualCapaity(w)>0&&!marked[w]){
					edgeTo[w]=e;
					marked[w]=true;
					queue.enqueue(w);
				}
			}
		}
		return marked[t];
	}
	//below two functions just a copy from algs 4th site
	private double excess(FlowNetwork G, int v) {
        double excess = 0.0;
        for (FlowEdge e : G.adj(v)) {
            if (v == e.from()) excess -= e.flow();
            else               excess += e.flow();
        }
        return excess;
    }

    // return excess flow at vertex v
    private boolean isFeasible(FlowNetwork G, int s, int t) {

        // check that capacity constraints are satisfied
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if (e.flow() < -FLOATING_POINT_EPSILON || e.flow() > e.capacity() + FLOATING_POINT_EPSILON) {
                    System.err.println("Edge does not satisfy capacity constraints: " + e);
                    return false;
                }
            }
        }

        // check that net flow into a vertex equals zero, except at source and sink
        if (Math.abs(value + excess(G, s)) > FLOATING_POINT_EPSILON) {
            System.err.println("Excess at source = " + excess(G, s));
            System.err.println("Max flow         = " + value);
            return false;
        }
        if (Math.abs(value - excess(G, t)) > FLOATING_POINT_EPSILON) {
            System.err.println("Excess at sink   = " + excess(G, t));
            System.err.println("Max flow         = " + value);
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s || v == t) continue;
            else if (Math.abs(excess(G, v)) > FLOATING_POINT_EPSILON) {
                System.err.println("Net flow out of " + v + " doesn't equal zero");
                return false;
            }
        }
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
        FlowNetwork G = new FlowNetwork(in);
        StdOut.println(G);
        int s = 0,t=G.V()-1;
        // compute maximum flow and minimum cut
        FordFulkerson maxflow = new FordFulkerson(G, s, t);
        StdOut.println("Max flow from " + s + " to " + t);
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if ((v == e.from()) && e.flow() > 0)
                    StdOut.println("   " + e);
            }
        }

        // print min-cut
        StdOut.print("Min cut: ");
        for (int v = 0; v < G.V(); v++) {
            if (maxflow.inCut(v)) StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.println("Max flow value = " +  maxflow.value());
	}

}
