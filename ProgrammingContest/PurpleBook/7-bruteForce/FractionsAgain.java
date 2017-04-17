import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FractionsAgain {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String line = reader.readLine();	if(line==null)	break;
			StringBuilder out = new StringBuilder();
			int k = Integer.parseInt(line);
			int cnt = 0;
			for(int y = k+1;y <= 2 * k; y++){
				if((k*y) % (y-k)==0){
					cnt++;
					out.append(String.format("1/%d = 1/%d + 1/%d\n", k,(k*y) / (y-k),y));
				}	
			}
			System.out.println(cnt);
			System.out.print(out.toString());
		}
	}

}
