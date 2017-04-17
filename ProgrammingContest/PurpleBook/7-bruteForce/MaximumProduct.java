import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class MaximumProduct {

	public static void main(String[] args) throws IOException {
		int rnd = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String line = reader.readLine();	if(line==null)	break;
			int n = Integer.parseInt(line);
			int[] nums = new int[n];
			String[] ss = reader.readLine().split(" ");
			for(int i=0;i<n;i++)
				nums[i] = Integer.parseInt(ss[i]);
			long max = Long.MIN_VALUE;
			for(int i=0;i<n;i++){
				long p = 1;
				for(int j = i;j<n;j++){
					p = p * nums[j];
					if(p>max)	max = p;
				}
			}
			if(max<0)	System.out.println(String.format("Case #%d: The maximum product is %d.\n", (++rnd),0));
			else		System.out.println(String.format("Case #%d: The maximum product is %d.\n", (++rnd),max));
			reader.readLine();
		}
	}

}
