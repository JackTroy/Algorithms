import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tour {
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public double[][] points;//n, x-y
	public double[][] d;
	public int n;
	public double dp(int i, int j){
		if(d[i][j] > 0)	return d[i][j];
		d[i][j] = Math.min(dp(i + 1, j) + dist(i, i + 1), dp(i + 1, i) + dist(i + 1, j));
		return d[i][j];
	}
	public void solve() throws IOException{
		while(true){
			String num = in.readLine();	if(num == null)	break;
			n = Integer.parseInt(num.trim());
			points = new double[n][2];
			for(int i = 0; i < n; i++){
				String[] xy = in.readLine().split(" ");
				points[i][0] = Double.parseDouble(xy[0]);
				points[i][1] = Double.parseDouble(xy[1]);
			}
			d = new double[n][n];
			double toDes = dist(n - 1, n - 2);
			for(int i = 0; i < n - 2; i++)
				d[n - 2][i] = toDes + dist(n - 1, i);
			double ans = dp(0, 0);
			out.append(String.format("%.2f\n", ans));
		}
		System.out.print(out.toString());
	}
	public double dist(int i, int j){
		double x = points[i][0];
		double y = points[i][1];
		double x1 = points[j][0];
		double y1 = points[j][1];
		return Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
	}
	public static void main(String[] args) throws IOException {
		Tour a = new Tour();
		a.solve();
	}

}
