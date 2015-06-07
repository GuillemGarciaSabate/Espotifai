package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PlayMusic;
/**
 * Class responsible of acting as an ActionListener for all Swing components of the reproducer
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class PlayerController implements ActionListener {
	private PlayMusic pm;
	private BottonsController bc;
	/**
	 * Constructor 
	 * @param pm PlayerMusic is the graphical interface of the Reproducer
	 * @param bc BottonsController is the principle controller of the client
	 */
	public PlayerController(PlayMusic pm, BottonsController bc) {
		this.pm = pm;
		this.bc = bc;
	}
	/**
	 * Catches an action of user in the reproducer
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("PLAY")) {
			pm.play();
		} else if (e.getActionCommand().equals("RESUME")) {
			pm.resume();
		} else if (e.getActionCommand().equals("STOP")) {
			pm.stop();
		} else if (e.getActionCommand().equals("PAUSE")) {
			pm.pause();
		} else if (e.getActionCommand().equals("NEXT")) {
			bc.nextMusic();
		} else if (e.getActionCommand().equals("PREVIOUS")) {
			bc.previousMusic();
		} else if (e.getActionCommand().equals("CYCLIC")) {
			pm.changeCyclicMode();
		}
	}

	
}
