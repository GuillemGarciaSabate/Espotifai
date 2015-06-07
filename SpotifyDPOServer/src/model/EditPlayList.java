package model;

import java.io.Serializable;

/**
 * This class is sent and received by the socket when the user makes any
 * change to any of his/hers PlayLists, possible actions are, 'add' a PlayList, 'up'date a PlayList,
 * 'del'ete a song from a PlayList, 'fin' delete a PlayList
 */
@SuppressWarnings("serial")
public class EditPlayList extends Stream implements Serializable{
	/**Variables*/
	private String name;
	private String action;
	private String song;
	
	/**Constructor*/
	public EditPlayList(){
		
	}

	/**
	 * Returns the name of the PlayList
	 * 
	 * @return String with the name of the PlayList
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the list
	 * 
	 * @param name String with the name of the PlayList
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the action
	 * 
	 * @return String with action name
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the name of the action
	 * 
	 * @param action String with the action on the PlayList
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Returns the name of the song
	 * 
	 * @return String with the name of the song
	 */
	public String getSong() {
		return song;
	}

	/**
	 * Sets the name of the song
	 * 
	 * @param song String with the new name of the song
	 */
	public void setSong(String song) {
		this.song = song;
	}

}
