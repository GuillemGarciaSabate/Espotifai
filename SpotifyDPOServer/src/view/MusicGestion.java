package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.ButtonColumn;
import model.CustomTableModel;
import model.TableColumnAdjuster;

import controller.ButtonsController;
import controller.RowController;

/**
 * Class responsible of creating the panel of MusicGestion
 * which contains the functions of getter, setter,
 * update and assign controllers to its elements
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MusicGestion extends JPanel {
	/**Variables*/
	private ButtonsController bc;
	private JTable jtTable;
	private CustomTableModel ctmTable;
	
	/**Swing and AWT Components*/
	private JTextField jtfNom;
	private JTextField jtfGenere;
	private JTextField jtfAlbum;
	private JTextField jtfArtista;
	private JTextField jtfChooser;
	private JButton jbChooser;
	private JButton jbAddMusic;
	private JButton jbStats;
	
	/**
	 * Constructor
	 * @param iTable Integer that indicates which table we want to generate, either user or songs
	 * @param data ArrayList with the data to add to our table
	 */
	public MusicGestion(int iTable, ArrayList<String[]> data) {
		this.setLayout(new GridLayout(2,1));
		ctmTable = new CustomTableModel(iTable, data);
		jtTable = new JTable(ctmTable);
		JScrollPane jspTable = new JScrollPane(jtTable);
		
		Action deleteMG = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        jtTable = (JTable)e.getSource();
		        int modelRow = Integer.valueOf(e.getActionCommand());
		        String sNom = (String) ((CustomTableModel)jtTable.getModel()).getValueAt(modelRow,0);
		        ((CustomTableModel)jtTable.getModel()).removeRow(modelRow);
		        bc.deleteMusicDB(sNom);
		        bc.updateGraph();
		
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(jtTable, deleteMG, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		jtTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(jtTable);
		tca.adjustColumns();
		
		jtTable.setPreferredScrollableViewportSize(jtTable.getPreferredSize());
		jtTable.setFillsViewportHeight(true);
			
		this.add(jspTable);
		
		this.addPanelSelectMusic();

	}
	
	/**
	 * Method that adds the song panel form in which we will add a new song
	 */
	private void addPanelSelectMusic() {
		JPanel jpAddMusic = new JPanel(new GridLayout(6,1));
		jpAddMusic.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY), "Afegir Música"));
		
		JLabel jlGenere = new JLabel("Genere");
		JPanel jpGenere = new JPanel(new FlowLayout());
		jtfGenere = new JTextField();
		jtfGenere.setPreferredSize(new Dimension(200,20));
		jpGenere.add(jlGenere);
		jpGenere.add(jtfGenere);
		JLabel jlNom = new JLabel("Nom");
		jlNom.setPreferredSize(jlGenere.getPreferredSize());
		JPanel jpNom = new JPanel(new FlowLayout());
		jtfNom = new JTextField();
		jtfNom.setPreferredSize(new Dimension(200,20));
		jpNom.add(jlNom);
		jpNom.add(jtfNom);
		JLabel jlAlbum = new JLabel("Album");
		jlAlbum.setPreferredSize(jlGenere.getPreferredSize());
		JPanel jpAlbum = new JPanel(new FlowLayout());
		jtfAlbum = new JTextField();
		jtfAlbum.setPreferredSize(new Dimension(200,20));
		jpAlbum.add(jlAlbum);
		jpAlbum.add(jtfAlbum);
		JLabel jlArtista = new JLabel("Artista");
		jlArtista.setPreferredSize(jlGenere.getPreferredSize());
		JPanel jpArtista = new JPanel(new FlowLayout());
		jtfArtista = new JTextField();
		jtfArtista.setPreferredSize(new Dimension(200,20));
		jpArtista.add(jlArtista);
		jpArtista.add(jtfArtista);
		JPanel jpChooser = new JPanel(new FlowLayout());
		jtfChooser = new JTextField();
		jtfChooser.setPreferredSize(new Dimension(200,20));
		jbChooser = new JButton("...");
		jbChooser.setPreferredSize(new Dimension(40,18));
		JPanel jpButtons = new JPanel(new FlowLayout());
		jbAddMusic = new JButton("Afegir");
		jbAddMusic.setPreferredSize(new Dimension(80,18));
		jbStats = new JButton("Stats");
		jbStats.setPreferredSize(new Dimension(80,18));
		jpChooser.add(jtfChooser);
		jpChooser.add(jbChooser);
		jpButtons.add(jbAddMusic);
		jpButtons.add(jbStats);
		
		jpAddMusic.add(jpNom);
		jpAddMusic.add(jpGenere);
		jpAddMusic.add(jpAlbum);
		jpAddMusic.add(jpArtista);
		jpAddMusic.add(jpChooser);
		jpAddMusic.add(jpButtons);
		
		this.add(jpAddMusic);
		
	}

	/**
	 * Method that adds Controllers to our buttons and table
	 * @param rc RowController that we will add to our table
	 * @param bc ButtonsController that we will add to our buttons
	 */
	public void setControllers(RowController rc, ButtonsController bc) {
		this.bc = bc;
		jtTable.addMouseListener(rc);
		jbChooser.addActionListener(bc);
		jbChooser.setActionCommand("...");
		jbAddMusic.addActionListener(bc);
		jbAddMusic.setActionCommand("ADDMUSIC");
		jbStats.addActionListener(bc);
		jbStats.setActionCommand("STATS");
	}
	
	/**
	 * Inserts a new info to our table, and adjusts the table according to the new info
	 * @param sNewRow String[] with the new info to add to our table
	 */
	public void insertData(String[] sNewRow) {
		ctmTable.setNewData(sNewRow);
		TableColumnAdjuster tca = new TableColumnAdjuster(jtTable);
		tca.adjustColumns();
	}
	
	/**
	 * Returns the dimensions of the table
	 * @return Dimension with the dimensions of the table
	 */
	public Dimension getTableSize() {
		return jtTable.getPreferredSize();
	}

	/**
	 * Setter of the song path
	 * @param sPath String with the path to add to our JTextField
	 */
	public void setTypedPath(String sPath) {
		jtfChooser.setText(sPath);
	}

	/**
	 * Getter of the name song
	 * @return Returns the contents of the name text field
	 */
	public String getNameSong() {
		return jtfNom.getText();
	}

	/**
	 * Getter of the genre song
	 * @return Returns the contents of the genre text field
	 */
	public String getGenreSong() {
		return jtfGenere.getText();
	}

	/**
	 * Getter of the album song
	 * @return Returns the contents of the album text field
	 */
	public String getAlbumSong() {
		return jtfAlbum.getText();
	}

	/**
	 * Getter of the artist song
	 * @return Returns the contents of the artist text field
	 */
	public String getArtistSong() {
		return jtfArtista.getText();
	}

	/**
	 * Getter of the URL of the song
	 * @return Returns the contents of the URL text field
	 */
	public String getURLSong() {
		return jtfChooser.getText();
	}
	
}
