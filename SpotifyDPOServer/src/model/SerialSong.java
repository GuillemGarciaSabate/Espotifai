package model;

import java.io.Serializable;

/**
 * The class SerialSong has the attributes of a song
 * 
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SerialSong extends Stream implements Serializable {
	/**Variables*/
	private String name;
	private String album;
	private String gender;
	private String artist;
	private String URL;
	
	/**Constructor*/
	public SerialSong(){
		
	}
	
	/**
	 * This method gets the name of a song
	 * 
	 * @return song is the name of the song
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the name of a song
	 * 
	 * @param name is the name of the song
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the name of an album
	 * 
	 * @return album that is the name of the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * This method sets the name of an album
	 * 
	 * @param album is the album of the song
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * This method gets the genre of a song
	 * 
	 * @return gender is the genre of a song
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * This method sets the gender of an song
	 * 
	 * @param gender is the gender of the song
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * This method gets the name of an artist
	 * 
	 * @return artist is the name of the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * This method sets the name of an artist
	 * 
	 * @param artist is the name of the artist of a song
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * This method puts all the attributes of a song in a String in a specific form 
	 * 
	 * @return out is a String with the attributes if a song in a specific form 
	 */
	public String toString() {
		String out = "";
		out=name+"---"+album+"---"+gender+"---"+artist;
		return out;
	}

	/**
	 * This method gets the name of an URL (oath of the song)
	 * 
	 * @return URL is the URL of where is the song located
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * This method sets the name of an URL of a song
	 * 
	 * @param uRL is the name of the URL of a song
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}
	
}
