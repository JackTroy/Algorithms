
public class Line {
	Point point, vector;
	public Line(Point p, Point vec) {
		point = p;	vector = vec;
	}
	static Point lineIntersection(Line a, Line b){
		Point u = Point.Minus(a.point, b.point);
		double t = Point.cross(b.vector, u) / Point.cross(a.vector, b.vector);
		return Point.Add(a.point, Point.Multiply(a.vector, t));
	}
	static double distanceToLine(Line l, Point p){
		Point a = l.point, b = Point.Add(l.point, Point.Multiply(l.vector, 1.0));
		Point v1 = Point.Minus(b, a), v2 = Point.Minus(p, a);
		return Math.abs(Point.cross(v1, v2)) / Point.Length(v1);
	}
	public static void main(String[] args) {

	}

}
