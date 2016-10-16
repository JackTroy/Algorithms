import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
	
	private Quick(){}
	
	private static final int CUTOFF=15;
	
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
		assert isSorted(a);
	}
	
	private static void sort(Comparable[] a,int lo,int hi){
		if(hi<=lo+CUTOFF){Insertion.sort(a, lo, hi);return;}
		int j = partition(a,lo,hi);
		sort(a,lo,j-1);
		sort(a,j+1,hi);
		paint(a);
	}
	
	//think of this in 3 way,all is small,all is large and small and large
	//1st case:i==hi,2nd case:j==lo,3rd case:i>=j
	private static int partition(Comparable[] a,int lo,int hi) {
		int i=lo,j=hi+1;
		Comparable v = a[lo];
		while(true){
			while(less(a[++i],v))if(i==hi)break;
			while(less(v,a[--j]))if(j==lo)break;
			if(i>=j)break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}

	private static void paint(Comparable[] a){
		int N=a.length;
		StdDraw.setYscale(0,1);
		StdDraw.setXscale(0,2*N);		
		StdDraw.clear();
		for(int i=0;i<N;i++){
			StdDraw.filledRectangle(2*i+1, 0, 0.5, (double) a[i]);
		}
		StdDraw.show(20); 
	}
	
	private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 1001;
		Double a[] = new Double[N];
		for(int i=0;i<N;i++){
			a[i] = StdRandom.uniform();
		}
		Quick.sort(a);
	}

}
