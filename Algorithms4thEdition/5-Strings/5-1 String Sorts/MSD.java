import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MSD {
	private MSD(){}
	private static int R = 256;
	private static final int M = 15;//threshold for sort change
	private static int charAt(String s,int d){
        if (d == s.length()) return -1;
        return s.charAt(d);
	}
	public static void sort(String[] a){
		int n=a.length;
		String[] aux = new String[n];
		sort(a, 0, n-1, 0, aux);
	}
	private static void sort(String[] a,int lo ,int hi,int d,String[] aux){
		if(hi<=lo+M){
			insertion(a,lo,hi,d);
			return;
		}
		int[] count = new int[R+2];
		
		//calculate frequency
		//key range from 0 to R-1,that is 0 to 255 here
		//count range from 0 to 256,
		for(int i=lo;i<=hi;i++){
			int c = charAt(a[i], d);
			count[c+2]++;
		}
			
		//compute index
		for(int r=0;r<R;r++)
			count[r+1]+=count[r];
			
		//move data
		for(int i=lo;i<=hi;i++)
			aux[count[a[i].charAt(d)+1]++]=a[i];
		
		//copy back,note in array aux[],string starts from 0 to hi-lo
		//because of index from array count[] 
		for(int i=lo;i<=hi;i++)
			a[i]=aux[i-lo];
		
		for(int r=0;r<R;r++)
			sort(a, lo+count[r], lo+count[r+1]-1, d+1, aux);
	}
	private static void insertion(String[] a,int lo ,int hi,int d){
		for(int i=lo;i<=hi;i++)
			for(int j=i;j>lo && less(a[j],a[j-1],d);j--)
				exch(a,j,j-1);
	}
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
        sort(a);

        // print results
        for (int i = 0; i < n; i++)
            StdOut.println(a[i]);
	}

}
