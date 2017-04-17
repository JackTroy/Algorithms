import java.util.Scanner;

public class AreTheyEqual {
	public static String toStandard(String a, int n){
		String sigDigiOfa = "";
		int pos = a.length(), firstNoneZero = a.length();
		for(int i = 0; i < a.length(); i++){
			char c = a.charAt(i);
			if(c == '.')		pos = i;
			else if(c != '0' && firstNoneZero == a.length())	firstNoneZero = i;
		}
		for(int i = firstNoneZero; i < a.length(); i++){
			if(sigDigiOfa.length() < n){
				if(a.charAt(i) != '.')
					sigDigiOfa += a.substring(i, i + 1);
			}
			else break;
		}
		while(sigDigiOfa.length() < n)	sigDigiOfa += "0";
		int exp;
		if(firstNoneZero == a.length())		exp = 0;
		else if(pos - firstNoneZero > 0)	exp = pos - firstNoneZero;
		else								exp = pos - firstNoneZero + 1;
		StringBuilder sa = new StringBuilder();
		sa.append(String.format("0.%s*10^%d", sigDigiOfa, exp));
		return sa.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in =  new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt();
			String a = in.next(), b = in.next();
			//aaaaa.aaaaa
			//bbbbb.bbbbb
			//for a
			String sta = toStandard(a, n), stb = toStandard(b, n);
			if(sta.equals(stb)){
				System.out.print("YES ");
				System.out.print(sta + "\n");
			}
			else	System.out.print(String.format("NO %s %s\n", sta, stb));
		}
		in.close();
	}

}
