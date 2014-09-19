package bezier;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import bezier.data.Constants;
import bezier.drawer.Drawer;
import bezier.model.Curve;
import bezier.model.basics.Point;

public class Main extends JFrame implements ActionListener {

	enum State {
		Waiting, Drawing
	}

	private static final long serialVersionUID = 1L;
	private JPanel bezierPanel;
	private JToggleButton commutator;
	private BufferedImage doubleBuffer;
	private State state;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize and set up the basic components of the frame.
	 */
	private void initialize() {
		setTitle("BEZIER bezier BEZIER bezier BEZIER bezier BEZIER bezier BEZIER bezier BEZIER bezier");
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		bezierPanel = new JPanel();
		bezierPanel.setBounds(10, 10, Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
		getContentPane().add(bezierPanel);
		bezierPanel.setBackground(Color.BLACK);
		
		commutator = new JToggleButton("Creator Mode");
		commutator.setBounds(Constants.WINDOW_WIDTH/2 - 60, Constants.WINDOW_HEIGHT - 60, 120, 25);
		getContentPane().add(commutator);
		commutator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (commutator.isSelected())
					commutator.setText("Interact Mode");
				else
					commutator.setText("Creator Mode");
			}
		});
		
		doubleBuffer = new BufferedImage(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		state = State.Drawing;
		start();
	}

	private void paint() {
		Graphics dbg = doubleBuffer.getGraphics();
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		Point p0 = new Point(100, 100);
		Point p1 = new Point(150, 200);
		Point p2 = new Point(200, 250);
		Point p3 = new Point(300, 300);
		Drawer.drawBezierCurve(dbg, new Curve(p0, p1, p2, p3, Color.CYAN));
		bezierPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
		state = State.Waiting;
	}
	
	public void start() {
		Timer timer = new Timer(1000/60, this);
		timer.start();
	}

	@Override
	public void update(Graphics g) {
	}

	@Override
	public void paint(Graphics g) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (state == State.Drawing) {
			paint();
		}
	}
}
