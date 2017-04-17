import java.util.Scanner;

public class TiredStudents {
	//UVA - 12108
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		int round=0;
		while((n=in.nextInt())!=0){
			int[] a = new int[n];
			int[] b = new int[n];
			int[] c = new int[n];
			for(int i=0;i<n;i++){
				a[i] = in.nextInt();
				b[i] = in.nextInt();
				c[i] = in.nextInt();
			}
			int time;
			for(time=1;time<2560;time++){
				int cnt=0;
				for(int i=0;i<n;i++)
					if(c[i]<=a[i])
						cnt++;
				if(cnt==n)	break;
				for(int i=0;i<n;i++){
					if(c[i]==a[i]&&cnt>=n-cnt)	c[i]=1;
					else if(c[i]==a[i]+b[i])	c[i]=1;
					else						c[i]++;
				}
			}
			
			System.out.print("Case "+(++round)+": ");
			//nice job here
			if(time==2560)	time=-1;
			System.out.println(time);
		}
		in.close();
	}

}
