import java.util.LinkedList;
import java.util.Scanner;
//uva 122
public class TreesOnTheLevel {
	public static Node root;
	public static boolean repeat;
	public static int nn,nv;
	public static class Node{
		int value;
		Node left,right;
		public Node(int val,Node left,Node right){
			this.value = val;
			this.left = left;
			this.right = right;
		}
	}
	public static void add(int val,String pos){
		Node cur = root;
		for(int i=0;i<pos.length();i++){
			if(pos.charAt(i)=='L'){
				if(cur.left==null)	{cur.left = new Node(-1, null, null);nn++;}
				cur = cur.left;
			}
			else if(pos.charAt(i)=='R'){
				if(cur.right==null)	{cur.right = new Node(-1, null, null);nn++;}
				cur = cur.right;
			}
		}
		if(cur.value!=-1)	repeat = true;
		cur.value = val;
		nv++;
	}
	public static void print(){
		if(repeat) 	{System.out.println("not complete");return;}
		if(nn>nv)	{System.out.println("not complete");return;}
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(root);
		while(!q.isEmpty()){
			Node cur = q.removeFirst();
			if(cur!=root)	System.out.print(' ');
			System.out.print(cur.value);
			if(cur.left!=null)	q.add(cur.left);
			if(cur.right!=null)	q.add(cur.right);
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			repeat = false;
			nv = 0;
			nn = 1;
			root = new Node(-1, null, null);
			while(true){
				String string = in.next();
				if(string.equals("()")) break;
				int coma = string.indexOf(',');
				int val = Integer.parseInt(string.substring(1,coma));
				String pos = string.substring(coma+1,string.length()-1);
				add(val, pos);
			}
			print();
		}
		in.close();
	}

}
