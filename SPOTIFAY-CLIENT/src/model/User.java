package model;

import java.util.LinkedList;

import javax.swing.JButton;
/**
 * The class User contains information of the user
 *  
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class User {
	
	private String Nickname;
	private String Password;
	private String mail;
	
	private List Favorites;
	private List Songs;
	
	private List PartyList;
	
	private LinkedList<List> lists;
	/**
	 * Constructor
	 */
	public User(){
		lists = new LinkedList<List>();
		
		Favorites = new List("Favorites");
		Songs = new List("Songs");
		PartyList = new List("Partylist");
		
	}
	/**
	 * Constructor
	 * 
	 * @param nickname is the name of the user
	 * @param password is the password of the user
	 * @param mail is the email of the user
	 * @param lists is a LinkedList that contains all the lists of the user
	 * @param Favorites is the list Favorites of a user
	 * @param Songs is the list Songs of a user
	 * @param PartyList is the list PartyList of a user
	 */

	public User(String nickname, String password, String mail,LinkedList<List> lists,List Favorites, List Songs,List PartyList) {
		Nickname = nickname;
		Password = password;
		this.mail = mail;
		this.lists = lists;
		
		this.Favorites = Favorites;
		this.Songs = Songs;
		this.PartyList = PartyList;
		
	}
	/**
	 * This method creates a new list for a user
	 * 
	 * @param nom is the name of the new playlist
	 * @return the size of the list created
	 */
	public int CreateNewlist(String nom){
		
		List l = new List(nom);
		lists.add(l);
		return lists.size();
	}
	/**
	 * This method choose the list selected by the user
	 * 
	 * @param i is and integer that indicate the selected list
	 * @return List is the list that the user wants
	 */
	public List ChooseList(int i){
		
			if(i ==0){
				return Favorites;
			}
			if(i == 1){
				return Songs;
			}
			if(i == 2){
				return PartyList;
			}

		return lists.get(i-3);	
		
	}
	/**
	 * This method remove all the lists of a client
	 * 
	 */
	public void removeAllLists(){
		for (int i = 0; i <lists.size();i++){
			if(!lists.get(i).getSongs().isEmpty()){
				lists.get(i).RemoveAllSongs();
			}
		
		}
		if(!Favorites.getSongs().isEmpty()){
			Favorites.RemoveAllSongs();
			
		}
		if(!Songs.getSongs().isEmpty()){
			Songs.RemoveAllSongs();
			
		}
		if(!PartyList.getSongs().isEmpty()){
			PartyList.RemoveAllSongs();
			
		}
	}
	/**
	 * This method gets the name of a user
	 * 
	 * @return Nickname is the name of the user
	 */

	public String getNickname() {
		return Nickname;
	}
	/**
	 * This method sets the name of a user
	 * 
	 * @param nickname is the name of the user
	 */
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	/**
	 * This method gets the password of a user
	 * 
	 * @return Password is the password of the user
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * This method sets the password of a user
	 * 
	 * @param password is the password of the user
	 */
	public void setPassword(String password) {
		Password = password;
	}
	/**
	 * This method gets the email of a user
	 * 
	 * @return mail is the email of the user
	 */

	public String getMail() {
		return mail;
	}
	/**
	 * This method sets the email of a user
	 * 
	 * @param mail is the email of the user
	 */

	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * This method gets the lists of a user
	 * @return lists lists of the user

	 */
	public LinkedList<List> getLists() {
		return lists;
	}
	/**
	 * This method sets the lists of the user
	 * @param lists is the lists of the user
	 */
	public void setLists(LinkedList<List> lists) {
		this.lists = lists;
	}
	/**
	 * This method updates the name of a list
	 * 
	 * @param row is the row selected
	 * @param Button is the button clicked by the user
	 */
	public void updateNameList (int row, JButton Button){
	//	System.out.println("3"); 
		Button.setOpaque(false);
		Button.setBorderPainted(false);
		Button.setContentAreaFilled(false);
		lists.get(row).setButton(Button);
	}
	/**
	 * This method get the favorite songs for a user
	 * 
	 * @return Favorites is the list with the favourites songs of a user
	 */
	public List getFavorites() {
		return Favorites;
	}
	/**
	 * This method set the favorite songs for a user
	 * 
	 * @param favorites is the list with the favourites songs of a user
	 */
	public void setFavorites(List favorites) {
		Favorites = favorites;
	}
	/**
	 * This method gets all the songs of a user
	 * 
	 * @return List is a list with the songs
	 */
	public List getSongs() {
		return Songs;
	}
	/**
	 * This method set all the songs for a user
	 * 
	 * @param songs is the songs of the system
	 */
	public void setSongs(List songs) {
		Songs = songs;
	}
	/**
	 * This method gets the list PartList
	 * 
	 * @return PartyList is the list with the songs from the PartyList
	 */
	public List getPartyList() {
		return PartyList;
	}
	/**
	 * This method set the list PartyList
	 * 
	 * @param partyList is the list
	 */
	public void setPartyList(List partyList) {
		PartyList = partyList;
	}
	
}
