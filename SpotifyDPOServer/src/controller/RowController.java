package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import model.DataBaseAccess;
import model.PlayMusic;

/**
 * Class that adds MouseAdapter to our rows in our tables
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class RowController extends MouseAdapter {
	private DataBaseAccess dba;
	private PlayMusic pc;
	
	/**Variables*/
	private int iCurrentMusic;
	private JTable jtTarget;
	
	/**
	 * Constructor
	 * @param dba DataBaseAccess pointer to allow interaction with our database
	 */
	public RowController(DataBaseAccess dba) {
		this.dba = dba;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			/**If a row is clicked twice, it will start playing the music in that row*/
            jtTarget = (JTable) e.getSource();
            iCurrentMusic = jtTarget.getSelectedRow();
            String sNameSong = (String) jtTarget.getValueAt(iCurrentMusic, 0);
            String sPathToMP3 = dba.getURLMusic(sNameSong);
            pc.insertNewMusic(sPathToMP3);
            pc.openMusic();
            pc.play();
        }
	}

	/**
	 * Setter of the PlayMusic
	 * @param pc PlayMusic pointer
	 */
	public void setPlayMusic(PlayMusic pc) {
		this.pc = pc;
	}
	
	/**
	 * Function that plays the next music in a row. It also changes the selection.
	 * It is cyclic.
	 */
	public void nextMusic() {
		int iTotalMusic = jtTarget.getRowCount();
		if (iTotalMusic > 0) {
			iCurrentMusic++;
			if (iCurrentMusic >= iTotalMusic) iCurrentMusic = 0;
			String sNameSong = (String) jtTarget.getValueAt(iCurrentMusic, 0);
			jtTarget.getSelectionModel().setSelectionInterval(iCurrentMusic, iCurrentMusic);
			String sPathToMP3 = dba.getURLMusic(sNameSong);
			pc.insertNewMusic(sPathToMP3);
			pc.openMusic();
			pc.play();
		}
	}
	
	/**
	 * Function that plays the previous music in a row. It also changes the selection.
	 * It is cyclic.
	 */
	public void previousMusic() {
		int iTotalMusic = jtTarget.getRowCount();
		if (iTotalMusic > 0) {
			iCurrentMusic--;
			if (iCurrentMusic < 0) iCurrentMusic = iTotalMusic-1;
			String sNameSong = (String) jtTarget.getValueAt(iCurrentMusic, 0);
			jtTarget.getSelectionModel().setSelectionInterval(iCurrentMusic, iCurrentMusic);
			String sPathToMP3 = dba.getURLMusic(sNameSong);
			pc.insertNewMusic(sPathToMP3);
			pc.openMusic();
			pc.play();
		}
	}

}
