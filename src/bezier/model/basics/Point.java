package bezier.model.basics;

public class Point extends Coordinates {

	public Point(double x, double y, double z) {
		super(x, y, z);
		w = 1;
	}
	
	public void checkW() {
		if (w == 0) {
			w = 1;
		} else if (w != 1) {
			x /= w;
			y /= w;
			z /= w;
			w /= w;
		}
	}
	
	public double getDistanceTo(Point p) {
		double dx = x - p.getX();
		double dy = y - p.getY();
		double dz = z - p.getZ();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));
	}
}
