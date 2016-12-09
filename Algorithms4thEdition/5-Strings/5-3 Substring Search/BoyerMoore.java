
public class BoyerMoore {
	private int[] right;
	private String pat;
	public BoyerMoore(String pat){
		this.pat=pat;
		int M = pat.length();
		int R = 256;
		right = new int[R];
		for(int c=0;c<R;c++)
			right[c]=-1;
		for(int j=0;j<M;j++)
			right[pat.charAt(j)]=j;
	}
	public int search(String txt){
		int N=txt.length(),M=pat.length();
		int skip;
		//i here is the start pointer of pat
		for(int i=0;i<=N-M;i+=skip){
			skip=0;
			for(int j=M-1;j>=0;j--)
				if(pat.charAt(j)!=txt.charAt(i+j)){
					skip = j-right[txt.charAt(i+j)];
					if(skip<1) skip=1;
					break;
				}
			if(skip==0) return i;
		}
		return N;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
