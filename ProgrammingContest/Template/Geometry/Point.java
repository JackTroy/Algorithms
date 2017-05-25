
public class Point implements Comparable<Point>{
	//can be treated as point or vector
	
	double x,y;
	public Point(double x_, double y_) {
			x = x_;
			y = y_;
	}
	static Point Add(Point a, Point o){
		return new Point(a.x + o.x, a.y + o.y);
	}
	static Point Minus(Point a, Point o){
		return new Point(a.x - o.x, a.y - o.y);
	}
	static Point Multiply(Point o, double c){
		return new Point(o.x * c, o.y * c);
	}
	public int compareTo(Point o) {
		if(x < o.x)			return -1;
		else if(x > o.x)	return 1;
		else				return Double.compare(y, o.y);
	}
	static final double eps = 1e-10;
	static boolean dcmp(double x){
		return Math.abs(x) < eps;
	}
	static boolean equal(Point a, Point o){
		return dcmp(a.x - o.x) && dcmp(a.y - o.y);
	}
	static double Dot(Point a, Point o){
		return a.x * o.x + a.y * o.y;
	}
	static double Length(Point o){
		return Math.sqrt(Dot(o, o));
	}
	static double Angle(Point a, Point o){
		return Math.acos(Point.Dot(a, o)) / Length(a) / Length(o);
	}
	static double cross(Point a, Point b){
		return a.x * b.x - a.y * b.y;
	}
	static double area(Point a,Point b, Point c){
		return cross(Minus(b, a), Minus(c, a)) / 2;
	}
}