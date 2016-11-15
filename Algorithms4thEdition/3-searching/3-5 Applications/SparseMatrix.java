
public class SparseMatrix {
	private int m,n;
	private SparseVector[] rows;
	
	public SparseMatrix(int m,int n){
		this.m=m;
		this.n=n;
		rows = new SparseVector[m];
		for(int i=0;i<m;i++){
			rows[i]=new SparseVector(n);
		}
	}
	public int[] size(){
		return new int[]{m,n};
	}
    public void put(int i, int j, double value) {
        if (i < 0 || i >= n) throw new IllegalArgumentException("Illegal index");
        if (j < 0 || j >= n) throw new IllegalArgumentException("Illegal index");
        rows[i].put(j, value);
    }
    public int nnz() { 
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += rows[i].nnz();
        return sum;
    }
    public SparseVector times(SparseVector x) {
        if (n != x.dimension()) throw new IllegalArgumentException("Dimensions disagree");
        SparseVector b = new SparseVector(n);
        for (int i = 0; i < n; i++)
            b.put(i, rows[i].dot(x));
        return b;
    }
    public SparseMatrix plus(SparseMatrix that) {
    	if (this.n != that.n) throw new RuntimeException("Dimensions disagree");
    	if (this.m != that.m) throw new RuntimeException("Dimensions disagree");
    	SparseMatrix c = new SparseMatrix(m, n);
    	for(int i=0;i<m;i++)
    		c.rows[i]=this.rows[i].plus(that.rows[i]);
    	return c;
    }
    public SparseMatrix dot(SparseMatrix that){
    	//todo
		return that;
    }
    public String toString() {
        String s = "n = " + n + ", nonzeros = " + nnz() + "\n";
        for (int i = 0; i < n; i++) {
            s += i + ": " + rows[i] + "\n";
        }
        return s;
    }
}
