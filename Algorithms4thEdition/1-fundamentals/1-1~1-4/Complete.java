

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Complete {

	public static void main(String[] args){ 
		Stack<String> ops = new Stack<String>();
		Stack<String> vals = new Stack<String>();
		while(!StdIn.isEmpty()){
			String s=StdIn.readString();
			
			if(s.equals("+")||
			   s.equals("-")||
			   s.equals("*")||
			   s.equals("/")||
               s.equals("sqrt"))ops.push(s);
			else if(s.equals(")")){
				String op=ops.pop();
				String v=vals.pop();
				
				if (op.equals("+") ||
				    op.equals("-") ||
				    op.equals("*") ||
				    op.equals("/"))
					v=String.format("( %s %s %s )",vals.pop(),op,v);
				else if(op.equals("sqrt"))
					v=String.format("%s ( %s )", op,v);
				vals.push(v);
			}
			else vals.push(s);
		}
		StdOut.println(vals.pop());
	}
}
