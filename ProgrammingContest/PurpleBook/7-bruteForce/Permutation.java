
public class Permutation {
	public static void print(String s,int n){
		if(s.length()==n)	System.out.println(s);
		else{
			for(int i = 1; i <= n; i++)
				if(s.indexOf(i+"")==-1)	print(s+i, n);
		}
	}
	public static void print2(String s,int[] n){
		if(s.length()==n.length)	System.out.println(s);
		else{
			for(int i = 0; i < n.length; i++)	
				if(i==0||n[i]!=n[i-1]){
					int c1 = 0, c2 = 0;
					for(int j = 0;j<s.length();j++) 
						if(s.charAt(j) == n[i] + '0')	
							c1++;
					for(int j = 0;j<n.length;j++) 
						if(n[j] == n[i])	
							c2++;
					if(c1<c2)
						print2(s+n[i], n);
			}
				
		}
	}
	public static void main(String[] args) {
		//print("",3);
		int[] a = {1,1, 2, 2};
		print2("",a);

	}

}
