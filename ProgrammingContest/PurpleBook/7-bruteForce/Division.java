import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Division {
	public int n;
	public StringBuilder out;
	public Division(){}
	public void solve() throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		out = new StringBuilder();
		boolean first = true;
		while(true){
			String s = r.readLine();
			n = Integer.parseInt(s);	if(n==0)	break;
			if(first)	first = false;
			else		out.append('\n');
			int max = 98765 / n;
			boolean sol = false;
			for(int de = 1234;de<=max;de++){
				int nu = de * n;
				if(!check(de,nu))	continue;
				sol = true;
				out.append(String.format("%05d / %05d  = %d\n", nu,de,n));
			}
			if(!sol)	out.append(String.format("There are no solutions for %d.\n", n));
		}
		System.out.print(out.toString());
	}	
	public boolean check(int de,int nu){
		if(nu>98765)	return false;
		int[] nums = new int[10];
		if(de<10000)	nums[0]++;
		while(de>0){
			nums[de%10]++;
			de/=10;
		}
		while(nu>0){
			nums[nu%10]++;
			nu/=10;
		}
		for(int i = 0;i<10;i++)
			if(nums[i]!=1)	return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Division a = new Division();
		a.solve();
	}

}
