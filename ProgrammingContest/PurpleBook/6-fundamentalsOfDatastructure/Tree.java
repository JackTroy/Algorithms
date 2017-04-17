import java.util.Scanner;
//uva 548
public class Tree {
	public int[] inOrder,postOrder,left,right;
	public int n,root,sum,leaf;
	public Tree(String in,String post){
		inOrder = new int[10000];
		postOrder = new int[10000];
		left = new int[10000];
		right = new int[10000];
		n = read(in, post);
		root = build(0, n-1, 0, n-1);
		sum = Integer.MAX_VALUE;
		dfsSearch(root, 0);
	}
	public int read(String in,String post){
		String[] ins = in.split(" ");
		String[] posts = post.split(" ");
		int i;
		for(i=0;i<ins.length;i++){
			inOrder[i] = Integer.parseInt(ins[i]);
			postOrder[i] = Integer.parseInt(posts[i]);
		}
		return i;	
	}
	public int build(int ilo,int ihi,int plo,int phi){
		if(ilo>ihi)	return 0;
		int tmproot = postOrder[phi];
		int index = 0;
		while(inOrder[index]!=tmproot)	index++;
		left[tmproot] = build(ilo, index-1, plo, plo+index-ilo-1);
		right[tmproot] = build(index+1, ihi, plo+index-ilo, phi-1);
		return tmproot;
	}
	public void dfsSearch(int node,int s){
		s+=node;
		if(left[node]==0&&right[node]==0){
			if(s<sum) {sum = s;leaf = node;}
			else if(s==sum&&node<leaf)	leaf = node;
		}
		if(left[node]!=0) 	dfsSearch(left[node], s);
		if(right[node]!=0) 	dfsSearch(right[node], s);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			String inline = in.nextLine(),postline = in.nextLine();
			Tree t = new Tree(inline,postline);
			System.out.println(t.leaf);
		}
		in.close();
	}

}
