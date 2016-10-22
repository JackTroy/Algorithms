import edu.princeton.cs.algs4.StdRandom;

public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private Key min;
	private int N=0;
	public MaxPQ(){
		this(1);
	}
	public MaxPQ(int max){
		pq = (Key[]) new Object[max+1];
		N=max;
 	}
	public MaxPQ(Key[] a){
		N=a.length;
		pq = (Key[]) new Comparable[N+1];
		for(int j=N;j>0;j--){
			pq[j]=a[j-1];
			if(min.compareTo(a[j-1])>0)
				min=a[j-1];
		}
		for(int k=N/2;k>0;k--)
			sink(k);
		assert isMaxHeap();
	}
	public void insert(Key v){
		if(min.compareTo(v)>0)
			min=v;
		pq[++N]=v;
		swim(N);
	}
	public Key max(){
		return pq[1];
	}
	public Key delmax(){
		Key max = pq[1];
		exch(1,N);
		pq[N--]=null;
		sink(1);
		return max;
	}
	private boolean less(int i,int j){
		return pq[i].compareTo(pq[j])<0;
	}
	private void exch(int i,int j){
		Key temp = pq[i];pq[i]=pq[j];pq[j]=temp;
	}
	private void swim(int k){
		while(k>1&&less(k,k/2)){
			exch(k,k/2);
			k/=2;
		}
	}
	private void sink(int k){
		while(2*k<=N){
			int j = 2*k;
			if(j+1<=N&&less(j,j+1))j++;
			if(!less(k,j))break;
			exch(k,j); 
			k=j;
		}
	}
	private boolean isMaxHeap(){
		return isMaxHeap(1);
	}
	//try to picture that you're working on every single node
	private boolean isMaxHeap(int k){
		if(k>N)return true;
		int left=2*k,right=2*k+1;
		if(left<=N&&less(k,left))return false;
		if(right<=N&&less(k,right))return false;
		return isMaxHeap(left)&&isMaxHeap(right);
		
	}
	public Key min(){
		return min;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 1000;
		Double a[] = new Double[N];
		for(int i=0;i<N;i++){
			a[i] = StdRandom.uniform();
		}
		MaxPQ pq = new MaxPQ<Double>(a);
		System.out.println(pq.isMaxHeap());
	}

}
