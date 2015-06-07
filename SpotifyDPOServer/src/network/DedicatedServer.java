package network;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import model.Auth;
import model.DataChecker;
import model.EditPlayList;
import model.PartyListSongs;
import model.PlayListDetails;
import model.PlaySong;
import model.SerialList;
import model.CurrentPlayList;
import model.SerialSong;
import model.SignUP;
import model.Stream;
import model.UserInfo;

/**
 * The class DedicatedServer manages the different client actions that a client does to the server
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class DedicatedServer extends Thread{
	/**Variables*/
	private Server s;
	private Socket sClient;
	private LinkedList<DedicatedServer> dServers;
	@SuppressWarnings("unused")
	private UserInfo UI;
	@SuppressWarnings("unused")
	private SerialList SL;
	private boolean isOn;
	private ObjectInputStream objectInput;
	private ObjectOutputStream objectOut;
	private DataChecker DC;
	
	/**
	 * Constructor
	 * 
	 * @param UI UserInfo with the info of the user that the DedicatedServer is managing
	 * @param sClient Socket in which we receive the client connection through
	 * @param dServers LinkedList with all the DedicatedServer for all clients
	 * @param SL SerialList that contains a list with all the song names
	 * @param s Server that we access from DedicatedServer
	 */
	public DedicatedServer(UserInfo UI, Socket sClient, LinkedList<DedicatedServer> dServers, SerialList SL, Server s){
		this.s = s;
		isOn = false;
		this.SL = SL;
		this.UI = UI;
		this.sClient = sClient;
		this.dServers = dServers;
		DC = new DataChecker();
	}
	
	/**
	 * This method starts the DedicatedServer
	 */
	public void startDedicatedServer(){
		isOn = true;
		this.start();
	}
	
	/**
	 * This method stops the DedicatedServer
	 */
	public void stopDedicatedServer(){
		isOn = false;
		this.interrupt();
	}
	
	/**
	 * This method initiates two stream channels, and keeps listening through the input one, 
	 * if there is something, the method will call other tasks to perform
	 * and will proceed to update the other clients when needed
	 */
	public void run() {
		Stream stream;
		Auth auth = new Auth();
		try {
			
			objectOut = new ObjectOutputStream(sClient.getOutputStream());
			objectInput = new ObjectInputStream(sClient.getInputStream());
	
			while (isOn) {
				
				stream = (Stream) objectInput.readObject();
				if(stream instanceof Auth)
				{
					/**Authenticates the user and returns if the login was valid or not*/
					auth = (Auth) stream;
					boolean bLogin = DC.checkUser(auth);
					auth.setFound(bLogin);
					this.updateClient(auth);
				}
				if(stream instanceof SerialList)
				{
					/**Gets all songs from a selected PlayList and sends it to the client*/
					SerialList SL = (SerialList) stream;					
					SL = DC.fillSongs(SL, auth);
					this.updateClient(SL);
			
				}
				if(stream instanceof SerialSong){
					/**Adds a song to the favorite PlayList. Not used*/
					SerialSong SS = (SerialSong) stream;
					DC.addToPlaylist(SS);
				}
				if(stream instanceof CurrentPlayList)
				{
					/**Used at the beginning of the program, it returns all the PlayLists of the user*/
					CurrentPlayList CP = (CurrentPlayList) stream;
					CP = DC.fillCurrentPlayList(CP, auth);
					this.updateClient(CP);
				}
				if(stream instanceof PlayListDetails)
				{
					/**Gets the songs of the PlayList and passes it to the client*/
					PlayListDetails PD = (PlayListDetails) stream;
					PD = DC.fillPlayListDetails(PD);
					this.updateClient(PD);
					
				}
				if(stream instanceof EditPlayList)
				{
					/**Edits all types of PlayList to add or remove songs, or to add or remove the same PlayLists*/
					EditPlayList EP = (EditPlayList) stream;
					DC.upDateLists(EP, auth);
					if (EP.getName().equals("partylist")) {
						String sAux[] = EP.getSong().split("%");
						String sSong[] = {sAux[0],sAux[1],sAux[2],sAux[3]};
						s.updateUserTable(2, sSong);
					}
					s.updateGraph();
				}
				
				if(stream instanceof PartyListSongs)
				{
					/**Gets all songs from PartyList and returns it to the user. Not used*/
					PartyListSongs PS = (PartyListSongs) stream;
					PS = DC.fillPartyListSongs(PS);
					this.updateClient(PS);
				}
				if(stream instanceof PlaySong){
					/**
					 * Receives the info of the song, increases its number of plays and sends the same music
					 * to the client so it can play it using its player
					 */
					PlaySong SS = (PlaySong) stream;
					String URL = DC.getURL(SS);
					DC.increaseNumPlays(SS);
					File file = new File(URL);
					SongSender SSender = new SongSender(URL);
					PlaySong PS = new PlaySong(SS.getName(), SS.getArtist());
					PS.setSize(file.length());
					PS.setBinarySong(SSender.sendStream());
					this.updateClient(PS);
					s.updateGraph();
				}
				if(stream instanceof SignUP)
				{
					/**
					 * Receives data for registering, tries to register the user and
					 * informs the client if it was possible or not
					 */
					SignUP SU = (SignUP) stream;
					boolean bReg = DC.checkSignUP(SU);
					SU.setDone(bReg);
					if (bReg) {
						DateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date date = new Date();
						String sDate = dfFormat.format(date);
						String[] sUser = {SU.getName(),sDate,sDate,"0","0","Delete"};
						s.updateUserTable(0,sUser);
					}
					this.updateClient(SU);
					
				}
				
			}
			
		} catch (ClassNotFoundException e1) {
			stopDedicatedServer();
			dServers.remove(this);
		} catch(IOException e) {
			stopDedicatedServer();
			dServers.remove(this);
		}
		
	}

	/**
	 * This  method gets the channel to send information
	 * @return ObjectOutputStream channel used
	 */
	private ObjectOutputStream getOutChannel() {
		return objectOut;
	}
	
	/**
	 * This method sends updates to the client that manages the DedicatedServer
	 * @param stream Stream that contains the update to send to the user
	 */
	public void updateClient(Stream stream) {
			ObjectOutputStream outStream = this.getOutChannel();
			try {
				outStream.reset();
				outStream.writeObject(stream);
				outStream.flush();	
								
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	/**
	 * This method reset the channel where we send the info
	 */
	public void resetChannel() {
		try {
			objectOut.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}	
