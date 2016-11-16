package undirectedGraphs;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
public class Concordance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
		String[] words=in.readAllStrings();
		ST<String, SET<Integer>> st = new ST<String,SET<Integer>>();
		
		for(int i=0;i<words.length;i++){
			String s=words[i];
			if(!st.contains(s))
				st.put(s, new SET<Integer>());
			SET<Integer> set=st.get(s);
			set.add(i);
		}
		for(String word:st.keys()){
			StdOut.println(word);
		}
		StdOut.println("Finished building concordance");
	}

}
