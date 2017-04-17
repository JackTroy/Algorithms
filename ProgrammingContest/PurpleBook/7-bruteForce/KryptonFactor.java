import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KryptonFactor {
	public int cnt,n,L;
	public KryptonFactor(){}
	public void solve() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String line = reader.readLine();	if(line.equals("0 0"))	break;
			String[] s = line.split(" ");
			cnt=0; 
			n = Integer.parseInt(s[0]); 
			L = Integer.parseInt(s[1]);
			dfs("");
		}
	}
	public boolean dfs(String s){
		if(cnt++ == n){
			int i;
			for(i=0;i<s.length();i++){
				if(i%64!=0&&i%4==0)	System.out.print(' ');
				System.out.print(s.charAt(i));
				if(i==63)	System.out.println();
			}
			if(i!=63) System.out.println();
			System.out.println(s.length());
			return true;
		}
		for(int i=0;i<L;i++){
			String tmp = s + (char)('A'+i);
			boolean ok = true;
			for(int j=1;2*j<tmp.length()+1;j++){
				boolean equal = true;
				for(int k = 0;k<j;k++)
					if(tmp.charAt(tmp.length()-1-k)!=tmp.charAt(tmp.length()-1-j-k))
					{
						equal = false;break;
					}
				if(equal)	{ok = false; break;}
			}
			if(ok)	if(dfs(tmp))	return true;
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		KryptonFactor a = new KryptonFactor();
		a.solve();
	}

}
