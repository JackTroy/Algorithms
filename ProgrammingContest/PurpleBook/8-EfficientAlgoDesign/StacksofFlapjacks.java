import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class StacksofFlapjacks {
//Stacks of Flapjacks UVA - 120 
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public ArrayList<Integer> pancakes;
	public LinkedList<Integer> onPan;
	public void solve() throws IOException{
		while(true){
			String line = in.readLine();	if(line == null)	break;
			out.append(line+"\n");
			StringTokenizer st = new StringTokenizer(line);
			pancakes = new ArrayList<Integer>();
			while(st.hasMoreTokens())
				pancakes.add(Integer.parseInt(st.nextToken()));
			
			onPan = new LinkedList<Integer>();
			onPan.addAll(pancakes);
			Collections.sort(pancakes);
			for(int i = pancakes.size() - 1; i >= 0; i--){
				if(check())	break;
				int max = pancakes.get(i);
				if(onPan.get(i) == max)	continue;
				flip(max,pancakes.size() - 1 - i);
			}
			out.append("0\n");
		}
		System.out.print(out.toString());
	}
	public void flip(int max, int n){
		LinkedList<Integer> tmp = new LinkedList<Integer>();
		if(onPan.peek() != max){
			int i = onPan.size();
			while(true){
				int cur = onPan.removeFirst();
				tmp.add(cur);
				if(cur == max)	break;
				i--;
			}
			out.append(i+" ");
			while(tmp.size() > 0)
				onPan.addFirst(tmp.pollFirst());
		}
		while(onPan.size() > n)
			tmp.add(onPan.removeFirst());
		while(tmp.size() > 0)
			onPan.addFirst(tmp.removeFirst());
		out.append(n + 1 + " ");
	}
	public boolean check(){
		int last = 0;
		for(Integer cur:onPan){
			if(last > cur)	return false;
			else			last = cur;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		StacksofFlapjacks a = new StacksofFlapjacks();
		a.solve();
	}

}
