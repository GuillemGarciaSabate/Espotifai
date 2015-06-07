package model;

import java.io.Serializable;

/**
 * The class PlaySong permits to send a song to the client to play it
 * 
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlaySong extends Stream implements Serializable{
	/**Variables*/
	private String name;
	private String artist;
	private long size;
	private byte[] binarySong;
	
	/**
	 * Constructor
	 * @param name String with the name of the song
	 * @param artist String with the artist of the song
	 */
	public PlaySong(String name, String artist){
		this.name = name;
		this.artist = artist;
	}

	/**
	 * Getter of the name of a song
	 * 
	 * @return name is the name of the song
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the name of the song
	 * 
	 * @param name is the name of the song
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the name of the artist
	 * 
	 * @return artist is the name of the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * This method sets the name if the artist
	 * 
	 * @param artist is the name of the artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * This method get the size of the song to send
	 * 
	 * @return size is the number of bytes that the song sizes
	 */
	public long getSize() {
		return size;
	}

	/**
	 * This method sets the size of a song
	 * 
	 * @param l is the number of bytes of a song
	 */
	public void setSize(long l) {
		this.size = l;
	}
	
	/**
	 * This method get a song in bytes
	 * 
	 * @return a song 
	 */
	public byte[] getBinarySong() {
		return binarySong;
	}

	/**
	 * This method sets a song
	 * 
	 * @param binarySong is the song in bytes
	 */
	public void setBinarySong(byte[] binarySong) {
		this.binarySong = binarySong;
	}

}