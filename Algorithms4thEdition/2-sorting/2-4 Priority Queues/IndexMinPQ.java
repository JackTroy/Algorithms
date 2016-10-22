import edu.princeton.cs.algs4.StdRandom;

public class IndexMinPQ<Key extends Comparable<Key>> {
	
	private int[] qp;
	private int[] pq;
	private Key[] keys;
	private int N=0;

	public IndexMinPQ(int size){
		pq = new int[size+1];
		qp = new int[size+1];
		keys =  (Key[]) new Comparable[size+1];
		for(int i=0;i<=size;i++)qp[i]=-1;
		N=0;
 	}
	
	/*
	public IndexMinPQ(Key[] a){
		N=a.length;
		pq = (Key[]) new Comparable[N+1];
		for(int j=N;j>0;j--){
			pq[j]=a[j-1];
		}
		for(int k=N/2;k>0;k--)
			sink(k);
		assert isMinHeap();
	}
	*/
	
	public void insert(int k,Key v){
		N++;
		pq[N]=k;
		qp[k]=N;
		keys[k]=v;
		swim(N);
	}
	
	public void change(int k,Key v){
		keys[k]=v;
		swim(qp[k]);
		sink(qp[k]);
	}
	
	public Key min(){
		return keys[pq[1]];
	}
	
	public Key delmin(){
		Key max = keys[pq[1]];
		exch(1,N--);
		sink(1);
		keys[pq[N+1]]= null;
		qp[pq[N+1]]=-1;
		return max;
	}
	
	public boolean contains(int k){
		return qp[k]!=-1;
	}
	
	public void delete(int k) {
		int index=qp[k];
		exch(index,N--);
		//note that argument index here is index of pq 
		swim(index);
		sink(index);
		qp[k]=-1;
		keys[k]=null;		
	}
	private boolean greater(int i,int j){
		return keys[pq[i]].compareTo(keys[pq[j]])>0;
	}
	
	private void exch(int i,int j){
		int temp = pq[i];pq[i]=pq[j];pq[j]=temp;
		// you miss this step 
		//anyway qp is just the inverse of pq ,so don't need to think too much
		qp[pq[i]]=i;qp[pq[j]] = j;
	}
	
	private void swim(int k){
		while(k>1&&greater(k/2,k)){
			exch(k,k/2);
			k/=2;
		}
	}
	
	private void sink(int k){
		while(2*k<=N){
			int j = 2*k;
			if(j+1<=N&&greater(j,j+1))j++;
			if(!greater(k,j))break;
			exch(k,j); 
			k=j;
		}
	}
	
    public boolean isEmpty(){
    	return N==0;
    }
    
    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    
    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeap(int k) {
        if (k > N) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= N && greater(k, left))  return false;
        if (right <= N && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 1000;
		IndexMinPQ pq = new IndexMinPQ<Double>(N);
		for(int i=1;i<=N;i++){
			pq.insert(i,StdRandom.uniform());
		}
		
		System.out.println(pq.isMinHeap());
		pq.change(2, (double)0.1);
		System.out.println(pq.isMinHeap());
		pq.delete(500);
		System.out.println(pq.isMinHeap());
	}

}