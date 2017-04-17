import java.util.Scanner;

public class AllInAll {
	//uva 10340
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			boolean is=true;
			char[] s = in.next().toCharArray(),t = in.next().toCharArray();
			int j=0;
			for(int i=0;i<s.length;i++){
				int k;
				for(k=j;k<t.length;k++)
					if(s[i]==t[k]){
						j=k+1;
						break;
					}
				if(k==t.length){
					is=false;
					break;
				}
			}
			if(is)	System.out.println("Yes");
			else	System.out.println("No");
			
		}
		in.close();
	}
}
