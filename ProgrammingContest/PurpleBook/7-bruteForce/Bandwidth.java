import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bandwidth {
	public int bOfG;
	public boolean G[][];
	public boolean Ls[];
	public int n;
	public String ans;
	public void solve() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String line = reader.readLine();	if(line.equals("#"))	break;
			G = new boolean[26][26];
			Ls = new boolean[26];
			n = 0;
			bOfG = Integer.MAX_VALUE;
			String[] vs = line.split(";");
			for(int i=0;i<vs.length;i++){
				String edge = vs[i];
				int v = edge.charAt(0)-'A';
				Ls[v] = true;
				for(int j = 2;j<edge.length();j++){
					int  s = edge.charAt(j)-'A';
					Ls[s] = true;
					G[v][s] = true;
					G[s][v] = true;
				}
					
			}
			for(int i=0;i<26;i++)
				if(Ls[i])	n++;
			dfs("");
			for(int i=0;i<n;i++)
				System.out.print(ans.charAt(i)+" ");
			System.out.println("-> "+bOfG);
		}
	}
	public void dfs(String s){
		if(s.length()==n){
			int b = compute(s);
			//System.out.println(s+b);
			if(b<bOfG)	{bOfG = b; ans = s;}
			return;
		}
		for(int i=0;i<26;i++){
			if(!Ls[i])	continue;
			char c = (char) ('A'+i);
			if(s.indexOf(c)!=-1)	continue;
			//String tmp = s+c;
			int maxb = 0;
			for(int j=0;j<s.length();j++){
				if(G[s.charAt(j)-'A'][c-'A']){
					maxb = s.length()-j;
					break;
				}		
			}
			if(maxb<bOfG)	dfs(s+c);
		}
	}
	public int compute(String s){
		//System.out.println(s);
		int maxb = Integer.MIN_VALUE;
		for(int i = 0;i<s.length()-1;i++){
			for(int j=i+1;j<s.length();j++){
				//System.out.println(i+" "+j);
				if(G[s.charAt(i)-'A'][s.charAt(j)-'A'])
					if(maxb<j-i)	maxb = j-i;
			}
		}
		return maxb;
	}
	public static void main(String[] args) throws IOException {
		Bandwidth a = new Bandwidth();
		a.solve();

	}

}
