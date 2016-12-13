

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class EvaluatePostfix {
	public static void main(String[] args){ 
		Stack<Double> vals = new Stack<Double>();
		while(!StdIn.isEmpty()){
			String s=StdIn.readString();
			
			if(s.equals("+")||
			   s.equals("-")||
			   s.equals("*")||
			   s.equals("/"))
			{
				double val2=vals.pop();
				double val1=vals.pop();			
				switch (s){
				case "+": vals.push(val1+val2);break;
				case "-": vals.push(val1-val2);break;
				case "*": vals.push(val1*val2);break;
				case "/": vals.push(val1/val2);break;
				}				
			}
			else if(s.equals("sqrt")) 
				{
				double val=vals.pop();
				vals.push(Math.sqrt(val));
				}
			else vals.push(Double.parseDouble(s));
		}
		StdOut.println(vals.pop());
	}
}
