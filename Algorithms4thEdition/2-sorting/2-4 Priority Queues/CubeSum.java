
public class CubeSum implements Comparable<CubeSum> {
	
	private final int sum;
	private final int i;
	private final int j;
	
	public CubeSum(int i,int j){
		this.sum = i*i*i+j*j*j;
		this.i=i;
		this.j=j;
	}
	public int compareTo(CubeSum that) {
		if(this.sum>that.sum)return 1;
		if(this.sum<that.sum)return -1;
		return 0;
	}
	public String toString(){
		return sum + " = " + i + "^3" + " + " + j + "^3";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N=10;
		MinPQ pq = new MinPQ<CubeSum>(N); 
		//if we compute N^2 answers results 
		//there would be too many duplicate i and j
		//so we can just compute i and i ,
		//then we increase the later number from i on in order to prevent duplicate
		for(int i=0;i<N;i++)
			pq.insert(new CubeSum(i,i));
		while(!pq.isEmpty()){
			CubeSum min = (CubeSum) pq.delmin();
			System.out.println(min);
			//every time (i,i+j) is delete, (i,i+j+1) will be inserted if i+j<N,
			//so no need to worry the rest of (i,i+k) will abandoned.
			if(min.j<N)
				pq.insert(new CubeSum(min.i, min.j+1));
		}
	}
}
