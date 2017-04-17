import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class BrokenKeyboard {

	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		while(true){
			String s = in.readLine();	if(s == null)	break;
			char[] cs = (" " + s).toCharArray();
			int[] next = new int[100000 + 5];
			int cur = 0,last = 0;
			next[0]=0;
			
			for(int i = 1; i < cs.length; i++){
				char c = cs[i];
				if(c=='[')		cur = 0;
				else if(c==']')	cur = last;
				else{
					next[i] = next[cur];
					next[cur] = i;
					if(cur==last)	last=i;
					cur = i;
				}
			}
			for(int i=next[0];i!=0;i=next[i])
				out.append(cs[i]);
			out.append('\n');
		}
		System.out.print(out.toString());
	}
}
