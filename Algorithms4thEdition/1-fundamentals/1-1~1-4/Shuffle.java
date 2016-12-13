

import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {
	public static void shuffle(int a[]){
		int len=a.length;
		for(int i=len;i>0;i--){
			int j=(int) (StdRandom.uniform()*(i-1));
			int temp=a[j];
			a[j]=a[i-1];
			a[i-1]=temp;
		}
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int[] a=new int[]{0,1,2,3,4,5,6};
		shuffle(a);
		for(int i=0;i<a.length;i++)
		System.out.print(a[i]);
	}
	
}
