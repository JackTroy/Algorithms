import java.util.Scanner;

public class PetriNetSimulation {
	public class Transition{
		public int[] in,out;
		public Transition(int pn){
			in = new int[pn];
			out = new int[pn];
		}
	}
	public PetriNetSimulation(){}
	public void solve(){
		Scanner in = new Scanner(System.in);
		int rnd = 0;
		while(true){
			int pn = in.nextInt();	if(pn==0)	break;
			int[] ps = new int[pn];
			for(int i=0;i<pn;i++)
				ps[i] = in.nextInt();
			int tn = in.nextInt();
			Transition[] ts = new Transition[tn];
			for(int i=0;i<tn;i++){
				ts[i] = new Transition(pn);
				while(true){
					int io = in.nextInt();
					if(io==0)	break;
					if(io>0)	ts[i].out[io-1]++;
					else		ts[i].in[-io-1]++;
				}
			}
			int times = in.nextInt();
			boolean dead = false;
			int t;
			for(t=0;t<times;t++){
				int j = 0;
				while(j<tn&&!enabled(ts[j],ps)) j++;
				//System.out.println(j);
				if(j==tn)	{dead = true;break;}
				for(int k=0;k<pn;k++){
					ps[k]-=ts[j].in[k];
					//System.out.println(String.format("rnd %d time %d t %d p %d", rnd+1,t,j,k));
				}		
				for(int k=0;k<pn;k++)
					ps[k]+=ts[j].out[k];
			}
			System.out.print(String.format("Case %d: ", ++rnd));
			if(dead)	System.out.println(String.format("dead after %d transitions", t));
			else		System.out.println(String.format("still live after %d transitions",times));
			System.out.print("Places with tokens:");
			for(int i = 0;i<pn;i++){
				if(ps[i]==0) continue;
				System.out.print(String.format(" %d (%d)", i+1,ps[i]));
			}
			System.out.print("\n\n");
		}
		in.close();
	}
	public boolean enabled(Transition t,int[] ps){
		for(int i=0;i<ps.length;i++)
			if(t.in[i]>ps[i])	return false;
		return true;
	}
	public static void main(String[] args) {
		PetriNetSimulation a = new PetriNetSimulation();
		a.solve();
	}
}
