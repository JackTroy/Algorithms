import java.util.ArrayList;

public class CoreOfTree {
	
	public static int[] d;
	public static int max;
	public static ArrayList<Integer>[] s;
	public static int dp(int u){
		for(int i = 0; i < s[u].size(); i++)
			d[u] += dp(s[u].get(i));
		if(d[u]++ > d[max])	max = u;
		return d[u];
	}
	public static void main(String[] args) {
		

	}

}
