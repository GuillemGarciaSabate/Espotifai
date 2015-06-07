package model;

import java.util.ArrayList;

/**
 * This class relates the DataBase with the socket, making possible
 * to control the user actions and acting consequently with those
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class DataChecker {
	
	/**Constructor*/
	public DataChecker(){
		
	}
	
	/**
	 * This method makes a connection with the DB. It's used to check if a login was right.
	 * 
	 * @param auth Object that contains attributes as the name and the password of the user
	 * @return true Boolean that confirms if it's been found in the DataBase
	 */
	public boolean checkUser(Auth auth){
		boolean info = false;
		DataBaseAccess dba = new DataBaseAccess();
		
		String nomUser = auth.getName();
		String passwordUSer = auth.getPassword();
		
		
		info = dba.getAuth(nomUser, passwordUSer); 
		return info;
	}
	
	/**
	 * This method it's called at initiating the program, used to request information
	 * to the DB from the PlayLists which belongs to a user
	 * 
	 * @param CP Object received from the socket, that we want to fill up
	 * @param auth Object containing data from the user who request the action
	 * @return CP Object already filled with the PlayList
	 */
	public CurrentPlayList fillCurrentPlayList(CurrentPlayList CP, Auth auth){
		ArrayList<String> info = new ArrayList<String>();
		DataBaseAccess dba = new DataBaseAccess();

		info = dba.getPlayLists(auth.getName());
		int size = info.size();
		
		for(int i=0; i<size; i++){
			CP.addPlayList(info.get(i));
		}

		return CP;
	}
	
	/**
	 * This method receives the data from a sign up action an after
	 * checking some basic requirements for a registration, it calls a method to insert the
	 * new user into the DataBase
	 * 
	 * @param SU Object contains the information filled by the user for the sign up
	 * @return true Boolean that confirms if it was successful or not
	 */
	public boolean checkSignUP(SignUP SU){
		ArrayList<String[]> info = new ArrayList<String[]>();
		DataBaseAccess dba = new DataBaseAccess();
		
		SignUP totesDades = SU;
		String nom = totesDades.getName();
		String password = String.valueOf(totesDades.getPwd());
		
		int size = password.length();
		int i = 0;
		int trobat = 0;
		int hiHaLletra = 0;
		int hiHaNum = 0;
		
		while(i < size && trobat == 0 ){
			
			if (Character.isDigit(password.charAt(i))){
				trobat = 1;
				hiHaNum = 1;
			}
			i++;
		}
		
		trobat = 0;
		i=0;
		
		while(i < size && trobat == 0 ){
			if (Character.isLetter(password.charAt(i))){
				trobat = 1;
				hiHaLletra = 1;
			}
			i++;
		}
		
		info = dba.getUsers();
		int sizeUser = info.size();
		String[] dada;
		
		if (sizeUser == -1){

		}
		else{
			for (int j = 0; j < sizeUser; j++){
				dada = info.get(j);
				String nomBBDD;
				nomBBDD = dada[0];
				if(nomBBDD == nom){
					return false;
				}
			}
		}
		if (size >= 6 && (hiHaLletra == 1) && (hiHaNum==1) ){
			dba.insertUser(nom, password);
			return true;
		}
		else{
			return false;
		}
		
	}
	
	/**
	 * This method has to fill and object with all the songs of the system or all the songs from a PartyList
	 * or all the songs from favorites, depending on the nameList attribute
	 * 
	 * @param SL Object that contains the list name to check, and a data structure to fill
	 * @param auth Object that contains who has requested the action
	 * @return SL Object that will be returned filled
	 */
	public SerialList fillSongs(SerialList SL, Auth auth){
		ArrayList<String[]> info = new ArrayList<String[]>();
		DataBaseAccess dba = new DataBaseAccess();
		String[] dada;
		//info = dba.getMusics();
		
		if(SL.getNameList().equals("song")){
			info = dba.getMusics();
		} else if(SL.getNameList().equals("favorites")){
			info = dba.getMusicsFromFavorites(auth.getName());
		} else if(SL.getNameList().equals("partylist")){
			info = dba.getPlayListsDetails("partylist");		
		} 
		
		int size = info.size();
		
		if (size == 0){
			
		}
		else{
			for (int i = 0; i < size; i++){
				dada = info.get(i);
				SL.addSong(dada[0]+"%"+dada[3]+"%"+dada[2]+"%"+dada[1]+"%"+dada[4]);
			}
		}
		return SL;
	}
	
	/**
	 * Adds a song to our favorite table. Not used
	 * @param SS SerialSong with the info of the song
	 */
	public void addToPlaylist(SerialSong SS){
		DataBaseAccess dba = new DataBaseAccess();
		dba.insertMusicToFavorites(SS.getName(), SS.getGender(), SS.getAlbum(), SS.getArtist(), SS.getURL());
	}
	
	/**
	 * This method checks the location of a song inside the server
	 * 
	 * @param SS Object that stores the information of the song
	 * @return String which contains the URL on the system server
	 */
	public String getURL(PlaySong SS){
		DataBaseAccess dba = new DataBaseAccess();

		return dba.getURLMusic(SS.getName());
	}
	
	/**
	 * This method receives an object PlayListDetails, it is a PlayList,
	 * which it's authenticated by it's attribute pD.getPlayListName()
	 * then the method fills his ArrayList with the name of all his songs
	 * 
	 * @param PD Object that contains the details of the PlayList to be filled
	 * @return PlayListDetails which got his attribute songs filled with the PlayList songs
	 */
	public PlayListDetails fillPlayListDetails(PlayListDetails PD) {
		ArrayList<String[]> info = new ArrayList<String[]>();
		DataBaseAccess dba = new DataBaseAccess();
		String[] dada;

		info = dba.getPlayListsDetails(PD.getPlayListName());
		int size = info.size();
			
		if (size == 0){

		}
		else{
			for (int i = 0; i < size; i++){
				dada = info.get(i);
				PD.addSong(dada[0]+"%"+dada[3]+"%"+dada[2]+"%"+dada[1]);
			}
		}
		return PD;
	}

	/**
	 * This method receives an object indicating an action on one of the user PlayLists and 
	 * who requested the action, and then it does it.
	 * 
	 * @param EP Object that stores all the information from the action to be done
	 * @param auth Object that stores the information from the user who requested the action
	 */
	public void upDateLists(EditPlayList EP, Auth auth) {
		DataBaseAccess dba = new DataBaseAccess();
		
		if(EP.getAction().equals("del")){
			dba.deleteSongFromPlayList(auth.getName(), EP.getName(), EP.getSong());
		}
		if(EP.getAction().equals("add")){
			dba.insertNewPlayList(auth.getName(), EP.getName());
		}
		if(EP.getAction().equals("up") && !EP.getName().equals("partylist")){
			dba.addSongToPlaylist(auth.getName(), EP);
		}
		if(EP.getAction().equals("fin")){
			dba.deletePlayList(auth.getName(), EP.getName());
		}
		if(EP.getName().equals("partylist")){
			if(dba.checkSongExistence(EP.getSong()) == false){
				dba.addSongToPlaylist(auth.getName(), EP);
			}
		}

	}	

	/**
	 * Function that returns all music from PartyList. Not used.
	 * @param pS PartyListSongs with the info of the PartyList
	 * @return PartyListSongs with the list of songs in it
	 */
	public PartyListSongs fillPartyListSongs(PartyListSongs pS) {
		return null;
	}
	
	/**
	 * This method receives an object indicating an action on one of the user PlayLists and 
	 * who requested the action, and then it does it.
	 * 
	 * @param ps Object that stores all the information from the action to be done
	 */
	public void increaseNumPlays(PlaySong ps){
		DataBaseAccess dba = new DataBaseAccess();
		
		dba.increaseNumPlays(ps.getName(),ps.getArtist());
	}

}
