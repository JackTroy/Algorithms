import java.util.Scanner;

public class MorseMismatches {
	//uva 12108
	public static void check(String code,String[][] words,int num){
		//the idea of distance is key
		//distance==0 means perfect match
		//distance!=0 means prefix match or perfect match incomplete code
		//what's more is that you have problem with reading pure English problem !!!
		int dist = 80,match=0;
		boolean mul = false;
		for(int i=0;i<num;i++){
			String codeOfWord = words[i][1];
			int len = Math.min(code.length(), codeOfWord.length());
			if(code.substring(0,len).equals(codeOfWord.substring(0,len))){
				int tmpdist = Math.abs(code.length()-codeOfWord.length());
				if(tmpdist<dist){
					match = i;
					dist = tmpdist;
				}
				else if(tmpdist==dist&&dist==0)	mul=true;
			}
		}
	if(dist>0)	System.out.println(words[match][0]+"?");
	else		{
		if(mul) System.out.println(words[match][0]+"!");
		else	System.out.println(words[match][0]);
	}
			
}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] st = new String[36];
		for(int i=0;i<36;i++){
			in.next();
			st[i] = in.next();
		}
		in.next();
		int cnt=0;
		String[][] words=new String[100][2];
 		while(true){
			String word=in.next();
			//damn you man ,how could you use "==" to compare string?
			if(word.equals("*"))	break;
			
			words[cnt][0]=word;
			words[cnt][1]="";
			for(int i=0;i<word.length();i++){
				int index;
				char c=word.charAt(i);
				if(c>='0'&&c<='9')
					index = 26+(c-'0');
				else
					index=c-'A';
				words[cnt][1]+=st[index];
			}
			cnt++;	
		}
 		while(true){
 			String code = in.next();
 			if(code.equals("*")) break;
 			check(code, words,cnt);
 		}
		in.close();	
	}
}
