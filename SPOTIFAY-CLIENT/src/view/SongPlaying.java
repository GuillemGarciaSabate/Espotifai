package view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class that creates the bottom left section of the screen that shows current music playing
 * 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class SongPlaying extends JPanelBackground {
	/**Swing and AWT Components*/
	private JLabel jlSong;
	private JLabel jlsingle;
	private JLabel jlArtist;
	private JLabel jlimg;
	
	/**Variables*/
	private int iLlista = 0;
	private int iSong = 0;
	
	/**Constants*/
	private static final String imgSongPlay = "./img/SongPlaying.png";
	private static final String imgDefaultPicture = "./img/DefaultPicture.png";
	
	/**
	 * Constructor
	 */
	public SongPlaying(){
		this.setBackground(imgSongPlay);
		this.setLayout(new GridBagLayout());
		this.CreateActualSong();
		this.PlaysContentsInFrame();
	}
	
	/**
	 * Creates all Swing and AWT Components needed for this panel
	 */
	private void CreateActualSong(){
		ImageIcon image = new ImageIcon (imgDefaultPicture);
		jlimg = new JLabel(image);
		
		jlSong = new JLabel("Playing");
		jlSong.setFont(new java.awt.Font("Futura (Light)",0, 15));
		jlSong.setForeground(new Color(200,200,200));
		
		jlsingle = new JLabel ("Song: nothing");
		jlsingle.setFont(new java.awt.Font("Futura (Light)",0, 12));
		
		jlArtist = new JLabel("Artist: nothing");
		jlArtist.setFont(new java.awt.Font("Futura (Light)",0, 12));
	}
	
	/**
	 * Places all Swing and AWT Components in the panel
	 */
	private void PlaysContentsInFrame(){	
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10,5,5,60);
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 2; 
		constraints.weighty = 0.0;
		this.add(jlSong,constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 2; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 0.0;
		this.add(jlimg,constraints);
		
		constraints.insets = new Insets(10,5,10,0);
		constraints.gridx = 1; 
		constraints.gridy = 2; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 0.0;
		this.add(jlsingle,constraints);
		
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 1; 
		constraints.gridy = 3; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.weighty = 0.0;
		this.add(jlArtist,constraints);
	}

	/**
	 * Setter of the name of the Single
	 * @param sName New name of the Single
	 */
	public void setSingle(String sName) {
		jlsingle.setText(sName);	
	}
	
	/**
	 * Setter the name of the artist
	 * @param sArtist New name of the artist
	 */
	public void setArtist(String sArtist) {
		jlArtist.setText(sArtist);
	}
	
	/**
	 * Getter of the position of the list of the song that is playing
	 * @return Index of the location of the list
	 */
	public int getLlista() {
		return iLlista;
	}
	
	/**
	 * Setter of the position of the list of the song that is playing
	 * @param iLlista Integer with the new location of the list
	 */
	public void setLlista(int iLlista) {
		this.iLlista = iLlista;
	}
	
	/**
	 * Setter of the position of the song in the list that is playing
	 * @param iSong Integer with the new location of the song
	 */
	public void setSong(int iSong) {
		this.iSong = iSong;
	}
	
	/**
	 * Getter of the position of the song in the list that is playing
	 * @return Index of the location of the song
	 */
	public int getSong(){
		return iSong;
	}


}
