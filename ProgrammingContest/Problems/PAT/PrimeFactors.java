import java.util.ArrayList;
import java.util.Scanner;

public class PrimeFactors {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while(in.hasNext()){
			ArrayList<Integer> primes = new ArrayList<>();
			Long n = in.nextLong(), origin = n;
			int m = (int) Math.sqrt(n);
			int[] isPrime = new int[m + 5];
			for(int i = 2; i <= m; i++){
				if(isPrime[i] == 0){
					for(int j = i * i; j <= m; j += i)
						isPrime[j] = -1;
					if(n % i == 0){
						primes.add(i);
						while(n % i == 0){
							isPrime[i]++;
							n = n / i;
						}
					}
				}
			}
			if(n == origin){
				out.append(n + "=" + n + "\n");
				continue;
			}
			out.append(origin + "=");
			if(primes.size() > 0){
				out.append(String.format("%d", primes.get(0)));
				if(isPrime[primes.get(0)] > 1)	out.append(String.format("^%d", isPrime[primes.get(0)]));
				for(int i = 1; i < primes.size(); i++){
					out.append(String.format("*%d", primes.get(i)));
					if(isPrime[primes.get(i)] > 1)	out.append(String.format("^%d", isPrime[primes.get(i)]));
				}
			}
			if(n > 1)	out.append("*" + n);
			out.append("\n");
		}
		System.out.print(out.toString());
		in.close();
	}

}
