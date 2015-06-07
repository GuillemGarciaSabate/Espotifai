package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class SerialList contains the name of a list and all his songs
 * 
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SerialList extends Stream implements Serializable {
	/**Variables*/
	private ArrayList<String> songs = new ArrayList<String>();
	private String nameList;

	/**Constructor*/
	public SerialList(){
		
	}
	
	/**
	 * This method add a new playlist to a user
	 * 
	 * @param name is the name of the playlist
	 */
	public void addSong(String name){
		songs.add(name);
	}
	
	/**
	 * This method gets all the songs from a playlist
	 * 
	 * @return songs is an arrayList with the name of all the songs
	 */
	public ArrayList<String> getSong(){
		return songs;
	}
	
	/**
	 * This method sets the name of a playlist
	 * 
	 * @param nameList is the name of the playlist
	 */
	public void setNameList (String nameList){
		this.nameList= nameList;
	}
	
	/**
	 * This method get the name of a playlist
	 * 
	 * @return nameList is the name of a playlist
	 */
	public String getNameList(){
		return nameList;
	}
}