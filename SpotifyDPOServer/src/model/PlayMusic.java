package model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import controller.ButtonsController;
import controller.RowController;
import controller.SliderController;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import view.PlayDialog;

/**
 * The class PlayMusic is used when the client want to play a song
 * 
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class PlayMusic implements BasicPlayerListener{
	private RowController rc;
	private PlayDialog pd;
	private BasicPlayer bcPlayer;
	private BasicController bcController;
	@SuppressWarnings("unused")
	private ButtonsController bc;
	
	/**Variables*/
	private float currentVolume;
	private boolean bNextMusic;
	private String sPathToMP3;
	
	/**Constants*/
	private static final float DEFAULT_VOLUME = 0.15f;
	
	/**
	 * Constructor
	 * 
	 * @param bc Controller that helps us to know which song is selected
	 * @param sc Controller that permits adjustment of the volume when playing a song
	 * @param rc Controller used to know which song is selected	  
	 */
	public PlayMusic(ButtonsController bc, SliderController sc, RowController rc) {
		this.rc = rc;
		this.bNextMusic = false;
		this.bc = bc;
		this.pd = new PlayDialog(bc,sc);
		this.bcPlayer = new BasicPlayer();
		this.bcController = (BasicController) bcPlayer;
		bcPlayer.addBasicPlayerListener(this);
		this.sPathToMP3 = "";
		this.currentVolume = DEFAULT_VOLUME;
		pd.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
					bcController.stop();
				} catch (BasicPlayerException e) {
					e.printStackTrace();
				}
            }   
        });
		
	}

	/**
	 * This method gets the URL of a song. If a music already exists,
	 * it stops it as it stopped was pressed.
	 * 
	 * @param sPathToMP3 String with the URL of the music we want to insert
	 */
	public void insertNewMusic(String sPathToMP3) {
		if (!sPathToMP3.equals("")) {
			try {
				bcController.stop();
			} catch (BasicPlayerException e) {
				e.printStackTrace();
			}
		}
		
		pd.openDialog();
		this.sPathToMP3 = sPathToMP3;

	}
	
	/**
	 * This method opens the music selected on the music player
	 */
	public void openMusic() {
		try {
			URL url = (new File("music/"+sPathToMP3)).toURI().toURL();
			bcController.open(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method plays a song in the music player
	 */
	public void play() {
		try {
			bcController.play();
			this.changeVolume();
		} catch (BasicPlayerException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * This method pauses a music that is being played
	 */
	public void pause() {
		try {
			bcController.pause();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method permits to resume a song in pause
	 */
	public void resume() {
		try {
			bcController.resume();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method stops a song that is being reproduced completely
	 */
	public void stop() {
		try {
			bcController.stop();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method permits setting the volume of the song
	 * 
	 * @param fVolume Float that sets the new volume of the song
	 */
	public void setCurrentVolume(float fVolume) {
		this.currentVolume = fVolume;
	}

	/**
	 * This method permits changing the volume of the song that is being played
	 */
	public void changeVolume() {
		try {
			bcController.setGain(currentVolume);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This implemented method from the BasicPlayerListener is not used
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void opened(Object oObject, Map mMap) {
		
	}

	/**
	 * This implemented method from the BasicPlayerListener is not used
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void progress(int iInt, long iLong, byte[] bByte, Map mMap) {
		
	}

	/**
	 * This implemented method from the BasicPlayerListener is not used
	 */
	@Override
	public void setController(BasicController bc) {
		
	}

	/**
	 * This method indicates the state of the music player.
	 * It's used to know if the music has been stopped naturally when it ends.
	 * First EOM:-1 confirms that the music has finished naturally.
	 * After that if we get STOPPED:-1, it means we are ready to swap to next music without causing problems
	 * 
	 * @param e BasicPlayerEvent that contains the event with the changed state of the player
	 */
	@Override
	public void stateUpdated(BasicPlayerEvent e) {
		if ((e.toString()).equals("EOM:-1")) {
			bNextMusic = true;
		} else if (bNextMusic && (e.toString()).equals("STOPPED:-1")) {
			bNextMusic = false;
			rc.nextMusic();
		}
	}
	/**
	 * 
	 */
	public void hideDialog() {
		pd.hideDialog();
	}

}
