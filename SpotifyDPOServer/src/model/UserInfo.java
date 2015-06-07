package model;

import java.sql.Date;

/**
 * This Class saves all the info of the user
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class UserInfo {
	/**Variables*/
	private CurrentPlayList lists = new CurrentPlayList();
	private Auth auth = new Auth();
	private SerialList SL = new SerialList();
	private PlayListDetails PD = new PlayListDetails();
	private EditPlayList EP = new EditPlayList();
	private SerialSong SS = new SerialSong();
	private PartyListSongs PS = new PartyListSongs();
	private SignUP SU = new SignUP();
	private String name;
	private Date registration;
	private Date lastAccess;
	private int PLNumber;
	private int SNumber;
	
	/**Constructor*/
	public UserInfo(){
		
	}
	
	/**
	 * Getter of the Auth object
	 * @return Auth object of the user
	 */
	public Auth getAuth() {
		return auth;
	}

	/**
	 * Setter of the Auth object
	 * @param auth New Auth to save
	 */
	public void setAuth(Auth auth) {
		this.auth.setId(1);
		this.auth = auth;
	}
	
	/**
	 * Getter of the SerialList object
	 * @return SerialList of the user
	 */
	public SerialList getSL() {
		return SL;
	}

	/**
	 * Setter of the SerialList object
	 * @param sL New SerialList to save
	 */
	public void setSL(SerialList sL) {
		this.SL.setId(2);
		SL = sL;
	}

	/**
	 * Getter of the CurrentPlayList object
	 * @return CurrentPlayList of the user
	 */
	public CurrentPlayList getLists() {
		return lists;
	}

	/**
	 * Setter of the CurrentPlayList object
	 * @param lists New CurrentPlayList to save
	 */
	public void setLists(CurrentPlayList lists) {
		this.lists.setId(3);
		this.lists = lists;
	}

	/**
	 * Getter of the PlayListDetails object
	 * @return PlayListDetails of the user
	 */
	public PlayListDetails getPD() {
		return PD;
	}

	/**
	 * Setter of the PlayListDetails object
	 * @param pD New PlayListDetails to save
	 */
	public void setPD(PlayListDetails pD) {
		this.PD.setId(4);
		PD = pD;
	}

	/**
	 * Getter of the EditPlayList object
	 * @return EditPlayList of the user
	 */
	public EditPlayList getEP() {
		return EP;
	}

	/**
	 * Setter of the EditPlayList object
	 * @param eP New EditPlayList to save
	 */
	public void setEP(EditPlayList eP) {
		this.EP.setId(5);
		EP = eP;
	}

	/**
	 * Getter of the PartyListSongs object
	 * @return PartyListSongs of the user
	 */
	public PartyListSongs getPS() {
		return PS;
	}

	/**
	 * Setter of the PartyListSongs object
	 * @param pS New PartyListSongs to save
	 */
	public void setPS(PartyListSongs pS) {
		this.PS.setId(6);
		PS = pS;
	}

	/**
	 * Getter of the SerialSong object
	 * @return SerialSong of the user
	 */
	public SerialSong getSS() {
		return SS;
	}

	/**
	 * Setter of the SerialSong object
	 * @param sS New SerialSong to save
	 */
	public void setSS(SerialSong sS) {
		this.SS.setId(7);
		SS = sS;
	}

	/**
	 * Getter of the SignUP object
	 * @return SignUP of the user
	 */
	public SignUP getSU() {
		return SU;
	}

	/**
	 * Setter of the SignUP object
	 * @param sU New SignUP to save
	 */
	public void setSU(SignUP sU) {
		this.SS.setId(8);
		SU = sU;
	}

	/**
	 * Getter of the name of the user
	 * @return Name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the name of the user
	 * @param name New name to save
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter of the registration Date
	 * @return Registration Date of the user
	 */
	public Date getRegistration() {
		return registration;
	}

	/**
	 * Setter of the Date of registration
	 * @param registration New Date to save
	 */
	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	/**
	 * Getter of the Last Access Date of the user
	 * @return Last Access Date of the user
	 */
	public Date getLastAccess() {
		return lastAccess;
	}

	/**
	 * Setter of the Date of the last access
	 * @param lastAccess New Date to save
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	/**
	 * Getter of the number of PlayLists of the user
	 * @return Returns the number of PlayLists
	 */
	public int getPLNumber() {
		return PLNumber;
	}

	/**
	 * Setter of the number of PlayLists of the user
	 * @param pLNumber New number to save
	 */
	public void setPLNumber(int pLNumber) {
		PLNumber = pLNumber;
	}

	/**
	 * Getter of the number of songs of the user
	 * @return Returns the number of songs
	 */
	public int getSNumber() {
		return SNumber;
	}

	/**
	 * Setter of the number of songs of the user
	 * @param sNumber New number to save
	 */
	public void setSNumber(int sNumber) {
		SNumber = sNumber;
	}
	
}
