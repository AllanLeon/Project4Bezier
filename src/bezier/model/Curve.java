package bezier.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import bezier.controller.BezierGenerator;
import bezier.model.basics.Point;

public class Curve {

	private Point point0;
	private Point point1;
	private Point point2;
	private Point point3;
	private Color color;
	private List<Point> points;
	
	public Curve(Point point0, Point point1, Point point2, Point point3,
			Color color) {
		this.point0 = point0;
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		this.color = color;
		this.points = new ArrayList<Point>();
		updatePoints();
	}

	public Point getPoint0() {
		return point0;
	}

	public Point getPoint1() {
		return point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public Point getPoint3() {
		return point3;
	}

	public Color getColor() {
		return color;
	}
	
	public List<Point> getPoints() {
		return points;
	}

	public void setPoint0(Point point0) {
		this.point0 = point0;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	public void setPoint3(Point point3) {
		this.point3 = point3;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Point calculateA() {
		double x = -point0.getX() + 3*point1.getX() - 3*point2.getX() + point3.getX();
		double y = -point0.getY() + 3*point1.getY() - 3*point2.getY() + point3.getY();
		return new Point(x, y);
	}
	
	public Point calculateB() {
		double x = 3*point0.getX() - 6*point1.getX() + 3*point2.getX();
		double y = 3*point0.getY() - 6*point1.getY() + 3*point2.getY();
		return new Point(x, y);
	}
	
	public Point calculateC() {
		double x = -3*point0.getX() + 3*point1.getX();
		double y = -3*point0.getY() + 3*point1.getY();
		return new Point(x, y);
	}
	
	public Point calculateD() {
		return point0;
	}
	
	public Point getPointAt(double t) {
		double x = Math.pow(1-t, 3)*point0.getX() + 3*t*Math.pow(1-t, 2)*point1.getX() + 3*Math.pow(t, 2)*(1-t)*point2.getX() + Math.pow(t, 3)*point3.getX();
		double y = Math.pow(1-t, 3)*point0.getY() + 3*t*Math.pow(1-t, 2)*point1.getY() + 3*Math.pow(t, 2)*(1-t)*point2.getY() + Math.pow(t, 3)*point3.getY();
		return new Point(x, y);
	}
	
	public void updatePoints() {
		points = BezierGenerator.generateBezierPoints(this);
	}
}
