import java.util.HashSet;

public class A
{
	public static int[] next = {-1, 1, -4, 4};
	public static HashSet<Integer> qualified;
	public static HashSet<Integer> cut;
	public static boolean[] marked;
	public static int cnt;
	public static void dfs(int u){
		for(int i = 0; i < 4; i++){
			if(i == 0 && u % 4 == 1)	continue;
			if(i == 1 && u % 4 == 0)	continue;
			int v = u + next[i];
			if(!(v > 0 && v < 13))	continue;
			if(cut.contains(v) && !marked[v]){
				marked[v] = true;
				dfs(v);
			}
		}
	}
	public static void combination(int cur, int selection){
		if(cur == 5){
			if(qualified.contains(selection))	return;
			else								qualified.add(selection);
			cut = new HashSet<Integer>();
			int seed = 0;
			for(int i = 1; i <= 12; i++){
				if((selection & (1 << i)) > 0){
					cut.add(i);
					seed = i;
				}
					
			}
			marked = new boolean[13];
			dfs(seed);
			for(Integer e:cut)
				if(!marked[e])
					return;
			cnt++;
		}
		for(int i = 1; i <= 12; i++){
			if((selection & (1 << i)) > 0)	continue;//have choose
			combination(cur + 1, selection ^ (1 << i));
		}
	}
	public static void main(String[] args)
	{
		cnt = 0;
		qualified = new HashSet<Integer>();
		combination(0, 0);
		System.out.println(cnt);
	}
}