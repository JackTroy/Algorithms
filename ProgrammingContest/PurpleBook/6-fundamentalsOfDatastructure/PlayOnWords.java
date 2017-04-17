import java.util.Scanner;
//uva 10129
public class PlayOnWords {
	public boolean[] exist;
	public int[][] G;
	public int[] degree;
	public Scanner in;
	public PlayOnWords(){}
	public void solve(){
		in = new Scanner(System.in);
		int c = in.nextInt();
		while(c-->0){
			G = new int[26][26];
			degree = new int[26];
			exist = new boolean[26];
			int n = in.nextInt();
			for(int i=0;i<n;i++){
				String word = in.next();
				int a = index(word.charAt(0)),b = index(word.charAt(word.length()-1));
				exist[a] = true;exist[b] = true;
				G[a][b]++;G[b][a]++;
				//degree out + in -;
				degree[a]++;degree[b]--;
			}
			boolean is = false;
			if(isConnected()) if(isEuler()) is = true;
				
			if(is)	System.out.println("Ordering is possible.");
			else	System.out.println("The door cannot be opened.");
			}
		in.close();
	}
	public boolean isConnected(){
		int[] idx = new int[26];
		for(int i=0;i<26;i++){
			if(exist[i])	{dfs(i,idx);break;}
		}
		for(int i=0;i<26;i++)
			if(exist[i]&&idx[i]!=1)
				return false;	
		return true;
	}
	public boolean isEuler(){
		boolean odd = false;
		int odd1 = 0,odd2 = 0;
		for(int i=0;i<26;i++){
			if(!exist[i])		continue;
			if(degree[i]==0)	continue;
			else{
				odd = true;
				if(degree[i]==-1) 		odd1++;
				else if(degree[i]==1)	odd2++;
				else 					return false;
			}
		}
		if(!odd)				return true;
		if(odd1==1&&odd2==1)	return true;
		else					return false;
		
	}
	public void dfs(int v,int[] idx){
		idx[v] = 1;
		for(int i=0;i<26;i++){
			if(G[v][i]>0&&idx[i]==0)
				dfs(i, idx);
		}
	}
	public static int index(char c){
		return c-'a';
	}
	public static void main(String[] args) {
		PlayOnWords a = new PlayOnWords();
		a.solve();
	}
}
