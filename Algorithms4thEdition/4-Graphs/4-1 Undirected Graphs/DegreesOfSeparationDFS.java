import edu.princeton.cs.algs4.StdOut;

public class DegreesOfSeparationDFS {
	//StackOverflowError occured
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String filename  = args[0];
        String delimiter = args[1];
        String source = args[2];
        String query = args[3];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        DepthFirstPaths dfp = new DepthFirstPaths(graph, sg.indexOf(source));
        if(dfp.hasPathTo(sg.indexOf(query)))
        	for(int w:dfp.pathTo(sg.indexOf(query))){
        		StdOut.println(sg.nameOf(w));
        	}
	}

}
