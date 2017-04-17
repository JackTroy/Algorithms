import java.util.Scanner;

public class TreeRecovery {
	public Scanner in;
	public int[] left,right;
	public int root;
	public String preo,ino;
	public TreeRecovery(){}
	public void solve(){
		in = new Scanner(System.in);
		while(in.hasNext()){
			left = new int[26];	right = new int[26];
			preo = in.next();ino = in.next();
			root = build(0,preo.length()-1,0,ino.length()-1);
			print(root);
			System.out.println();
		}
		in.close();
	}
	public int build(int prelo,int prehi,int inlo,int inhi){
		if(prelo>prehi)	return -1;
		//System.out.println(String.format("lo %d hi %d", prelo,prehi));
		int p = ID(preo.charAt(prelo));
		int index = inlo;
		while(ID(ino.charAt(index))!=p)	index++;
		//System.out.println("index:"+index);
		left[p] = build(prelo+1, prelo+index-inlo, inlo, index-1);
		right[p] = build(prelo+index-inlo+1, prehi, index+1, inhi);
		return p;
	}
	public void print(int node){
		if(left[node]!=-1)	print(left[node]);
		if(right[node]!=-1)	print(right[node]);
		System.out.print((char)('A'+node));
	}
	public int ID(char c){
		return c-'A';
	}
	public static void main(String[] args) {
		TreeRecovery a = new TreeRecovery();
		a.solve();

	}

}
