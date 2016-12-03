import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchStrings {
	private BinarySearchStrings(){}
	public static void search(String[] a,String tar){
		search(a, tar,0,a.length-1,0);
	}
	private static void search(String[] a,String tar,int lo ,int hi,int d){
		if(lo>=hi){
			StdOut.println("Not found");
			return;
		}
		int lod = sameC(a[lo], tar, d);
		int hid = sameC(a[hi], tar, d);
		d=Math.min(lod, hid);
		
		int mid = (lo+hi)/2;
		int cmp = compareTo(tar, a[mid], d);
		if(cmp>0)		search(a, tar,mid+1,hi,d);
		else if(cmp<0)	search(a, tar,lo,mid-1,d);
		else
			StdOut.println(mid);
	}
    private static int compareTo(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return -1;
            if (v.charAt(i) > w.charAt(i)) return 1;
        }
        if(v.length()<w.length())		return -1;
        else if(v.length()>w.length())	return 1;
        return 0;
    }
    private static int sameC(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
        	if (v.charAt(i) == w.charAt(i))d++;
        	else break;
        }
        return d;
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
        LSD.sort(a, w);

        // print results
        for (int i = 0; i < n; i++)
            StdOut.println(a[i]);
        
        BinarySearchStrings.search(a,"tap");
        
	}

}
