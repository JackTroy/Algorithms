import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeigthedQuickUnionPathCompressionUF {
    private int[] id,sz;
    private int count;
	public WeigthedQuickUnionPathCompressionUF(int N){
        count = N;
        id = new int[N];
        sz = new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
            sz[i]=1;
        }
    }
	public void show(){
        for(int i=0;i<id.length;i++){
        	StdOut.println(i+":"+id[i]);
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
    	int pRoot=find(p),qRoot=find(q);
    	if(sz[pRoot]>sz[qRoot]){
    		id[qRoot] = pRoot;
    		sz[pRoot]+=sz[qRoot];
    	}   		
    	else{
    		id[pRoot] = qRoot;
    		sz[qRoot]+=sz[pRoot];
    	}    	
    	count--;
    }
    public int find(int p){
    	
    	int curp = p;
    	while(id[curp]!=curp){
    		curp=id[curp];
    	}
    	
    	int curpForPC;
    	while(id[p]!=p){
    		curpForPC = id[p];
    		id[p] = curp;
    		p = curpForPC;
    	}
    	//these few lines are for path compression.
        return id[curp];
    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeigthedQuickUnionPathCompressionUF uf = new WeigthedQuickUnionPathCompressionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
        uf.show();
    }
}
