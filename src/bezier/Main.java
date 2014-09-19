package bezier;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bezier.data.WindowConstants;

import javax.swing.JToggleButton;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel bezierPanel;

	private JToggleButton commutator;

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
		setSize(WindowConstants.WIDTH, WindowConstants.HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		bezierPanel = new JPanel();
		bezierPanel.setBounds(10, 10, WindowConstants.WIDTH, WindowConstants.HEIGHT - 80);
		getContentPane().add(bezierPanel);
		bezierPanel.setBackground(Color.BLACK);
		
		commutator = new JToggleButton("Creator Mode");
		commutator.setBounds(WindowConstants.WIDTH/2 - 60, WindowConstants.HEIGHT - 60, 120, 25);
		getContentPane().add(commutator);
		commutator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (commutator.isSelected())
					commutator.setText("Interact Mode");
				else
					commutator.setText("Creator Mode");
			}
		});
				
		super.paintComponents(getGraphics());

		paint();
	}

	private void paint() {
		
	}

	@Override
	public void update(Graphics g) {
	}

	@Override
	public void paint(Graphics g) {
	}
}
