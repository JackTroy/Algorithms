import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
//not the same as dfs , assistant array onStack is not useful
public class ShortestDirectedCycle {
	private int length;
	private Stack<Integer> cycle;
	public ShortestDirectedCycle(Digraph G) {
		Digraph R = G.reverse();
		length = G.V()+1;
		for(int v=0;v<G.V();v++){
			BreathFirstPathsDirected bfs = new BreathFirstPathsDirected(R, v);
			for(int w:G.adj(v)){
				if(bfs.hasPathTo(w)&&(bfs.distTo(w)+1)<length){
					//replace with shortest path
					length = bfs.distTo(w)+1;
					cycle = new Stack<Integer>();
					for(int x:bfs.pathTo(w))
						cycle.push(x);
					cycle.push(v);
				}
			}
		}
	}
	public boolean hasCycle() {
		return cycle!=null;
	}
	public Iterable<Integer> cycle(){
		return cycle;
	}
	public int length()              { return length;          }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        ShortestDirectedCycle finder = new ShortestDirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Shortest directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
	}

}
