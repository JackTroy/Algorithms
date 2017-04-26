import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionTemplate {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	void solve() throws NumberFormatException, IOException{
		while(true){
			String str = in.readLine();	if(str == null)	break;
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		SolutionTemplate a = new SolutionTemplate();
		a.solve();
	}

}
