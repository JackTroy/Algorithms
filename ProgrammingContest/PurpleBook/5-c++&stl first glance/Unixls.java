import java.util.Arrays;
import java.util.Scanner;

public class Unixls {
//uva 400
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt();
			String[] names = new String[n];
			int maxL=0;
			for(int i=0;i<n;i++){
				names[i] = in.next();
				if(names[i].length()>maxL)	maxL = names[i].length();
			}
			Arrays.sort(names);
				
			int cols = 60/(maxL+2);
			if(60-cols*(maxL+2)>=maxL)	cols++;
			int rows = n/cols;
			if(n%cols!=0)	rows++;
			for(int i=0;i<60;i++)
				System.out.print('-');
			System.out.println();
			for(int r=0;r<rows;r++){
				for(int c=0;c<cols;c++){
					int index = c*rows+r;
					if(index>=n)	break;
					System.out.print(names[index]);
					if(names[index].length()<maxL){
						for(int j = names[index].length();j<maxL;j++)
							System.out.print(" ");
					}
					if(c!=cols-1)	System.out.print("  ");
				}
				System.out.println();
			}	
		}
		in.close();
	}
}
