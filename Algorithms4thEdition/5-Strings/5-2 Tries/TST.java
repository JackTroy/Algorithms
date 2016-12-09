import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class TST<Value> {
	private Node root;
	public TST(){}
	private class Node{
		Value val;
		char c;
		Node left,mid,right;
	}
	public int size(){
		return size(root);
	}
	private int size(Node x){
		if(x==null) return 0;
		int cnt=0;
		if(x.val!=null) cnt++;
		cnt+=size(x.left);
		cnt+=size(x.right);
		cnt+=size(x.mid);
		return cnt;
	}
	public Value get(String key){
		Node x = get(root, key, 0);
		if(x==null) return null;
		return x.val;
	}
	private Node get(Node x,String key,int d){
		if(x==null)return null;

		char c = key.charAt(d);
		if(c<x.c) return get(x.left, key, d);
		if(c>x.c) return get(x.right, key, d);
		if(d==key.length()-1)	return x;
		return get(x.mid, key, d+1);

	}
	public void put(String key,Value val){
		root = put(root, key, val, 0);
	}
	private Node put(Node x,String key,Value val,int d){
		char c = key.charAt(d);
		if(x==null){
			x = new Node();
			x.c=c;
		}
		if(c<x.c)	x.left = put(x.left, key,val, d);
		else if(c>x.c)	x.right = put(x.right, key,val, d);
		else if(d==key.length()-1)	x.val=val;
		else	x.mid = put(x.mid, key, val, d+1);
		
		return x;
	}
	public Iterable<String> keys(){
		Queue<String> queue = new Queue<String>();
        collect(root,"", queue);
        return queue;
	}
	public Iterable<String> keysWithPrefix(String pre){
		Queue<String> q = new Queue<String>();
		collect(get(root, pre, 0).mid, pre, q);
		return q;
	}
	private void collect(Node x,String pre,Queue<String> q){
		if(x==null) return;
		if(x.val!=null) q.enqueue(pre+x.c);
		collect(x.left,pre,q);
		collect(x.right, pre, q);
		collect(x.mid,pre+x.c , q);
	}
	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> q = new Queue<String>();
		collect(root,"", pat, q);
		return q;
	}
	private void collect(Node x,String pre,String pat,Queue<String> q){
		if(x==null) return;
				
		int d = pre.length();
		char next = pat.charAt(d);
		if(next=='.'){
			if(d+1==pat.length()&&x.val!=null) q.enqueue(pre+x.c);
			if(d+1==pat.length())	return;
			collect(x.left,pre,pat,q);
			collect(x.right, pre,pat, q);
			collect(x.mid,pre+x.c , pat,q);
		}
		else{
			if(next<x.c) 							collect(x.left, pre, pat, q);
			else if(next>x.c)						collect(x.right, pre, pat, q);
			else if(d+1==pat.length()&&x.val!=null) q.enqueue(pre);
			else if(d+1==pat.length())				return;
			else 									collect(x.mid, pre+x.c, pat, q);
		}
	}
	public String longestPrefixOf(String s){
		int len = search(root, s, -1, 0);
		if(len==-1) return null;
		return s.substring(0, len);
	}
	private int search(Node x,String s,int len,int d){
		if(x==null) return len;
		if(d==s.length()) return len;
		char c = s.charAt(d);
		
		if(c<x.c) 		return search(x.left, s, len, d);
		else if(c>x.c)	return search(x.right, s, len, d);
		else if(x.val!=null) len = d+1;
		return search(x.mid, s, len, d+1);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        TST<Integer> st = new TST<Integer>();
        In in = new In(args[0]);
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            StdOut.println(key);
            st.put(key, i);
        }

        // print results
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }

        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("longestPrefixOf(\"shell\"):");
        StdOut.println(st.longestPrefixOf("shell"));
        StdOut.println();

        StdOut.println("keysWithPrefix(\"shor\"):");
        for (String s : st.keysWithPrefix("shor"))
            StdOut.println(s);
        StdOut.println();
        
        StdOut.println("keysThatMatch(\".he.l.\"):");
        for (String s : st.keysThatMatch(".he.l."))
            StdOut.println(s);
        
	}

}
