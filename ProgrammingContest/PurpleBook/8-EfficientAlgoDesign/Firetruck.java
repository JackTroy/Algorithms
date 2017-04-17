import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Firetruck {
//Firetruck UVA - 208 
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	public int[][] map;
	public int totalRoute;
	public int[] marked;
	public int[] reach;
	public int des;
	public LinkedList<Integer> route;
	public void solve() throws IOException{
		int rnd = 0;
		while(true){
			String line = in.readLine();	if(line == null)	break;
			des = Integer.parseInt(line);
			map = new int[21][21];
			marked = new int[21];
			totalRoute = 0;
			reach = new int[21];
			route = new LinkedList<Integer>();
			while(true){
				String street = in.readLine();	if(street.equals("0 0"))	break;
				String[] scs = street.split(" ");
				//System.out.println(street);
				int c1 = Integer.parseInt(scs[0]), c2 = Integer.parseInt(scs[1]);
				map[c1][c2] = 1;
				map[c2][c1] = 1;
			}
			out.append("CASE "+(++rnd)+":\n");
			marked[des] = 1;
			check(des);
			Arrays.fill(marked, 0);
			marked[1] = 1;
			route.add(1);
			dfs(1);
			out.append(String.format("There are %d routes from the firestation to streetcorner %d.\n", totalRoute, des));
			
		}
		System.out.print(out.toString());
	}
	public void check(int u){
		reach[u] = 1;
		for(int i = 1; i < 21; i++){
			if(map[u][i] == 1 && marked[i] == 0){
				marked[i] = 1;
				check(i);
			}	
		}
	}
	public void dfs(int u){
		if(u == des){
			totalRoute++;
			for(Integer i:route){
					if(i != 1)	out.append(" ");
					out.append(i);
				}
			out.append("\n");		
		}
		for(int i = 1; i < 21; i++){
			if(map[u][i] == 1 && marked[i] == 0 && reach[i] == 1){
				marked[i] = 1;
				route.add(i);
				dfs(i);
				marked[i] = 0;
				route.removeLast();
			}	
		}
	}
	public static void main(String[] args) throws IOException {
		Firetruck a = new Firetruck();
		a.solve();
	}
}
