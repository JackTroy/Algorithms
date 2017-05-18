
public class UnionFindSet {
	public static int NumOfSet, pa[];
	public static void init(int num){
		NumOfSet = num;
		pa = new int[NumOfSet];
		for(int i = 0; i < NumOfSet; i++)	pa[i] = i;
	}
	public static int find(int x){
		return pa[x] != x ? pa[x] = find(pa[x]) : x;
	}
	public static void union(int x, int y){
		pa[x] = y;
	}
	public static void main(String[] args) {

	}
}
