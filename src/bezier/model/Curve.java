package bezier.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import bezier.controller.BezierGenerator;
import bezier.data.Constants;
import bezier.model.basics.Point;

public class Curve {

	private Color color;
	private List<Point> points;
	private List<Point> bezierPoints;
	
	public Curve(Point point0, Point point1, Point point2, Point point3,
			Color color) {
		this.color = color;
		this.bezierPoints = new ArrayList<Point>();
		this.points = new ArrayList<Point>();
		points.add(point0);
		points.add(point1);
		points.add(point2);
		points.add(point3);
		updateBezierPoints();
	}

	public Point getPoint0() {
		return points.get(0);
	}

	public Point getPoint1() {
		return points.get(1);
	}

	public Point getPoint2() {
		return points.get(2);
	}

	public Point getPoint3() {
		return points.get(3);
	}

	public Color getColor() {
		return color;
	}
	
	public List<Point> getPoints() {
		return bezierPoints;
	}

	public void setPoint(int pos, Point point) {
		points.set(pos, point);
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Point calculateA() {
		double x = -points.get(0).getX() + 3*points.get(1).getX() - 3*points.get(2).getX() + points.get(3).getX();
		double y = -points.get(0).getY() + 3*points.get(1).getY() - 3*points.get(2).getY() + points.get(3).getY();
		return new Point(x, y);
	}
	
	public Point calculateB() {
		double x = 3*points.get(0).getX() - 6*points.get(1).getX() + 3*points.get(2).getX();
		double y = 3*points.get(0).getY() - 6*points.get(1).getY() + 3*points.get(2).getY();
		return new Point(x, y);
	}
	
	public Point calculateC() {
		double x = -3*points.get(0).getX() + 3*points.get(1).getX();
		double y = -3*points.get(0).getY() + 3*points.get(1).getY();
		return new Point(x, y);
	}
	
	public Point calculateD() {
		return points.get(0);
	}
	
	public void updateBezierPoints() {
		bezierPoints = BezierGenerator.generateBezierPoints(this);
	}
	
	public int checkMouseCollision(Point clickedPoint) {
		double d0 = points.get(0).getDistanceTo(clickedPoint);
		if (d0 <= Constants.POINT_RADIUS) {
			return 0;
		}
		double d1 = points.get(1).getDistanceTo(clickedPoint);
		if (d1 <= Constants.POINT_RADIUS) {
			return 1;
		}
		double d2 = points.get(2).getDistanceTo(clickedPoint);
		if (d2 <= Constants.POINT_RADIUS) {
			return 2;
		}
		double d3 = points.get(3).getDistanceTo(clickedPoint);
		if (d3 <= Constants.POINT_RADIUS) {
			return 3;
		}
		return -1;
	}
}
