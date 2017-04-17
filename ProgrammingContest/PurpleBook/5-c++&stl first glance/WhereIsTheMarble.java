import java.util.Arrays;
import java.util.Scanner;

public class WhereIsTheMarble {
//uva 10474
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int rnd=0;
		while(in.hasNext()){
			int n = in.nextInt();
			int qus = in.nextInt();
			if(n==0&&qus==0)	break;
			int[] a = new int[n];
			for(int i=0;i<n;i++)
				a[i]=in.nextInt();
			Arrays.sort(a);
			System.out.println("CASE# "+(++rnd)+":");
			for(int i=0;i<qus;i++){
				int key = in.nextInt();
				int index = Arrays.binarySearch(a, key);
				if(index<0)	System.out.println(key+" not found");
				else{
					//search lower bound,may result in 
					//bad performance if too many same elements
					while(index-1>=0&&a[index-1]==key)	index--;	
					System.out.println(key+" found at "+(index+1));
				}	
			}
		}
		in.close();
	}

}
