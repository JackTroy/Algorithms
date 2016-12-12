import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class RunLength {
	//the longest length of single run 
	private static final int R = 256;
	//use 8 bits to represent the number of the length of single run
	private static final int LG_R = 8;
	private RunLength(){}
	public static void compress(){
		char run=0;
		//starts with 0
		boolean old = false;
		while(!BinaryStdIn.isEmpty()){
			//read a bit
			boolean b = BinaryStdIn.readBoolean();
			if(b!=old){
				BinaryStdOut.write(run,LG_R);
				run=1;
				old = !old;
			}
			else{
				//note that 8 bits represent 0-255 at most 
				//when the run has come to 255,it shall re-run
				if(run==R-1){
					BinaryStdOut.write(run,LG_R);
					run=0;
					BinaryStdOut.write(run,LG_R);
				}
				run++;
			}
		}
	}
	public static void expand(){
		boolean b = false;
		while(!BinaryStdIn.isEmpty()){
			int run = BinaryStdIn.readInt(LG_R);
			for(int i=0;i<run;i++)
				BinaryStdOut.write(b);
			b=!b;
		}
		BinaryStdOut.close();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
	}

}
