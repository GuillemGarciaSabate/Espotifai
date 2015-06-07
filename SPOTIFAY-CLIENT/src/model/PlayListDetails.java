package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class PlayListsDetails helps to do some operations with the playlists of a user
 *  
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlayListDetails extends Stream implements Serializable{
	/**Variables*/
	private String playListName;
	private ArrayList<String> songs = new ArrayList<String>();
	
	/**Constructor*/
	public PlayListDetails(){
		
	}
	
	/**
	 * This method adds a songs to the list songs
	 * 
	 * @param name is the name of the song
	 */
	public void addSong(String name){
		songs.add(name);
	}
	
	/**
	 * This method get all the songs from the list songs
	 * 
	 * @return an ArrayList with the name of all the songs
	 */
	public ArrayList<String> getSongs(){
		return songs;
	}

	/**
	 * This method gets the name of a playlist
	 *
	 * @return String with the name of the playlist
	 */
	public String getPlayListName() {
		return playListName;
	}

	/**
	 * This method sets the name of a playlist
	 * 
	 * @param playListName is the name of the playlist to set the name
	 */
	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}
	
}
