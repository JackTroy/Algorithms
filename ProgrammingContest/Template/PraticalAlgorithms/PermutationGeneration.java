
public class PermutationGeneration {
	public static void generatePermutation(Integer[] p, int n, int d){
		//default for 0~n-1
		if(d == n){
			for(Integer i:p)	System.out.print(i);
			System.out.println();
		}
		else{
			boolean exist[] = new boolean[n];
			for(int i = 0; i < d; i++)	exist[p[i]] = true;
			for(int i = 0; i < n; i++)
				if(!exist[i]){
					p[d] = i;
					generatePermutation(p, n, d+1);
				}
		}
	}
	public static void generatePermutationWithRepeat(Integer[] p, int[] n, int d){
		//the n[] should be sorted
		if(d == n.length){
			for(Integer i:p)	System.out.print(i);
			System.out.println();
		}
		else{
			for(int i = 0; i < n.length; i++)	
				if(i==0||n[i]!=n[i-1]){
					int c1 = 0, c2 = 0;
					for(int j = 0;j < d;j++) 
						if(p[j] == n[i])	
							c1++;
					for(int j = 0;j<n.length;j++) 
						if(n[j] == n[i])	
							c2++;
					if(c1<c2){
						p[d] = n[i];
						generatePermutationWithRepeat(p, n, d+1);
					}
			}
				
		}
	}
	public static void main(String[] args) {
		int[] a = {1, 1, 2, 2};
		Integer[] p = new Integer[4];
		generatePermutationWithRepeat(p, a, 0);
		generatePermutation(p, 4, 0);
	}

}
