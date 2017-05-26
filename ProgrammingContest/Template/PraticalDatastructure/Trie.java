
public class Trie {
	static int R = 256;
	class Node{
		int val;
		Node[] next;
		public Node(int v) {
			val = v;
			next = new Node[R];
		}
		public Node(){
			//use -1 to represent no value in this node
			this(-1);
		}
	}
	static Node root;
	Node put(Node x, String key, int val, int d){
		if(x == null)	x = new Node();
		if(d == key.length()){
			x.val = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}
	void ncput(String key, int val){
		if(root == null)	root = new Node();
		Node x = root;
		int d = 0;
		while(d < key.length()){
			char c = key.charAt(d++);
			if(x.next[c] == null) x.next[c] = new Node();
			x = x.next[c];
		}
		x.val = val;
	}
	int get(String key){
		Node x = root;
		int d = 0;
		while(d < key.length()){
			char c = key.charAt(d++);
			if(x.next[c] == null)	return -1;
			x = x.next[c];
		}
		return x.val;
	}
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.ncput("anc", 99);
		System.out.printf("%d %d", trie.get("anc"), trie.get("jj"));
	}

}
