import edu.princeton.cs.algs4.StdOut;

public class TestBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST<String, Integer> st = new BST<String, Integer>();
		for (int i = 0; i < 10; i++) {
			String key=i+"";
			Integer value=i;
			st.directPut(key, value);
		}
		for (String num:st.keys()) {
			StdOut.println(num);
		}
		
		st.delete(5+"");
		for (String num:st.keys()) {
			StdOut.println(num);
		}
		StdOut.println(st.max());
		StdOut.println(st.min());
		StdOut.println(st.ceiling(3.5+""));
		StdOut.println(st.floor(3.5+""));
		StdOut.println(st.select(5));
		StdOut.println(st.rank(6+""));
		st.deleteMin();
		for (String num:st.keys()) {
			StdOut.println(num);
		}
		StdOut.println(st.directGet(""+9));
		StdOut.println(st.directGet(""+10));
	}
}
