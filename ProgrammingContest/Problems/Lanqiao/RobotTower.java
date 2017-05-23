import java.util.Scanner;

public class RobotTower {
	public static int m, n;
	public static Long dfs(int curm, int curn, String lastlayer){
		Long ans = new Long(0);
		if(curm == m && curn == n)	return (long)1;
		if(lastlayer.length() == 0){
			if(curm + 1 <= m)	ans += dfs(curm + 1, curn, "A");
			if(curn + 1 <= n)	ans += dfs(curm, curn + 1, "B");
		}
		else{
			String nextlayer = "", nextlayer_alt = "";;
			int nextm = 0, nextn = 0;
			if(lastlayer.charAt(0) == 'A') {
				nextlayer += "AA";
				nextlayer_alt += "BB";
				nextm += 2;
			}
			else{
				nextlayer += "AB";
				nextlayer_alt += "BA";
				nextm++;
				nextn++;
			}
			for(int i = 1; i < lastlayer.length(); i++){
				if(lastlayer.charAt(i) == 'A'){
					if(nextlayer.charAt(i) == 'A'){
						nextlayer += "A";
						nextlayer_alt += "B";
						nextm++;
					}
					else{
						nextlayer += "B";
						nextlayer_alt += "A";
						nextn++;
					}
				}
				else{
					if(nextlayer.charAt(i) == 'A'){
						nextlayer += "B";
						nextlayer_alt += "A";
						nextn++;
					}
					else{
						nextlayer += "A";
						nextlayer_alt += "B";
						nextm++;
					}
				}
			}
			if(curm + nextm <= m && curn + nextn <= n){
				ans += dfs(curm + nextm, curn + nextn, nextlayer);
			}
			if(curm + nextn <= m && curn + nextm <= n){
				ans += dfs(curm + nextn, curn + nextm, nextlayer_alt);
			}
		}
		return ans;
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		m = in.nextInt();
		n = in.nextInt();
		in.close();
		
		Long ans = dfs(0, 0, "");
		System.out.println(ans);
	}
}
