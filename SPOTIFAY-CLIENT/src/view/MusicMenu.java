package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BottonsController;
import model.List;
import model.ListTableModel;
import model.PlaylistsTableModel;
/**
 * This class administrates the options of the user at the main window, through this class
 * we incorporate on the GUI all the choices that the user can do with the program
 * @see WindowClient
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class MusicMenu extends JPanel{

	private BarLists barLists;
	private ListSongs listSongs;
	private PlayBar playBar;
	private SongPlaying barSearch;
	private BarSuperior barSuperior;
	
	private JPanel pWest;

	
	private JScrollPane scrollList;
	private JScrollPane scrollSongs;
	
	private static final String FAVORITES_VIEW = "0";
	private static final String SONGS_VIEW = "1";
	private static final String PARTYLIST_VIEW = "2";
	private static final String PLAYLIST = "3";
	
	/**
	 * This is the constructor of the class, which associates elemenets such as the ones that provides different utilities to the user like the list
	 * of playlits or the list of songs with the main view of the user
	 * @param tablemodel this is the table which contains the playlist created by the user
	 * @param listablemodel this is the table which contains the songs associated with any playlist of the user ruling the program 
	 * @param listablemodel2 this is the table which contains the songs avaiable in the server
	 * @param listablemodel3 this is the table which contains the songs from the partylist
	 **/
	public MusicMenu (PlaylistsTableModel tablemodel,ListTableModel listablemodel,ListTableModel listablemodel2,ListTableModel listablemodel3){

		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		pWest = new JPanel(new BorderLayout());
		pWest.setOpaque(false);
		//BARSUPERIOR
		
		barSuperior = new BarSuperior();
		barSuperior.setOpaque(false);

		this.add(barSuperior,BorderLayout.NORTH);
		
		//REPRODUCTOR MUSICA
		
		playBar = new PlayBar();
		playBar.setOpaque(false);

		this.add(playBar,BorderLayout.SOUTH);
		
		//BARSEARCH
		
		barSearch = new SongPlaying();
		barSearch.setOpaque(false);
		
		//BARLISTSMUSIC
		barLists = new BarLists(tablemodel);
		barLists.setOpaque(false);
		scrollList  = new JScrollPane(barLists,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollList.setBorder(BorderFactory.createEmptyBorder());
		scrollList.getVerticalScrollBar().setOpaque(false);
		scrollList.getVerticalScrollBar().setForeground(new Color(0,0,0));
		scrollList.getVerticalScrollBar().setBackground(Color.BLACK);
		scrollList.getVerticalScrollBar().setUnitIncrement(16);

		pWest.add(scrollList,BorderLayout.CENTER);
		pWest.add(barSearch,BorderLayout.SOUTH);
		this.add(pWest,BorderLayout.WEST);
		//LISTSONGS
		
		listSongs = new ListSongs(listablemodel,listablemodel2,listablemodel3);
		listSongs.setOpaque(false);
		scrollSongs  = new JScrollPane(listSongs,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSongs.setBorder(BorderFactory.createEmptyBorder());
		scrollSongs.getVerticalScrollBar().setUnitIncrement(16);

		this.add(scrollSongs,BorderLayout.CENTER);

	}
	/**
	 * This is a getter to get barLists
	 * @return BarLists
	 */
	public BarLists getBarLists() {
		return barLists;
	}
	/**
	 * This method sets a new barLists
	 * @param barLists it's the new attribute
	 */
	public void setBarLists(BarLists barLists) {
		this.barLists = barLists;
	}
	/**
	 * This is a getter to get listSongs
	 * @return ListSongs
	 */
	public ListSongs getListSongs() {
		return listSongs;
	}
	/**
	 * This method sets a new listSongs
	 * @param listSongs it's the new attribute
	 */
	public void setListSongs(ListSongs listSongs) {
		this.listSongs = listSongs;
	}
	/**
	 * This is a getter to get playBar
	 * @return PlayBar
	 */
	public PlayBar getPlayBar() {
		return playBar;
	}
	/**
	 * This method sets a new playBar
	 * @param playBar it's the new attribute
	 */
	public void setPlayBar(PlayBar playBar) {
		this.playBar = playBar;
	}
	/**
	 * This is a getter to get barSearch
	 * @return SongPlaying
	 */
	public SongPlaying getBarSearch() {
		return barSearch;
	}
	/**
	 * This method sets a new barSearch
	 * @param barSearch it's the new attribute
	 */
	public void setBarSearch(SongPlaying barSearch) {
		this.barSearch = barSearch;
	}
	/**
	 * This is a getter to get scrollList
	 * @return JScrollPane
	 */
	public JScrollPane getScrollList() {
		return scrollList;
	}
	/**
	 * This method sets a new scrollList
	 * @param scrollList it's the new attribute
	 */
	public void setScrollList(JScrollPane scrollList) {
		this.scrollList = scrollList;
	}
	/**
	 * This is a getter to get scrollSongs
	 * @return JScrollPane
	 */
	public JScrollPane getScrollSongs() {
		return scrollSongs;
	}
	/**
	 * This method sets a new scrollSongs
	 * @param scrollSongs it's the new attribute
	 */
	public void setScrollSongs(JScrollPane scrollSongs) {
		this.scrollSongs = scrollSongs;
	}

	/**
	 * This method change the view of the panel on which the user is working on
	 * @param id this is an identificator to determine which view should be loaded
	 * @param nom This attribute it's used to generate a title for the loaded view
	 */
	public void ChangeList(int id,String nom) {
		// TODO Auto-generated method stub
		System.out.println("NAMELIST :"+nom);
		if(id == 0){
			listSongs.showCardList(listSongs,FAVORITES_VIEW);
		}else if(id ==1){
			listSongs.showCardList(listSongs,SONGS_VIEW);
		}else if(id==2){
			listSongs.showCardList(listSongs,PARTYLIST_VIEW);
		}else if(id == 3){
			String nomBe ="";
			for (int i = 0 ; i< nom.length();i++){
				nomBe = nomBe +" "+ nom.charAt(i); 
			}
			JLabel title = new JLabel(nomBe);
			listSongs.getPanells().get(id).setNomlist(title.getText());
			listSongs.showCardList(listSongs,PLAYLIST);
		}
		barLists.ChangeIcons(id);

	}
	
	/**
	 * This method load a pop up, it's used to confirm an action from the user associated with adding an item
	 * @param lists these are the lists avaiable on the user playlists structures
	 * @param ActualList this is the name from the List where the user is working on currently
	 * @param x it's a row index
	 * @param y it's a column index
	 * @param table it's an index pointing to the actual table
	 * @param controlador it's a mouse controller for the generated item
	 */
	public void ShowPopupOptions(LinkedList<List> lists,String ActualList,int x,int y,int table,BottonsController controlador){
		
		listSongs.getPanells().get(table).ShowPopupOptions(lists, ActualList, x, y,controlador);
		
	}
	/**
	 * This method load a pop up, it's used to confirm an action from the user associated with deleteing an item
	 * @param lists these are the lists avaiable on the user playlists structures
	 * @param ActualList this is the name from the List where the user is working on currently
	 * @param x it's a row index
	 * @param y it's a column index
	 * @param table it's an index pointing to the actual table
	 * @param controlador it's a mouse controller for the generated item
	 */
	public void ShowPopupOptions2(LinkedList<List> lists,String ActualList,int x,int y,int table,BottonsController controlador){
		
		listSongs.getPanells().get(table).ShowPopupOptions2(lists, ActualList, x, y,controlador);
		
	}
	/**
	 * This is a getter to get barSuperior
	 * @return BarSuperior
	 */
	public BarSuperior getBarSuperior() {
		return barSuperior;
	}
	/**
	 * This method sets a new barSuperior
	 * @param barSuperior it's the new attribute
	 */
	public void setBarSuperior(BarSuperior barSuperior) {
		this.barSuperior = barSuperior;
	}
}
