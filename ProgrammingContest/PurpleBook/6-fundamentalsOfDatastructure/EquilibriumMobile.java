import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class EquilibriumMobile {
	public EquilibriumMobile(){}
	public void solve() throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(r.readLine());
		while(cases-->0){
			String m = r.readLine();
			ArrayList<Long> sums = new ArrayList<Long>();
			int l = 0,idx = 0,N = 0;
			while(idx<m.length()){
				char c = m.charAt(idx);
				if(c=='[')		l++;
				else if(c==']')	l--;
				else if(c==',') ;
				else{
					N++;
					int i = idx+1;
					while(i<m.length()&&m.charAt(i)<='9'&&m.charAt(i)>='0')	i++;
					long sum = Long.parseLong(m.substring(idx,i))<<l;
					sums.add(sum);
					idx = i - 1;
				}
				idx++;
			}
			Collections.sort(sums);
			int cnt = 1,max = 1;
			for(int i=1;i<N;i++){
				if(sums.get(i).equals(sums.get(i-1)))	cnt++;
				else							cnt = 1;
				if(cnt>max)	max = cnt;
			}
			System.out.println(N-max);
		}
	}
	public static void main(String[] args) throws IOException {
		EquilibriumMobile a = new EquilibriumMobile();
		a.solve();
	}

}
