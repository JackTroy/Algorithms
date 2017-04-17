import java.util.Scanner;

public class Floyd {
	public int v, e;
	public double[][] dist;
	public boolean[][] edge;
	public final double INF = 1000.0;
	public void init(){
		Scanner in = new Scanner(System.in);
		v = in.nextInt();	e = in.nextInt();
		dist = new double[v][v];	edge = new boolean[v][v];
		for(int i = 0; i < v; i++)
			for(int j = 0; j < v; j++)
				dist[i][j] = INF;
		for(int i = 0; i < e; i++){
			int v1 = in.nextInt(), v2 = in.nextInt();
			double w = in.nextDouble();
			edge[v1][v2] = true;
			dist[v1][v2] = w;
		}
		for(int i = 0; i < v; i++){	
			dist[i][i] = 0;
			edge[i][i] = true;
		}
		in.close();
	}
	public void floyd(){
		for(int k = 0; k < v; k++)
			for(int i = 0; i < v; i++)
				for(int j = 0; j < v; j++)
					if(edge[i][k] && edge[k][j])	{
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
						edge[i][j] = true;
					}
		System.out.println("finish");
	}
	public static void main(String[] args) {
		/*
		 * input format
		 * v
		 * e
		 * v1 v2 w (v1 to v2 with weight w)
		 * .........
		 */
		Floyd a = new Floyd();
		a.init();
		a.floyd();
	}
}
