

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class Interval1D {
	private double lo,hi;
	public Interval1D(double lo,double hi){
		if(lo>hi){
			double temp=lo;
			lo=hi;
			hi=temp;
		}
		this.lo=lo;
		this.hi=hi;
	}
	public Interval1D(){
		this.lo=0;
		this.hi=0;
	}
	public double length(){
		return hi-lo;
	}
	public boolean contains(double x){
		if(x>=this.lo&&x<=this.hi) return true;
		else return false;
	}
	public boolean intersect(Interval1D that){
		if(this.lo>that.hi||this.hi<that.lo) return false;
		else return true;		
	}
	public double getlow(){
		return this.lo;
 	}
	public double gethigh(){
		return this.hi;
 	}
	void draw(){
		StdDraw.line(lo, 50, hi, 50);
	}
	public static void main(String[] args){
		int N=Integer.parseInt(args[0]);
		double[][] a=new double [N][2];
		for(int i=0;i<N;i++){
			System.out.print("Low:");
			a[i][0]=StdIn.readDouble();
			System.out.print("High:");
			a[i][1]=StdIn.readDouble();
			System.out.println();			
		}
		Interval1D[] b=new Interval1D[N];
		StdDraw.setCanvasSize(800,800);
		StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
		StdDraw.setPenRadius(0.005);
		for(int i=0;i<N;i++){
			b[i]=new Interval1D(a[i][0],a[i][1]);
			b[i].draw();
		}
	}
}
