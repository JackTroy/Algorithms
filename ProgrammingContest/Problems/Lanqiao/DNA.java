import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DNA {
	static int n, m, k, group[];
	static Integer sa[];
	static String dnas[], jointString;
	
	static boolean check(int pos1, int pos2){
		if(pos1 + k >= jointString.length() || pos2 + k >= jointString.length())	return false;
		for(int i = 0; i < k; i++)
			if(jointString.charAt(pos1 + i) != jointString.charAt(pos2 + i))
				return false;
		return true;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		dnas = new String[n];
		group = new int[100000 + 10];
		
		int sep = 0;
		jointString = "";
		for(int i = 0; i < n; i++){
			dnas[i] = in.next();
			Arrays.fill(group, jointString.length(), jointString.length() + dnas[i].length() + 1, sep);
			jointString += dnas[i] + sep++;
		}
		
		sa = new Integer[jointString.length()];
		for(int i = 0; i < sa.length; i++)	sa[i] = i;
		
		Arrays.sort(sa, new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return jointString.substring(arg0).compareTo(jointString.substring(arg1));
			}
		});
		for(int i = 0; i < jointString.length(); i++)
			System.out.println(jointString.substring(sa[i]));
		int idx = 0, ans = 0;
		while(idx < jointString.length()){
			int count = 1, groupNum = 1, groupCount[] = new int[10];
			groupCount[group[sa[idx]]]++;
			while(idx + count < jointString.length()){
				if(check(sa[idx], sa[idx + count])){
					if(groupCount[group[sa[idx + count]]] == 0)	groupNum++;
					count++;
				}
				else 						break;
			}
			if(groupNum >= m){
				//todo 
				//iterate over c(m,n) and add up all the product
			}
			idx += count;
		}
		
		System.out.println(ans);
		in.close();
	}

}
