import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>,Value> {
	private Node root,rec;
	private class Node{
		private Key key;
		private Value val;
		private Node left,right;
		private int N;
		
		public Node(Key key,Value value,int N){
			this.key=key;this.val=value;this.N=N;
		}
	}
	public BST() {
	}
	public boolean isEmpty(){
		return size()==0;
	}
	public int size(){
		return size(root);
	}
	private int size(Node x) {
		if(x==null)	return 0;
		else 		return x.N;
	}
	public int height(){
		return height(root);
	}
	private int height(Node x){
		if(x==null)	return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}
	public Value get(Key key){
		if(key.compareTo(rec.key)==0)	return rec.val;
		return get(root,key);
	}
	private Value get(Node x,Key key) {
		if(x==null)	return null;
		int cmp=x.key.compareTo(key);
		if(cmp==0)		return x.val;
		else if(cmp<0)	return get(x.right,key);
		else			return get(x.left, key);
	}
	public void put(Key key,Value value){
		if(key.compareTo(rec.key)==0)	rec.val=value;
		root=put(root, key, value);
	}
	private Node put(Node x,Key key,Value value){
		if(x==null) return new Node(key, value, 1);
		int cmp=key.compareTo(x.key);
		if		(cmp<0) x.left= put(x.left,key, value);
		else if	(cmp>0)	x.right= put(x.right, key, value);
		else			x.val=value;
		x.N=size(x.left)+size(x.right)+1;
		return x;
	}
	public void directPut(Key key,Value value){
		if(isEmpty()) {
			root=new Node(key, value, 1);
			return;
		}
		Node x = root;
		while(true){
			int cmp=key.compareTo(x.key);
			if(cmp>0){
				if(x.right==null)	{x.N+=1;x.right=new Node(key, value, 1);break;}
				else 				{x.N+=1;x=x.right;}
			}
			else if(cmp<0) {
				if(x.left==null) 	{x.N+=1;x.left=new Node(key, value, 1);break;}
				else 				{x.N+=1;x=x.left;}
			}
			else{
				x.val=value;
				break;
			}
		}
	}
	public Value directGet(Key key) {
		Node x=root;
		while(x!=null){
			int cmp=key.compareTo(x.key);
			if(cmp>0)		x=x.right;
			else if(cmp<0)	x=x.left;
			else 			return x.val;
		}
		return null;
	}
	public Key min() {
		return min(root).key;
	}
	//don't forget to detect it's null pointer or not 
	private Node min(Node x) {
		if(x.left==null)return x;
		return min(x.left);
	}
	public Key max(){
		return max(root).key;
	}
	private Node max(Node x) {
		if(x.right==null)return x;
		return max(x.right);
	}
	public Key floor(Key key) {
		Node x=floor(root,key);
		if(x==null)	return null;
		else 		return x.key;
	}
	private Node floor(Node x,Key key) {
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp<0)		return floor(x.left, key);
		else if(cmp==0)	return x;
		//when x.key is smaller than input key,there'd be two different cases
		//1st there exists a node or more with key smaller than the input key
		//2nd there doesn't, then we should return x
		Node temp=floor(x.right, key);
		if(temp==null)	return x;
		else 			return temp;
	}
	public Key ceiling(Key key) {
		Node x=ceiling(root, key);
		if(x==null)	return null;
		else 		return x.key;
	}
	private Node ceiling(Node x,Key key) {
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp>0)		return ceiling(x.right, key);
		else if(cmp==0)	return x;
		//when x.key is smaller than input key,there'd be two different cases
		//1st there exists a node or more with key smaller than the input key
		//2nd there doesn't, then we should return x
		Node temp=ceiling(x.left, key);
		if(temp==null)	return x;
		else 			return temp;
	}
	public Key select(int k) {
		return select(root,k).key;
	}
	private Node select(Node x,int k){
		if(x==null)	return null;
		int t=size(x.left);
		if(t==k)		return x;
		else if(t<k)	return select(x.right, k-t-1);
		else			return select(x.left, k);
	}
	public int rank(Key key){
		return rank(root,key);
	}
	private int rank(Node x,Key key){
		if(x==null)return 0;
		int cmp=key.compareTo(x.key);
		if		(cmp<0)	return rank(x.left,key);
		//notice the relation between size and rank!
		else if	(cmp>0)	return size(x.left)+1+rank(x.right, key);
		else 			return size(x.left);
	}
	public void deleteMin() {
		root=deleteMin(root);
	}
	private Node deleteMin(Node x) {
		if(x.left==null)return x.right;
		x.left=deleteMin(x.left);
		x.N=size(x.left)+size(x.right)+1;
		return x;
	}
	public void delete(Key key){
		root=delete(root,key);
	}
	private Node delete(Node x,Key key) {
		if(x==null)	return null;
		int cmp = key.compareTo(x.key);
		if		(cmp<0)	x.left	= delete(x.left, key);
		else if	(cmp>0)	x.right	= delete(x.right, key);
		else{
			if(x.right == null) return x.left;
			if(x.left == null)	return x.right;
			Node t = x;
			x = min(t.right);
			x.right=deleteMin(x.right);
			x.left=t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	public Iterable<Key> keys() {
		return keys(min(),max());
	}
	public Iterable<Key> keys(Key lo,Key hi){
		Queue<Key> queue=new Queue<Key>();
		keys(root,queue,lo,hi);
		return queue;
	}
	private void keys(Node x,Queue<Key> queue,Key lo,Key hi) {
		if(x==null)	return;
		int cmplo=lo.compareTo(x.key);
		int cmphi=hi.compareTo(x.key);
		if(cmplo<0)	keys(x.left,queue,lo,hi);
		if(cmplo<=0&&cmphi>=0) queue.enqueue(x.key);
		if(cmphi>0)	keys(x.right, queue, lo, hi);
	}
	private boolean isBST() {
		return isBST(root,null,null);
	}
	private boolean isBST(Node x,Key min,Key max){
		if(x==null)	return true;
		if(min!=null&&x.key.compareTo(min)<=0)	return false;
		if (max != null && x.key.compareTo(max) >= 0) return false;
		return isBST(x.left,min,max)&&isBST(x.right, min, max);
	}
	private boolean isSizeConsistent(){
		return isSizeConsistent(root);
	}
	private boolean isSizeConsistent(Node x){
		if(x==null)	return true;
		if(size(x)!=size(x.left)+size(x.right)+1)	return false;
		//combination of boolean value to make sure child tree is size consistent 
		return isSizeConsistent(x.left)&&isSizeConsistent(x.right);
	}
	private boolean isRankConsistent(){
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
	}
	public static void main(String[] args) {
		BST<String, Integer> st = new BST<String,Integer>();
		st.put(""+0, 0);
		//same reference
		BST.Node x=st.root;
		BST.Node y=st.root;
		StdOut.print(x==y);	
	}
}
