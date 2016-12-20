import edu.princeton.cs.algs4.Quick3string;

public class SuffixArray {
	private final String[] sa;
	private final int N;
	public SuffixArray(String text){
		N=text.length();
		sa = new String[N];
		for(int i=0;i<N;i++)
			sa[i]=text.substring(i);
		Quick3string.sort(sa);
	}
	public int length(){
		return N;
	}
	public String select(int i){
		return sa[i];
	}
	public int index(int i){
		//return the beginning index of the string in original text with the index i in SuffixArray
		return N-sa[i].length();
	}
	public int lcp(int i){
		return lcp(sa[i],sa[i-1]);
	}
	private static int lcp(String a,String b){
		int N = Math.min(a.length(), b.length());
		for(int i=0;i<N;i++)
			if(a.charAt(i)!=b.charAt(i))
				return i;
		return N;
	}
	public int rank(String key){
		int lo=0,hi=N-1;
		while(lo<=hi){
			int mid=lo+(hi-lo)/2;
			int cmp = key.compareTo(sa[mid]);
			if(cmp==0)		return mid;
			else if(cmp<0)	lo=mid+1;
			else			hi=mid-1;
		}
		return N;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
