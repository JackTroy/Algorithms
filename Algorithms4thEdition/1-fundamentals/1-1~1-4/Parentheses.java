

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

public class Parentheses {
	public static boolean isMatch(String s){
		int left=0,right=0;
		Stack<Character> key=new Stack<Character>();
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
				key.push(s.charAt(i));
				left++;
			}
			else if(s.charAt(i)==')'){
				right++;
				if(key.pop()!='(')return false;
			}
			else if(s.charAt(i)==']'){
				right++;
				if(key.pop()!='[')return false;
			}
			else if(s.charAt(i)=='}'){
				right++;
				if(key.pop()!='{')return false;
			}
		}
		return right==left?true:false;
	}
	public static void main(String[] args){
		// TODO 自动生成的方法存根
		String s=StdIn.readString();
		System.out.print(isMatch(s));
	}
}
