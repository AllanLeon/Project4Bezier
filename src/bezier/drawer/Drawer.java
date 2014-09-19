package bezier.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import bezier.controller.BezierGenerator;
import bezier.data.Constants;
import bezier.model.Curve;
import bezier.model.basics.Point;

public class Drawer {

	private static void putPixel(Graphics g, int x, int y) {
		g.drawLine(x, Constants.PANEL_HEIGHT - y, x, Constants.PANEL_HEIGHT - y);
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
	
	public static void drawDashedLine(Graphics g, int x0, int y0, int x1, int y1, Color color) {
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
		int n = 1;
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
				if (n == 1 || n == 2 || n == 3) {
					putPixel(g, x, y);
				} else {
					if (n > 6) {
						n = 0;
					}
				}
				n++;
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
				if (n == 1 || n == 2 || n == 3) {
					putPixel(g, x, y);
				} else {
					if (n > 5) {
						n = 0;
					}
				}
				n++;
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
		Color color = curve.getColor();
		for (int i = 1; i < points.size(); i++) {
			Point p0 = points.get(i - 1);
			Point p1 = points.get(i);
			drawLine(g, (int)p0.getX(), (int)p0.getY(), (int)p1.getX(), (int)p1.getY(), color);
		}
		Point p0 = curve.getPoint0();
		Point p1 = curve.getPoint1();
		Point p2 = curve.getPoint2();
		Point p3 = curve.getPoint3();
		drawDashedLine(g, (int)p0.getX(), (int)p0.getY(), (int)p1.getX(), (int)p1.getY(), color);
		drawDashedLine(g, (int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY(), color);
		drawDashedLine(g, (int)p2.getX(), (int)p2.getY(), (int)p3.getX(), (int)p3.getY(), color);
		drawDashedLine(g, (int)p3.getX(), (int)p3.getY(), (int)p0.getX(), (int)p0.getY(), color);
		for (int i = 1; i <= Constants.POINT_RADIUS; i++) {
			drawCircle(g, (int)p0.getX(), (int)p0.getY(), i, color);
			drawCircle(g, (int)p1.getX(), (int)p1.getY(), i, color);
			drawCircle(g, (int)p2.getX(), (int)p2.getY(), i, color);
			drawCircle(g, (int)p3.getX(), (int)p3.getY(), i, color);
		}
	}
}
