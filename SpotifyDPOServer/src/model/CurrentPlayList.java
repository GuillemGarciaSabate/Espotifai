package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains all the PlayList which belongs to the user who
 * is playing the program, it is sent once at the beginning of the program
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CurrentPlayList  extends Stream implements Serializable{
	/**Variables*/
	private ArrayList<String> playlists = new ArrayList<String>();
	
	/**Constructor*/
	public CurrentPlayList(){
		
	}
	
	/**
	 * Add the name of PlayList, which is used in the DB classes
	 * 
	 * @param name String with the name of the PlayList to be added
	 */
	public void addPlayList(String name){
		playlists.add(name);
	}
	
	/**
	 * This method it's just a PlayLists getter
	 * 
	 * @return ArrayList of String of every PlayList of the user
	 */
	public ArrayList<String> getPlayList(){
		ArrayList<String> myPlaylists = new ArrayList<String>();
		String[] songAsArray;
		for(int i=0; i<playlists.size(); i++){
			songAsArray = playlists.get(i).split("%");
			if(!myPlaylists.contains(songAsArray[0])){
				myPlaylists.add(playlists.get(i));
			}
		}
		return myPlaylists;
	}
}
