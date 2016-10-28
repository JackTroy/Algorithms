import edu.princeton.cs.algs4.StdOut;

public class TestBinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchST<String, String> st = new BinarySearchST<String, String>(10);
		for (int i = 0; i < 10; i++) {
			st.put(i+"", i+"");
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
	}

}
