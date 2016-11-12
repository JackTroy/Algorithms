import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SeparateChainingHashST<Key,Value> {
	private int N;//Key number
	private int M;//hash table amount
	private SequentialSearchST<Key, Value>[] st;
	
	public SeparateChainingHashST(){
		this(997);
	}
	@SuppressWarnings("unchecked")
	public SeparateChainingHashST(int M){
		this.M=M;
		st = (SequentialSearchST<Key,Value>[]) new SequentialSearchST[M];
		for(int i=0;i<M;i++)
			st[i]= new SequentialSearchST();
	}
	private int hash(Key key) {
		return (key.hashCode()&0x7fffffff)%M;
	}
	public Value get(Key key) {
		return (Value)st[hash(key)].get(key);
	}
	public void put(Key key,Value val){
		st[hash(key)].put(key, val);
	}
	public void delete(Key key){
		st[hash(key)].delete(key);
	}
	public Iterable<Key> keys(){
		Queue<Key> q = new Queue<Key>();
		for (int i = 0; i < st.length; i++) 
			for(Key key:st[i].keys())
				q.enqueue(key);
		return q;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s));

	}

}
