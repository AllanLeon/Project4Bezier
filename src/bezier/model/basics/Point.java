package bezier.model.basics;

public class Point {

	protected double x;
	protected double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public static Point addition2Points(Point a, Point b) {
		double x = a.getX() + b.getX();
		double y = a.getY() + b.getY();
		return new Point(x, y);
	}
	
	public double getDistanceTo(Point p) {
		double dx = x - p.getX();
		double dy = y - p.getY();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}
}
