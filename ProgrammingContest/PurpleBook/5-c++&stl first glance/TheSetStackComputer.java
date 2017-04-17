import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
//UVA - 12096
public class TheSetStackComputer {
	public static HashMap<HashSet<Integer>, Integer> map;
	public static ArrayList<HashSet<Integer>> setArray;
	
	public static int getID(HashSet<Integer> set) {
		if(map.containsKey(set))	
			return map.get(set);
		setArray.add(set);
		map.put(set, setArray.size()-1);
		return setArray.size()-1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int rnd=in.nextInt();
		while(rnd-->0){
			map = new HashMap<HashSet<Integer>, Integer>();
			setArray = new ArrayList<HashSet<Integer>>();
			Stack<Integer> s = new Stack<Integer>();
			int n = in.nextInt();
			for(int i=0;i<n;i++){
				String op = in.next();
				if(op.charAt(0)=='P')	s.push(getID(new HashSet<Integer>()));
				else if(op.charAt(0)=='D')	s.push(s.peek());
				else{
					HashSet<Integer> top = setArray.get(s.pop());
					HashSet<Integer> top2 = setArray.get(s.pop());
					HashSet<Integer> result = new HashSet<Integer>();
					if(op.charAt(0)=='U'){
						result.addAll(top);
						result.addAll(top2);
					}
					else if(op.charAt(0)=='I'){
						result.addAll(top);
						result.retainAll(top2);
					}
					else{
						result.addAll(top2);
						result.add(getID(top));
					}
					s.push(getID(result));
				}
				System.out.println(setArray.get(s.peek()).size());
			}
			System.out.println("***");
		}
		in.close();
	}

}
