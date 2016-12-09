
public class Brute {
	private Brute(){}
	public static int search(String txt,String pat){
		int n = txt.length(),m=pat.length();
		for(int i=0;i<=n-m;i++){
			int j;
			for(j=0;j<m;j++){
				if(txt.charAt(i+j)!=pat.charAt(j))break;
			}
			if(j==m) return i;
		}
		return n;
	}
	public static int searchRe(String txt,String pat){
		int n = txt.length(),m=pat.length();
		for(int i=n-1;i>=m-1;i--){
			int j;
			for(j=m-1;j>=0;j--){
				if(txt.charAt(i+j+1-m)!=pat.charAt(j))break;
			}
			if(j==-1) return i-m+1;
		}
		return n;
	}
	public static int search2(String txt,String pat){
		int n = txt.length(),m=pat.length(),i,j;
		for(i=0,j=0;i<n&&j<m;i++)
			if(txt.charAt(i)==pat.charAt(j))	j++;
			else{
				i-=j;
				j=0;
			}
		if(j==m)	return i-m;
		else 		return n;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String pat = args[0];
        String txt = args[1];
        int offset = search(txt, pat);
        int offsetRe = searchRe(txt, pat);
        int offset2 = search(txt, pat);
        System.out.println(txt.substring(offset, offset+pat.length()));
        System.out.println(txt.substring(offsetRe, offset+pat.length()));
        System.out.println(txt.substring(offset2, offset+pat.length()));
	}
}
