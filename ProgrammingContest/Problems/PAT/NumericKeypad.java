import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NumericKeypad {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	//i 0 1 2 3, j 0 1 2
	static final int[][] keypad = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
			{-1, 0, -1},
	};
	
	ArrayList<Integer>[] adj;
	public void solve() throws NumberFormatException, IOException{
		adj = (ArrayList<Integer>[]) new ArrayList[10];
		for(int i = 0; i < 10; i++)	adj[i] = new ArrayList<Integer>();
		adj[0].add(0);
		for(int i = 0; i < 10; i++) adj[1].add(i);
		adj[2].add(0);
		adj[2].add(2);
		adj[2].add(3);
		adj[2].add(5);
		adj[2].add(6);
		adj[2].add(8);
		adj[2].add(9);
		adj[3].add(3);
		adj[3].add(6);
		adj[3].add(9);
		adj[4].add(0);
		for(int i = 4; i < 10; i++) adj[4].add(i);
		adj[5].add(0);
		adj[5].add(5);
		adj[5].add(6);
		adj[5].add(8);
		adj[5].add(9);
		adj[6].add(6);
		adj[6].add(9);
		adj[7].add(0);
		adj[7].add(7);
		adj[7].add(8);
		adj[7].add(9);
		adj[8].add(0);
		adj[8].add(8);
		adj[8].add(9);
		adj[9].add(9);
		
		int t = Integer.parseInt(in.readLine());
		while(t-- > 0){
			String k = in.readLine();
			int[] ans = new int[k.length()];
			for(int i = 0; i < k.length(); i++){
				int n1 = Integer.parseInt(k.substring(i, i + 1));
				int n2 = Integer.parseInt(k.substring(i + 1, i + 2));
				if(n1 == n2 || adj[n1].contains(n2)){
					ans[i] += n1;
					if(i == k.length() - 2){
						ans[i + 1] = n2;
						break;
					}
				}
				else{
					boolean exist = false;
					for(int j = adj[n1].size() - 1; j >= 0; j--){
						int next = adj[n1].get(j);
						if(next < n2){
							exist = true;
							ans[i] = n1;
							ans[i + 1] = next;
							for(int z = i + 2; z < ans.length; z++)
								ans[z] = adj[ans[z - 1]].get(adj[ans[z - 1]].size() - 1);
						}
					}
					if(exist)	break;
					int index = i;
					while(index >= 0 && Integer.parseInt(k.substring(index, index + 1)) == n1)
						index--;
					int pre;
					if(index >= 0)	pre = Integer.parseInt(k.substring(index, index + 1));
					else			pre = 1;
					for(int j = adj[pre].size() - 1;j >= 0; j--)
						if(adj[pre].get(j) < n1){
							ans[index + 1] = adj[pre].get(j);
							break;
						}
					for(int j = index + 2; j < ans.length; j++)
						ans[j] = adj[ans[j - 1]].get(adj[ans[j - 1]].size() - 1);
					break;
				}
			}
			for(int i = 0; i < ans.length; i++)
				out.append(ans[i]);
			out.append("\n");
		}
		System.out.print(out.toString());
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		NumericKeypad a = new NumericKeypad();
		a.solve();
	}

}
