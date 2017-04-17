import java.util.Scanner;

public class OptimalPairs3D {

	public static int n;
	public static double[][] points;
	public static double[] d;
	public static double dist(int i, int j){
		return Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + 
				Math.pow(points[i][1] - points[j][1], 2) +
				Math.pow(points[i][2] - points[j][2], 2));
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		points = new double[n][3];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < 3; j++)
				points[i][j] = in.nextDouble();
		d = new double[1 << n];
		d[0] = 0;
		for(int s = 1; s < (1 << n); s++){
			int i, j;
			d[s] = Double.MAX_VALUE;
			for(i = 0; i < n; i++)
				if((s & (1 << i)) != 0)	break;
			for(j = i + 1; j < n; j++){
				if((s & (1 << j)) == 0)	continue;
				d[s] = Math.min(d[s], dist(i, j) + d[s ^ (1 << i) ^ (1 << j)]);
			}
		}
		System.out.println(d[(1 << n) - 1]);
		in.close();
	}

}
