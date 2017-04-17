import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TheRotationGame {
//The Rotation Game UVA - 1343
/* map
      00    01
      02    03
04 05 06 07 08 09 10
      11    12
13 14 15 16 17 18 19
      20    21
      22    23
*/
	public char[] ans;
	public int[] a = new int[24];
	public int map[][] = {
			  { 0, 2, 6,11,15,20,22}, // A
			  { 1, 3, 8,12,17,21,23}, // B
			  {10, 9, 8, 7, 6, 5, 4}, // C
			  {19,18,17,16,15,14,13}, // D
			  {23,21,17,12,8,3,1},//E
			  {22,20,15,11,6,2,0},//F
			  {13,14,15,16,17,18,19},//G
			  {4,5,6,7,8,9,10},//H
	};
	public int center[] = {6,7,8,11,12,15,16,17};
	public int rev[] = {5, 4, 7, 6, 1, 0, 3, 2};
	public void solve() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		while(true){
			String line = in.readLine();	if(line.equals("0"))	break;
			StringTokenizer p = new StringTokenizer(line);
			for(int i = 0; i < 24; i++)	{
				a[i] = Integer.parseInt(p.nextToken());
				//System.out.print(a[i]);
			}
			if(isCorrect()){
				out.append("No moves needed\n" + a[6] + "\n");
				continue;
			}
			ans = new char[100];
			for(int maxd = 1;; maxd++)
				if(dfs(0,maxd))	break;
			for(int i = 0; ans[i] != 'Z'; i++)	out.append(ans[i]);
			out.append("\n" + a[6] + "\n");
		}
		System.out.print(out.toString());
	}
	public boolean isCorrect(){
		for(int i = 1; i < center.length; i++)
			if(a[center[0]] != a[center[i]])	
				return false;
		
		return true;
	}
	public int h(){
		int c1 = 0,c2 = 0,c3 = 0;
		for(int i = 0; i < center.length; i++){
			int n = a[center[i]];
			if(n == 1)		c1++;
			else if(n == 2) c2++;
			else			c3++;
		}
		return 8 - Math.max(c1, Math.max(c2, c3));
	}
	public void move(int n){
		int tmp = a[map[n][0]];
		for(int i = 0; i < 6; i++){
			a[map[n][i]] = a[map[n][i+1]];
		}
		a[map[n][6]] = tmp;
	}
	public boolean dfs(int d,int maxd){
		if(isCorrect())		{
			ans[d] = 'Z';
			return true;
		}
		if(d + h() > maxd)	return false;
		
		for(int i = 0; i < 8; i++){
			ans[d] = (char) ('A'+i);
			move(i);
			if(dfs(d+1, maxd))	return true;
			move(rev[i]);
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TheRotationGame aGame = new TheRotationGame();
		aGame.solve();
	}

}
