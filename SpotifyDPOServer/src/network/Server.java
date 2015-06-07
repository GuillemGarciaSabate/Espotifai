package network;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.SerialList;
import model.UserInfo;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import controller.ButtonsController;

import view.WindowServer;

/**
 * The class Server manage the connections for the different clients and create a DedicatedServer for everyone
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class Server extends Thread{
	/**Variables*/
	private ButtonsController bc;
	private WindowServer ws;
	private ServerSocket sSocket;
	private LinkedList<DedicatedServer> dServers;
	private boolean isOn = false;
	private int port;
	private SerialList SL;
	
	/**
	 * This is the constructor of the server, which reads from an .xml file the network configuration.
	 * There is also a error-control sequence 
	 * 
	 * @param ws WindowServer that we access from Server
	 * @param bc ButtonsController we interact from Server
	 */
	public Server(WindowServer ws, ButtonsController bc){
		this.ws = ws;
		this.bc = bc;
		dServers = new LinkedList<DedicatedServer>();
		try{
			File file = new File("NetworkStatements.xml");
			DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
			DBF.setIgnoringComments(true);
			DocumentBuilder builder = DBF.newDocumentBuilder();
			Document document = builder.parse(file);
			this.port = Integer.parseInt(document.getElementsByTagName("PORT").item(0).getTextContent());
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}catch(SAXException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	
	/**
	 * This method start the server that manages all the connections
	 */
	public void startServer(){
		try{
			isOn = true;
			this.sSocket = new ServerSocket(port);
			this.start();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Trouble while reading NetworkStatements.xml",
					"Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This  method stops the server that manages all the connections
	 */
	public void stopServer(){
		isOn = false;
		this.interrupt();
	}
	
	/**
	 * This method creates a new DedicatedServer for every new Client that the server receives
	 */
	public void run()  {
		
		while (isOn) {
			
			try {
				
				Socket sClient = sSocket.accept();
				SL = new SerialList();
				UserInfo UI = new UserInfo();
				DedicatedServer newUser = new DedicatedServer(UI, sClient, dServers, SL,this);
				dServers.add(newUser);
				newUser.startDedicatedServer();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sends the new registered user to window server so it updates the table of UserGestion
	 * @param sUser String[] with all the information of the new user
	 * @param iTable Integer that indicates which table we want to update
	 */
	public void updateUserTable(int iTable, String[] sUser) {
		ws.setNewData(iTable,sUser);
	}

	/**
	 * Function that asks the ButtonsController to make a request to update its graph
	 */
	public void updateGraph() {
		bc.updateGraph();
	}

}
