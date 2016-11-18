public class Topological {
	private Iterable<Integer> order;
	public Topological(Digraph G) {
		DirectedCycle cyclefinder = new DirectedCycle(G);
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
