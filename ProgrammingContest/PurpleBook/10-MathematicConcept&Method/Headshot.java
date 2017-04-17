import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Headshot {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();

	public void solve() throws IOException{
		while(true){
			String line = in.readLine();	if(line == null)	break;
			char[] chambers = line.toCharArray();
			double pshoot, protate;
			int cntz = 0,cnt2z = 0;
			for(int i = 0; i < chambers.length; i++){
				if(chambers[i] == '0')	cntz++;
				if(chambers[i] == '0' && chambers[(i + 1) % chambers.length] == '0')	
					cnt2z++;
			}
			pshoot = (double) cnt2z / cntz;
			protate = (double)	cntz / chambers.length;
			if(cnt2z * chambers.length == cntz * cntz)	out.append("EQUAL\n");
			else if(pshoot < protate)					out.append("ROTATE\n");
			else										out.append("SHOOT\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws IOException {
		Headshot a = new Headshot();
		a.solve();
	}

}
