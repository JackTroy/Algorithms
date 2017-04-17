import java.util.Scanner;
import java.util.TreeSet;

public class AndysFirstDictionary {

	public static void main(String[] args) {
		//case like "you've",code below can break into two words
		Scanner in = new Scanner(System.in);
		TreeSet<String> set = new TreeSet<String>();
		String text="";
		while(in.hasNextLine())
			text+=in.nextLine()+'\t';
		in.close();
		char[] textc = text.toCharArray();
		for(int i=0;i<textc.length;i++){
			char c = textc[i];
			if(Character.isAlphabetic(c))	
				textc[i]=Character.toLowerCase(textc[i]);
			else	textc[i]=' ';
		}
		Scanner ps = new Scanner(String.valueOf(textc));
		while(ps.hasNext())	set.add(ps.next());
		ps.close();
		for(String s:set)
			System.out.println(s);
	}
}