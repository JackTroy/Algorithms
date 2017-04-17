import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LightingSystemDesign {
	public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public StringBuilder out = new StringBuilder();
	
	public class Lamp implements Comparable<Lamp>{
		public int[] data;
		public Lamp(){
			data = new int[4];
		}
		public int compareTo(Lamp that){
			if(data[0] < that.data[0])		return -1;
			else if(data[0] > that.data[0])	return 1;
			else							return 0;
		}
	}
	public void solve() throws NumberFormatException, IOException{
		while(true){
			int types = Integer.parseInt(in.readLine());	if(types == 0)	break;
			Lamp[] lamps = new Lamp[types];
			int[] sums = new int[types];
			for(int i = 0; i < types; i++){
				String[] paras = in.readLine().split(" ");
				Lamp tmp = new Lamp();
				for(int j = 0; j < 4; j++)
					tmp.data[j] = Integer.parseInt(paras[j]);
				lamps[i] = tmp;
			}
			Arrays.sort(lamps);
			for(int i = 0; i < types; i++){
				if(i == 0)	sums[0] = lamps[i].data[3];
				else		sums[i] = sums[i - 1] + lamps[i].data[3];
			}
			int[] d = new int[types];
			for(int i = 0; i < types; i++){
				int ans = Integer.MAX_VALUE;
				for(int j = 0; j <= i; j++){
					int tmp = (sums[i] - sums[j] + lamps[j].data[3]) * lamps[i].data[2] + lamps[i].data[1];
					if(j > 0) tmp += d[j - 1];
					ans = Math.min(ans, tmp);
				}
				d[i] = ans;
			}
			out.append(d[types - 1] + "\n");
		}
		System.out.print(out.toString());
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		LightingSystemDesign a = new LightingSystemDesign();
		a.solve();
	}

}
