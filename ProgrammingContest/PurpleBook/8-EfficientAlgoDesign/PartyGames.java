import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PartyGames {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		while(true){
			int n = Integer.parseInt(in.readLine());	if(n == 0)	break;
			String[] words = new String[n];
			for(int i = 0; i < n; i++)	words[i] = in.readLine();
			Arrays.sort(words);
			out.append(search(words) + "\n");
		}
		System.out.print(out.toString());
	}
	public static String mod(String s){
		if(s.length() == 1)	return s;
		String ans = "";
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != 'Z')	{ans += (char)(s.charAt(i) + 1); break;}
			else					ans += "Z";
		}
		return ans;		
	}
	public static String bigger(String s){
		String ans = "";
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != 'Z'){
				if(i == s.length() - 1)	ans += (char)s.charAt(i);
				else					ans += (char)(s.charAt(i) + 1);
				return ans;
			}
			ans += "Z";
		}
		return ans;
	}
	public static String search(String[] words){
		String small = words[(words.length + 1) / 2 - 1];
		String big = words[(words.length + 1) / 2];
		int minLen = Math.min(small.length(), big.length());
		int i;
		for(i = 0; i < minLen; i++)
			if(small.charAt(i) != big.charAt(i))
				break;
		if(i == minLen)	return small;
		else{
			String ans = small.substring(0,i);
			if(small.length() == i + 1 && big.length() == i + 1)		return small;
			else if(small.length() == i + 1 && big.length() > i + 1)	return small;
			else if(small.length() > i + 1 && big.length() == i + 1){
				if(small.charAt(i) + 1 == big.charAt(i)){
					return small.substring(0, i + 1) + bigger(small.substring(i + 1, small.length()));
				}
				else	return ans + (char)(small.charAt(i) + 1);
			}
			else{
				/*
				 * if(small.charAt(i) + 1 == big.charAt(i)){
					return small.substring(0, i + 1) + bigger(small.substring(i + 1, small.length()));
				}
				else	return ans + (char)(small.charAt(i) + 1);
				 */
				return ans + (char)(small.charAt(i) + 1);
			}
		}
	}

}
