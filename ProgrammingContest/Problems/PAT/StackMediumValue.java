import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class StackMediumValue {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	Stack<Integer> stack = new Stack<>();
	int exist[][], count[], size;
	
	public void solve() throws NumberFormatException, IOException{
		while(true){
			String str = in.readLine();	if(str == null)	break;
			int n = Integer.parseInt(str);
			stack.clear();
			count = new int[318];
			exist = new int[318][318];
			StringTokenizer tk;
			for(int i = 0; i < n; i++){
				tk = new StringTokenizer(in.readLine());
				String op = tk.nextToken();
				if(op.equals("Pop")){
					if(!stack.isEmpty()){
						int num = stack.pop();
						out.append(num);
						exist[num / 318][num % 318]--;
						count[num / 318]--;
						size--;
					}
					else					out.append("Invalid");
					out.append("\n");
				}
				else if(op.equals("Push")){
					int num = Integer.parseInt(tk.nextToken());
					stack.push(num);
					exist[num / 318][num % 318]++;
					count[num / 318]++;
					size++;
				}
				else{
					if(!stack.isEmpty())	out.append(medium());
					else					out.append("Invalid");
					out.append("\n");
				}
			}
		}
		System.out.print(out.toString());
	}
	public int medium(){
		int acc = 0, no = size % 2 == 0 ? size / 2 : (size + 1) / 2;
		for(int i = 0; i < 318; i++){
			if(acc + count[i] >= no){
				int tmpcount = 0, subno = no - acc;
				for(int j = 0; j < 318; j++){
					if(tmpcount + exist[i][j] >= subno){
						return i * 318 + j;
					}
					else tmpcount += exist[i][j];
				}
			}
			else	acc += count[i];
		}
		return 0;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		StackMediumValue a = new StackMediumValue();
		a.solve();
	}

}
