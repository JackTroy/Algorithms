import java.util.Arrays;
import java.util.Scanner;
//Flooded! UVA - 815
public class Flooded {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int rnd=0;
		while(true){
			int n = in.nextInt(),m=in.nextInt();
			if(n==0&&m==0)	break;
			
			double[] heights=new double[n*m+1];
			for(int i=0;i<n*m;i++)
				heights[i]=in.nextInt();
			heights[n*m] = Double.POSITIVE_INFINITY;
			Arrays.sort(heights);
			
			double totalHeight = in.nextInt()/100.0;
			double aveHeight=0;
			int i;
			for(i=1;i<=n*m;i++){
				totalHeight+=heights[i-1];
				aveHeight = totalHeight/i;
				if(heights[i]>aveHeight)	break;
			}
			System.out.println("Region "+(++rnd));
			System.out.println(String.format("Water level is %.2f meters.", aveHeight));
			System.out.println(String.format("%.2f percent of the region is under water.",(double)i/(n*m)*100));
			//do notice the exact output requirement such as blank line after every case
			System.out.println();
		}
		in.close();
	}

}
