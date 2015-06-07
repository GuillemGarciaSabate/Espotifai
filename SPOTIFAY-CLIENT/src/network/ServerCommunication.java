package network;
	
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Auth;
import model.CurrentPlayList;
import model.EditPlayList;
import model.ListTableModel;
import model.PartyListSongs;
import model.PlayListDetails;
import model.PlaySong;
import model.SerialList;
import model.SerialSong;
import model.SignUP;
import model.Stream;
import model.User;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import controller.BottonsController;
import view.WindowClient;
/**
* This class opens a socket and keeps listening everything that come through this socket
* when the user does any action, it's tracked and then sent through this class to the server
* sameway
* @author Albert Trias Torroglosa
* @author Daniel Mateu Elizalde
* @author Guillem Garcia Sabater
* @author Jordi Badia Iglesias
* @author Adria Acero Montes
*/
public class ServerCommunication  extends Thread {
	
	private boolean isOn = false;
	private Socket socketToServer;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private String ip;
	private int port;
	private BottonsController bc;
	
	/**
	 * This is the constructor, the main function in it, it's to read the file which stores
	 * the network parameters
	 * @param view WindowClient that we interact with in the Server
	 */
	public ServerCommunication(WindowClient view){
		try{
			File file = new File("NetworkStatements.xml");
			DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
			DBF.setIgnoringComments(true);
			DocumentBuilder builder = DBF.newDocumentBuilder();
			Document document = builder.parse(file);
			this.ip = document.getElementsByTagName("IP").item(0).getTextContent();
			this.port = Integer.parseInt(document.getElementsByTagName("PORT").item(0).getTextContent());	
		}catch(ParserConfigurationException e){
			System.out.println("ParserConfigurationException");
		}catch(SAXException e) {
			System.out.println("SAXException");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	

	/**
	 * This makes the thread to start
	 */
	public void startServerComunication() {
		isOn = true;
		this.start();
	}
	/**
	 * This stops the thread
	 */
	public void stopServerComunication() {
		this.isOn = false;
		this.interrupt();
	}
	/**
	 * This method it's executing himself all the time, administrating the information
	 * that arrives from the server
	 */
	public void run() {
		Stream stream;
			try{
				this.socketToServer = new Socket(ip,port);
				this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
				this.objectIn = new ObjectInputStream(socketToServer.getInputStream());

			}catch(IOException e){
				e.printStackTrace();
			}
		
			try {
				CurrentPlayList CP = new CurrentPlayList();
				while(isOn){
					stream = (Stream) objectIn.readObject();
					/** If the data received from the server it's an authentication response frame*/
					if(stream instanceof Auth){
						Auth auth = (Auth) stream;
						if (auth.getFound() == true) {
							bc.showUserConnected();
							
							this.sendInfo(CP);
							
						} else {
							System.out.println("not found");
							//bc.showErrorMessage("Incorrect Login or Password");
						}
						System.out.println("REBUT AL CLIENT: "+auth.getName());
					}
					/** If the data received from the server it's a signup response frame*/
					if(stream instanceof SignUP){
						SignUP sign = (SignUP) stream;
						if(sign.getDone()){
							System.out.println("REBUT AL CLIENT usuari registrat: "+sign.getName());
						}else{
							System.out.println("usuari no registrat");
							//bc.showErrorMessage("User already exists");
						}
						
					}
					/** If the data received from the server it's a playlistDetails response frame */
					if(stream instanceof PlayListDetails){
						PlayListDetails PD = (PlayListDetails) stream;
						String aux[];
						int c = 0;
						SerialSong sl = new SerialSong();
						for(int j = 0 ; j < bc.getUser().getLists().size();j++){
							if(PD.getPlayListName().equals(bc.getUser().getLists().get(j).getNom())){
								bc.getUser().getLists().get(j).RemoveAllSongs();
								for(int i=0; i< PD.getSongs().size(); i++){
									
									aux = PD.getSongs().get(i).split("%");

									System.out.println("PRINTEM"+aux[0]);
									
									sl.setName(aux[0]);
									sl.setArtist(aux[1]);
									sl.setAlbum(aux[2]);
									sl.setGender(aux[3]);
									sl.setId(i);
									bc.getUser().getLists().get(j).CreateNewSong(sl);
									
								}
							c = j;
							j = bc.getUser().getLists().size();
							}
						}
						if(bc.getWc().getMusicMenu().getListSongs().getPanells().size()==4){
							bc.getWc().getMusicMenu().getListSongs().removeLastPanel();
						}						
						ListTableModel listablemodel4 = new ListTableModel(bc.getUser().getLists().get(c));
						bc.getWc().getMusicMenu().getListSongs().CreatePanelList(bc.getUser().getLists().get(c).getNom(), listablemodel4,bc);						
						bc.getWc().getMusicMenu().ChangeList(3,bc.getUser().getLists().get(c).getNom());
						SwingUtilities.updateComponentTreeUI(bc.getWc().getMusicMenu().getScrollSongs());
						
					}
					/** If the data received from the server it's a SerialList response frame */
					if(stream instanceof SerialList){
						SerialList SL = (SerialList) stream;
						String aux[];
						
						SerialSong sl = new SerialSong();
						
						if(SL.getNameList().equals("favorites")){
							
							for(int i=0; i< SL.getSong().size(); i++){
								
								aux = SL.getSong().get(i).split("%");

								
								sl.setName(aux[0]);
								sl.setArtist(aux[1]);
								sl.setAlbum(aux[2]);
								sl.setGender(aux[3]);
								sl.setId(i);
								sl.setURL(aux[4]);

								bc.getUser().getFavorites().CreateNewSong(sl);
								
								
						}
							bc.getWc().getMusicMenu().ChangeList(0,null);
					}
						if(SL.getNameList().equals("song")){
							for(int i=0; i< SL.getSong().size(); i++){
								aux = SL.getSong().get(i).split("%");

								
								sl.setName(aux[0]);
								sl.setArtist(aux[1]);
								sl.setAlbum(aux[2]);
								sl.setGender(aux[3]);
								sl.setId(i);
								sl.setURL(aux[4]);

								bc.getUser().getSongs().CreateNewSong(sl);
								
							}
							bc.getWc().getMusicMenu().ChangeList(1,null);
						}
						
						if(SL.getNameList().equals("partylist")){
							
							for(int i=0; i< SL.getSong().size(); i++){
								aux = SL.getSong().get(i).split("%");

								
								sl.setName(aux[0]);
								sl.setArtist(aux[1]);
								sl.setAlbum(aux[2]);
								sl.setGender(aux[3]);
								sl.setId(i);
								sl.setURL(aux[4]);
								
								bc.getUser().getPartyList().CreateNewSong(sl);
								System.out.println(bc.getUser().getPartyList().getSongs().size());
						}
						
							bc.getWc().getMusicMenu().ChangeList(2,null);
							
							SwingUtilities.updateComponentTreeUI(bc.getWc().getMusicMenu().getListSongs().getPanells().get(2).getTablesongs());
					}
						
					}
					/** If the data received from the server it's a EditPlayList response frame */
					if(stream instanceof EditPlayList){
						EditPlayList EP = (EditPlayList) stream;
						System.out.println("REBUT AL CLIENT: "+EP.getName());
					}
					/** If the data received from the server it's a CurrentPlayList response frame */
					if(stream instanceof CurrentPlayList){
						CP = (CurrentPlayList) stream;
						for(int i=0; i< CP.getPlayList().size(); i++){
							String pl = CP.getPlayList().get(i);
							User u = bc.getUser();
							if(!CP.getPlayList().get(i).equals("partylist")){
								u.CreateNewlist(pl);
								bc.setUser(u);
								bc.getWc().refreshTable();
							}						
						}
						
						if(!CP.getPlayList().isEmpty()){
							ListTableModel listablemodel4 = new ListTableModel(bc.getUser().getLists().get(0));
							bc.getWc().getMusicMenu().getListSongs().CreatePanelList(bc.getUser().getLists().get(0).getNom(), listablemodel4,bc);
						}
						
						SerialList SL = new SerialList();
						SL.setNameList("favorites");
						this.sendInfo(SL);
					}
					/** If the data received from the server it's a PartyListSongs response frame */
					if(stream instanceof PartyListSongs){
						PartyListSongs PS = (PartyListSongs) stream;
						System.out.println("REBUT AL CLIENT: "+PS.getSong().get(0));
					}
					/** If the data received from the server it's a PlaySong response frame */
					if(stream instanceof PlaySong){
						PlaySong playS = (PlaySong) stream;
						playS.getBinarySong();
						playS.setName(playS.getName().replaceAll(" ", "_"));
						FileOutputStream fos = new FileOutputStream("./music/"+playS.getName()+".mp3");
						fos.write(playS.getBinarySong());
						fos.flush();
						fos.close();
					    
					    bc.getPm().insertNewMusic("music/"+playS.getName()+".mp3");
					    bc.getPm().openMusic();
					    bc.getPm().play();
					}
					
				}
			}  catch (ClassNotFoundException e1) {
				stopServerComunication();
			} catch(IOException e) {
				stopServerComunication();
			}			
		stopServerComunication();
		}
	
	/**
	 * This method returns the output Channel
	 * @return objectOut socket output channel
	 */
	private ObjectOutputStream getOutChannel() {
		return objectOut;
	}
		
	/**This method sends the information to the server
	 * @param stream it's the frame to send
	 */
	public void sendInfo(Stream stream) {
		ObjectOutputStream objectOut = this.getOutChannel();
		try {
			objectOut.writeObject(stream);
		} catch (IOException e) {
			stopServerComunication();
			e.printStackTrace();
		}
	}
	/**This method sets the controller
	 * @param bc it's the controller to associate
	 */
	public void setController(BottonsController bc) {
		this.bc = bc;
	}
}