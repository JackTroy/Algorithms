import java.util.Arrays;

public class BinarySearch {
	
	public static int lowerBound(int[] a, int key){
		int x = 0, y = a.length - 1, m;
		while(x < y){
			m = (x + y) / 2;
			if(a[m] >= key) y = m;
			else			x = m + 1;
		}
		return x;
	}
	public static int upperBound(int[] a, int key){
		int x = 0, y = a.length - 1, m;
		while(x < y){
			m = (x + y) / 2;
			if(a[m] <= key) x = m + 1;
			else			y = m;
		}
		return x;
	}
	public static int bs(int[] a, int key){
		int x = 0, y = a.length - 1, m;
		while(x < y){
			m = (x + y) / 2;
			if(a[m] == key)		return m;
			else if(a[m] < key)	x = m + 1;
			else				y = m - 1;
		}
		return -1;
	}
	public static void main(String[] args) {
		int a[] = {1, 23, 3124, 9, 55, 87, 13221, 9};
		Arrays.sort(a);
		System.out.println(bs(a, 23));
		//for key belong to [L,R) , return L & R
		System.out.println(lowerBound(a, 9));
		System.out.println(upperBound(a, 9));
	}
}
