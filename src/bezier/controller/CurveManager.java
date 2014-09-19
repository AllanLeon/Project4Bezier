package bezier.controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import bezier.data.Constants;
import bezier.drawer.Drawer;
import bezier.model.Curve;
import bezier.model.basics.Point;

public class CurveManager {

	private List<Curve> curves;
	
	public CurveManager() {
		curves = new ArrayList<Curve>();
	}
	
	public void addCurve(Curve curve) {
		curves.add(curve);
	}
	
	public void drawCurves(Graphics g) {
		for (int i = 0; i < curves.size(); i++) {
			Drawer.drawBezierCurve(g, curves.get(i));
		}
	}
	
	public Point checkMouseCollision(Point clickedPoint) {
		for (int i = 0; i < curves.size(); i++) {
			Point p0 = curves.get(i).getPoint0();
			double d0 = p0.getDistanceTo(clickedPoint);
			if (d0 <= Constants.POINT_RADIUS) {
				return p0;
			}
			Point p1 = curves.get(i).getPoint1();
			double d1 = p1.getDistanceTo(clickedPoint);
			if (d1 <= Constants.POINT_RADIUS) {
				return p0;
			}
			Point p2 = curves.get(i).getPoint2();
			double d2 = p2.getDistanceTo(clickedPoint);
			if (d2 <= Constants.POINT_RADIUS) {
				return p0;
			}
			Point p3 = curves.get(i).getPoint3();
			double d3 = p3.getDistanceTo(clickedPoint);
			if (d3 <= Constants.POINT_RADIUS) {
				return p0;
			}
		}
		return null;
	}
}
