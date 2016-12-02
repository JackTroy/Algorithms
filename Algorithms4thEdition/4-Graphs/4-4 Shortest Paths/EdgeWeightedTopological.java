
public class EdgeWeightedTopological {
	private Iterable<Integer> order;
	public EdgeWeightedTopological(EdgeWeightedDigraph G) {
		EdgeWeightedCycleFinder cyclefinder = new EdgeWeightedCycleFinder(G);
		if(!cyclefinder.hasCycle()){
			DepthFirstOder dfs = new DepthFirstOder(G);
			order=dfs.reversePost();
		}
	}
	public Iterable<Integer> order(){
		return order;
	}
	public boolean isDAG(){
		return order!=null;
	}
}
