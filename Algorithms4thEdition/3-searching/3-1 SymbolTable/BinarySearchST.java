import edu.princeton.cs.algs4.Queue;

public class BinarySearchST<Key extends Comparable<Key>,Value>{
	private Key keys[];
	private Value vals[];
	private int N;
	public BinarySearchST(int capacity){
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	public int rank(Key key) {
		int lo=0,hi=N-1;
		while(lo<=hi){
			//mid must be in the middle of lo and hi
			int mid=lo+(hi-lo)/2;
			int cmp = keys[mid].compareTo(key);
			if(cmp>0)hi=mid-1;
			else if(cmp<0)lo=mid+1;
			else return mid;
		}
		return lo;
	}
	public void put(Key key,Value value){
		if(value==null){delete(key);}
		int num=rank(key);
		if(num<N&&keys[num].compareTo(key)==0){vals[num]=value;}
		for(int i=N-1;i>=num;i--){
			keys[i+1]=keys[i];vals[i+1]=vals[i];
		}
		keys[num]=key;vals[num]=value;
		N++;
		assert isOrdered();
	}
	public Value get(Key key) {
		if(isEmpty())return null;
		int i=rank(key);
		//i<N is to prevent key is larger than every one in keys
		if(i<N&&key.compareTo(keys[i])==0)	return vals[i];
		else 								return null;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public void delete(Key key) {
		if(isEmpty())return;
		int i = rank(key);
		if(i<N&&key.compareTo(keys[i])==0){
			for(int j=i+1;j<N;j++){
				keys[j-1]=keys[j];vals[j-1]=vals[j];
			}
		}
		keys[N-1]=null;
		vals[N-1]=null;
		N--;
		assert isOrdered();
	}
	public boolean contains(Key key) {
		int i=rank(key);
		return i<N&&key.compareTo(keys[i])==0;
	}
	public int size() {
		return N;
	}
	public Key min(){
		if(isEmpty())return null;
		return keys[0];
	}
	public Key max() {		
		if(isEmpty())return null;
		return keys[N-1];
	}
	public Key floor(Key key){
		int i=rank(key);
		if(i<N&&key.compareTo(keys[i])==0) 	return keys[i];
		else								return keys[i-1];
	}
	public Key ceiling(Key key) {
		int i=rank(key);
		if (i<N) 	return keys[i];
		else		return null;
	}
	public Key select(int k) {
		return keys[k-1];
	}
	public void deleteMin() {
		delete(min());
	}
	public void deleteMax() {
		delete(max());
	}
	public int size(Key lo,Key hi) {
		if(hi.compareTo(lo)<0)	return 0;
		else if(contains(hi))	return rank(hi)-rank(lo)+1;
		else 					return rank(hi)-rank(lo);
	}
	private boolean isOrdered() {
		for(int i=0;i<N;i++)
			if(i!=rank(select(i)))
				return false;
		return true;
	}
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	public Iterable<Key> keys(Key lo,Key hi) {
		Queue<Key> q = new Queue<Key>();
		for(int i =rank(lo);i<rank(hi);i++)
			q.enqueue(keys[i]);
		if(contains(hi))
			q.enqueue(keys[rank(hi)]);
		return q;
	}
	public static void main(String[] args) {
	
	}

}
