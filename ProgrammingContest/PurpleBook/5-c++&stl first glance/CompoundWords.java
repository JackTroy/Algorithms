import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
//uva 10391
public class CompoundWords {
	private static int prefixlen(String a,String b){
		int i;
		for(i=0;i<a.length()&&i<b.length();i++)
			if(a.charAt(i)!=b.charAt(i))
				break;
		return i;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String[] wordsList = new String[120000];
		//TreeSet<String> set = new TreeSet<String>();
		//TreeSet<String> cpwords = new TreeSet<String>();
		ArrayList<String> cpwords = new ArrayList<String>();
		int cnt=0;
		while(in.hasNext()){
			String s = in.next();
			wordsList[cnt++]=s;
			//set.add(s);
		}
		in.close();
		for(int i=1;i<cnt;i++){
			for(int j=i-1;j>=0;j--){
				if(wordsList[i].length()<=wordsList[j].length())	continue;
				int len = prefixlen(wordsList[i],wordsList[j]);
				if(len==0)	break;
				if(len<wordsList[j].length())	continue;
				else{
					String rest = wordsList[i].substring(len);
					//System.out.println("rest:"+rest);
					//System.out.println(wordsList[3].equals(rest));
					if(Arrays.binarySearch(wordsList,0,cnt, rest)>=0&&!cpwords.contains(wordsList[i]))	cpwords.add(wordsList[i]);
					//if(set.contains(rest))	cpwords.add(wordsList[i]);
						
				}
			}
		}
		Collections.sort(cpwords);
		for(String word:cpwords)
			System.out.println(word);
	}

}
