package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.ButtonsController;
import controller.MenuController;
import controller.RowController;

/**
 * Class responsible for creating a new Server Window
 * It also contains functions for creating these view and assign
 * controllers to all his elements
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class WindowServer extends JFrame{
	/**Swing and AWT Components*/
	private JMenuItem jmiUserGestion;
	private JMenuItem jmiMusicGestion;
	private JMenuItem jmiPartyList;
	private JPanel jpCardPanel;
	private CardLayout clLayout;
	
	private MusicGestion mg;
	private UserGestion ug;
	private PartyList pl;
	
	public WindowServer() {
		
	}
	
	/**
	 * Method which creates all the elements for this window
	 * and assign them to these, also show the window once it's done
	 * 
	 * @param dataUG ArrayList of String[] that has the initial data of the table from user
	 * @param dataMG ArrayList of String[] that has the initial data of the table from music
	 * @param dataPL ArrayList of String[] that has the initial data of the table from PartyList
	 */
	public void createandshowGUI(ArrayList<String[]> dataUG,
			ArrayList<String[]> dataMG, ArrayList<String[]> dataPL) {
		this.getContentPane().setLayout(new BorderLayout());
		
		this.createMenuBar();
		
		this.createCardPanels(dataUG, dataMG, dataPL);
		
		this.configureWindow();
		
	}

	/**
	 * Private method that creates the panels that will be displayed on the CardLayout
	 * and assign them to that main window
	 * 
	 * @param dataUG ArrayList of String[] that has the initial data of the table from user
	 * @param dataMG ArrayList of String[] that has the initial data of the table from music
	 * @param dataPL ArrayList of String[] that has the initial data of the table from PartyList
	 */
	private void createCardPanels(ArrayList<String[]> dataUG,
			ArrayList<String[]> dataMG, ArrayList<String[]> dataPL) {
		clLayout = new CardLayout();
		jpCardPanel = new JPanel(clLayout);
		
		mg = new MusicGestion(1,dataMG);
		ug = new UserGestion(0,dataUG);
		pl = new PartyList(2,dataPL);
		JPanel jpEmpty = new JPanel();
		
		jpCardPanel.add(jpEmpty,"NONE");
		jpCardPanel.add(ug, "UG");
		jpCardPanel.add(mg, "MG");
		jpCardPanel.add(pl, "PL");
		
		this.getContentPane().add(jpCardPanel);
		
		clLayout.show(jpCardPanel,"NONE");
	}

	/**
	 * Method which creates the bar and assign it to the main view
	 */
	private void createMenuBar() {
		jmiMusicGestion = new JMenuItem("Music Gestion");
		jmiUserGestion = new JMenuItem("User Gestion");
		jmiPartyList = new JMenuItem("Party List");
		
		JMenu jmOptions = new JMenu("Options");
		jmOptions.add(jmiUserGestion);
		jmOptions.add(jmiMusicGestion);
		jmOptions.add(jmiPartyList);
		
		JMenuBar jmbBar = new JMenuBar();
		jmbBar.add(jmOptions);
		this.setJMenuBar(jmbBar);
	}

	/**
	 * Private method which configures the options of the window
	 */
	private void configureWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setTitle("Server");
	}
	
	/**
	 * Method that assign controllers to the ActionListeners of the menu  and the buttons
	 * 
	 * @param mc Controller which assigns as a ActionListener to the menus
	 * @param bc Controller which assigns as a ActionListener to the buttons
	 * @param rc Controller which assigns as a ActionListener to the rows
	 */
	public void setControllers(MenuController mc, RowController rc, ButtonsController bc) {
		jmiMusicGestion.addActionListener(mc);
		jmiMusicGestion.setActionCommand("MUSICM");
		jmiUserGestion.addActionListener(mc);
		jmiUserGestion.setActionCommand("USERM");
		jmiPartyList.addActionListener(mc);
		jmiPartyList.setActionCommand("PARTYM");
		
		mg.setControllers(rc,bc);
		ug.setControllers(bc,rc);
		pl.setControllers(rc,bc);
	}
	
	/**
	 * Method that depending on the input parameter shows the appropriate CardPanel
	 *  
	 * @param sCard String That points the CardPanel to show. Only can be a UG, MG o PL
	 */
	public void changeCardPanel(String sCard) {
		clLayout.show(jpCardPanel,sCard);
		if (sCard.equals("UG")) {
			this.setSize(ug.getTableSize().width+20,400);
		} else if (sCard.equals("MG")) {
			this.setSize(mg.getTableSize().width+20,400);
		} else if (sCard.equals("PL")) {
			this.setSize(pl.getTableSize().width+20,400);
		}
	}
	
	/**
	 * Method that puts in the desired table our data
	 * @param iTable Integer that indicates which table we want to put our data to
	 * @param data ArrayList with the data to add to our table
	 */
	public void setNewData(int iTable, String[] data){
		switch (iTable){
		case 0:
			ug.insertData(data);
			this.setSize(ug.getTableSize().width+20,400);
			break;
		case 1:
			mg.insertData(data);
			this.setSize(mg.getTableSize().width+20,400);
			break;
		case 2:
			pl.insertData(data);
			this.setSize(pl.getTableSize().width+20,400);
			break;
		default:
			break;
		}	
	}
	
	/**
	 * Method that returns the current name of the selected card
	 * @return String with the name of the card
	 */
	public String getCurrentCardSelection() {
		JPanel card = null;
		for (Component comp : jpCardPanel.getComponents()) {
		    if (comp.isVisible() == true) {
		        card = (JPanel) comp;
		    }
		}
		return card.getName();
	}
	
	/**
	 * Sets the path inside MusicGestion, the URL field
	 * @param sPath String with the URL to set
	 */
	public void setTypedPath(String sPath) {
		mg.setTypedPath(sPath);
	}

	/**
	 * Getter of the name of song in MusicGestion
	 * @return String with the name of the song
	 */
	public String getNameSong() {
		return mg.getNameSong();
	}

	/**
	 * Getter of the genre of song in MusicGestion
	 * @return String with the genre of the song
	 */
	public String getGenreSong() {
		return mg.getGenreSong();
	}

	/**
	 * Getter of the album of song in MusicGestion
	 * @return String with the album of the song
	 */
	public String getAlbumSong() {
		return mg.getAlbumSong();
	}

	/**
	 * Getter of the artist of song in MusicGestion
	 * @return String with the artist of the song
	 */
	public String getArtistSong() {
		return mg.getArtistSong();
	}

	/**
	 * Getter of the URL of song in MusicGestion
	 * @return String with the URL of the song
	 */
	public String getURLSong() {
		return mg.getURLSong();
	}

	/**
	 * Function that deletes the first row of the table in PartyList
	 */
	public void deleteFirstRowFromPlayList() {
		pl.deleteFirstRow();
	}

	
}
