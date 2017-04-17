import java.util.Scanner;
import java.util.Stack;
//uva 673
public class ParenthesesBalance {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int c = in.nextInt();in.nextLine();
		while(c-->0){
			boolean balance = true;
			String string = in.nextLine();
			Stack<Character> s = new Stack<Character>();
			for(int i=0;i<string.length();i++){
				char symbol = string.charAt(i);
				if(symbol=='('||symbol=='[')	s.push(symbol);
				else{
					if(s.isEmpty())	{balance = false;break;}
					char presymbol = s.pop();
					if(symbol==')'&&presymbol=='(')	continue;
					else if(symbol==']'&&presymbol=='[') continue;
					else	balance = false;
				}
			}
			if(!s.isEmpty())	balance = false;
			if(balance)	System.out.println("Yes");
			else		System.out.println("No");
		}
		in.close();
	}
}
