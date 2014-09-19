package bezier.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bezier.Main;
import bezier.Main.ModState;
import bezier.Main.State;
import bezier.data.Constants;
import bezier.model.Curve;
import bezier.model.basics.Point;

public class MouseHandler implements MouseListener {
	
	private List<Point> creatorPoints;
	private Random random; 
	
	public MouseHandler() {
		creatorPoints = new ArrayList<Point>();
		random = new Random();
	}
	
	public void reset() {
		creatorPoints.clear();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		double x = e.getX();
		double y = Constants.PANEL_HEIGHT - e.getY();
		if (Main.modState == ModState.Creating) {
			creatorPoints.add(new Point(x, y));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
