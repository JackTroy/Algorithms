import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ErraticExpansion {
	public static long compute(long k, long i){
		//System.out.prlongln(i);
		if(i == 0)	return 0;
		if(k == 0)	return 1;
		if(i <= (1 << (k - 1)))	return 2 * compute(k - 1, i);
		else					return 2 * (long)Math.pow(3, k - 1) + compute(k - 1, i - (1 << (k - 1)));
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		long rnd = Long.parseLong(in.readLine()), n = 0;
		while(n++ < rnd){
			String[] nums = in.readLine().split(" ");
			long k = Long.parseLong(nums[0]);
			long a = Long.parseLong(nums[1]);
			long b = Long.parseLong(nums[2]);
			long ansA = compute(k,a - 1);
			long ansB = compute(k,b);
			
			out.append(String.format("Case %d: %d\n", n, ansB - ansA));
		}
		System.out.print(out.toString());
	}

}
