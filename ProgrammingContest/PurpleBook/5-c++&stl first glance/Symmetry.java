import java.util.ArrayList;
import java.util.Scanner;
//uva 1595
public class Symmetry {

	private static class Dot{
		public int x,y;
		public Dot(int x,int y){
			this.x=x;
			this.y=y;
		}
		public boolean isSym(double ax,Dot o){
			if(Math.abs(2*ax-x-o.x)<1e-4&&o.y==y)	return true;
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int rnd = in.nextInt();
		for(int i=0;i<rnd;i++){
			int n = in.nextInt();
			ArrayList<Dot> dots = new ArrayList<Dot>();
			double sum=0;
			for(int j=0;j<n;j++){
				Dot dot = new Dot(in.nextInt(), in.nextInt());
				dots.add(dot);
				sum+=dot.x;
			}
			double ax = sum/n;
			int j;
			for(j=0;j<n;j++){
				Dot dot = dots.get(j);
				if(Math.abs(dot.x-ax)<1e-4)	continue;
				boolean sym=false;
				for(int k=0;k<n;k++){
					if(k==j)	continue;
					if(dot.isSym(ax, dots.get(k)))	{sym=true;break;}
				}
				if(sym==false)	break;
			}
			if(j<n)	System.out.println("NO");
			else	System.out.println("YES");
		}
		in.close();
	}
}
