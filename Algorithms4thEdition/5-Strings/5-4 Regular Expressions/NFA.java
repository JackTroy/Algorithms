import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.Stack;

public class NFA {
	private char[] re;
	private Digraph G;
	private int M;

	public NFA(String regexp) {
		Stack<Integer> ops = new Stack<Integer>();
		re = regexp.toCharArray();
		M = re.length;
		// state M stands for last state without specific corresponding char
		G = new Digraph(M + 1);

		for (int i = 0; i < M; i++) {
			int lp = i;
			if (re[i] == '(' || re[i] == '|')
				ops.push(i);
			else if (re[i] == ')') {
				int or = ops.pop();
				if (re[or] == '|') {
					lp = ops.pop();
					G.addEdge(lp, or + 1);
					G.addEdge(or, i);
				}
				// if not '|' ,then must be '(',set lp to be the state of '('
				else
					lp = or;
			}
			// add red edge for '*'
			// if lp==i,then it's the case like "A*"
			// if lp==or or lp = ops.pop(); ,then this is the case like ()*
			if (i < M - 1 && re[i + 1] == '*') {
				G.addEdge(lp, i + 1);
				G.addEdge(i + 1, lp);
			}
			// note that for '|',there is no red edge
			if (re[i] == '(' || re[i] == '*' || re[i] == ')')
				G.addEdge(i, i + 1);
		}
	}

	public boolean recognizes(String txt) {
		Bag<Integer> pc = new Bag<Integer>();
		DirectedDFS dfs = new DirectedDFS(G, 0);
		for (int v = 0; v < G.V(); v++)
			if (dfs.marked(v))
				pc.add(v);

		for (int i = 0; i < txt.length(); i++) {
			// create a temporary bag to store all state that is possible to
			// change to in black edge
			Bag<Integer> match = new Bag<Integer>();
			// class bag has already implements Iterable
			for (int v : pc)
				if (v < M)
					if (re[v] == txt.charAt(i) || txt.charAt(i) == '.')
						match.add(v + 1);

			pc = new Bag<Integer>();
			// match as argument , find all new possible to access state with
			// state in match bag
			dfs = new DirectedDFS(G, match);
			for (int v = 0; v < G.V(); v++)
				if (dfs.marked(v))
					pc.add(v);

		}
		for (int v : pc)
			if (v == M)
				return true;
		return false;
	}
}
