import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Huffman {
	//after compression,binary data follow below rule
	//trie(inorder)+length of original txt+huffman coding data
	private static int R=256;
	private Huffman() { }
	private static class Node implements Comparable<Node>{
		private char ch;
		private int freq;
		private final Node left,right;
		Node(char ch,int freq,Node left,Node right){
			this.ch=ch;
			this.freq=freq;
			this.left  = left;
            this.right = right;
		}
		private boolean isLeaf(){
			return (left==null&&right==null);
		}
		public int compareTo(Node that){
			return this.freq - that.freq;
		}
	}
	public static void expand(){
		Node root = readTrie();
		int N = BinaryStdIn.readInt();
		for(int i=0;i<N;i++){
			Node x = root;
			while(!x.isLeaf()){
				boolean bit = BinaryStdIn.readBoolean();
				if(bit) x=x.right;
				else 	x=x.left;
			}
			BinaryStdOut.write(x.ch,8);
		}
		BinaryStdOut.close();
	}
	public static void compress(){
		char[] input = BinaryStdIn.readString().toCharArray();
		
		//1 count frequency
		int[] freq = new int[R];
		for(int i=0;i<input.length;i++)
			freq[input[i]]++;
		
		//2 construct Trie
		Node root = buildTrie(freq);
		
		//3 construct corresponding table between char as index and huffman code as value
		String[] st = new String[R];
		buildCode(st,root,"");
		
		//4 write trie in inorder and the length of data
		writeTrie(root);
		
		BinaryStdOut.write(input.length);
		
		//5 write char consecutively with table above
		for(int i=0;i<input.length;i++){
			String code = st[input[i]];
			for(int j=0;j<code.length();i++){
				if(code.charAt(j)=='0')
					BinaryStdOut.write(false);
				else if(code.charAt(j)=='1')
					BinaryStdOut.write(true);
			}
		}
		BinaryStdOut.close();
	}
	private static Node readTrie(){
		boolean isLeaf = BinaryStdIn.readBoolean();
		//note that while expanding , frequency does not matter
		//because Trie is built up 
		if(isLeaf) return new Node(BinaryStdIn.readChar(),-1,null,null);
		//this line of code is credible 
		else return new Node('\0', -1, readTrie(), readTrie());
	}
	private static void writeTrie(Node x){
		if(!x.isLeaf()){
			BinaryStdOut.write(false);
			writeTrie(x.left);
			writeTrie(x.right);
		}
		else{
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch,8);
			return;
		}
	}
	private static void buildCode(String[] st,Node x,String s){
		if(x.isLeaf()){
			st[x.ch]=s;
			return;
		}
		buildCode(st,x.left,s+'0');
		buildCode(st,x.right,s+'1');
	}
	private static Node buildTrie(int[] freq){
		MinPQ<Node> pq = new MinPQ<Node>();
		for(char i=0;i<R;i++)
			if(freq[i]>0)
				pq.insert(new Node(i, freq[i], null, null));
		
		// special case in case there is only one character with a nonzero frequency
		if(pq.size()==1){
			if (freq['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
            else                 pq.insert(new Node('\1', 0, null, null));
		}
		
		
		while(pq.size()>1){
			Node left = pq.delMin();
			Node right = pq.delMin();
			Node parent = new Node('\0', left.freq + right.freq, left, right);
			pq.insert(parent);
		}
		return pq.delMin();	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
	}

}
