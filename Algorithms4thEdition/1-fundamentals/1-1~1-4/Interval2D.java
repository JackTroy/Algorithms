

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Interval2D {
	private double x0,y0,x1,y1;
	public Interval2D(Interval1D x, Interval1D y){
		this.x0=x.getlow();
		this.x1=x.gethigh();
		this.y0=y.getlow();
		this.y1=y.gethigh();
	}
	public Interval2D(){
		this.x0=0;
		this.x1=0;
		this.y0=0;
		this.y1=0;
	}
	public double area(){
		return (this.x1-this.x0)*(this.y0-this.y1);
	}
	public boolean contains(Point2D p){
		if((p.x()>=this.x0&&p.x()<=this.x1)&&(p.y()<=this.y0&&p.y()>=this.y1)) return true;
		else return false;
	}
	public boolean intersect(Interval2D that){
		if((this.x0>that.x1||this.x1<that.x0)||(this.y0>that.y1||this.y1<that.y0)) return true;
		else return false;
	}
	public boolean include(Interval2D that){
		if((this.x0<that.x0&&this.x1>that.x1)&&(this.y0<that.y0&&this.y1>that.y1)) return true;
		else return false;
	}
	public void draw(){
		StdDraw.rectangle((x1+x0)/2, (y1+y0)/2, (x1-x0)/2, (y1-y0)/2);
	}
	public static void main(String[] args){
		int N=Integer.parseInt(args[0]);
		double min=Double.parseDouble(args[1]);
		double max=Double.parseDouble(args[2]);
		StdDraw.setCanvasSize(800,800);
		StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.005);
        StdDraw.square((max+min)/2, (max+min)/2, (max-min)/2);
		Interval2D[] a=new Interval2D[N];
		for(int i=0;i<N;i++){
			a[i]=new Interval2D(new Interval1D(StdRandom.uniform(min,max),StdRandom.uniform(min,max)),new Interval1D(StdRandom.uniform(min,max),StdRandom.uniform(min,max)));
			a[i].draw();
		}
		Counter ct=new Counter();
		Counter ic=new Counter();
		for(int i=0;i<N;i++){
			for(int j=N-1;j>i;j--){
				if(a[i].include(a[j])) ic.increment();
				if(a[i].intersect(a[j])) ct.increment();
			}
		}
		System.out.println(ic);
		System.out.println(ct);
	}
}
