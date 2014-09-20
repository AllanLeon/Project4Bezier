package bezier.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

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
	
	public List<Point> getCreatorPoints() {
		return creatorPoints;
	}
	
	private Point getClickedPoint(MouseEvent e) {
		double x = e.getX();
		double y = Constants.PANEL_HEIGHT - e.getY();
		return new Point(x, y);
	}
	
	private void handlePointCreation(Point clickedPoint) {
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
	
	private void handleCurveDeletion(Point clickedPoint) {
		List<Curve> curves = Main.getCurveManager().getCurves();
		int i = curves.size() - 1;
		boolean found = false;
		while (i >= 0 && !found) {
			selectedPoint = curves.get(i).checkMouseCollision(clickedPoint);
			if (selectedPoint != -1) {
				curves.remove(i);
				found = true;
				Main.state = State.Drawing;
			}
			i--;
		}
	}
	
	private void handlePointSelection(Point clickedPoint) {
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
	
	private void handlePointMovement(Point draggedPoint) {
		if (selectedPoint != -1) {
			selectedCurve.setPoint(selectedPoint, draggedPoint);
			selectedCurve.updateBezierPoints();
			Main.state = State.Drawing;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (Main.modState == ModState.Creating) {
				handlePointCreation(clickedPoint);
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			if (Main.modState == ModState.Editing) {
				handleCurveDeletion(clickedPoint);
			}
		}
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
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (Main.modState == ModState.Editing) {
				Point clickedPoint = getClickedPoint(e);
				handlePointSelection(clickedPoint);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (Main.modState == ModState.Editing) {
				if (selectedPoint != -1) {
					Point draggedPoint = getClickedPoint(e);
					handlePointMovement(draggedPoint);
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
