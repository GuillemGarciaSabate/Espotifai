package network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class SongSender is used to send a song from the server to a client
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class SongSender {
	/**Variables*/
	private byte[] fileContent;
	private File file;
	private FileInputStream fis;
	
	/**
	 * Constructor
	 * 
	 * @param URL String with the path of the chosen song
	 */
	public SongSender(String URL){
		file = new File("music/"+URL);
		//file = new File(URL);
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fileContent = new byte[(int)file.length()];
	}

	/**
	 * This method sends the song to a client 
	 * 
	 * @return byte[] that contains the song in bytes to send
	 */
	public byte[] sendStream(){
		
		try {
			fis.read(fileContent);
			return fileContent;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

