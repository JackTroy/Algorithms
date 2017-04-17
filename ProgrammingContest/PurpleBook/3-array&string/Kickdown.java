import java.util.Arrays;
import java.util.Scanner;
//uva 1588
public class Kickdown {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			String a = in.next(),b=in.next();
			int[] m = new int[200];
			int[] n = new int[200];
			Arrays.fill(m, 0);
			Arrays.fill(n, 0);
			int lenM=a.length(),lenN=b.length();
			for(int i=0;i<lenM;i++)
				m[i]=a.charAt(i)-'0';
			for(int i=0;i<lenN;i++)
				n[i]=b.charAt(i)-'0';
			
			int len1;
			int startOnM;
			for(startOnM=0;startOnM<lenM;startOnM++){
				int i;
				for(i=0;i<lenN;i++)
					if(n[i]+m[startOnM+i]>3)	break;
				if(i==lenN)	break;
			}
			len1 = startOnM+lenN;
			
			int len2;
			int startOnN;
			for(startOnN=0;startOnN<lenN;startOnN++){
				int i;
				for(i=0;i<lenM;i++)
					if(n[startOnN+i]+m[i]>3)	break;						
				if(i==lenM)	break;
			}
			len2=startOnN+lenM;
			System.out.println(Math.max(Math.min(len1, len2), Math.max(lenM, lenN)));
		}
		in.close();
	}
}
