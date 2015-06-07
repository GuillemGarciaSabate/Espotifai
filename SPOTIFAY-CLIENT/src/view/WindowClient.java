package view;


import java.awt.CardLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BottonsController;
import model.ListTableModel;
import model.PlaylistsTableModel;
/**
 * This class creates the base of the GUI from the client, by the instance of this
 * the user it's going to be able to see a console which he can use to use the program
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde	
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class WindowClient extends JFrame{
	private Login login;
	private Register register;

	private JPanel PanellPrincipal;
	private MusicMenu musicMenu;
	private JScrollPane scrollList;
	private JScrollPane scrollSongs;
	private CardLayout cards;
	private ImageIcon image1;
	private ImageIcon image3;


	/**
	 * This is the constructor of this class, which associates elemenets such as the ones that provides different utilities to the user like the list
	 * of playlits or the list of songs with the main view of the user
	 * @param tablemodel this is the table which contains the playlist created by the user
	 * @param listablemodel this is the table which contains the songs associated with any playlist of the user ruling the program 
	 * @param listablemodel2 this is the table which contains the songs avaiable in the server
	 * @param listablemodel3 this is the table which contains the songs from the partylist
	 */
	public WindowClient(PlaylistsTableModel tablemodel,ListTableModel listablemodel,ListTableModel listablemodel2,ListTableModel listablemodel3){
		login = new Login();
		PanellPrincipal = new JPanel();
		
		cards = new CardLayout();
		PanellPrincipal.setLayout(cards);
		
		musicMenu = new MusicMenu(tablemodel,listablemodel,listablemodel2,listablemodel3);	
		PanellPrincipal.add(login,"1");
		PanellPrincipal.add(musicMenu,"2");
		cards.show(PanellPrincipal, "1");
		
		PanellPrincipal.setOpaque(false);
		
		add(PanellPrincipal);
		setSize(400,350);
		setTitle("Espotyfai");
		setLocationRelativeTo(null);
		//setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		register = new Register(this, ModalityType.DOCUMENT_MODAL);
		
		register.setVisible(false);
		
	}

	/**
	 * This method acts like a setter, but actually it's function it's to associate the current view
	 * with the buttons controller created in the main method
	 * @param controlador this is a controller, it will be used to respond towards an act from the user
	 */
	public void  registerController(BottonsController controlador){
		
		login.setControllers(controlador);

		register.setControllers(controlador);
		
		musicMenu.getBarLists().setControllers(controlador);

		musicMenu.getBarLists().getMyTable().addMouseListener(controlador);
		
		musicMenu.getPlayBar().setControllers(controlador);

		musicMenu.getListSongs().getPanells().get(0).getTablesongs().addMouseListener(controlador);
		musicMenu.getListSongs().getPanells().get(1).getTablesongs().addMouseListener(controlador);
		musicMenu.getListSongs().getPanells().get(2).getTablesongs().addMouseListener(controlador);

		
	}
	/**
	 * This is an important method, while it's the responsible of repainting the lists everytime one of those
	 * suffer a change, which can be produced by some interaction by the user with the GUI
	 */
	public synchronized void refreshTable() {
		getMusicMenu().getBarLists().refreshTable();
		
		JScrollPane s = musicMenu.getScrollList();
		s.paint(s.getGraphics());
		musicMenu.getScrollList().paint(musicMenu.getScrollList().getGraphics());
		
	}
	/**
	 * This is an important method, while it's the responsible of repainting the songs table everytime the user demands 
	 * to switch the view to another one avaiable
	 */
	public synchronized void refreshLists() {
		getMusicMenu().getListSongs().getPanells().get(2).getTablesongs().refreshTable();
		
		JScrollPane s = musicMenu.getScrollSongs();
		s.paint(s.getGraphics());
		musicMenu.getScrollList().paint(musicMenu.getScrollSongs().getGraphics());
		
	}
	/**
	 * This method returns the value of the clicked column of the list of songs
	 * @param StaticTables this is the number of the table you are working on
	 * @param argX this is the object you had clicked
	 * @return int this is the clicked column
	 */
	public int valueColumn(int StaticTables,int argX){
		return musicMenu.getListSongs().valueColumn(StaticTables,argX);
		
	}
	/**
	 * This method returns the number of the row the user had clicked
	 * @param staticTables this is the staticTable the user is working on
	 * @return int this is the number of the clicked row
	 */
	public int valueRow(int staticTables){
		return musicMenu.getListSongs().valueRow(staticTables);
	}
	/**
	 * This method returns the value of the clicked column of the playlists lists
	 * @param X this is the int value from the object you had clicked
	 * @return int this is the clicked column
	 */
	public int ColumnIndex(int X){
		return musicMenu.getBarLists().getMyTable().getColumnModel().getColumnIndexAtX(X);
	}
	/**
	 * This method returns the object on the clicked position
	 * @param Y Integer with the row height we want to check
	 * @return the value of height row
	 */
	public int RowHeight(int Y){
		return Y / musicMenu.getBarLists().getMyTable().getRowHeight();
	}
	/**
	 * This method returns the object on the clicked position row
	 * @return int this returns the count row
	 */
	public int PlaylistsTableRowCount(){
		return musicMenu.getBarLists().getMyTable().getRowCount();
	}
	/**
	 * This method returns the object on the clicked position column
	 * @return int this returns the count column
	 */
	public int PlaylistsTableColumnCount(){
		return musicMenu.getBarLists().getMyTable().getColumnCount();
	}
	/**
	 * return the object of the cell clicked
	 * @param row row
	 * @param column column
	 * @return object of the cell
	 */
	public Object PlaylistsValueAt(int row, int column){
		return musicMenu.getBarLists().getMyTable().getValueAt(row, column);
	}
	/**This method gets the login
	 * @return Login
	 */
	public Login getLogin() {
		return login;
	}
	/**
	 * this is a method which counts the number of rows
	 * @param i this is the index of the panel we are working on
	 * @return this is the number of columns
	 */
	public int SongsListRowCount(int i){
		return musicMenu.getListSongs().getPanells().get(i).getTablesongs().getRowCount();
	}
	/**
	 * This method count the number of columns on the table songs list
	 * @param i this is the index of the panel we are working on
	 * @return this is the number of columns
	 */
	public int SongsListColumnCount(int i){
		return musicMenu.getListSongs().getPanells().get(i).getTablesongs().getColumnCount();
	}
	/**
	 * This mthod returns the value at the clicked cell by the user on the songs list
	 * @param i index of the panel
	 * @param row index of the row
	 * @param column index of the column
	 * @return Object this is the object at this position
	 */
	public Object SongsListValueAt(int i,int row,int column){
		return musicMenu.getListSongs().getPanells().get(i).getTablesongs().getValueAt(row, column);
	}
	/**
	 * This method sets the Login
	 * @param login Login panel to set in WindowClient
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
	/**
	 * This method gets the register
	 * @return register
	 */
	public Register getRegister() {
		return register;
	}
	/**
	 * This method sets the Register
	 * @param register the register
	 */
	public void setRegister(Register register) {
		this.register = register;
	}
	/**
	 * This method sets the main Panel of the view
	 * @return JPanel value of the PanellPrincipal it's the main panel
	 */
	public JPanel getPanellPrincipal() {
		return PanellPrincipal;
	}

	public void setPanellPrincipal(JPanel panellPrincipal) {
		PanellPrincipal = panellPrincipal;
	}
	/**
	 * This method it's a getter from the cards
	 * @return CardLayout
	 */
	public CardLayout getCards() {
		return cards;
	}
	/**
	 * This method sets the cards of the view
	 * @param cards those are the cards
	 */
	public void setCards(CardLayout cards) {
		this.cards = cards;
	}

	/**
	 * This method gets an Icon
	 * @return ImageIcon the icon
	 */
	public ImageIcon getImage3() {
		return image3;
	}
	/**
	 * This method sets an Image for an icon
	 * @param image3 the icon
	 */
	public void setImage3(ImageIcon image3) {
		this.image3 = image3;
	}
	/**
	 * This method gets an Icon
	 * @return ImageIcon the icon
	 */
	public ImageIcon getImage1() {
		return image1;
	}
	/**
	 * This method sets an Image for an icon
	 * @param image1 the icon
	 */
	public void setImage1(ImageIcon image1) {
		this.image1 = image1;
	}

	/**
	 * this method gets the JScrollPane from the scrollList
	 * @return JScrollPane that it's returned
	 */
	public JScrollPane getScrollList() {
		return scrollList;
	}
	/**
	 * this method set the SrollList
	 * @param scrollList to set
	 */
	public void setScrollList(JScrollPane scrollList) {
		this.scrollList = scrollList;
	}
	/**
	 * This method gets the ScrollPanel from the songs
	 * @return JScrollPane
	 */
	public JScrollPane getScrollSongs() {
		return scrollSongs;
	}
	/**
	 * This method sets the scrollPanel which shows the songs
	 * @param scrollSongs this is the scrollPane
	 */
	public void setScrollSongs(JScrollPane scrollSongs) {
		this.scrollSongs = scrollSongs;
	}
	/**
	 * this method sets the musicMenu
	 * @param musicMenu MusicMenu to set in WindowClient
	 */
	public void setMusicMenu(MusicMenu musicMenu) {
		this.musicMenu = musicMenu;
	}
	/**
	 * this method gets the musicMenu
	 * @return MusicMenu
	 */
	public MusicMenu getMusicMenu(){
		return musicMenu;
	}
	/**
	 * This method ensures that the field password from the loggin window
	 * it's filled with something
	 * @return boolean true if it's filled
	 */	
	public boolean AutenticationPassword(){
		
		if(!register.getUser().isEmpty() && !register.getPassword().isEmpty()
				&& !register.getPasswordAgain().isEmpty()
				&& register.getPassword().equals(register.getPasswordAgain())){
			return true;
		}
		else{
			return false;
		}
	}
}
