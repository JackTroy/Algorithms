import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.ArrayList;

public class LifeForms {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	Integer string[], sa[], rank[], height[], group[];
	Integer NumLetter, MaxLen = 140000 + 5, n, MaxLenDNA;
	void solve() throws NumberFormatException, IOException{
		boolean first = true;
		for(;;){
			String str = in.readLine();	if(str.equals("0"))	break;
			if(first)	first = false;
			else		out.append("\n");
			
			MaxLenDNA = 0;
			NumLetter = 0;
			string = new Integer[MaxLen];
			group = new Integer[MaxLen];
			n = Integer.parseInt(str);
			if(n == 1){
				out.append(in.readLine() + "\n");
				continue;
			}
			int count = 30;
			for(int i = 0; i < n; i++){
				str = in.readLine();
				if(str.length() > MaxLenDNA)	MaxLenDNA = str.length();
				for(int j = 0; j < str.length(); j++)	
					string[NumLetter + j] = str.charAt(j) - 'a';
				string[NumLetter + str.length()] = count++;
				Arrays.fill(group, NumLetter, NumLetter + str.length() + 1, i);
				NumLetter += str.length() + 1;
			}
			
			sa = new Integer[NumLetter];
			height = new Integer[NumLetter];
			rank = new Integer[NumLetter];
			for(int i = 0; i < NumLetter; i++)	sa[i] = i;	
			Arrays.sort(sa, new Comparator<Integer>() {

				public int compare(Integer o1, Integer o2) {
					for(int i = 0; o1 + i < NumLetter && o2 + i < NumLetter; i++){
						if(string[o1 + i] < string[o2 + i])			return -1;
						else if(string[o1 + i] > string[o2 + i])	return 1;
					}
					if(o1 < o2)			return 1;
					else if(o1 > o1)	return -1;
					else 				return 0;
				}
				
			});
			for(int i = 0; i < NumLetter; i++)	rank[sa[i]] = i;
			for(int i = 0, k = 0; i < NumLetter; i++){
				if(k != 0)	k--;
				if(rank[i] == 0){
					height[rank[i]] = 0;
					continue;
				}
				int j = sa[rank[i] - 1];
				while(string[i + k] == string[j + k]){
					k++;
					if(i + k == NumLetter || j + k == NumLetter)	break;
				}
				height[rank[i]] = k;
			}
			int MaxAnsLen = 0;
			ArrayList<String> MaxAns = null;
			int l = 1, r = MaxLenDNA;
			while(l <= r){
				int i = (l + r) >> 1;
				ArrayList<String> ans = search(i);
				if(ans.size() > 0){
					if(i > MaxAnsLen){
						MaxAnsLen = i;
						MaxAns = ans;
					}
					l = i + 1;
				}
				else	r = i - 1;
			}
			if(MaxAnsLen == 0)	out.append("?\n");
			else{
				Collections.sort(MaxAns);
				for(String s:MaxAns)	out.append(s + "\n");
			}
		}
		System.out.print(out.toString());
	}
	public ArrayList<String> search(int p){
		int i = 1;
		ArrayList<String> ans = new ArrayList<>();
		while(i < NumLetter){
			HashSet<Integer> set = new HashSet<Integer>();
			while(i < NumLetter && p <= height[i]){
				set.add(group[sa[i - 1]]);
				set.add(group[sa[i]]);
				i++;
			}
			if(set.size() > n / 2){
				String s = "";
				for(int j = 0; j < p; j++)
					s += (char)('a' + string[sa[i - 1] + j]);
				ans.add(s);
			}
			i++;
		}
		return ans;
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		LifeForms a = new LifeForms();
		a.solve();
	}

}
