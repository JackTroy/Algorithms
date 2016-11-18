import edu.princeton.cs.algs4.StdOut;

public class BaconHistogram {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String filename  = args[0];
        String delimiter = args[1];
        String name = args[2];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        BreadthFirstPaths bfp = new BreadthFirstPaths(graph, sg.indexOf(name));
        int MAX_BACON = 100;
        int[] hist = new int[MAX_BACON + 1];
        for(int v=0;v<graph.V();v++){
        	int bacon = Math.min(MAX_BACON, bfp.distTo(v));
        	hist[bacon]++;
        }
        for (int i = 0; i < MAX_BACON; i += 2) {
            if (hist[i] == 0) break;
            StdOut.printf("%3d %8d\n", i/2, hist[i]);
        }
	}
	//the output corresponds to 6 degrees theory
}
