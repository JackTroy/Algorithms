import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AccordianPatience {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	//value 1-13 suit 1-4
	public int cnt;
	public char value[], suit[];
	public ArrayList<ArrayList<Integer>> all;
	public void solve() throws IOException{
		while(true){
			String[] ss = new String[2];
			ss[0] = in.readLine();	if(ss[0].equals("#"))	break;
			ss[1] = in.readLine();
			value = new char[52];
			suit = new char[52];
			for(int i = 0; i < 2; i++){
				StringTokenizer st = new StringTokenizer(ss[i]);
				for(int j = 0; j < 26; j++){
					int index = i * 26 + j;
					char[] cs = st.nextToken().toCharArray();
					value[index] = cs[0];
					suit[index] = cs[1];
				}
			}
			all = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < 52; i++){
				ArrayList<Integer> a = new ArrayList<Integer>();
				a.add(i);
				all.add(a);
			}
			while(true){
				//System.out.println(rmcount++);
				boolean rm = false;
				for(int i = 1; i < all.size(); i++){
					ArrayList<Integer> a = all.get(i);
					int topa = a.get(a.size() - 1);
					if(i - 3 >= 0){
						ArrayList<Integer> b = all.get(i - 3);
						int topb = b.get(b.size() - 1);
						if(value[topa] == value[topb] || suit[topa] == suit[topb]){
							b.add(topa);
							a.remove(a.size() - 1);
							rm = true;
						}
					}
					if(!rm){
						ArrayList<Integer> b = all.get(i - 1);
						int topb = b.get(b.size() - 1);
						if(value[topa] == value[topb] || suit[topa] == suit[topb]){
							b.add(topa);
							a.remove(a.size() - 1);
							rm = true;
						}
					}
					if(rm){
						if(a.size() == 0)	all.remove(i);
						break;
					}
				}
				if(!rm)	break;
			}
			//System.out.println("make it here");
			if(all.size() > 1)	out.append(all.size() + " piles remaining:");
			else				out.append(all.size() + " pile remaining:");
			
			for(int i = 0; i < all.size(); i++)
				out.append(" " + all.get(i).size());
			out.append("\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws IOException {
		AccordianPatience accordianPatience = new AccordianPatience();
		accordianPatience.solve();
	}

}
