import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
	public static int v, e;
	public static int[][] edges;//for every edge ,save u and v
	public static int[] ufs;
	public static int find(int n){
		if(ufs[n] == n)	return n;
		else{
			ufs[n] = find(ufs[n]);
			return ufs[n];
		}
	}
	public static Integer[] edgesIndex;
	public static Double[] edgesWieght;
	public static class WeightComparator implements Comparator<Integer>{
		public int compare(Integer o1, Integer o2) {
			if(edgesWieght[o1] < edgesWieght[o2])		return -1;
			else if(edgesWieght[o1] > edgesWieght[o2])	return 1;
			return 0;
		}
	}
	public static double kruskal(){
		Arrays.sort(edgesIndex,new WeightComparator());
		double ans = 0;
		ufs = new int[v];
		for(int i = 0; i < v; i++)	ufs[i] = i;
		for(int i = 0; i < e; i++){
			double weight = edgesWieght[edgesIndex[i]];
			int u = edges[edgesIndex[i]][0], v = edges[edgesIndex[i]][1];
			int rootu = find(u), rootv = find(v);
			if(rootu == rootv)	continue;
			ufs[rootu] = rootv;
			ans += weight;
			System.out.println(u + " " + v);
		}
		return ans;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		v = in.nextInt();	e = in.nextInt();
		edges = new int[e][2];
		edgesIndex = new Integer[e];
		edgesWieght = new Double[e];
		for(int i = 0; i < e; i++){
			int u = in.nextInt(), v = in.nextInt();
			double weight = in.nextDouble();
			edges[i][0] = u;	edges[i][1] = v;
			edgesIndex[i] = i;
			edgesWieght[i] = weight;
		}
		kruskal();
		System.out.println("finish");
		in.close();
	}

}
