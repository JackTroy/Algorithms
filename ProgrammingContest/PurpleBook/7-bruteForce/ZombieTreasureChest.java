import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Zombie's Treasure Chest UVA - 12325 
public class ZombieTreasureChest {
	public int n,s1,v1,s2,v2;
	public void solve() throws NumberFormatException, IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int rnd = Integer.parseInt(in.readLine());
		for(int r = 1; r <= rnd; r++){
			String[] nums = in.readLine().split(" ");
			n = Integer.parseInt(nums[0]);
			s1 = Integer.parseInt(nums[1]);
			v1 = Integer.parseInt(nums[2]);
			s2 = Integer.parseInt(nums[3]);
			v2 = Integer.parseInt(nums[4]);
			if(s1 > s2)	{
				int temp = s2;
				s2 = s1;
				s1 = temp;
				temp = v2;
				v2 = v1;
				v1 = temp;
			}
			//for(int i = 0; i < 5; i++)	data[i] = Integer.parseInt(nums[i]);
			out.append(String.format("Case #%d: %d\n", r,compute()));
		}
		System.out.print(out.toString());
	}
	public Long compute() {
		Long ans = (long)0;
		
	    if(s2 < n/s2){ // both s1 and s2 are small
	      for(Long i = (long)0; i <= s1; i++){
	        ans = Math.max(ans, v2*i + (n-s2*i)/s1*v1);
	      }
	      for(Long i = (long)0; i <= s2; i++){
	        ans = Math.max(ans, v1*i + (n-s1*i)/s2*v2);
	      }
	    }
	    else{ // s2 is large
	      for(Long i = (long)0; s2*i <= n; i++)
	        ans = Math.max(ans, v2*i + (n-s2*i)/s1*v1);
	    }
		return ans;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		ZombieTreasureChest a = new ZombieTreasureChest();
		a.solve();
	}

}
