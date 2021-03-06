import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CC {
	private boolean[] marked;
	private int[] id;
	private int count;
	public CC(Graph G){
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int s=0;s<G.V();s++){
			if(!marked[s]){
				dfs(G,s);
				count++;
			}
		}
	}
	private void dfs(Graph G,int v) {
		marked[v]=true;
		id[v]=count;
		for(int w:G.adj(v))
			//prevent get stuck in cycle
			if(!marked[w])
				dfs(G, w);
	}
	public boolean connected(int v,int w){
		return id[v]==id[w];
	}
	public int id(int v){
		return id[v];
	}
	public int count(){
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Graph G = new Graph(new In(args[0]));
        CC cc=new CC(G);
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
