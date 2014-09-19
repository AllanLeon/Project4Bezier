package bezier;

import java.util.List;

import bezier.model.Curve;
import bezier.model.basics.Point;

public class BezierGenerator {
	
	private Curve curve;
	private Point d1, d2, d3;
	private Point a, b, c, d;
	private int n;
	
	public BezierGenerator(Curve curve) {
		this.curve = curve;
		n = 50;
		this.a = curve.calculateA();
		this.b = curve.calculateB();
		this.c = curve.calculateC();
		this.d = curve.calculateD();
		initializeDStart();
	}

	private void initializeDStart() {
		d3 = calculateD3();
		d2 = calculateD2();
		d1 = calculateD1();
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
}
