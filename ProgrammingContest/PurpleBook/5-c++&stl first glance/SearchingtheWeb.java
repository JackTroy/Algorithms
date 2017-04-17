import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SearchingtheWeb {
//uva 1597
	public static String[][] documents;
	public static int n;
	public static int[] nls;
	public static ArrayList<Boolean[]> search(String query ,boolean not){
		//System.out.println("query:"+query);
		Pattern p = Pattern.compile(query);
		ArrayList<Boolean[]> result = new ArrayList<Boolean[]>(n);
		for(int i=0;i<n;i++){
			Boolean[] lines = new Boolean[nls[i]];
			boolean exist = not;
			for(int j=0;j<nls[i];j++){
				Matcher m = p.matcher(documents[i][j].toLowerCase());
				if(m.find())	{lines[j] = !not;exist = !not;}
				else			lines[j] = not;
			}
			if(exist) 	result.add(lines);
			else		result.add(null);
		}
		//System.out.println(result.size());
		return result;
	}
	public static ArrayList<Boolean[]> and(ArrayList<Boolean[]> result1,ArrayList<Boolean[]> result2){
		ArrayList<Boolean[]> result = new ArrayList<Boolean[]>(n);
		for(int i=0;i<documents.length;i++){
			if(result1.get(i)!=null&&result2.get(i)!=null){
				Boolean[] lines = new Boolean[nls[i]];
				for(int j=0;j<nls[i];j++)	
					lines[j] = result1.get(i)[j]||result2.get(i)[j];
				result.add(lines);
			}
			else result.add(null);
		}
		return result;
	}
	public static ArrayList<Boolean[]> or(ArrayList<Boolean[]> result1,ArrayList<Boolean[]> result2){
		ArrayList<Boolean[]> result = new ArrayList<Boolean[]>(n);
		for(int i=0;i<documents.length;i++){
			if(result1.get(i)!=null||result2.get(i)!=null){
				Boolean[] lines = new Boolean[nls[i]];
				Arrays.fill(lines, false);
				for(int j=0;j<nls[i];j++){
					if(result1.get(i)!=null)	lines[j] = result1.get(i)[j]||lines[j];
					if(result2.get(i)!=null)	lines[j] = result2.get(i)[j]||lines[j];
				}
				result.add(lines);
			}
			else result.add(null);
		}
		return result;
	}
	public static void print(ArrayList<Boolean[]> result){
		boolean isEmpty = true;
		int cnt = 0;
		for(int i=0;i<documents.length;i++){
			if(result.get(i)==null)	continue;
			isEmpty = false;
			if(cnt++>0)	System.out.println("---------");
			for(int j=0;j<nls[i];j++){
				if(result.get(i)[j]==true)
					System.out.println(documents[i][j]);
			}
		}
		if(isEmpty)	System.out.println("Sorry, I found nothing.");
		System.out.println("==========");
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		in.nextLine();
		documents = new String[n][1500];
		nls = new int[n];
		for(int i=0;i<n;i++){
			//documents[i] = new ArrayList<String>();
			while(true){
				String line = in.nextLine();
				if(line.equals("**********"))	break;
				documents[i][nls[i]++] = line;
			}
		}
		int qn = in.nextInt();
		in.nextLine();
		for(int i=0;i<qn;i++){
			String string = in.nextLine();
			String[] queries = string.split("\\s");
			if(queries.length==1)	print(search(queries[0].toLowerCase(),false));
			else if(queries.length==2)	print(search(queries[1].toLowerCase(),true));
			else if(queries.length==3&&queries[1].charAt(0)=='A')
				print(and(search(queries[0].toLowerCase(),false),search(queries[2].toLowerCase(),false)));
			else
				print(or(search(queries[0].toLowerCase(),false),search(queries[2].toLowerCase(),false)));
		}
		in.close();
	}
}
