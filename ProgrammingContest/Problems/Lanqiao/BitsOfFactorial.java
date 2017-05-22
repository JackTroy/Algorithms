
public class BitsOfFactorial {
	
	public static void main(String[] args) {
		double ans = 0;
		for(int i = 1; i <= 9999; i++){
			double tmp = Math.log(i) / Math.log(2);
			ans += tmp;
		}
		System.out.println((int)ans + 1);
	}

}
