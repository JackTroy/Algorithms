
public class Point implements Comparable<Point>{
	//can be treated as point or vector
	
	double x,y;
	public Point(double x_, double y_) {
			x = x_;
			y = y_;
	}
	static Point add(Point a, Point o){
		return new Point(a.x + o.x, a.y + o.y);
	}
	static Point minus(Point a, Point o){
		return new Point(a.x - o.x, a.y - o.y);
	}
	static Point multiply(Point o, double c){
		return new Point(o.x * c, o.y * c);
	}
	public int compareTo(Point o) {
		if(x < o.x)			return -1;
		else if(x > o.x)	return 1;
		else				return Double.compare(y, o.y);
	}
	static final double eps = 1e-10;
	static int dcmp(double x){
		if(Math.abs(x) < eps) 	return 0;
		else if(x < 0)			return -1;
		else					return 1;
	}
	static boolean equal(Point a, Point o){
		return dcmp(a.x - o.x) == 0 && dcmp(a.y - o.y) == 0;
	}
	static double dot(Point a, Point o){
		return a.x * o.x + a.y * o.y;
	}
	static double length(Point o){
		return Math.sqrt(dot(o, o));
	}
	static double angle(Point a, Point o){
		return Math.acos(Point.dot(a, o)) / length(a) / length(o);
	}
	static double cross(Point a, Point b){
		return a.x * b.y - a.x * b.y;
	}
	static double area(Point a,Point b, Point c){
		return cross(minus(b, a), minus(c, a)) / 2;
	}
}