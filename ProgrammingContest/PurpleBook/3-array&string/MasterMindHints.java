import java.util.Scanner;
//uva340
public class MasterMindHints {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int round=0;
		while(in.hasNext()){
			int n = in.nextInt();
			if(n==0) break;
			int[] ans = new int[n];
			for(int i=0;i<n;i++)
				ans[i]=in.nextInt();
			
			System.out.println("Game "+(++round)+":");
			
			while(in.hasNextLine()){
				boolean end=true;
				int[] gus = new int[n];
				int a=0,b=0;
				for(int i=0;i<n;i++){
					gus[i]=in.nextInt();
					if(ans[i]==gus[i]) a++;
					if(gus[i]!=0)	end=false;
				}
				if(end==true)	break;
				for(int d=1;d<=9;d++){
					int ansC=0,gusC=0;
					for(int i=0;i<n;i++){
						if(ans[i]==d) ansC++;
						if(gus[i]==d) gusC++;
					}
					b+=Math.min(ansC, gusC);
				}
				System.out.println(String.format("    (%d,%d)", a,b-a));
			}
		}
		in.close();
	}

}
