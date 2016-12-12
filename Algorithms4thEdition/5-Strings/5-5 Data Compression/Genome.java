import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class Genome {
	public static void compress(){
		Alphabet DNA = new Alphabet("ATCG");
		String string = BinaryStdIn.readString();
		int N = string.length();
		BinaryStdOut.write(N);//write the total number of chars
		for(int i=0;i<N;i++){
			int d = DNA.toIndex(string.charAt(i));
			BinaryStdOut.write(d,DNA.lgR());					
		}
		BinaryStdOut.close();
	}
	public static void expand(){
		Alphabet DNA = new Alphabet("ATCG");
		int w=DNA.lgR();
		int N = BinaryStdIn.readInt();
		for(int i=0;i<N;i++){
			char c = BinaryStdIn.readChar(w);
			BinaryStdOut.write(DNA.toChar(c));
		}
		BinaryStdOut.close();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args[0].equals("-")) compress();
		if(args[0].equals("+")) expand();
	}

}
