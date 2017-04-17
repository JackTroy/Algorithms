import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Borrowers {
//uva 230
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<String> authors = new ArrayList<String>();
		TreeMap<String, ArrayList<String>> map = new TreeMap<String, ArrayList<String>>();
		HashMap<String, String> bookToAuthor = new HashMap<String, String>();
		HashMap<String, Boolean> exist = new HashMap<String, Boolean>();
		while(true){
			String line = in.nextLine();
			if(line.equals("END"))	break;
			int pos = line.indexOf("by");
			String book = line.substring(1,pos-2);
			String author = line.substring(pos+3,line.length());
			authors.add(author);
			if(map.containsKey(author))	map.get(author).add(book);
			else{
				ArrayList<String> books = new ArrayList<String>();
				books.add(book);
				map.put(author,books);
			}
			bookToAuthor.put(book, author);
			exist.put(book, true);
		}
		for(String author:map.keySet())
			Collections.sort(map.get(author));
		while(true){
			TreeMap<String, ArrayList<String>> rmap = new TreeMap<String, ArrayList<String>>();
			String s = in.nextLine();
			if(s.charAt(0)=='E')	break;
			while(true){
				if(s.charAt(0)=='B')	exist.replace(s.substring(8,s.length()-1), false);
				else if(s.charAt(0)=='R')	{
					String rbook = s.substring(8,s.length()-1);
					String rauthor = bookToAuthor.get(rbook);
					if(rmap.containsKey(rauthor))	rmap.get(rauthor).add(rbook);
					else{
						ArrayList<String> tmp = new ArrayList<String>();
						tmp.add(rbook);
						rmap.put(rauthor,tmp);
					}
				}
				else	break;
				s = in.nextLine();
			}
			ArrayList<String> allbooks = new ArrayList<String>();
			for(String author:map.keySet()){
				ArrayList<String> list = map.get(author);
				Collections.sort(list);
				for(String book:list)
					allbooks.add(book);
			}
			for(String author:rmap.keySet()){
				ArrayList<String> rlist = rmap.get(author);
				Collections.sort(rlist);
				for(String book:rlist){
					String prebook = null;
					int i;
					for(i=allbooks.indexOf(book)-1;i>=0;i--)
						if(exist.get(allbooks.get(i))){
							prebook = allbooks.get(i);
							break;
						}
					exist.replace(book, true);
					if(i<0)	System.out.println(String.format("Put \"%s\" first", book));
					else	System.out.println(String.format("Put \"%s\" after \"%s\"", book,prebook));
				}
			}
			System.out.println("END");
		}
		in.close();
	}
}

