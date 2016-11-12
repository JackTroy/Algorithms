import edu.princeton.cs.algs4.Queue;

public class LinearProbingHashST<Key,Value> {
	private static final int INIT_CAPACITY = 4;
	private int N;//actual number of table
	private int M;//length of sparse array
	private Key[] keys;
	private Value[] vals;
	public LinearProbingHashST(){
		this(INIT_CAPACITY);
	}
	public LinearProbingHashST(int capacity){
		M=capacity;
		N=0;
		keys=(Key[]) new Object[M];
		vals=(Value[]) new Object[M];
	}
	private int hash(Key key) {
		return (key.hashCode()&0x7fffffff)%M;
	}
	public void put(Key key,Value value){
		if(N>=M/2)	resize(2*M);
		int i;
		for(i=hash(key);keys[i]!=null;i=(i+1)%M){
			if(keys[i].equals(key)){
				//if exists,update value
				vals[i]=value;
				return;
			}
		}
		keys[i]=key;
		vals[i]=value;
		N++;
	}
	public Value get(Key key){
		for(int i=hash(key);keys[i]!=null;i=(i+1)%M)
			if(keys[i].equals(key))
				return vals[i];
		return null;
	}
	public void delete(Key key){
		int i = hash(key);
		while(!keys[i].equals(key))
			i=(i+1)%M;
		keys[i]=null;
		vals[i]=null;
		N--;
		i=(i+1)%M;
		while(keys[i]!=null){
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i]=null;
			vals[i]=null;
			N--;
			this.put(keyToRedo, valToRedo);
			i=(i+1)%M;
		}
		if(N>0&&N==M/8)	resize(M/2);
	}
	private void resize(int cap){
		LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key,Value>(cap);
		for(int i=0;i<M;i++){
			if(keys[i]!=null)
				t.put(keys[i],vals[i]);
		}
		keys=t.keys;
		vals=t.vals;
	}
	public Iterable<Key> keys(){
		Queue<Key> queue=new Queue<Key>();
		for(int i=0;i<M;i++)
			if(keys[i]!=null) queue.enqueue(keys[i]);				
		return queue;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
