import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TeamQueue {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int rnd=0;
		while(in.hasNext()){
			int tn = in.nextInt();
			if(tn==0)	break;
			HashMap<Integer, Integer> all = new HashMap<Integer, Integer>();
			LinkedList<Integer> qt = new LinkedList<Integer>();
			//@SuppressWarnings("unchecked")
			LinkedList<Integer>[] qe = (LinkedList<Integer>[]) new LinkedList[tn]; 
			for(int i=0;i<tn;i++){
				int n = in.nextInt();
				qe[i] = new LinkedList<Integer>();
				for(int j=0;j<n;j++)
					all.put(in.nextInt(), i);
			}
			System.out.println("Scenario #"+(++rnd));
			while(in.hasNext()){
				String s = in.next();
				if(s.charAt(0)=='S')	break;
				if(s.charAt(0)=='E'){
					int num = in.nextInt();
					//System.out.println("number is "+num);
					int t = all.get(num);
					//System.out.println(t);
					//if(!qt.contains(t))	qt.add(t);
					if(qe[t].isEmpty())	qt.add(t);
					qe[t].add(num);
				}
				else{
					int t = qt.peek();
					int num = qe[t].pollFirst();
					if(qe[t].isEmpty())	qt.pollFirst();
					System.out.println(num);
				}
			}
			System.out.println();
		}
		in.close();
	}

}
