
public class Line {
	Point point, vector;
	public Line(Point p, Point vec) {
		point = p;	vector = vec;
	}
	static Point lineIntersection(Line a, Line b){
		Point u = Point.minus(a.point, b.point);
		double t = Point.cross(b.vector, u) / Point.cross(a.vector, b.vector);
		return Point.add(a.point, Point.multiply(a.vector, t));
	}
	static double distanceToLine(Line l, Point p){
		Point a = l.point, b = Point.add(l.point, Point.multiply(l.vector, 1.0));
		Point v1 = Point.minus(b, a), v2 = Point.minus(p, a);
		return Math.abs(Point.cross(v1, v2)) / Point.length(v1);
	}
	static boolean isPointOnSegment(Point p, Point a, Point b){
		Point vec1 = Point.minus(b, p), vec2 = Point.minus(a, p);
		return Point.dcmp(Point.cross(vec1, vec2)) == 0 && Point.dcmp(Point.dot(vec1, vec2)) < 0; 
	}
	public static void main(String[] args) {

	}
}
