import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class IrrelevantElements {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt() - 1, m = in.nextInt(),tmpM = m;
			
			ArrayList<Integer> primes = new ArrayList<Integer>();
			int maxprime = (int) (Math.sqrt(tmpM) + 0.5);
			for(int i = 2; i <= maxprime; i++){
				if(tmpM % i == 0){
					primes.add(i);
					while(tmpM % i == 0)	tmpM /= i;
				}
				if(tmpM == 1)	break;
			}
			if(tmpM > 1)	primes.add(tmpM);
			
			
			boolean[] irrelevant = new boolean[n + 2];
			Arrays.fill(irrelevant, true);
			irrelevant[0] = false;
			irrelevant[n] = false;
			for(int i = 0; i < primes.size(); i++){
				int prime = primes.get(i), e = 0, eOfM = 0;
				
				tmpM = m;
				while(tmpM % prime == 0)	{ eOfM++;	tmpM /= prime; }
				
				for(int k = 1; k < n; k++){
					int tmp = n - k + 1;
					while(tmp % prime == 0) { e++;	tmp /= prime; }
					tmp = k;
					while(tmp % prime == 0) { e--;	tmp /= prime; }
					if(e < eOfM)	irrelevant[k] = false;
				}
			}
			ArrayList<Integer> irn = new ArrayList<Integer>();
			for(int i = 0; i <= n; i++)
				if(irrelevant[i])
					irn.add(i + 1);
			System.out.println(irn.size());
			if(!irn.isEmpty()){
				System.out.print(irn.get(0));
				for(int i = 1; i < irn.size(); i++)	System.out.print(" " + irn.get(i));
			}
			System.out.println();
		}
		in.close();
	}

}
