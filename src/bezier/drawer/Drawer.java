package bezier.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import bezier.BezierGenerator;
import bezier.data.WindowConstants;
import bezier.model.Curve;
import bezier.model.basics.Point;

public class Drawer {

	private static void putPixel(Graphics g, int x, int y) {
		g.drawLine(x, WindowConstants.HEIGHT - y, x, WindowConstants.HEIGHT - y);
	}
	
	public static void drawLine(Graphics g, int x0, int y0, int x1, int y1, Color color) {
		g.setColor(color);
		
		int dx, dy, d, x, y, deltaE, deltaNE, stepx = 0, stepy = 0;
		dx = x1-x0;
		dy = y1-y0;
		if (dx<0) {
			dx = -dx;
			stepx = -1;
		} else if (dx>0) { 
			stepx = 1;
		} if (dy<0) {
			dy = -dy;
			stepy = -1;
		} else if (dy>0) {
			stepy = 1;
		}
		x = x0;
		y = y0;
		putPixel(g, x, y);
		if (dx > dy) {
			d = 2*dy - dx;
			deltaE = 2 * dy;
			deltaNE = 2 * (dy-dx);
			while (x != x1) {
				x += stepx;
				if (d<0) {
					d += deltaE;
				} else {
					y += stepy;
					d += deltaNE;
				}
				putPixel(g, x, y);
			}
		} else {
			d = -2*dx + dy;
			deltaE = -2 * dx;
			deltaNE = 2 * (dy-dx);
			while (y != y1) {
				y += stepy;
				if (d>0) {
					d += deltaE;
				} else {
					x += stepx;
					d += deltaNE;
				}
				putPixel(g, x, y);
			}
		}
	}
	
	public static void drawCircle(Graphics g, int centerX, int centerY, int radius, Color color) {
		g.setColor(color);
		
		int x, y, d, dE, dSE;
		x = 0;
		y = radius;
		d = 1 - radius;
		dE = 3;
		dSE = -2*radius+5;
		simetry(g, x, y, centerX, centerY);
		while (y > x) {
			if (d < 0) {
				d += dE;
				dE += 2;
				dSE += 2;
				x += 1;
			} else {
				d += dSE;
				dE += 2;
				dSE += 4;
				x += 1;
				y += -1;
			}
			simetry(g, x, y, centerX, centerY);
		}
	}
	
	private static void simetry(Graphics g, int x, int y, int centerX, int centerY) {
		putPixel(g, x + centerX, y + centerY);
		putPixel(g, y + centerX, x + centerY);
		putPixel(g, y + centerX, -x + centerY);
		putPixel(g, x + centerX, -y + centerY);
		putPixel(g, -x + centerX, -y + centerY);
		putPixel(g, -y + centerX, -x + centerY);
		putPixel(g, -y + centerX, x + centerY);
		putPixel(g, -x + centerX, y + centerY);
	}
	
	public static void drawBezierCurve(Graphics g, Curve curve) {
		BezierGenerator generator = new BezierGenerator(curve);
		List<Point> points = generator.generateBezierPoints();
		for (int i = 1; i < points.size(); i++) {
			Point p0 = points.get(i - 1);
			Point p1 = points.get(i);
			drawLine(g, (int)p0.getX(), (int)p0.getY(), (int)p1.getX(), (int)p1.getY(), curve.getColor());
		}
	}
}
