import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Merge {

	private Merge(){} 
	
	private static final int CUTOFF=7;
	
	//private static Comparable [] aux;
	//do not use static array above because once sort is 
	//called at the same time ,multiple arrays will 
	//be mixed up.So initialize array when calling 
	//sort,that would create different array for 
	//different sort.
	
	public static void sort(Comparable[] a){
		Comparable[] aux=new Comparable[a.length];
		sort(a,aux,0,a.length-1);
		assert isSorted(a);
	}
	//this sort can be improved by switch array at every recursion,
	//it save the time of copying array every time calling merge
	//use array.clone() to copy array and if a[mid]<a[mid+1],
	//use System.arraycopy();
	
	private static void sort(Comparable[] a,Comparable[] aux,int lo,int hi){
		if(hi-lo<=CUTOFF){
			Insertion.sort(a, lo, hi);
			return;
		}
		if(hi<=lo)return;
		int mid = lo+(hi-lo)/2;
		sort(a,aux,lo,mid);
		sort(a,aux,mid+1,hi);
		if(less(a[mid+1],a[mid]))
			merge(a,aux,lo,mid,hi);
		paint(a);
	}
		
	public static void sortDownTop(Comparable[] a){
		Comparable[] aux=new Comparable[a.length];
		for(int sz=1;sz<a.length;sz*=2){
			//a.length-1-(sz-1) i.e. the estimated pointer
			for(int lo=0;lo<a.length-sz;lo+=2*sz){
				merge(a,aux,lo,lo+sz-1,Math.min(a.length-1, lo+2*sz-1));//
			}
			paint(a);
		}
	}
	
	private static void merge(Comparable[] a,Comparable[] aux,int lo,int mid,int hi){
		int i=lo,j=mid+1;
		
		for(int k=lo;k<=hi;k++){
			aux[k]=a[k];
		}
		for(int k=lo;k<=hi;k++){//the iteration here is just for counting 
			if(i>mid)					a[k]=aux[j++];
			else if(j>hi)				a[k]=aux[i++];
			else if(less(aux[i],aux[j]))a[k]=aux[i++];
			else 						a[k]=aux[j++];
		}
		//another version of merge is from aux to a ,not based on a then think of aux
		//this version shows more simplicity 
	}
	public static Integer[] perm(Comparable[] a){
		Integer[] perm = new Integer[a.length];
		Integer[] perm_copy = new Integer[a.length];
		for(int i=0;i<a.length;i++){
			perm[i]=i;
		}			
		perm(a,perm,perm_copy,0,a.length-1);
		return perm;
	}
	
	private static void perm(Comparable[] a,Integer[] perm,Integer[] perm_copy,int lo,int hi){
		if(hi<=lo)return;
		int mid = lo+(hi-lo)/2;
		perm(a,perm,perm_copy,lo,mid);
		perm(a,perm,perm_copy,mid+1,hi);
		merge(a,perm,perm_copy,lo,mid,hi);
	}
	
	private static void merge(Comparable[] a,Integer[] perm,Integer[] perm_copy,int lo,int mid,int hi){
		int i=lo,j=mid+1;
		
		for(int k=lo;k<=hi;k++){
			perm_copy[k]=perm[k];
		}
		//think of index here as pointer to entries in array a.
		for(int k=lo;k<=hi;k++){
			if(i>mid)					{perm[k]=perm_copy[j++];}
			else if(j>hi)				{perm[k]=perm_copy[i++];}
			else if(less(a[perm_copy[i]],a[perm_copy[j]])){perm[k]=perm_copy[i++];}
			else 						{perm[k]=perm_copy[j++];}
		}
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
		int N = 1024;
		Double a[] = new Double[N];
		for(int i=0;i<N;i++){
			a[i] = StdRandom.uniform();
		}
		Merge.sort(a);
		Merge.show(a);
	}

}
