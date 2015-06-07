package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.ListTableModel;
import network.ServerCommunication;
import model.Auth;
import model.EditPlayList;
import model.PlayListDetails;
import model.PlayMusic;
import model.PlaySong;
import model.PlaylistsTableModel;
import model.SerialList;
import model.SignUP;
import model.User;
import view.WindowClient;
/**
 * this Class implements all actions of the window client
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
public class BottonsController implements ActionListener,MouseListener,KeyListener{
	private WindowClient wc;
	private PlaylistsTableModel ListTableModel;
	
	private String sAction;
	private User user;
	private boolean isCreating;
	private int StaticTables = -1;
	private int PlayTable = -1;
	private int click = -1;
	private int row = 0;
	private PlayMusic pm;
	private ServerCommunication SC;
	
	/**
	 * The constructor of the BottonsController
	 * @param wc WindowClient is the graphical interface of the user
	 * @param user user is the client that executes the program
	 * @param SC SliderController is the controller thats manage the volum
	 */
	public BottonsController(WindowClient wc,User user,ServerCommunication SC){
		this.pm = null;
		this.wc = wc;
		this.user = user;
		this.SC = SC;
		this.isCreating= false;
	}
	/**
	 * Catches an action of user
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getActionCommand().equals("DELLIST")){
			int result = JOptionPane.showConfirmDialog(new JDialog(), "Are you sure you want to delete this playlist?");
			if (result == JOptionPane.OK_OPTION) {
			
				user.getLists().get(this.row).RemoveAllSongs();
				EditPlayList EP = new EditPlayList();
				EP.setName(user.getLists().get(this.row).getNom());
				EP.setAction("fin");
				user.getLists().remove(this.row);
				StaticTables = 1;
				wc.getMusicMenu().ChangeList(StaticTables,null);
				wc.getMusicMenu().getBarLists().disableTableCellSelection(); 
				SC.sendInfo(EP);
				wc.refreshTable();
				SwingUtilities.updateComponentTreeUI(wc.getMusicMenu().getBarLists().getMyTable());
				
			}
		}
		
		if(e.getActionCommand().equals("sign in")){
			wc.getRegister().setVisible(true);
		}
		
		
		if(e.getActionCommand().equals("OK")){
			SignUP SU = new SignUP();

			if(wc.AutenticationPassword()){
				SU.setName(wc.getRegister().getUser());
				SU.setPwd(wc.getRegister().getPassword());
				wc.getRegister().setVisible(false);
				SC.sendInfo(SU);
				
			}
			else{			
				wc.getRegister().showErrorMessage();
			}
		}
		
		
		if(e.getActionCommand().equals("log in")){
			
			wc.setExtendedState(JFrame.MAXIMIZED_BOTH);			
			wc.getMusicMenu().getBarLists().ChangeIcons(0);
			wc.setLocationRelativeTo(null);
			StaticTables = 0;
			Auth auth = new Auth();
			auth.setName(wc.getLogin().getUser());
			auth.setPassword(wc.getLogin().getPassword());
			SC.sendInfo(auth);
			wc.getMusicMenu().getBarSuperior().setNameUser(wc.getLogin().getUser());
		}
		
		if(e.getActionCommand().equals("favorites")){
			if(StaticTables != 0){
				user.removeAllLists();
				StaticTables = 0;
				wc.getMusicMenu().getBarLists().disableTableCellSelection();
				SerialList SL = new SerialList();
				SL.setNameList("favorites");
				SC.sendInfo(SL);	
			}

		}
		
		if(e.getActionCommand().equals("Songs")){
			if(StaticTables != 1){
				user.removeAllLists();
				StaticTables = 1;
				
				wc.getMusicMenu().getBarLists().disableTableCellSelection(); 
				SerialList SL = new SerialList();
				SL.setNameList("song");
				SC.sendInfo(SL);
			}		
		}
		if(e.getActionCommand().equals("PartyList")){
			if(StaticTables != 2){
				user.removeAllLists();
				StaticTables = 2;
				wc.getMusicMenu().getBarLists().ChangeIcons(StaticTables);
				wc.getMusicMenu().ChangeList(StaticTables,null);
				wc.getMusicMenu().getBarLists().disableTableCellSelection();
				
				SerialList SL = new SerialList();
				SL.setNameList("partylist");
				SC.sendInfo(SL);
			}
		}
		 if (e.getSource() instanceof JMenuItem){
			 
			 EditPlayList EP = new EditPlayList();
			 EP.setAction(sAction);
			 if(StaticTables !=-1){
			 for(int i = 0; i<user.getLists().size();i++){
				 if(e.getActionCommand().equals(user.getLists().get(i).getNom())){
					 EP.setName(user.getLists().get(i).getNom());
					 String songName = user.ChooseList(StaticTables).getSongs().get(PlayTable).getName()+"%";
					 String songArtist = user.ChooseList(StaticTables).getSongs().get(PlayTable).getArtist()+"%";
					 String songAlbum = user.ChooseList(StaticTables).getSongs().get(PlayTable).getAlbum()+"%";
					 String songGender = user.ChooseList(StaticTables).getSongs().get(PlayTable).getGender();
					 EP.setSong(songName+songArtist+songAlbum+songGender);
					 
					 SC.sendInfo(EP);
					 i = user.getLists().size();
					 
					 
				 }
				
			 }
			 if(e.getActionCommand().equals("Partylist")){
				 EP.setName("partylist");
				 EP.setSong(user.ChooseList(StaticTables).getSongs().get(PlayTable).getName()+"%"+user.ChooseList(StaticTables).getSongs().get(PlayTable).getArtist()+"%"+user.ChooseList(StaticTables).getSongs().get(PlayTable).getAlbum()+"%"+user.ChooseList(StaticTables).getSongs().get(PlayTable).getGender());
				 SC.sendInfo(EP);
				 
			 }
			 SwingUtilities.updateComponentTreeUI(wc.getMusicMenu().getScrollSongs());
				
			 
			   
		   }else{
			   for(int i = 0; i<user.getLists().size();i++){
					 if(e.getActionCommand().equals(user.getLists().get(i).getNom())){
						 EP.setName(user.getLists().get(i).getNom());
						 String songName = user.getLists().get(this.row).getSongs().get(PlayTable).getName()+"%";
						 String songArtist = user.getLists().get(this.row).getSongs().get(PlayTable).getArtist()+"%";
						 String songAlbum = user.getLists().get(this.row).getSongs().get(PlayTable).getAlbum()+"%";
						 String songGender = user.getLists().get(this.row).getSongs().get(PlayTable).getGender();
						 EP.setSong(songName+songArtist+songAlbum+songGender);
						 SC.sendInfo(EP);
						 i = user.getLists().size();
						 user.getLists().get(this.row).getSongs().remove(PlayTable);
						 
						 
					 }
					
				 }
				 if(e.getActionCommand().equals("Partylist")){
					 EP.setName("partylist");
					 EP.setSong(user.getLists().get(this.row).getSongs().get(PlayTable).getName()+"%"+user.getLists().get(this.row).getSongs().get(PlayTable).getArtist()+"%"+user.getLists().get(this.row).getSongs().get(PlayTable).getAlbum()+"%"+user.getLists().get(this.row).getSongs().get(PlayTable).getGender());
					 SC.sendInfo(EP);
					 
				 }
					
		   }
			 SwingUtilities.updateComponentTreeUI(wc.getMusicMenu().getScrollSongs());
		 }	 
		if(e.getActionCommand().equals("Create")){
			
			wc.getMusicMenu().getBarLists().setTextList("New PlayList");
			wc.getMusicMenu().getBarLists().showPanelToNewPlayList();
			isCreating = true;
		}
		
		if(e.getActionCommand().equals("FINALPLAY")){
			//si play no esta seleccionado
			if(!wc.getMusicMenu().getPlayBar().getPlay().isSelected()){
				wc.getMusicMenu().getPlayBar().getPlay().setSelected(true);
			}
		}
		
		
	}	
	/**
	 * This method catches all actions of the mouse clicked
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	   int column = wc.ColumnIndex(arg0.getX());
	   int row    = wc.RowHeight(arg0.getY()); 
	   
	   if(arg0.getSource().equals(wc.getMusicMenu().getBarLists().getMyTable()) ){
  
		   if (row < wc.PlaylistsTableRowCount() && row >= 0 && column < wc.PlaylistsTableColumnCount() && column >= 0) {
			   Object value = wc.PlaylistsValueAt(row, column);
			  
			   		if (value instanceof JButton) {	
			   			this.row = row;
			   			StaticTables = -1;
			   			user.removeAllLists();
			   			wc.getMusicMenu().getBarLists().enableTableCellSelection();
			   			((JButton)value).doClick();			   			
			   			this.row = row;			   			
			   			PlayListDetails PD = new PlayListDetails();
						PD.setPlayListName(user.getLists().get(row).getNom());
						SC.sendInfo(PD);
			   		}
		   		}
		  
	    	}
 
	   if(StaticTables!=-1){
		   int column2 = wc.valueColumn(StaticTables, arg0.getX());
		   int row2 = arg0.getY()/wc.valueRow(StaticTables); 	
				if(arg0.getSource().equals(wc.getMusicMenu().getListSongs().getPanells().get(StaticTables).getTablesongs())){
					if (row2 < wc.SongsListRowCount(StaticTables) && row2 >= 0 && column2 < wc.SongsListColumnCount(StaticTables) && column2 >= 4){
						Object value2 = wc.SongsListValueAt(StaticTables, row2, column2);

						if (value2 instanceof JButton) {
							if(column2 ==5){
									click = row2;
									wc.getMusicMenu().getBarSearch().setSingle(user.ChooseList(StaticTables).getSongs().get(click).getName());
									wc.getMusicMenu().getBarSearch().setArtist(user.ChooseList(StaticTables).getSongs().get(click).getArtist());
									wc.getMusicMenu().getBarSearch().setLlista(StaticTables);
									wc.getMusicMenu().getBarSearch().setSong(click);
									
									PlaySong h = new PlaySong(user.ChooseList(StaticTables).getSongs().get(row2).getName(),user.ChooseList(StaticTables).getSongs().get(row2).getArtist());
									SC.sendInfo(h);
																		
								}else if(wc.getMusicMenu().getPlayBar().getPlay().isSelected()){
											if(StaticTables == wc.getMusicMenu().getBarSearch().getLlista()){
												if(wc.getMusicMenu().getBarSearch().getSong()==row2){
													wc.getMusicMenu().getPlayBar().getPlay().setSelected(false);
													}else{
														wc.getMusicMenu().getListSongs().getPanells().get(wc.getMusicMenu().getBarSearch().getLlista()).getTablesongs().changeSelection(wc.getMusicMenu().getBarSearch().getSong(), 5, false, false);												
														click = row2;
														wc.getMusicMenu().getBarSearch().setSingle(user.ChooseList(StaticTables).getSongs().get(click).getName());
														wc.getMusicMenu().getBarSearch().setArtist(user.ChooseList(StaticTables).getSongs().get(click).getArtist());
														wc.getMusicMenu().getBarSearch().setLlista(StaticTables);
														wc.getMusicMenu().getBarSearch().setSong(click);
													}
												}
									}
							}
							if(column2 == 6){
								PlayTable = row2;
								sAction = "del";
								wc.getMusicMenu().ShowPopupOptions2(user.getLists(), user.ChooseList(StaticTables).getNom(),arg0.getX(), arg0.getY(), StaticTables,this);
		
						}

							if(column2 == 4){
								sAction = "up";
								wc.getMusicMenu().ShowPopupOptions(user.getLists(), user.ChooseList(StaticTables).getNom(),arg0.getX(), arg0.getY(), StaticTables,this);
								PlayTable = row2;
								}
						}
					}
	   }else{
		   int column2 = wc.valueColumn(3, arg0.getX());
		   int row2 = arg0.getY()/wc.valueRow(3); 	
				if(arg0.getSource().equals(wc.getMusicMenu().getListSongs().getPanells().get(3).getTablesongs())){
					if (row2 < wc.SongsListRowCount(3) && row2 >= 0 && column2 < wc.SongsListColumnCount(3) && column2 >= 4){
						Object value2 = wc.SongsListValueAt(3, row2, column2);

						if (value2 instanceof JButton) {
							//PLAY
							if(column2 ==5){
									click = row2;
									wc.getMusicMenu().getBarSearch().setSingle(user.getLists().get(this.row).getSongs().get(click).getName());
									wc.getMusicMenu().getBarSearch().setArtist(user.getLists().get(this.row).getSongs().get(click).getArtist());
									wc.getMusicMenu().getBarSearch().setLlista(this.row);
									wc.getMusicMenu().getBarSearch().setSong(click);
									
									PlaySong h = new PlaySong(user.getLists().get(this.row).getSongs().get(row2).getName(),user.getLists().get(this.row).getSongs().get(row2).getArtist());
									SC.sendInfo(h);
																		
								}else if(wc.getMusicMenu().getPlayBar().getPlay().isSelected()){
											if(this.row == wc.getMusicMenu().getBarSearch().getLlista()){
												if(wc.getMusicMenu().getBarSearch().getSong()==row2){
													wc.getMusicMenu().getPlayBar().getPlay().setSelected(false);
													}else{
														wc.getMusicMenu().getListSongs().getPanells().get(wc.getMusicMenu().getBarSearch().getLlista()).getTablesongs().changeSelection(wc.getMusicMenu().getBarSearch().getSong(), 5, false, false);
												
														click = row2;
														wc.getMusicMenu().getBarSearch().setSingle(user.getLists().get(this.row).getSongs().get(click).getName());
														wc.getMusicMenu().getBarSearch().setArtist(user.getLists().get(this.row).getSongs().get(click).getArtist());
														wc.getMusicMenu().getBarSearch().setLlista(this.row);
														wc.getMusicMenu().getBarSearch().setSong(click);
													}
											}
									}
							}
							if(column2 == 6){
								PlayTable = row2;
								sAction = "del";
								wc.getMusicMenu().ShowPopupOptions2(user.getLists(),user.getLists().get(this.row).getNom(),arg0.getX(), arg0.getY(), 3,this);
		
						}

							if(column2 == 4){
								sAction = "up";								
								wc.getMusicMenu().ShowPopupOptions(user.getLists(),user.getLists().get(this.row).getNom(),arg0.getX(), arg0.getY(), 3,this);
								PlayTable = row2;
								}
						}
					}
	   }

		if(isCreating && wc.getMusicMenu().getBarLists().isTextListFocused() && !wc.getMusicMenu().getBarLists().getTextList().equals("New PlayList")){
			user.CreateNewlist(wc.getMusicMenu().getBarLists().getTextList());
			
			wc.getMusicMenu().getBarLists().hidePanelToNewPlayList();
			
			wc.refreshTable();
		}
		else{
			wc.getMusicMenu().getBarLists().hidePanelToNewPlayList();
		}
		
		
	
		}
	
	/**
	 * catch all movements of the mouse Entered
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	/**
	 * catch all actions of the mouse exited
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	/**
	 * catch all actions of the mouse pressed
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	/**
	 * catch all actions of mouse released
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	/**
	 * catch all actions of key pressed
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	/**
	 * catch all actions of key released
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER && wc.getMusicMenu().getBarLists().isRequestFocusEnabled() && !wc.getMusicMenu().getBarLists().getTextList().equals("New PlayList")){
			EditPlayList EP = new EditPlayList();
			
			if(user.getLists().isEmpty()){
				user.CreateNewlist(wc.getMusicMenu().getBarLists().getTextList());
				ListTableModel table = new ListTableModel(user.getLists().get(0));
				wc.getMusicMenu().getListSongs().CreatePanelList(user.getLists().get(0).getNom(), table, this);
				EP.setAction("add");
				EP.setName(user.getLists().get(0).getNom());
				EP.setSong(null);
				
			}else{
				int row =user.CreateNewlist(wc.getMusicMenu().getBarLists().getTextList());
				EP.setAction("add");
				//file -1 ; 0-1=-1
				EP.setName(user.getLists().get(row-1).getNom());
				EP.setSong(null);
			}

			wc.getMusicMenu().getBarLists().hidePanelToNewPlayList();
			wc.refreshTable();
			isCreating = false;			
			SC.sendInfo(EP);
		}
	}
	/**
	 * catch all actions of key typed
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * This method entries on the client interface
	 */
	public void showUserConnected() {
		wc.getCards().show(wc.getPanellPrincipal(), "2");
		
	}
	/**
	 * this method returns Window Client
	 * @return wc is the WindowClient
	 */
	public WindowClient getWc() {
		return wc;
	}
	/**
	 * this method sets is the Window Client
	 * @param wc is the WindowClient
	 */
	public void setWc(WindowClient wc) {
		this.wc = wc;
	}
	/**
	 * this method returns PlaylistsTableModel 
	 * @return ListTableModel is the PlaylistsTableModel
	 */
	public PlaylistsTableModel getListTableModel() {
		return ListTableModel;
	}
	/**
	 * this method sets the PlaylistsTableModel
	 * @param listTableModel is the the PlaylistsTableModel
	 */
	public void setListTableModel(PlaylistsTableModel listTableModel) {
		ListTableModel = listTableModel;
	}
	
	/**
	 * this method returns User 
	 * @return user is the User
	 */
	public User getUser() {
		return user;
	}
	/**
	 * this method sets the User
	 * @param user is the the User
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * this method returns ServerCommunication 
	 * @return SC is the ServerCommunication
	 */
	public ServerCommunication getSC() {
		return SC;
	}
	/**
	 * this method sets the ServerComunication
	 * @param sC is the the ServerComunication
	 */
	public void setSC(ServerCommunication sC) {
		SC = sC;
	}
	/**
	 * this method returns Integer of StaticTables 
	 * @return Statictables is int
	 */
	public int getStaticTables() {
		return StaticTables;
	}
	
	/**
	 * this method sets the StaticTables
	 * @param staticTables is Integer
	 */
	public void setStaticTables(int staticTables) {
		StaticTables = staticTables;
	}
	/**
	 * this method returns PlayMusic 
	 * @return pm is the PlayMusic
	 */
	public PlayMusic getPm() {
		return pm;
	}
	/**
	 * this method sets the PlayMusic
	 * @param pm is the the PlayMusic
	 */
	public void setPm(PlayMusic pm) {
		this.pm = pm;
	}
	/**
	 * sellect next song
	 */
	public void nextMusic() {
		if(StaticTables!=-1){
			click++;
			if(wc.getMusicMenu().getListSongs().getPanells().get(StaticTables).getTablesongs().getRowCount() <= click) click = 0;
			PlaySong h = new PlaySong(user.ChooseList(StaticTables).getSongs().get(click).getName(),user.ChooseList(StaticTables).getSongs().get(click).getArtist());
			SC.sendInfo(h);
		}else{
			click++;
			if(wc.getMusicMenu().getListSongs().getPanells().get(3).getTablesongs().getRowCount() <= click) click = 0;
			PlaySong h = new PlaySong(user.getLists().get(this.row).getSongs().get(click).getName(),user.getLists().get(this.row).getSongs().get(click).getArtist());
			SC.sendInfo(h);
		}
	}
	/**
	 * sellect previous song
	 */
	public void previousMusic() {
		if(StaticTables!=-1){
			click--;
			if(click <  0) click = wc.getMusicMenu().getListSongs().getPanells().get(StaticTables).getTablesongs().getRowCount()-1;
			PlaySong h = new PlaySong(user.ChooseList(StaticTables).getSongs().get(click).getName(),user.ChooseList(StaticTables).getSongs().get(click).getArtist());
			SC.sendInfo(h);
		}else{
			click--;
			if(click < 0) click = wc.getMusicMenu().getListSongs().getPanells().get(3).getTablesongs().getRowCount()-1;
			PlaySong h = new PlaySong(user.getLists().get(this.row).getSongs().get(click).getName(),user.getLists().get(this.row).getSongs().get(click).getArtist());
			SC.sendInfo(h);
		}
	}
	
}
