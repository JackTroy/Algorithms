import java.util.Scanner;
//uva 699
public class TheFallinLeaves {
	public static Scanner in = new Scanner(System.in);
	public static void build(int[] sum,int pos){
		int weight = in.nextInt();
		if(weight==-1)	return;
		sum[pos]+=weight;
		build(sum, pos-1);
		build(sum, pos+1);
	}
	
	public static void main(String[] args) {
		int c = 0;
		while(true){
			int root = in.nextInt();
			int[] sum = new int[100];
			if(root==-1)	break;
			sum[50] = root;
			build(sum, 49);
			build(sum, 50);
			System.out.println(String.format("Case %d:", ++c));
			boolean first = true;
			for(int i=0;i<10000;i++){
				if(sum[i]>0){
					if(first)	{first=!first;}
					else		System.out.print(' ');
					System.out.print(sum[i]);
				}
					
			}
			System.out.print("\n\n");
		}
		in.close();
	}

}
