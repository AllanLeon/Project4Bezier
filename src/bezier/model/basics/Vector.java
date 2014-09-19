package bezier.model.basics;

public class Vector  extends Coordinates{

	public Vector(double x, double y, double z) {
		super(x, y, z);
		w = 0;
	}
	
	public void normalize() {
		double scale = getScale();
		x /= scale;
		y /= scale;
		z /= scale;
	}
	
	public double getScale() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}
	
	public static Vector invert(Vector a) {
		double x = -a.getX();
		double y = -a.getY();
		double z = -a.getZ();
		return new Vector(x, y, z);
	}
	
	public static double dotProduct(Vector a, Vector b) {
		return a.getX()*b.getX() + a.getY()*b.getY() + a.getZ()*b.getZ();
	}
}
