package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import controller.BottonsController;
import model.List;
import model.ListTableModel;

/**
 * Class that generates the panel with the list of songs of a playlist, favorites or partylist
 * 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class ListPanel extends JPanel {
	/**Swing and AWT Component*/
	private JLabel jlPlaylist;
	private JLabel jlNomList;
	private JPopupMenu popup;
	private JPopupMenu popup2;
	
	/**Constants*/
	private static final String sURLILM = "./img/IconListMusic.png";
	private static final String sURLIPL = "./img/IconPartylist.png";
	private static final String sURLIR = "./img/IconRemove.png";

	/**Table*/
	private MyTableSongs tablesongs;
	
	/**
	 * Constructor
	 * @param sName String with the name of the playlist
	 * @param listablemodel Tablemodel to apply to our panel
	 */
	public ListPanel(String sName, ListTableModel listablemodel){
		tablesongs = new MyTableSongs(listablemodel);
		
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
		
		popup2 = new JPopupMenu();
		popup2.setBackground(new Color(120,120,120));
		
		popup = new JPopupMenu();
		popup.setBackground(new Color(120,120,120));
		
		this.createPlayListTitle();
		this.createLabelNameList(sName);
		this.placeElementsToPanel();
	}
	
	
	/**
	 * Creates the label that contains the name of the PlayList
	 * @param sName Content of the label we want to create
	 */
	private void createLabelNameList(String sName) {
		jlNomList = new JLabel();
		jlNomList.setText(sName);
		jlNomList.setFont(new java.awt.Font("Futura (Light)",0, 30));
		jlNomList.setForeground(new Color(0,0,0));
	}

	/**
	 * Creates the label with the title playlist
	 */
	private void createPlayListTitle() {
		jlPlaylist = new JLabel("PLAYLIST");
		jlPlaylist.setFont(new java.awt.Font("Century Gothic",0, 15));
		jlPlaylist.setForeground(new Color(0,0,0));
	}

	/**
	 * Places all Swing and AWT elements inside the panel
	 */
	private void placeElementsToPanel() {
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.insets = new Insets(10,20,20,100);
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 0.0;		
		this.add(jlPlaylist,constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 1; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 0.0;	
		this.add(jlNomList,constraints);

		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridx = 0; 
		constraints.gridy = 3; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1; 
		constraints.weighty = 1.0;
		constraints.insets = new Insets(20,0,0,0);
		this.add(tablesongs,constraints);
	}

	/**
	 * Function that creates a popup with the playlist we currently are and another for partylist to allow
	 * the user to remove songs from them
	 * 
	 * @param lists LinkedList with the list that we will use to generate our options in our popup
	 * @param sActualList Name of the playlist we currently are seeing
	 * @param x Position x where our popup will appear
	 * @param y Position y where our popup will appear
	 * @param controlador Controller that we will use to assign Listeners to our JMenuItems
	 */
	public void ShowPopupOptions(LinkedList<List> lists, String sActualList,
			int x, int y, BottonsController controlador){
		ImageIcon image = new ImageIcon(sURLILM);
		ImageIcon image2 = new ImageIcon(sURLIPL);
		
		popup.removeAll();
		
		for(int i = 0; i < lists.size(); i++){
			if(!lists.get(i).getNom().equals(sActualList)){
				JMenuItem Item = new JMenuItem();
				Item.setIcon(image);
				Item.setRolloverIcon(image);
				Item.setText(lists.get(i).getNom());
				Item.setBackground(new Color(120,120,120));
				Item.setRolloverEnabled(false);
				Item.setFont(new java.awt.Font("Century Gothic",0, 12));
				Item.setForeground(new Color(250,250,250));
				Item.addActionListener(controlador);
				Item.setActionCommand(lists.get(i).getNom());
				popup.add(Item);
			}
		}
		
		popup.addSeparator();
		
		JMenuItem Item2 = new JMenuItem();
		Item2.setIcon(image2);
		Item2.setRolloverIcon(image2);
		Item2.setText("Partylist");
		Item2.setBackground(new Color(120,120,120));
		Item2.setRolloverEnabled(false);
		Item2.setFont(new java.awt.Font("Century Gothic",0, 12));
		Item2.setForeground(new Color(250,250,250));
		Item2.addActionListener(controlador);
		Item2.setActionCommand("Partylist");
		popup.add(Item2);
		popup.show(this,x,y);
	}

	
	/**
	 * Function that creates a popup with the playlist we currently are and another for partylist to allow
	 * the user to remove songs from them
	 * 
	 * @param lists LinkedList with the list that we will use to generate our options in our popup
	 * @param ActualList Name of the playlist we currently are seeing
	 * @param x Position x where our popup will appear
	 * @param y Position y where our popup will appear
	 * @param controlador Controller that we will use to assign Listeners to our JMenuItems
	 */
	public void ShowPopupOptions2(LinkedList<List> lists, String ActualList,
			int x, int y, BottonsController controlador){
		ImageIcon image = new ImageIcon(sURLIR);
	
		popup2.removeAll();
		
		for(int i = 0; i < lists.size(); i++){
			JMenuItem Item = new JMenuItem();
			Item.setIcon(image);
			Item.setRolloverIcon(image);
			Item.setText(lists.get(i).getNom());
			Item.setBackground(new Color(120,120,120));
			Item.setRolloverEnabled(false);
			Item.setFont(new java.awt.Font("Century Gothic",0, 12));
			Item.setForeground(new Color(250,250,250));
			Item.addActionListener(controlador);
			Item.setActionCommand(lists.get(i).getNom());
			popup2.add(Item);
			
		}

		popup2.show(this,x,y);	
	}

	/**
	 * Draws the line that separates playlist and songs
	 */
	public void paint(Graphics g){
        super.paint(g);

        g.setColor (Color.BLACK);
        g.drawLine (0, 120, 1200, 120);
    }
	
	/**
	 * Function that indicates the value of the pressed column
	 * @param iColumn Column we want to get its value
	 * @return Integer with the position of the pressed column
	 */
	public int valueColumn(int iColumn){
		 return tablesongs.getColumnModel().getColumnIndexAtX(iColumn);
	}
	
	/**
	 * Function that indicates the value of the pressed row
	 * @return Integer with the position of the pressed row
	 */
	public int valueRow(){
		 return tablesongs.getRowHeight();
	}
	
	
	/**
	 * Setter of the name of the playlist
	 * @param sName String with the new name of the playlist
	 */
	public void setNomlist(String sName) {
		jlNomList.setText(sName);
	}

	public MyTableSongs getTablesongs() {
		return tablesongs;
	}
}
