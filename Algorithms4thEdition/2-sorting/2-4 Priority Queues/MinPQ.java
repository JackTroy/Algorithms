
public class MinPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N=0;
	public MinPQ(){
		this(1);
	}
	public MinPQ(int size){
		pq = (Key[]) new Comparable[size+1];
		N=0;
 	}
	public MinPQ(Key[] a){
		N=a.length;
		pq = (Key[]) new Comparable[N+1];
		for(int j=N;j>0;j--){
			pq[j]=a[j-1];
		}
		for(int k=N/2;k>0;k--)
			sink(k);
		assert isMinHeap();
	}
	public void insert(Key v){
		pq[++N]=v;
		swim(N);
	}
	public Key min(){
		return pq[1];
	}
	public Key delmin(){
		Key max = pq[1];
		exch(1,N);
		pq[N--]=null;
		sink(1);
		return max;
	}
	private boolean greater(int i,int j){
		return pq[i].compareTo(pq[j])>0;
	}
	private void exch(int i,int j){
		Key temp = pq[i];pq[i]=pq[j];pq[j]=temp;
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
    private boolean isMinHeap() {
        return isMinHeap(1);
    }
    public boolean isEmpty(){
    	return N==0;
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

}
