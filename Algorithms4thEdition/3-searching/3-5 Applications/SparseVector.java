import edu.princeton.cs.algs4.StdOut;

public class SparseVector {
	private int d;//dimension
	private ST<Integer,Double> st;
	
	public SparseVector(int d) {
		this.d=d;
		this.st=new ST<Integer,Double>();
	}
	public void put(int i,double x){
        if (i < 0 || i >= d) throw new IndexOutOfBoundsException("Illegal index");
        if (x == 0.0) st.delete(i);
        else			st.put(i, x);
	}
	public double get(int i) {
		if (i < 0 || i >= d) throw new IndexOutOfBoundsException("Illegal index");
		if(st.contains(i))	return st.get(i);
		else return 0.0;
	}
	public int nnz(){
		return st.size();
	}
	public int dimension(){
		return d;
	}
	public double dot(SparseVector that){
		if (this.d != that.d) throw new IllegalArgumentException("Vector lengths disagree");
		double sum = 0.0;
		// iterate over the vector with the fewest non-zeros
		if(this.st.size()<=that.st.size()){
			for(int i:this.st.keys())
				if(that.st.contains(i)) sum+=this.get(i)*that.get(i);
		}
		else{
			for(int i:that.st.keys())
				if(this.st.contains(i))	sum+=this.get(i)*that.get(i);
		}
		return sum;
	}
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }
    public SparseVector plus(SparseVector that){
    	if (this.d != that.d) throw new IllegalArgumentException("Vector lengths disagree");
    	SparseVector c = new SparseVector(this.d);
    	for(int i:this.st.keys())	c.put(i, this.get(i));
    	for(int i:that.st.keys())	c.put(i, that.get(i)+c.get(i));
    	return c;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i : st.keys()) {
            s.append("(" + i + ", " + st.get(i) + ") ");
        }
        return s.toString();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SparseVector a = new SparseVector(10);
        SparseVector b = new SparseVector(10);
        a.put(3, 0.50);
        a.put(9, 0.75);
        a.put(6, 0.11);
        a.put(6, 0.00);
        b.put(3, 0.60);
        b.put(4, 0.90);
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("a dot b = " + a.dot(b));
        StdOut.println("a + b   = " + a.plus(b));
    }
}


