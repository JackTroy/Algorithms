import edu.princeton.cs.algs4.StdIn;

public class LRS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = StdIn.readAll().replaceAll("\\s+", " ").trim();
		int N = text.length();
		SuffixArray sa = new SuffixArray(text);
		String lrs = "";
		String test = "";
		for(int i=1;i<N;i++){
			int len = sa.lcp(i);
			if(lrs.length()<len){
				lrs = sa.select(i).substring(0,len);
				test = text.substring(sa.index(i),sa.index(i)+len);
			}	
		}
		System.out.println(lrs);
		System.out.println(test);
		System.out.println(text);
	}

}
