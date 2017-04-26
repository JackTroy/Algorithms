import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class GraduateAdmission {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	int n, m, k, quota[], ge[], gi[], pref[][];
	double ga[];
	Integer strank[];
	boolean exceed[], admited[];
	public void solve() throws NumberFormatException, IOException{
		while(true){
			String str = in.readLine();	if(str == null)	break;
			StringTokenizer tokenizer = new StringTokenizer(str);
			n = Integer.parseInt(tokenizer.nextToken());
			m = Integer.parseInt(tokenizer.nextToken());
			k = Integer.parseInt(tokenizer.nextToken());
			tokenizer = new StringTokenizer(in.readLine());
			quota = new int[m];
			ge = new int[n];
			gi = new int[n];
			ga = new double[n];
			pref = new int[n][k];
			strank = new Integer[n];
			exceed = new boolean[m];
			admited = new boolean[n];
			ArrayList<Integer>[] admission = (ArrayList<Integer>[]) new ArrayList[m];
			for(int i = 0; i < m; i++)	{
				quota[i] = Integer.parseInt(tokenizer.nextToken());
				admission[i] = new ArrayList<>();
			}
			for(int i = 0; i < n; i++){
				strank[i] = i;
				tokenizer = new StringTokenizer(in.readLine());
				ge[i] = Integer.parseInt(tokenizer.nextToken());
				gi[i] = Integer.parseInt(tokenizer.nextToken());
				ga[i] = (ge[i] + gi[i]) / 2.0;
				for(int j = 0; j < k; j++)	pref[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
			Arrays.sort(strank, new Comparator<Integer>() {

				public int compare(Integer o1, Integer o2) {
					if(ga[o1] > ga[o2])			return -1;
					else if(ga[o1] < ga[o2])	return 1;
					else{
						if(ge[o1] > ge[o2])			return -1;
						else if(ge[o1] < ge[o2])	return 1;
						else						return 0;
					}
				}
			});
			
			int curRank = 0;
			while(curRank < n){
				int lastRank = curRank + 1;
				while(lastRank < n && ga[strank[curRank]] == ga[strank[lastRank]] 
						&& ge[strank[curRank]] == ge[strank[lastRank]])	lastRank++;
				for(int j = 0; j < m; j++)
					if(quota[j] <= 0)
						exceed[j] = true;
				HashSet<Integer> sch = new HashSet<>();
				for(int i = 0; i < k; i++){
					
					for(int j = curRank; j < lastRank; j++){
						int school = pref[strank[j]][i];
						if(!admited[strank[j]] && !exceed[school]){
							quota[school]--;
							admited[strank[j]] = true;
							admission[school].add(strank[j]);
							sch.add(school);
						}
					}
					
				}
				curRank = lastRank;
				for(Integer s:sch)
					if(quota[s] <= 0)
						exceed[s] = true;
			}
			for(int i = 0; i < m; i++){
				Collections.sort(admission[i]);
				if(admission[i].size() > 0)	out.append(admission[i].get(0));
				for(int j = 1; j < admission[i].size(); j++)
					out.append(" " + admission[i].get(j));
				out.append("\n");
			}
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		GraduateAdmission a = new GraduateAdmission();
		a.solve();
	}

}
