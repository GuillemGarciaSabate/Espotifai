package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controller.BottonsController;
import model.ListTableModel;

/**
 * Class that generates the cardpanel with the panels of favorites, song and partylist
 * 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class ListSongs extends JPanelBackground {
	/**Swing and AWT Components*/
	private CardLayout cards;
	private LinkedList<ListPanel> panells;

	/**Constants*/
	private static final String FAVORITES_VIEW = "0";
	private static final String SONGS_VIEW = "1";
	private static final String PARTYLIST_VIEW = "2";
	private static final String PLAYLIST = "3";
	private static final String sURLBACK = "./img/BarSongs2.png";
	private static final String sURLBD = "./img/ButtonDisconnect.png";
	private static final String sURLBD2 = "./img/ButtonDisconnect2.png";
	
	/**
	 * Constructor
	 * @param listablemodel Tablemodel for favorites panel
	 * @param listablemodel2 Tablemodel for songs panel
	 * @param listablemodel3 Tablemodel for partylist panel
	 */
	public ListSongs(ListTableModel listablemodel, ListTableModel listablemodel2,
			ListTableModel listablemodel3){	
		cards = new CardLayout();
		this.setBackground(sURLBACK);
		this.setLayout(cards);
		
		panells = new LinkedList<ListPanel>();
		
		ListPanel list = new ListPanel("F A V O R I T E S",listablemodel);
		panells.add(list);
		this.add(panells.get(0),FAVORITES_VIEW);
		
		ListPanel list2 = new ListPanel("S O N G S",listablemodel2);
		panells.add(list2);
		this.add(panells.get(1),SONGS_VIEW);
		
		ListPanel list3 = new ListPanel("P A R T Y L I S T",listablemodel3);	
		panells.add(list3);
		this.add(panells.get(2),PARTYLIST_VIEW);
	}

	/**
	 * Function that creates the panel of the selected playlist
	 * @param sNom String with the name of the playlist
	 * @param listatablemodel Tablemodel of the table in this panel
	 * @param controlador Controller to add as listener to the button created here
	 */
	public void CreatePanelList(String sNom, ListTableModel listatablemodel,
			BottonsController controlador){
		removeLastPanel();
		String sNomBe = "";
		for (int i = 0 ; i< sNom.length();i++){
			sNomBe = sNomBe +" "+ sNom.charAt(i); 
		}
		
		ListPanel list = new ListPanel(sNomBe, listatablemodel);

		ImageIcon image1 = new ImageIcon(sURLBD);
		ImageIcon image2 = new ImageIcon(sURLBD2);
		
		JButton deleteList = new JButton("Delete List");
		deleteList.setFont(new java.awt.Font("Apple SD Gothic Neo Thin",0, 12));
		deleteList.setForeground(new Color(0,0,0));
		deleteList.setIcon(image1);
		deleteList.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteList.setVerticalTextPosition(SwingConstants.CENTER);
		deleteList.setRolloverIcon(image2);
		deleteList.setContentAreaFilled(false);
		deleteList.setFocusable(false);
		deleteList.setVisible(true);
		deleteList.setBounds(300, 200, 40, 50);
		deleteList.setBorderPainted(false);
		deleteList.addActionListener(controlador);
		deleteList.setActionCommand("DELLIST");
		
		list.add(deleteList);
		panells.add(list);
		panells.get(3).getTablesongs().addMouseListener(controlador);
		this.add(panells.get(3),PLAYLIST);
	}
	
	public LinkedList<ListPanel> getPanells() {
		return panells;
	}

	/**
	 * Function that returns the value of the column pressed of a chosen panel
	 * @param iStaticTables Index of the panel we want to check its table
	 * @param iColumn Column we want to check
	 * @return Index of the pressed column in the table from the chosen panel
	 */
	public int valueColumn(int iStaticTables,int iColumn) {
		return panells.get(iStaticTables).valueColumn(iColumn);		
	}
	
	/**
	 * Function that returns the value of the row pressed of a chosen panel
	 * @param iStaticTables Index of the panel that we want to check its table
	 * @return Index of the pressed row in the table from the chosen panel
	 */
	public int valueRow(int iStaticTables) {
		return panells.get(iStaticTables).valueRow();
		
	}
	
	/**
	 * Removes the last added panel of the cardlayout
	 */
	public void removeLastPanel(){
		if(panells.size() == 4){
			panells.removeLast();
		}
	}

	/**
	 * Shows the panel we want to see from our cardpanel
	 * @param listSongs Panel that we want to change panels
	 * @param sFavoritesView Name of the panel we want to see
	 */
	public void showCardList(ListSongs listSongs, String sFavoritesView) {
		cards.show(listSongs, sFavoritesView);
	}
}
