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

import bezier.controller.CurveManager;
import bezier.controller.MouseHandler;
import bezier.data.Constants;
import bezier.drawer.Drawer;

public class Main extends JFrame implements ActionListener {

	public enum State {
		Waiting, Drawing
	}
	
	public enum ModState {
		Creating, Editing
	}

	private static final long serialVersionUID = 1L;
	
	public static State state;
	public static ModState modState;
	
	private static CurveManager curveManager;
	private JPanel bezierPanel;
	private JToggleButton commutator;
	private BufferedImage doubleBuffer;
	private MouseHandler mouseHandler;

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
		mouseHandler = new MouseHandler();
		
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
		bezierPanel.addMouseListener(mouseHandler);
		bezierPanel.addMouseMotionListener(mouseHandler);
		
		commutator = new JToggleButton("Creator Mode");
		commutator.setBounds(Constants.WINDOW_WIDTH/2 - 60, Constants.WINDOW_HEIGHT - 60, 120, 25);
		getContentPane().add(commutator);
		commutator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (commutator.isSelected()) {
					modState = ModState.Editing;
					commutator.setText("Interact Mode");
					mouseHandler.reset();
					paint();
				}
				else {
					modState = ModState.Creating;
					commutator.setText("Creator Mode");
					mouseHandler.reset();
				}
			}
		});
		
		doubleBuffer = new BufferedImage(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		state = State.Drawing;
		modState = ModState.Creating;
		curveManager = new CurveManager();
		start();
	}

	private void paint() {
		Graphics dbg = doubleBuffer.getGraphics();
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		curveManager.drawCurves(dbg);
		bezierPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
		state = State.Waiting;
	}
	
	private void paintCreatorDots() {
		Graphics dbg = doubleBuffer.getGraphics();
		Drawer.drawCreatorPoints(dbg, mouseHandler.getCreatorPoints());
		bezierPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
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
		if (modState == ModState.Creating) {
			paintCreatorDots();
		}
	}
	
	public static CurveManager getCurveManager() {
		return curveManager;
	}
}
