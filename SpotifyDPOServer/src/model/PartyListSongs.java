package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This Class saves all the songs belonging to the PartyList list from the DB
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PartyListSongs extends Stream implements Serializable{
	/**Variables*/
	private ArrayList<String> songs = new ArrayList<String>();
	
	/**Constructor*/
	public PartyListSongs(){
		
	}
	
	/**
	 * Add a PlayList, used in the DB classes
	 * 
	 * @param name String with the name of the PlayList
	 */
	public void addSong(String name){
		songs.add(name);
	}
	
	/**
	 * This method is a PartyList getter
	 * @return ArrayList of String containing all the songs of PartyList
	 */
	public ArrayList<String> getSong(){
		return songs;
	}

}
