import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputReader {
	public BufferedReader r;
	public StringTokenizer s;
	public InputReader(InputStream in){
		r = new BufferedReader(new InputStreamReader(in));
	}
	public String next(){
		if(!hasNext())	return null;
		else 			return s.nextToken();
	}
	public boolean hasNext(){
		if(s==null||!s.hasMoreTokens()){
			String line;
			try	{line = r.readLine();}
			catch(Exception e)	{return false;}
			if(line==null)	return false;
			s = new StringTokenizer(line);
			return true;
		}
		else	return true;
	}
	public int nextInt(){
		return Integer.parseInt(next());
	}
}
