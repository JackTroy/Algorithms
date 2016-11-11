import edu.princeton.cs.algs4.Queue;

public class RedBlackBST<Key extends Comparable<Key>,Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	private class Node{
		private Key key;
		private Value val;
		private Node left,right;
		private int N;
		boolean color;
		public Node(Key key,Value val,int N,boolean color){
			this.key=key;this.val=val;this.N=N;this.color=color;
		}
	}
    public RedBlackBST() {
    }
	private boolean isRed(Node x){
		if(x==null)	return false;
		return x.color==RED;
	}
	//below are three basic operation to make sure tree is ordered and balanced.
	private Node rotateLeft(Node h){
		Node x=h.right;
		h.right=x.left;
		//do remember to connect x and h
		x.left=h;
        x.color = x.left.color;
        x.left.color = RED;
		x.N=h.N;
		h.N=1+size(h.left)+size(h.right);
		return x;	
	}
	//mistake to be found
	private Node rotateRight(Node h){
		Node x=h.left;
		h.left=x.right;
		//do remember to connect x and h
		x.right=h;
        x.color = x.right.color;
        x.right.color = RED;
		x.N=h.N;
		h.N=1+size(h.left)+size(h.right);
		return x;
	}
	private void flipColors(Node h){
		h.color=!h.color;
		h.left.color=!h.left.color;
		h.right.color=!h.right.color;
	}
	//you can't use size method to check isEmpty
	//because when is empty,root is null!!!!
	public boolean isEmpty(){
		return root==null;
	}
	public int size(){
		return size(root);
	}
	private int size(Node x) {
		if(x==null)	return 0;
		else 		return x.N;
	}
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }
	public int height(){
		return height(root);
	}
	private int height(Node x){
		if(x==null)	return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}
	public void put(Key key,Value val) {
		root = put(root,key, val);
		root.color=BLACK;
	}
	//insert,adjust,(pass red link upward)!
	//maintaining the structure of rb-tree
	private Node put(Node h,Key key,Value val){
		if(h==null) return new Node(key, val, 1,RED);
		int cmp=key.compareTo(h.key);
		if		(cmp<0) h.left= put(h.left,key, val);
		else if	(cmp>0)	h.right= put(h.right, key, val);
		else			h.val=val;
		
		if(isRed(h.right)&&!isRed(h.left))		h=rotateLeft(h);
		if(isRed(h.left)&&isRed(h.left.left))	h=rotateRight(h);
		if(isRed(h.left)&&isRed(h.right))		flipColors(h);
		
		h.N=size(h.left)+size(h.right)+1;
		return h;
	}
	private Node balance(Node h) {
		if (isRed(h.right))						 h=rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;		
	}
	private Node moveRedLeft(Node h) {
		flipColors(h);
		if(isRed(h.right.left)){
			h.right = rotateRight(h.right);
			h=rotateLeft(h);
			flipColors(h);
		}
		return h;
	}
	public void deleteMin() {
		if(!isRed(root.left)&&!isRed(root.right))	
			root.color=RED;
		root=deleteMin(root);
		if(!isEmpty())	root.color=BLACK;
	}
	private Node deleteMin(Node h) {
		if(h.left==null)	
			return null;
		
		if(!isRed(h.left)&&!isRed(h.left.left))	
			h=moveRedLeft(h);
		
		h.left=deleteMin(h.left);
		return balance(h);
	}
	private Node moveRedRight(Node h){
		flipColors(h);
		if(isRed(h.left.right)){
			h=rotateRight(h);
			flipColors(h);
		}
		return h;
	}
	public void deleteMax() {
		if(!isRed(root.left)&&!isRed(root.right))
			root.color=RED;
		root=deleteMax(root);
		if(!isEmpty())	root.color=BLACK;
	}
	private Node deleteMax(Node h) {
		//turn red link from left to right 
		//make it just like deleteMin to construct 2-3-5 tree easy
		if(isRed(h.left))
			h=rotateRight(h);
		if(h.right==null)	
			return null;
		if(!isRed(h.right)&&!isRed(h.right.left))
			h=moveRedRight(h);
		h.right=deleteMax(h.right);
		balance(h);
		return h;
	}
	//i gotta say that deleteMin function is driving me crazy , 
	//but it's my own fault
	
	public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
        
        if(!isRed(root.left)&&!isRed(root.right))
			root.color=RED;
        
        root=delete(root,key);
        if (!isEmpty()) root.color = BLACK;
	}
	private Node delete(Node h,Key key) {
		if(key.compareTo(h.key)<0){
			if(!isRed(h.left)&&!isRed(h.left.left))	
				h=moveRedLeft(h);
			h.left=delete(h.left, key);
		}
		else{
			if(isRed(h.left))
				h=rotateRight(h);
			//the case that this is the largest one
			if(key.compareTo(h.key)==0&&(h.right==null))
				return null;
			//make sure not to destroy the structure of b-tree
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            //delete Node
            if (key.compareTo(h.key) == 0){
            	//a more convenient way to delete Node
            	//just like the method in priority queue
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right=delete(h.right, key);
		}
		return balance(h);
	}
	public Value get(Key key){
		return get(root,key);
	}
	private Value get(Node x,Key key) {
		if(x==null)	return null;
		int cmp=x.key.compareTo(key);
		if(cmp==0)		return x.val;
		else if(cmp<0)	return get(x.right,key);
		else			return get(x.left, key);
	}
	//you bitch notice the difference != and == 
	public boolean contains(Key key) {
		return get(key)!=null;
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
	public boolean is23() {
		return is23(root);
	}
	private boolean is23(Node h){
		if(h==null)	return true;
        if (isRed(h.right)) return false;
        if (h != root && isRed(h) && isRed(h.left))
            return false;
		return is23(h.left)&&is23(h.right);
	}
	//open up your mind,isBlanced achieve a nice trick
	//let the recursive function take the black number downwards
	//finally check if it's equal to zero
    private boolean isBalanced() { 
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
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
		//if(x==null)	return null;
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
	public Iterable<Key> keys() {
		if (isEmpty()) return new Queue<Key>();
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
	public static void main(String[] args) {
		

	}

}
