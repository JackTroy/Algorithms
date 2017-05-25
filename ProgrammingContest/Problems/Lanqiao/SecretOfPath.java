import java.util.Scanner;

public class SecretOfPath {
	static int n, sum, x[], y[], path[], x_inprogress[], y_inprogress[];
	static boolean marked[][];
	static final int dims[][] = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1},
	};
	static boolean legal(int xi, int yi){
		return xi >= 0 && xi < n && yi >= 0 && yi < n;
 	}
	static boolean checkPath(){
		for(int i = 0; i < n; i++)
			if(x[i] != x_inprogress[i] || y[i] != y_inprogress[i])
				return false;
		return true;
	}
	static boolean dfs(int xi, int yi, int step){
		x_inprogress[xi]++;
		y_inprogress[yi]++;
		marked[xi][yi] = true;
		path[step] = yi * n + xi;
		
		if(step == sum / 2 - 1){
			return xi == n-1 && yi == n-1 && checkPath();
		}
		else{
			for(int i = 0; i < 4; i++){
				int nxi = xi + dims[i][0], nyi = yi + dims[i][1];
				if(legal(nxi, nyi) && !marked[nxi][nyi] && 
						x_inprogress[nxi] < x[nxi] && y_inprogress[nyi] < y[nyi]){
					if(dfs(nxi, nyi, step + 1))	return true;
				}
			}
		}
		
		x_inprogress[xi]--;
		y_inprogress[yi]--;
		marked[xi][yi] = false;
		return false;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		x = new int[n];
		y = new int[n];
		x_inprogress = new int[n];
		y_inprogress = new int[n];
		marked = new boolean[n][n];
		sum = 0;
		for(int i = 0; i < n; i++)	{	x[i] = in.nextInt(); sum += x[i];	}
		for(int i = 0; i < n; i++)	{	y[i] = in.nextInt(); sum += y[i];	}
		path = new int[sum / 2];
		dfs(0, 0, 0);
		for(int i = 0; i < sum / 2; i++)
			System.out.print(path[i] + " ");
		in.close();
		
	}

}
