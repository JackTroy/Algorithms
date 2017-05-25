import java.util.ArrayList;
import java.util.Arrays;

public class Polygon {
	static double PolygonArea(Point[] points){
		double area = 0;
		for(int i = 1; i < points.length - 1; i++)
			area += Point.cross(Point.Minus(points[i], points[0]), Point.Minus(points[i+1], points[0]));
		return area / 2;
	}
	static ArrayList<Point> convexHull(Point[] points){
		Arrays.sort(points);
		ArrayList<Point> ch = new ArrayList<>();
		int m = 0;
		for(int i = 0; i < points.length; i++){
			//to increase precision, use dcmp instead of comparing to 0 directly
			while(m > 1 && Point.cross(Point.Minus(ch.get(m-1), ch.get(m-2)), Point.Minus(points[i], ch.get(m-2))) >= 0)	m--;
			ch.add(m++, points[i]);
		}
		int k = m;
		for(int i = points.length-2; i >= 0; i--){
			while(m > k && Point.cross(Point.Minus(ch.get(m-1), ch.get(m-2)), Point.Minus(points[i], ch.get(m-2))) >= 0)	m--;
			ch.add(m++, points[i]);
		}
		return ch;
	}
}
