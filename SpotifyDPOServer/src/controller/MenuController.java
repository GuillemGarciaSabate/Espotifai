package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.DataBaseAccess;

import view.WindowServer;

/**
 * Class responsible of controlling the JMenuItems used in the WindowServer
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class MenuController implements ActionListener {
	private WindowServer ws;
	@SuppressWarnings("unused")
	private DataBaseAccess dba;
	
	/**Variables*/
	private ArrayList<String[]> dataUG;
	private ArrayList<String[]> dataMG;
	private ArrayList<String[]> dataPL;
	
	/**Constants*/
	private static final String[] sType = {"UG","MG","PL"};

	/**
	 * Constructor
	 * @param ws WindowServer pointer to be able to interact with the main window when needed
	 * @param dba DatabaseAccess pointer to allow interaction with our local database
	 */
	public MenuController(WindowServer ws, DataBaseAccess dba) {
		this.ws = ws;
		this.dba = dba;
		this.dataUG = dba.getUsers();
		this.dataMG = dba.getMusics();
		this.dataPL = dba.getPlayListsDetails("partylist");
		ws.createandshowGUI(dataUG,dataMG,dataPL);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("USERM")) {
			/**UserGestion menu item has been pressed*/
			ws.changeCardPanel(sType[0]);
		} else if (e.getActionCommand().equals("MUSICM")) {
			/**MusicGestion menu item has been pressed*/
			ws.changeCardPanel(sType[1]);
		} else if (e.getActionCommand().equals("PARTYM")) {
			/**PartyList menu item has been pressed*/
			ws.changeCardPanel(sType[2]);
		}
	}
	
}
