package bezier.controller;

import java.util.ArrayList;
import java.util.List;

import bezier.model.Curve;
import bezier.model.basics.Point;

public class BezierGenerator {
	
	private static Point d1, d2, d3;
	private static Point a, b, c, d;
	private static Point actualPoint;
	private static final double n = 50;

	private static void initializeStart() {
		d3 = calculateD3();
		d2 = calculateD2();
		d1 = calculateD1();
		actualPoint = d;
	}
	
	private static Point calculateD3() {
		double k = 6/Math.pow(n, 3);
		double x = k * a.getX();
		double y = k * a.getY();
		return new Point(x, y);
	}
	
	private static Point calculateD2() {
		double kb = 2/Math.pow(n, 2);
		double ka = 6/Math.pow(n, 3);
		double x = kb * b.getX() + ka * a.getX();
		double y = kb * b.getY() + ka * a.getY();
		return new Point(x, y);
	}
	
	private static Point calculateD1() {
		double ka = 1/Math.pow(n, 3);
		double kb = 1/Math.pow(n, 2);
		double kc = 1/n;
		double x = ka * a.getX() + kb * b.getX() + kc * c.getX();
		double y = ka * a.getY() + kb * b.getY() + kc * c.getY();
		return new Point(x, y);
	}
	
	private static Point generateNextPoint() {
		Point nextD2 = Point.addition2Points(d2, d3);
		Point nextD1 = Point.addition2Points(d1, d2);
		Point nextPoint = Point.addition2Points(actualPoint, d1);
		d2 = nextD2;
		d1 = nextD1;
		return nextPoint;
	}
	
	public static List<Point> generateBezierPoints(Curve curve) {
		a = curve.calculateA();
		b = curve.calculateB();
		c = curve.calculateC();
		d = curve.calculateD();
		initializeStart();
		List<Point> points = new ArrayList<Point>();
		points.add(actualPoint);
		for (int i = 0; i < n; i++) {
			actualPoint = generateNextPoint();
			points.add(actualPoint);
		}
		return points;
	}
}
