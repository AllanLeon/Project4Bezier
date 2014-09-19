package bezier.controller;

import java.util.ArrayList;
import java.util.List;

import bezier.model.Curve;
import bezier.model.basics.Point;

public class BezierGenerator {
	
	private Curve curve;
	private Point d1, d2, d3;
	private Point a, b, c, d;
	private Point actualPoint;
	private int n;
	
	public BezierGenerator(Curve curve) {
		n = 50;
		this.curve = curve;
		this.a = curve.calculateA();
		this.b = curve.calculateB();
		this.c = curve.calculateC();
		this.d = curve.calculateD();
		initializeStart();
	}

	private void initializeStart() {
		d3 = calculateD3();
		d2 = calculateD2();
		d1 = calculateD1();
		actualPoint = d;
	}
	
	private Point calculateD3() {
		double k = 6/Math.pow(n, 3);
		double x = k * a.getX();
		double y = k * a.getY();
		return new Point(x, y);
	}
	
	private Point calculateD2() {
		double kb = 2/Math.pow(n, 2);
		double ka = 6/Math.pow(n, 3);
		double x = kb * b.getX() + ka * a.getX();
		double y = kb * b.getY() + ka * a.getY();
		return new Point(x, y);
	}
	
	private Point calculateD1() {
		double ka = 1/Math.pow(n, 3);
		double kb = 1/Math.pow(n, 2);
		double kc = 1/n;
		double x = ka * a.getX() + kb * b.getX() + kc * c.getX();
		double y = ka * a.getY() + kb * b.getY() + kc * c.getY();
		return new Point(x, y);
	}
	
	private Point generateNextPoint() {
		Point nextD2 = Point.addition2Points(d2, d3);
		Point nextD1 = Point.addition2Points(d1, d2);
		Point nextPoint = Point.addition2Points(actualPoint, d1);
		d2 = nextD2;
		d1 = nextD1;
		return nextPoint;
	}
	
	public List<Point> generateBezierPoints() {
		List<Point> points = new ArrayList<Point>();
		/*points.add(actualPoint);
		for (int i = 0; i < n; i++) {
			actualPoint = generateNextPoint();
			points.add(actualPoint);
		}*/
		double u = 0;
		double du = 1.0/n;
		while (u <= 1.000000001) {
			points.add(curve.getPointAt(u));
			u += du;
			System.out.println(u + " " + du);
		}
		return points;
	}
}
