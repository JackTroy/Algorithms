import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class LSD {
	private LSD(){}
	public static void sort(String[] a,int w){
		int N = a.length;
		int R = 256;//base number
		String[] aux = new String[N];
		for(int d = w-1;d>=0;d--){
			int[] count = new int[R+1];
			
			//calculate frequency
			//key range from 0 to R-1,that is 0 to 255 here
			//count range from 0 to 256,
			for(int i=0;i<N;i++)
				count[a[i].charAt(d)+1]++;
			
			//compute index
			for(int r=0;r<R;r++)
				count[r+1]+=count[r];
				
			//move data
			for(int i=0;i<N;i++)
				aux[count[a[i].charAt(d)]++]=a[i];
			
			//copy back
			for(int i=0;i<N;i++)
				a[i]=aux[i];
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
        String[] a = in.readAllStrings();
        int n = a.length;

        // check that strings have fixed length
        int w = a[0].length();
        for (int i = 0; i < n; i++)
            assert a[i].length() == w : "Strings must have fixed length";

        // sort the strings
        sort(a, w);

        // print results
        for (int i = 0; i < n; i++)
            StdOut.println(a[i]);
	}

}
