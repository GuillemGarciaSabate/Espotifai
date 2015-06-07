package model;

import java.io.Serializable;

/**
 * This class it's sended and recived by the socket when the user makes any
 * change to any of his/hers playlists, possible actions are, 'add' a playlist, 'up'date a playlist,
 * 'del'ete a song froma playlist, 'fin' delete a playlist 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class EditPlayList extends Stream implements Serializable{
	
	private String name;
	private String action;
	private String song;
	
	public EditPlayList(){
		
	}
	 /** 
	 * Returns the name of the playlist
	 * @return String playlist name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the list
	 * @param name of the playlist*/
	public void setName(String name) {
		this.name = name;
	}
	/**
	* Returns the name of the action
	* @return String action name*/
	public String getAction() {
		return action;
	}
	/**
	 * Sets the name of the action
	 * @param action on the playlist*/
	public void setAction(String action) {
		this.action = action;
	}
	 /**
	 * Returns the name of the song
	 * @return String playlist song*/
	public String getSong() {
		return song;
	}
	 /**
	 * Sets the name of the song
	 * @param song of the song*/
	public void setSong(String song) {
		this.song = song;
	}

	

}
