package controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.PlayMusic;

/**
 * Class responsible to adding a ChangeListener to our volume slider in our player
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class SliderController implements ChangeListener{
	private PlayMusic pm;

	@Override
	public void stateChanged(ChangeEvent e) {
		/**If the slider is moved selects the new value and changes the volume of the player*/
		JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) {
	        float fVolume = (float)(source.getValue())/100;
	        pm.setCurrentVolume(fVolume);
	        pm.changeVolume();
	    }
	}
	
	
	/**
	 * Setter of PlayMusic
	 * @param pm PlayMusic pointer
	 */
	public void setPlayMusic(PlayMusic pm) {
		this.pm = pm;
	}

}
