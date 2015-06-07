package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import controller.PlayerController;
import controller.SliderController;

/**
 * That class implements the window and the buttons for play the music.
 * 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */

@SuppressWarnings("serial")
public class PlayDialog extends JDialog{
	private JButton jbPrevious;
	private JButton jbPlay;
	private JButton jbResume;
	private JButton jbStop;
	private JButton jbPause;
	private JButton jbNext;
	private JButton jbCyclic;
	private JSlider jsSlider;
	
	private static final int FPS_MIN = 0;
	private static final int FPS_MAX = 100;
	private static final int FPS_INIT = 15;
	
	/**
	 * Constructor of the class
	 * @param pc Controller for the buttons of the music PREVIOUS, PLAY, RESUME, PAUSE, STOP, NEXT
	 * @param sc Slider Controller for change the volume of the song
	 */
	public PlayDialog(PlayerController pc, SliderController sc) {
		jsSlider = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
		jsSlider.setMajorTickSpacing(10);
		jsSlider.setMinorTickSpacing(1);
		jsSlider.setPaintTicks(true);
		jsSlider.setPaintLabels(true);
		jsSlider.addChangeListener(sc);
		JPanel jpSlider = new JPanel();
		jpSlider.add(jsSlider);
		
		jbPrevious = new JButton("Previous");
		jbPrevious.addActionListener(pc);
		jbPrevious.setActionCommand("PREVIOUS");
		jbPlay = new JButton("Play");
		jbPlay.addActionListener(pc);
		jbPlay.setActionCommand("PLAY");
		jbResume = new JButton("Resume");
		jbResume.addActionListener(pc);
		jbResume.setActionCommand("RESUME");
		jbPause = new JButton("Pause");
		jbPause.addActionListener(pc);
		jbPause.setActionCommand("PAUSE");
		jbStop = new JButton("Stop");
		jbStop.addActionListener(pc);
		jbStop.setActionCommand("STOP");
		jbNext = new JButton("Next");
		jbNext.addActionListener(pc);
		jbNext.setActionCommand("NEXT");
		jbCyclic = new JButton("Cyclic OFF");
		jbCyclic.addActionListener(pc);
		jbCyclic.setActionCommand("CYCLIC");
		JPanel jpButtons = new JPanel(new FlowLayout());
		jpButtons.add(jbPrevious);
		jpButtons.add(jbPlay);
		jpButtons.add(jbResume);
		jpButtons.add(jbPause);
		jpButtons.add(jbStop);
		jpButtons.add(jbNext);
		jpButtons.add(jbCyclic);
		
		this.setLayout(new GridLayout(2,1));
		this.add(jpSlider);
		this.add(jpButtons);
		this.setSize(400,200);
		this.setAlwaysOnTop(true);
	}

	/**
	 * This method get the window of the controller of the song visible
	 */
	public void openDialog() {
		this.setVisible(true);
	}

	/**
	 * This method set the position of the slider of the volume
	 * @param f Float with a number between 0-100 for setting the volume of the song
	 */
	public void setPositionSlider(float f) {
		jsSlider.setValue((int) f);
		repaint();
	}

	/**
	 * Changes the text of the button to show if cyclic mode is pressed or not
	 */
	public void changeButtonText() {
		if (jbCyclic.getText().equals("Cyclic OFF")){
			jbCyclic.setText("Cyclic ON");
		} else {
			jbCyclic.setText("Cyclic OFF");
		}
	}
}
