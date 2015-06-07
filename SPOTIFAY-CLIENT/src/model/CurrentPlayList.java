package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
	* This class contains all the playlist which belongs to the user who
	* is playing the program, it is sended once at the begining of the program
	*  * @author Albert Trias Torroglosa
	* @author Daniel Mateu Elizalde
 	* @author Guillem Garcia Sabater
 	* @author Jordi Badia Iglesias
 	* @author Adria Acero Montes
 	*/
	@SuppressWarnings("serial")
	public class CurrentPlayList  extends Stream implements Serializable{
	
	private ArrayList<String> playlists = new ArrayList<String>();
	
	public CurrentPlayList(){
		
	}
	
	/**
	 * Add a playlist to the native data structure, 
	 * it is used in the DB classes
	 * @param name name of the playlist to be added
	 */
	public void addPlayList(String name){
		playlists.add(name);
	}
	/**
	 * This method it's just a PlayLists getter
	 * @return ArrayList of Strings of every PlayList of the user
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
