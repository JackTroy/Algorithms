import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 100000; i++){
			boolean[] a = new boolean[10000];
		}
	    long end = System.currentTimeMillis();
	    System.out.println(start - end);
	    start = System.currentTimeMillis();
	    boolean[] a = new boolean[10000];
	    for(int i = 0; i < 100000; i++)
	    	Arrays.fill(a, false);
	    end = System.currentTimeMillis();
	    System.out.println(start - end);
	}
}
