import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    public int[] id;
    private int count;
	public QuickFindUF(int N){
        count = N;
        id = new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
        }
    }
    public int count(){
        return count;
    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    public void union(int p, int q){
    	if(connected(p,q)) return;
    	int newroot = id[q],oldroot = id[p];
    	for(int i=0;i<id.length;i++){
    		if(id[i]==oldroot){
    			id[i] = newroot;
    		}
    	}
    	count--;
    }
    public int find(int p){
        return id[p];
    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");        
        for(int i=0;i<n;i++){
        	StdOut.println();
        	StdOut.print(i);
        	StdOut.print(uf.id[i]);
        }
    }
}
