import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class LZW {
	private static final int R=256;//the number of different char
	private static final int L=4096;//the number of different strings
	private static final int W=12;//
	
	public static void expand(){
		//use string array instead of TST
		//index is the integer code from input
		//value is corresponding string
		String[] st = new String[L];
		
		for(int i=0;i<R;i++)
			st[i]=""+(char)i;
		//for new string val+c
		int code = R+1;
		
		int codeword = BinaryStdIn.readInt(W);
		String val = st[codeword];
		
		while(true){
			BinaryStdOut.write(val);
			codeword = BinaryStdIn.readInt(W);
			if(codeword==R+1) break;
			if(codeword==code) st[code]=val+val.charAt(0);
			if(code<L)
				st[code++]=val+st[codeword].charAt(0);
			val = st[codeword];
		}
		BinaryStdOut.close();
	}
	public static void compress(){
		String input = BinaryStdIn.readString();
		TST<Integer> st = new TST<Integer>();
		
		for(int i=0;i<R;i++)
			st.put(""+(char)i, i);
		
		int code=R+1;
		
		while(input.length()>0){
			String string = st.longestPrefixOf(input);
			BinaryStdOut.write(st.get(string),W);
			
			int t = string.length();
			if(t<input.length()&&code<L)
				st.put(string+input.charAt(t), code++);
			//remove the string already been wrote
			input.substring(t);
		}
		BinaryStdOut.write(R,W);
		BinaryStdOut.close();
	}
    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
