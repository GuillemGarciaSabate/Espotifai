package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;

import controller.ButtonsController;
import controller.SliderController;

/**
 * Class responsible of creating the JDialog
 * where we will interact with our songs
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlayDialog extends JDialog {
	/**Swing and AWT Components*/
	private JButton jbPrevious;
	private JButton jbPlay;
	private JButton jbResume;
	private JButton jbStop;
	private JButton jbPause;
	private JButton jbNext;
	private JSlider jsSlider;
	
	/**Constants*/
	private static final int FPS_MIN = 0;
	private static final int FPS_MAX = 100;
	private static final int FPS_INIT = 15;
	
	/**
	 * Constructor
	 * @param bc ButtonsController to assign to our buttons
	 * @param sc SliderController to assign to our slider
	 */
	public PlayDialog(ButtonsController bc, SliderController sc) {
		jsSlider = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
		jsSlider.setMajorTickSpacing(10);
		jsSlider.setMinorTickSpacing(1);
		jsSlider.setPaintTicks(true);
		jsSlider.setPaintLabels(true);
		jsSlider.addChangeListener(sc);
		JPanel jpSlider = new JPanel();
		jpSlider.add(jsSlider);
		
		jbPrevious = new JButton("Previous");
		jbPrevious.addActionListener(bc);
		jbPrevious.setActionCommand("PREVIOUS");
		jbPlay = new JButton("Play");
		jbPlay.addActionListener(bc);
		jbPlay.setActionCommand("PLAY");
		jbResume = new JButton("Resume");
		jbResume.addActionListener(bc);
		jbResume.setActionCommand("RESUME");
		jbPause = new JButton("Pause");
		jbPause.addActionListener(bc);
		jbPause.setActionCommand("PAUSE");
		jbStop = new JButton("Stop");
		jbStop.addActionListener(bc);
		jbStop.setActionCommand("STOP");
		jbNext = new JButton("Next");
		jbNext.addActionListener(bc);
		jbNext.setActionCommand("NEXT");
		JPanel jpButtons = new JPanel(new FlowLayout());
		jpButtons.add(jbPrevious);
		jpButtons.add(jbPlay);
		jpButtons.add(jbResume);
		jpButtons.add(jbPause);
		jpButtons.add(jbStop);
		jpButtons.add(jbNext);
		
		this.setLayout(new GridLayout(2,1));
		this.add(jpSlider);
		this.add(jpButtons);
		this.setSize(400,200);
		this.setAlwaysOnTop(true);
	}

	/**
	 * Shows the Dialog 
	 */
	public void openDialog() {
		this.setVisible(true);
	}
	
	/**
	 * Hides the Dialog 
	 */
	public void hideDialog() {
		this.setVisible(false);
	}

	/**
	 * Changes the position of the slider according to the new value
	 * @param f Float with new position of the slider
	 */
	public void setPositionSlider(float f) {
		jsSlider.setValue((int) f);
		repaint();
	}
}
