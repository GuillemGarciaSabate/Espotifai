package model;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.SwingConstants;
/**
 * this Class implements a list of songs
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
public class List {
	
	private String nom;
	private LinkedList<SerialSong> songs;
	private int num;
	private JButton Button;
	/**
	 * Constructor of the class
	 * @param nom name of the list
	 */
	public List(String nom){
		this.Button = new JButton(nom);
		this.Button.setFont(new java.awt.Font("Century Gothic",0, 14));
		this.Button.setForeground(new Color(150,100,100));
		this.Button.setHorizontalTextPosition(SwingConstants.CENTER);
		this.Button.setVerticalTextPosition(SwingConstants.CENTER);
		this.nom = nom;
		num = 0;
		songs = new LinkedList<SerialSong>();
		
	}
	/**
	 * This method creates new song and add this on the dynamic list
	 * @param song SerialSong is the serializable class that contents the information of the song
	 */
	public void CreateNewSong(SerialSong song){
		
		boolean found = false;
		
		for(int i = 0 ; i <songs.size();i++){
			if(songs.get(i).getName().equals(song.getName())){
				found = true;
			}
		}
		
		if(!found || nom.equals("Partylist")){
			System.out.println("createnewsong: "+song.getName());
			SerialSong aux = new SerialSong();
			aux.setName(song.getName());
			aux.setArtist(song.getArtist());
			aux.setAlbum(song.getAlbum());
			aux.setGender(song.getGender());
			aux.setId(song.getId());
			
			songs.add(aux);
		}
		
		
	}
	/**
	 * This method remove all songs in the dynamic list
	 */
	public void RemoveAllSongs(){
		songs.removeAll(songs);

	}
	/**
	 * This method get the name of the list
	 * 
	 * @return nom is the name of a list
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * This method sets the name of a List
	 * 
	 * @param nom is the name of the list
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * This method get the songs of the list
	 * 
	 * @return songs is the dynamic list
	 */
	public LinkedList<SerialSong> getSongs() {
		return songs;
	}
	/**
	 * This method sets the songs of a list
	 * 
	 * @param songs is the dynamic list
	 */
	public void setSongs(LinkedList<SerialSong> songs) {
		this.songs = songs;
	}
	/**
	 * This method get the number of a list
	 * 
	 * @return num is the number of list
	 */
	public int getNum() {
		return num;
	}
	/**
	 * This method sets the number of the list
	 * 
	 * @param num is an Integer that indicates the number of the list
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * This method get the Button of a list
	 * 
	 * @return Button is the button of the list
	 */
	public JButton getButton() {
		return Button;
	}
	/**
	 * This method sets the Button of a list
	 * @param button is the button of the list
	 */
	public void setButton(JButton button) {
		Button = button;
	}
	
	

}
