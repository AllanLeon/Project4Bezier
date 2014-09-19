package bezier.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bezier.Main;
import bezier.Main.ModState;
import bezier.Main.State;
import bezier.data.Constants;
import bezier.model.Curve;
import bezier.model.basics.Point;

public class MouseHandler implements MouseListener, MouseMotionListener {
	
	private List<Point> creatorPoints;
	private Random random;
	private int selectedPoint;
	private Curve selectedCurve;
	
	public MouseHandler() {
		creatorPoints = new ArrayList<Point>();
		random = new Random();
		selectedCurve = null;
		selectedPoint = -1;
	}
	
	public void reset() {
		creatorPoints.clear();
		selectedCurve = null;
		selectedPoint = -1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Main.modState == ModState.Creating) {
			double x = e.getX();
			double y = Constants.PANEL_HEIGHT - e.getY();
			Point clickedPoint = new Point(x, y);
			creatorPoints.add(clickedPoint);
			if (creatorPoints.size() >= 4) {
				Main.getCurveManager().addCurve(
						new Curve(creatorPoints.get(0), creatorPoints.get(1), creatorPoints.get(2), creatorPoints.get(3),
								new Color(random.nextFloat(), random.nextFloat(), random.nextFloat())));
				creatorPoints.remove(0);
				creatorPoints.remove(0);
				creatorPoints.remove(0);
				creatorPoints.remove(0);
				Main.state = State.Drawing;
			}
		}
	}
	
	public List<Point> getCreatorPoints() {
		return creatorPoints;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Main.modState == ModState.Editing) {
			double x = e.getX();
			double y = Constants.PANEL_HEIGHT - e.getY();
			Point clickedPoint = new Point(x, y);
			selectedCurve = null;
			selectedPoint = -1;
			List<Curve> curves = Main.getCurveManager().getCurves();
			int i = curves.size() - 1;
			boolean found = false;
			while (i >= 0 && !found) {
				selectedPoint = curves.get(i).checkMouseCollision(clickedPoint);
				if (selectedPoint != -1) {
					selectedCurve = curves.get(i);
					found = true;
				}
				i--;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (Main.modState == ModState.Editing) {
			if (selectedPoint != -1) {
				double x = e.getX();
				double y = Constants.PANEL_HEIGHT - e.getY();
				Point draggedPoint = new Point(x, y);
				selectedCurve.setPoint(selectedPoint, draggedPoint);
				selectedCurve.updateBezierPoints();
				Main.state = State.Drawing;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
