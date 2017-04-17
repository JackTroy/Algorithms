import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.TreeSet;

public class LUCKYSTRING {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	public void solve() throws IOException{
		while(true){
			String str = in.readLine();	if(str == null)	break;
			char[] chars = str.toCharArray();
			TreeSet<String> set = new TreeSet<String>();
			int f1 = 1, f2 = 1;
			boolean[] isFib = new boolean[30];
			while(f1 < 30){
				isFib[f1] = true;
				int tmp = f2;
				f2 = f1 + f2;
				f1 = tmp;
			}
			HashSet<Character> diffchar = new HashSet<>();
			for(int i = 0; i < chars.length; i++)
				for(int j = i; j < chars.length; j++){
					String substr = str.substring(i, j + 1);
					//System.out.println(substr);
					if(!set.contains(substr)){
						diffchar.clear();
						for(int k = 0; k < substr.length(); k++)
							diffchar.add(substr.charAt(k));
						if(isFib[diffchar.size()])	set.add(substr);
					}
				}
			for(String s:set)	out.append(s + "\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws IOException {
		LUCKYSTRING a = new LUCKYSTRING();
		a.solve();
	}

}
