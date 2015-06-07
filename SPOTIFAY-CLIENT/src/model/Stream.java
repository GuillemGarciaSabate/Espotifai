package model;

import java.io.Serializable;

/**
 * The class Stream indicates the type of object that we sent
 *  
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Stream implements Serializable{
	/**Variables*/
	protected int id;
	
	/**Constructor*/
	public Stream(){
		
	}

	/**
	 * This method gets the id of the stream
	 * 
	 * @return id is the id of the stream
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method sets the id of a stream
	 * 
	 * @param id is the identifier of the stream
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
