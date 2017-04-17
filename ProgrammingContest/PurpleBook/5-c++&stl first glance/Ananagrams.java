import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap;

public class Ananagrams {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		ArrayList<String> words = new ArrayList<String>();
 		int n=-1;
		while(true){
			String strWord = in.next();
			if(strWord.equals("#"))	break;
			
			n++;
			words.add(strWord);
			
			char[] word = strWord.toLowerCase().toCharArray();
			Arrays.sort(word);
			strWord = String.valueOf(word);
			if(map.containsKey(strWord))	map.replace(strWord, -1);
			else							map.put(strWord, n);
		}
		in.close();
		ArrayList<String> ags = new ArrayList<String>();
		for(String word:map.keySet()){
			int index = map.get(word);
			if(index!=-1)
				ags.add(words.get(index));
		}
		Collections.sort(ags);
		for(String word:ags)
			System.out.println(word);
	}

}
