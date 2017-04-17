import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TangoTangoInsurrection {
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public char[] foot = {'.','L', 'R'};
	public int[] seq;
	public int[][][][] d, action;
	//i-th arrow, 1 foot pos, 2 foot pos, lseqt action
	//up 0 1 1 2 2 down 3
	public int n;
	public void solve() throws IOException{
		while(true){
			String line = in.readLine();	if(line.equals("#"))	break;
			n = line.length();
			int[] trans = new int[256];
			trans['U'] = 0;
			trans['L'] = 1;
			trans['R'] = 2;
			trans['D'] = 3;
			trans['.'] = -1;
			seq = new int[n];
			for(int i = 0; i < n; i++){
				seq[i] = trans[line.charAt(i)];
			}
			d = new int[n + 1][4][4][3];
			action = new int[n + 1][4][4][3];
			for(int i = n - 1; i >= 0; i--){
				for(int a = 0; a < 4; a++){
					for(int b = 0; b < 4; b++){
						for(int s = 0; s < 3; s++){
							d[i][a][b][s] = 10 * n;
							if(seq[i] == -1){
								update(i, a, b, s, 0, 0);
								for(int t = 0; t < 4; t++){
									update(i, a, b, s, 1, t);
									update(i, a, b, s, 2, t);
								}
							}
							else{
								update(i, a, b, s, 1, seq[i]);
								update(i, a, b, s, 2, seq[i]);
							}
						}
					}
				}
			}
			int a = 1, b = 2, s = 0;
			for(int i = 0; i < n; i++){
				int f = action[i][a][b][s] / 4;
				int t = action[i][a][b][s] % 4;
				System.out.print(foot[f] + "");
				s = f;
				if(f == 1)		a = t;
				else if(f == 2)	b = t;
			}
			System.out.println();
		}
	}
	public void update(int i, int a, int b, int s, int f, int t){
		int[] abe = energy(i, a, b, s, f, t);
		if(abe[2] < 0)	return;
		int cost = d[i + 1][abe[0]][abe[1]][f] + abe[2];
		if(d[i][a][b][s] > cost){
			d[i][a][b][s] = cost;
			action[i][a][b][s] = f * 4 + t;
		}
	}
	public int[] energy(int i,int a, int b, int s, int f, int t){
		int[] abe = new int[3];
		abe[0] = a;
		abe[1] = b;
		if (f == 1)
			abe[0] = t;
		else if (f == 2)
			abe[1] = t;
		abe[2] = -1;
		// check target arrows
		if (abe[0] == abe[1])
			return abe;
		if (abe[0] == 2 && abe[1] == 1)
			return abe;
		if (a == 2 && abe[1] != b)
			return abe; 
		if (b == 1 && abe[0] != a)
			return abe;

		// compute energy
		if (f == 0)
			abe[2] = 0; // no move
		else if (f != s)
			abe[2] = 1; // alternative foot, low energy
		else {
			if (f == 1)
				abe[2] = energy(a, abe[0]);
			else
				abe[2] = energy(b, abe[1]);
		}
		return abe;
	}
	public int energy(int a, int ta){
		if(a == ta)		return 3;
		if(a + ta == 3)	return 7;
		else			return 5;
	}
	public static void main(String[] args) throws IOException {
		TangoTangoInsurrection a = new TangoTangoInsurrection();
		a.solve();
	}

}
