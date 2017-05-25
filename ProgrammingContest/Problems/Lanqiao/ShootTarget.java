
public class ShootTarget {
	static int N = 8;
	static void f(int ta[], int da[], int k, int ho, int bu, int sc)
	{
		int i,j;
		if(ho<0 || bu<0 || sc<0) return;
		if(k==N){
			if(ho>0 || bu>0 || sc>0) return;
			for(i=0; i<N; i++){
				for(j=0; j<da[i]; j++) 
					System.out.print(ta[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(i=0; i<=bu; i++){
			da[k] = i;
			f(ta, da, k+1, i == 0 ? ho : ho-1 , bu-i, sc-ta[k]*i);  //Ìî¿ÕÎ»ÖÃ
		}
		
		da[k] = 0;
	}
	public static void main(String[] args) {
		int ta[] = {1,2,3,5,10,20,25,50};
		int da[] = new int[N];
		f(ta, da, 0, 3, 6, 96);
	}

}
