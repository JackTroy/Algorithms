import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class KosarajuSCC {
	private boolean[] marked;
	private int[] id;
	private int cnt;
	public KosarajuSCC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		DepthFirstOder dfs = new DepthFirstOder(G.reverse());
		for(int s:dfs.reversePost()){
			if(!marked[s]){
				dfs(G,s);
				cnt++;
			}
		}
		assert check(G);
	}
	private void dfs(Digraph G,int v){
		marked[v]=true;
		id[v]=cnt;
		for(int w:G.adj(v))
			if(!marked[w])
				dfs(G, w);
	}
	public boolean stronglyConnected(int v,int w){
		return id[v]==id[w];
	}
	public int id(int v) {
		return id[v];
	}
	public int count() {
		return cnt;
	}
    private boolean check(Digraph G) {
        TransitiveClosure tc = new TransitiveClosure(G);
        for (int v = 0; v < G.V(); v++) {
            for (int w = 0; w < G.V(); w++) {
                if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
                    return false;
            }
        }
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Digraph G = new Digraph(new In(args[0]));
        KosarajuSCC cc=new KosarajuSCC(G);
        int M = cc.count();
        StdOut.println(cc.count()+" components.");
        
        @SuppressWarnings("unchecked")
		Bag<Integer>[] components =(Bag<Integer>[]) new Bag[cc.count()];
        for(int i=0;i<M;i++){
        	components[i]=new Bag<Integer>();
        }
        for(int i=0;i<G.V();i++){
        	components[cc.id(i)].add(i);
        }
        for(int i=0;i<M;i++){
        	for(int v:components[i])
        		StdOut.print(v+" ");
        	StdOut.println();
        }
	}

}
