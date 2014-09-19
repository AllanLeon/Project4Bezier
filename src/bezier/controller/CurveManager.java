package bezier.controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import bezier.drawer.Drawer;
import bezier.model.Curve;

public class CurveManager {

	private List<Curve> curves;
	
	public CurveManager() {
		curves = new ArrayList<Curve>();
	}
	
	public void addCurve(Curve curve) {
		curves.add(curve);
	}
	
	public List<Curve> getCurves() {
		return curves;
	}
	
	public void drawCurves(Graphics g) {
		for (int i = 0; i < curves.size(); i++) {
			Drawer.drawBezierCurve(g, curves.get(i));
		}
	}
}
