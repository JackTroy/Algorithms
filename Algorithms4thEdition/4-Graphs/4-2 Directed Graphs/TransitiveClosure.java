
public class TransitiveClosure {

	private DirectedDFS[] all;
	public TransitiveClosure(Digraph G) {
		//indicate that all is a array first
		all = new DirectedDFS[G.V()];
		for(int v=0;v<G.V();v++){
			//then create a instance for every entries in array
			all[v] = new DirectedDFS(G, v);
		}
	}
	public boolean reachable(int v,int w){
		return all[v].marked(w);
	}
}
