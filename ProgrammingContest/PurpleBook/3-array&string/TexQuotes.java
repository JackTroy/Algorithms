import java.util.Scanner;
//uva 272
public class TexQuotes {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in); 
		boolean flag = true;
		while(in.hasNextLine()){
			String s = in.nextLine();
			for(char c:s.toCharArray()){
				if(c=='"'){
					if(flag==true)	System.out.print("``");
					else	System.out.print("''");
					flag=!flag;
				}
				else 
					System.out.print(c);
			}
			System.out.println();
		}
		in.close();
	}
}
