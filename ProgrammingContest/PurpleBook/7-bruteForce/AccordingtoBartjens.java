import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Arrays;

public class AccordingtoBartjens {
//According to Bartjens UVA - 817
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public boolean im;
	public char[] exp, ops = {'#', '+', '-', '*'};
	public int n;
	public void solve() throws IOException{
		int rnd = 0;
		while(true){
			String line = in.readLine();	if(line.equals("="))	break;
			exp = new char[18];
			Arrays.fill(exp, '#');
			for(int i = 0; i < line.length() - 1; i++){
				exp[i * 2] = line.charAt(i);
			}
			exp[17] = '=';
			n = line.length() - 1;
			//[2, #, 1, #, 0, #, 0, #, 1, #, 0, #, 0, #, #, #, #, =]
			//0 - 17
			out.append("Problem "+(++rnd)+"\n");
			im = false;
			dfs(1);
			if(im == false)	out.append("  IMPOSSIBLE\n");
		}
		System.out.println(out.toString());
	}
	public void dfs(int d){
		if(d == n * 2 - 1)	{check(); return;}
		for(int i = 0; i < 4; i++){
			exp[d] = ops[i];
			dfs(d + 2);
		}
	}
	public void check(){
		int count = 0;
		String token = "";
		String line = "";
		LinkedList<String> os = new LinkedList<String>();
		for(int i = 0; i < 17; i++){
			if(exp[i] == '#')	continue;
			if(exp[i] >= '0' && exp[i] <= '9')	token += exp[i];
			else{
				if(isNum(token)){
					//System.out.println(token);
					os.add(token);
					os.add(exp[i]+"");
					line +=token;
					line +=exp[i];
					token = "";
					count++;
				}
				else return;
			}
		}
		if(!isNum(token))	return;
		line += token;
		os.add(token);
		if(count == 0)	return;
		//System.out.println(line);
		if(eval(os))	{
			out.append("  "+line+"=\n");
			im = true;
		}
	}
	public boolean isNum(String s){
		if(s.charAt(0) == '0')	if(s.length() > 1)	return false;
		return true;
	}
	public boolean eval(LinkedList<String> os){
		LinkedList<String> tmp = new LinkedList<String>();
		while(!os.isEmpty()){
			String s = os.removeFirst();
			if(Character.isDigit(s.charAt(0)))	tmp.add(s);
			else if(s.equals("*")){
				int n1 = Integer.parseInt(tmp.removeLast());
				int n2 = Integer.parseInt(os.removeFirst());
				tmp.add(n1*n2+"");
			}
			else{
				tmp.add(s);
			}
		}
		int sum = Integer.parseInt(tmp.removeFirst());
		while(!tmp.isEmpty()){
			String s = tmp.removeFirst();
			if(s.equals("+")){
				sum += Integer.parseInt(tmp.removeFirst());
			}
			else{
				sum -= Integer.parseInt(tmp.removeFirst());
			}
		}
		return sum == 2000;
	}
	public int compute(int n1, int n2, String s){
		if(s.equals("+"))		return n1 + n2;
		else if(s.equals("-"))	return n1 - n2;
		else 					return n1 * n2;
	}
	public static void main(String[] args) throws IOException {
		AccordingtoBartjens a = new AccordingtoBartjens();
		a.solve();
	}
}
