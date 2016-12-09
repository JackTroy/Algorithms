import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class TriesST<Value> {
	private Node root;
	private static int R=256;
	private static class Node{
		//can not use generic type in static class
		private Object val;
		private Node[] next = new Node[R];
	}
	public TriesST() {
	}
	public void put(String key,Value val) {
		root = put(root,key,val,0);
	}
	private Node put(Node x,String key,Value val,int d){
		if(x==null) x = new Node();
		if(d==key.length()){
			x.val=val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}
	public void ncput(String key,Value val){
		if(root==null)	root = new Node();
		Node x = root;
		int d=0;
		while(true){
			char c = key.charAt(d);
			if(x.next[c]==null)
				x.next[c] = new Node();
			if(d+1==key.length()){
				x.next[c].val=val;
				break;
			}
			else{
				x = x.next[c];
				d++;
			}
		}
	}
	public Value get(String key){
		Node x = get(root, key, 0);
		if(x==null) return null;
		return (Value) x.val;
	}
	private Node get(Node x, String key,int d){
		if(x==null) return null;
		
		if(key.length()==d)	return x;
		char c = key.charAt(d);
		//do use return !
		return get(x.next[c],key,d+1);
	}
	public Value ncget(String key){
		return (Value) ncget(root,key).val;
	}
	private Node ncget(Node x,String key){
		if(key==null)	return root;
		int d=0;
		while(true){
			char c = key.charAt(d);
			if(x.next[c]==null)
				return null;
			if(d+1==key.length()&&x.next[c].val!=null)
				return x.next[c];
			if(d+1==key.length())
				return null;
			x = x.next[c];
			d++;
		}
	}
	public int size(){
		return size(root);
	}
	private int size(Node x){
		if(x==null)	return 0;
		int cnt=0;
		if(x.val!=null) cnt++;
		for(char c = 0;c<R;c++)
			cnt+=size(x.next[c]);
		
		return cnt;
	}
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}
	public Iterable<String> keysWithPrefix(String pre){
		Queue<String> q = new Queue<String>();
		collect(get(root, pre, 0), pre, q);
		return q;
	}
	private void collect(Node x,String pre,Queue<String> q){
		if(x==null) return;
		if(x.val!=null)	q.enqueue(pre);	
		for(char c=0;c<R;c++)
			//don't forget to combine pre and c
			collect(x.next[c],pre+c, q);
	}
	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> q = new Queue<String>();
		collect(root,"", pat, q);
		return q;
	}
	private void collect(Node x,String pre,String pat,Queue<String> q){
		int d = pre.length();
		if(x==null) return;
		if(d==pat.length()&&x.val!=null) q.enqueue(pre);
		if(d==pat.length())	return;
		
		char next = pat.charAt(d);
		for(char c=0;c<R;c++)
			if(next=='.'||next==c)
				collect(x.next[c], pre+c, pat,q);
	}
	public String longestPrefixOf(String s){
		int len = search(root, s, 0, 0);
		return s.substring(0, len);
	}
	private int search(Node x,String s,int len,int d){
		if(x==null) return len;
		if(x.val!=null) len = d;
		if(d==s.length()) return len;
		char c = s.charAt(d);
		return search(x.next[c], s, len, d+1);
	}
	public void delete(String key){
		root = delete(root,key,0);
	}
	private Node delete(Node x,String key,int d){
		if(x==null) return null;
		
		if(d==key.length()){
			x.val=null;
		}
		else{
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c],key,d+1);
		}
		
		//to stop at word that is closet to key as prefix
		if(x.val!=null) return x;
		
		//to see if words with prefix identical to key exist
		for(char c=0;c < R;c++)
			if(x.next[c]!=null)
				return x;
		
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TriesST<Integer> st = new TriesST<Integer>();
        In in = new In(args[0]);
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            StdOut.println(key);
            st.ncput(key, i);
        }

        // print results
        if (true) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.ncget(key));
            }
            StdOut.println();
        }

        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("longestPrefixOf(\"shell\"):");
        StdOut.println(st.longestPrefixOf("shell"));
        StdOut.println();
	}

}
