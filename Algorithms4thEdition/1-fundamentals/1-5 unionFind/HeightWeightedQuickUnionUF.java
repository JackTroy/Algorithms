import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class HeightWeightedQuickUnionUF {
    private int[] id,height;
    private int count;
	public HeightWeightedQuickUnionUF(int N){
        count = N;
        id = new int[N];
        height = new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
            height[i]=1;
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
    	if(height[pRoot]>height[qRoot]){
    		id[qRoot] = pRoot;
    	}
    	else if(height[pRoot]==height[qRoot]){
    		id[qRoot] = pRoot;
    		height[pRoot]+=1;
    	}
    	else{
    		id[pRoot] = qRoot;
    	}    	
    	count--;
    }
    public int find(int p){
    	
    	int curp = p;
    	while(id[curp]!=curp){
    		curp=id[curp];
    	}
    	
        return id[curp];
    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        HeightWeightedQuickUnionUF uf = new HeightWeightedQuickUnionUF(n);
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
