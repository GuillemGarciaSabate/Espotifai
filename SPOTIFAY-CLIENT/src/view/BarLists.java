package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.BottonsController;

import model.PlaylistsTableModel;

/**
 * Class that creates the left side of the screen with the Your Music and PlayLists section
 * 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class BarLists extends JPanelBackground {
	/**Swing and AWT Components*/
	private JPanel jpYourMusic;
	private JPanel jpPreLists;
	private JButton jbFavorites;
	private JButton jbSongs;
	private JButton jbPartyList;
	private JLabel jlYourLists;
	private JButton jbCreateLists;
	private JPanel jpIntroduceNameList;
	private JERoundTextField jertfTextList;	

	/**Table*/
	private MyTable myTable;

	/**Constants*/
	private static final String sURLBACK = "./img/ListsOfMusic7.png";
	private static final String sURLFB1 = "./img/FinalButton.png";
	private static final String sURLFB2 = "./img/FinalButton2.png";
	private static final String sURLFB3 = "./img/FinalButton3.png";
	private static final String sURLFBS1 = "./img/FinalButtonSongs.png";
	private static final String sURLFBS2 = "./img/FinalButtonSongs2.png";
	private static final String sURLFBS3 = "./img/FinalButtonSongs3.png";
	private static final String sURLFBPL1 = "./img/FinalButtonPList.png";
	private static final String sURLFBPL2 = "./img/FinalButtonPList2.png";
	private static final String sURLFBPL3 = "./img/FinalButtonPList3.png";
	private static final String sURLFCL1 = "./img/FinalCreateList.png";
	private static final String sURLFCL2 = "./img/FinalCreateList2.png";
	private static final String sURLFCL3 = "./img/FinalCreateLis3.png";
	
	/**
	 * Constructor
	 * @param tablemodel TableModel for the table of this panel
	 */
	public BarLists(PlaylistsTableModel tablemodel){
		
		this.initializePanel(tablemodel);
		this.createYourMusicTitle();
		this.createYourMusicContent();
		this.createYourListsTitle();
		this.createNewListSection();
		this.createNewListTextField();
		this.placeContentsInPanel();

	}

	/**
	 * Place all our panels and other Swing or AWT elements inside our main panel using GridBagConstraints
	 */
	private void placeContentsInPanel() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.insets = new Insets(10,0,0,0);
		constraints.weighty = 0.0;
		constraints.weightx = 1.0;
		this.add(jpYourMusic,constraints);

		constraints.gridx = 0; 
		constraints.gridy = 2; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 0.0;
		this.add(jpPreLists,constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 3; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 0.0;
		this.add(jlYourLists,constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 4; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 0.0;
		this.add(jbCreateLists,constraints);
		
		
		constraints.gridx = 0; 
		constraints.gridy = 5; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 0.0;
		this.add(jpIntroduceNameList,constraints);
		
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridx = 0; 
		constraints.gridy = 6; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 1.0;
		this.add(myTable,constraints);
	}

	/**
	 * Puts a background, sets a layout and creates the table for this panel
	 * @param tablemodel TableModel for the table of this panel
	 */
	private void initializePanel(PlaylistsTableModel tablemodel) {
		myTable = new MyTable(tablemodel);
		
		this.setBackground(sURLBACK);	
		this.setLayout(new GridBagLayout());
	}

	/**
	 * Creates the section where you write the name of the new playlist. Invisible unless you press the button
	 */
	private void createNewListTextField() {
		jpIntroduceNameList = new JPanel();
		jpIntroduceNameList.setOpaque(false);
		jertfTextList = new JERoundTextField(15,15,200);
		jertfTextList.setVisible(true);
		jpIntroduceNameList.setVisible(false);
		jpIntroduceNameList.add(jertfTextList);
	}

	/**
	 * Creates the button that allows to generate a new Playlist
	 */
	private void createNewListSection() {
		ImageIcon image10 = new ImageIcon(sURLFCL1);
		ImageIcon image11 = new ImageIcon(sURLFCL2);
		ImageIcon image12 = new ImageIcon(sURLFCL3);
		jbCreateLists = new JButton("New PlayList");
		jbCreateLists.setFont(new java.awt.Font("Century Gothic",0, 15));
		jbCreateLists.setForeground(new Color(150,100,100));
		jbCreateLists.setIcon(image10);
		jbCreateLists.setHorizontalTextPosition(SwingConstants.CENTER);
		jbCreateLists.setVerticalTextPosition(SwingConstants.CENTER);
		jbCreateLists.setRolloverIcon(image11);
		jbCreateLists.setRolloverSelectedIcon(image12);	
		jbCreateLists.setContentAreaFilled(false);
		jbCreateLists.setFocusable(false);
		jbCreateLists.setBorderPainted(false);
	}

	/**
	 * Creates the title PlayLists
	 */
	private void createYourListsTitle() {
		jlYourLists = new JLabel("PLAYLISTS");		
		jlYourLists.setFont(new java.awt.Font("Century Gothic",0, 17));
		jlYourLists.setForeground(new Color(250,250,250));
	}

	/**
	 * Function that generates the content of the Your Music section
	 */
	private void createYourMusicContent() {
		/**Creates all images we will use for the buttons*/
		ImageIcon image1 = new ImageIcon(sURLFB1);
		ImageIcon image2 = new ImageIcon(sURLFB2);
		ImageIcon image3 = new ImageIcon(sURLFB3);
		ImageIcon image4 = new ImageIcon(sURLFBS1);
		ImageIcon image5 = new ImageIcon(sURLFBS2);
		ImageIcon image6 = new ImageIcon(sURLFBS3);
		ImageIcon image7 = new ImageIcon(sURLFBPL1);
		ImageIcon image8 = new ImageIcon(sURLFBPL2);
		ImageIcon image9 = new ImageIcon(sURLFBPL3);
	
		/**Creates the button of Favourites and configures it*/
		jbFavorites = new JButton("Favorites");
		jbFavorites.setFont(new java.awt.Font("Century Gothic",0, 15));
		jbFavorites.setForeground(new Color(150,100,100));
		jbFavorites.setIcon(image1);
		jbFavorites.setHorizontalTextPosition(SwingConstants.CENTER);
		jbFavorites.setVerticalTextPosition(SwingConstants.CENTER);
		jbFavorites.setRolloverIcon(image2);
		jbFavorites.setSelectedIcon(image3);
		jbFavorites.setContentAreaFilled(false);
		jbFavorites.setFocusable(false);
		jbFavorites.setBorderPainted(false);
		
		/**Creates the button of Songs and configures it*/
		jbSongs = new JButton("Songs");
		jbSongs.setFont(new java.awt.Font("Century Gothic",0, 15));
		jbSongs.setForeground(new Color(150,100,100));
		jbSongs.setIcon(image4);
		jbSongs.setHorizontalTextPosition(SwingConstants.CENTER);
		jbSongs.setVerticalTextPosition(SwingConstants.CENTER);
		jbSongs.setRolloverIcon(image5);
		jbSongs.setSelectedIcon(image6);
		jbSongs.setContentAreaFilled(false);
		jbSongs.setFocusable(false);
		jbSongs.setBorderPainted(false);
		
		/**Creates the button of PartyList and configures it*/
		jbPartyList = new JButton("PartyList");
		jbPartyList.setFont(new java.awt.Font("Century Gothic",0, 15));
		jbPartyList.setForeground(new Color(150,100,100));
		jbPartyList.setIcon(image7);
		jbPartyList.setHorizontalTextPosition(SwingConstants.CENTER);
		jbPartyList.setVerticalTextPosition(SwingConstants.CENTER);
		jbPartyList.setRolloverIcon(image8);
		jbPartyList.setSelectedIcon(image9);	
		jbPartyList.setContentAreaFilled(false);
		jbPartyList.setFocusable(false);
		jbPartyList.setBorderPainted(false);
		
		/**Creates the panel where we will put all buttons*/
		jpPreLists = new JPanel(new BorderLayout());
        jpPreLists.setOpaque(false);
		jpPreLists.add(jbFavorites,BorderLayout.NORTH);
		jpPreLists.add(jbSongs,BorderLayout.CENTER);
		jpPreLists.add(jbPartyList,BorderLayout.SOUTH);
	}

	/**
	 * Function that creates the panel with the title of Your Music
	 */
	private void createYourMusicTitle() {
		jpYourMusic = new JPanel(new GridLayout(1,1));
		jpYourMusic.setOpaque(false);
		JLabel jlYourMusic = new JLabel("YOUR MUSIC");		
		jlYourMusic.setFont(new java.awt.Font("Century Gothic",0, 17));
		jlYourMusic.setForeground(new Color(250,250,250));
		jpYourMusic.add(jlYourMusic);
	}

	/**
	 * Refreshes the content of our table
	 */
	public void refreshTable() {
		myTable.refreshTable();
	}

	/**
	 * Changes the state of one button to selected and the others to unselected
	 * @param iButton Integer that indicates which button to set to selected
	 */
	public void ChangeIcons(int iButton){
		if(iButton == 0){
			jbFavorites.setSelected(true);
			jbSongs.setSelected(false);
			jbPartyList.setSelected(false);
		}
		if(iButton == 1){
			jbFavorites.setSelected(false);
			jbSongs.setSelected(true);
			jbPartyList.setSelected(false);
		}
		
		if(iButton == 2){
			jbFavorites.setSelected(false);
			jbSongs.setSelected(false);
			jbPartyList.setSelected(true);
		}
		if(iButton == 3){
			jbFavorites.setSelected(false);
			jbSongs.setSelected(false);
			jbPartyList.setSelected(false);
		}
	}
	
	/**
	 * Getter of text in our JERTextField jertfTextList
	 * @return String of text inside our JERoundTextField jertfTextList
	 */
	public String getTextList() {
		return jertfTextList.getText();
	}

	/**
	 * Setter of text in our JERTextField jertfTextList
	 * @param sText New text to set
	 */
	public void setTextList(String sText) {
		jertfTextList.setText(sText);
	}

	/**
	 * Getter of MyTable mytable
	 * @return MyTable table
	 */
	public MyTable getMyTable() {
		return myTable;
	}

	/**
	 * Shows section where you can write a new PlayList
	 * and adds focus to the textfield
	 */
	public void showPanelToNewPlayList() {
		jpIntroduceNameList.setVisible(true);
		jpIntroduceNameList.setFocusable(true);
		jpIntroduceNameList.requestFocus(true);
		jpIntroduceNameList.setRequestFocusEnabled(true);
		jpIntroduceNameList.requestFocus();
		
		
		
		
	}

	/**
	 * Hides section where you can write a new PlayList
	 */
	public void hidePanelToNewPlayList() {
		jpIntroduceNameList.setVisible(false);
	}

	/**
	 * Informs if the text inside the textfield is currently focused
	 * @return Boolean that indicates if it is focused or not
	 */
	public boolean isTextListFocused() {
		return jpIntroduceNameList.isRequestFocusEnabled();
	}

	/**
	 * Sets controllers to all buttons
	 * @param controlador Controler of the buttons
	 */
	public void setControllers(BottonsController controlador) {
		jbFavorites.addActionListener(controlador);
		jbFavorites.setActionCommand("favorites");
		
		jbSongs.addActionListener(controlador);
		jbSongs.setActionCommand("Songs");
		
		jbPartyList.addActionListener(controlador);
		jbPartyList.setActionCommand("PartyList");
		
		jbCreateLists.addActionListener(controlador);
		jbCreateLists.setActionCommand("Create");
		
		jertfTextList.addKeyListener(controlador);
		jertfTextList.setActionCommand("PRESS ENTER");
	}

	/**
	 * Disallows selection in a table
	 */
	public void disableTableCellSelection() {
		myTable.setCellSelectionEnabled(false);
	}

	/**
	 * Allows selection in a table
	 */
	public void enableTableCellSelection() {
		myTable.setCellSelectionEnabled(true);
	}
}
