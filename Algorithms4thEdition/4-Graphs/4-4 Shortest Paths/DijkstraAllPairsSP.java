
public class DijkstraAllPairsSP {
	private DijkstraSP[] allPairs;
	public DijkstraAllPairsSP(EdgeWeightedDigraph G){
		allPairs = new DijkstraSP[G.V()];
		for(int v=0;v<G.V();v++)
			allPairs[v] = new DijkstraSP(G, v);
	}
	public Iterable<DirectedEdge> path(int s,int v){
		return allPairs[s].pathTo(v);
	}
	public double dist(int s,int v){
		return allPairs[s].distTo(v);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
