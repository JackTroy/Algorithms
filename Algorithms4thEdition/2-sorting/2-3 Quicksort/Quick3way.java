import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3way {
	
	private Quick3way(){}
	
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
	}
	
	private static void sort(Comparable[] a,int lo,int hi){
		if(hi<=lo)return;
		int lt=lo,gt=hi,i=lo+1;
		Comparable v=a[lo];
		while(i<=gt){
			int cmp = a[i].compareTo(v);
			if		(cmp>0)	exch(a,i,gt--);
			else if	(cmp<0)	exch(a,i++,lt++);
			else 			i++;
		}
		sort(a,lo,lt-1);
		sort(a,gt+1,hi);
		paint(a);
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
		Quick3way.sort(a);
		Quick3way.show(a);
	}

}
