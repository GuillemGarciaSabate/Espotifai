package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DataBaseAccess;
import model.PlayMusic;

import view.GraphDialog;
import view.WindowServer;

/**
 * Class responsible of acting as an ActionListener for all Swing components that are buttons
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class ButtonsController implements ActionListener {
	/**Swing and AWT Components*/
	private WindowServer ws;
	private DataBaseAccess dba;
	private GraphDialog gd;
	private PlayMusic pm;
	private RowController rc;
	private JFileChooser jfcPath;
	private JPanel jpShow;
	private FileFilter fcFilter;
	
	/**Variables*/
	private int[] iSongs;
	private String[] sSongs;

	/**
	 * Constructor
	 * 
	 * @param ws WindowServer pointer to be able to interact with the main window
	 * @param dba DataBaseAccess pointer to be able to make access to our local database
	 * @param gd GraphDialog pointer to be able to update the graph with the top 5 songs
	 */
	public ButtonsController(WindowServer ws, DataBaseAccess dba, GraphDialog gd) {
		this.iSongs = new int[5];
		this.sSongs = new String[5];
		this.ws = ws;
		this.dba = dba;
		this.gd = gd;
		this.pm = null;
		jfcPath = new JFileChooser("./music");
		jpShow = new JPanel();
		fcFilter = new FileNameExtensionFilter("MP3 File","mp3");
		jfcPath.setFileFilter(fcFilter);
		this.updateGraph();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("PLAY")) {
			/**Button Play from the player is pressed*/
			pm.play();
		} else if (e.getActionCommand().equals("RESUME")) {
			/**Button Resume from the player is pressed*/
			pm.resume();
		} else if (e.getActionCommand().equals("STOP")) {
			/**Button Stop from the player is pressed*/
			pm.stop();
		} else if (e.getActionCommand().equals("PAUSE")) {
			/**Button Pause from the player is pressed*/
			pm.pause();
		} else if(e.getActionCommand().equals("...")) {
			/**Button ... of the song form is pressed to grab a file using a JFileChooser*/
			int returnVal = jfcPath.showOpenDialog(jpShow);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jfcPath.getSelectedFile();
                ws.setTypedPath(file.getAbsolutePath());
			}
		} else if(e.getActionCommand().equals("ADDMUSIC")) {
			/**Button Add Music from the song form is pressed to add a song to the database*/
			String sName = ws.getNameSong();
			String sGenre = ws.getGenreSong();
			String sAlbum = ws.getAlbumSong();
			String sArtist = ws.getArtistSong();
			String sURL = ws.getURLSong();
			if (sName.isEmpty() || sGenre.isEmpty() || sAlbum.isEmpty()
					|| sArtist.isEmpty() || sURL.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Make sure you filled all fields", "Error",
                        JOptionPane.ERROR_MESSAGE);
			} else {
				File f = new File(sURL);
				if(f.exists() && !f.isDirectory()) {
					boolean bDone = dba.insertMusic(sName, sGenre, sAlbum, sArtist, f.getName());
					if (!bDone) {
						JOptionPane.showMessageDialog(null, "Name of song already exists", "Error",
		                        JOptionPane.ERROR_MESSAGE);
					} else {
						String[] sNewRow = {sName,sGenre,sAlbum,sArtist,"Delete"};
						ws.setNewData(1, sNewRow);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Path to File", "Error",
	                        JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (e.getActionCommand().equals("STATS")) {
			/**Button Stats from the song form is pressed to show the top 5 graph*/
			gd.setVisible(true);
		} else if (e.getActionCommand().equals("NEXT")) {
			/**Button next from the player is pressed to play the next music*/
			rc.nextMusic();
		} else if (e.getActionCommand().equals("PREVIOUS")) {
			/**Button previous from the player is pressed to play the previous music*/
			rc.previousMusic();
		} else if (e.getActionCommand().equals("DELETEPL")) {
			/**Button delete from party list is pressed*/
			pm.stop();
			pm.hideDialog();
			dba.deletePartyListSong();
			ws.deleteFirstRowFromPlayList();
		}
	}
	
	/**
	 * Setter of the PlayMusic
	 * @param pm PlayMusic from which we will play songs
	 */
	public void setPlayMusic(PlayMusic pm) {
		this.pm = pm;
	}
	
	/**
	 * Function that asks the database to delete a song with the selected name
	 * @param sNom String with the name of the song
	 */
	public void deleteMusicDB(String sNom) {
		dba.deleteMusic(sNom);
	}

	/**
	 * Function that asks the database to delete a user with the selected name
	 * @param sLogin String with the login of the user
	 */
	public void deleteUserDB(String sLogin) {
		dba.deleteUser(sLogin);
	}

	/**
	 * Function that updates the graph of top 5 songs when needed
	 */
	public void updateGraph() {
		dba.getTop5(iSongs,sSongs);
		gd.updateGraph(iSongs, sSongs);
		gd.paint(gd.getGraphics());
	}
	
	/**
	 * Sets the RowController in ButtonsController to be able to have access to it
	 * @param rc RowController that controls the rows of the table
	 */
	public void setControllers(RowController rc) {
		this.rc = rc;
	}

}
