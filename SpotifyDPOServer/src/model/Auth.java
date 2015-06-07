package model;

import java.io.Serializable;

/**This class contains the attributes filled by the user when he is at
 * loggin, it also has a boolean attribute which turns to true if the user
 * is found on the system,
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Auth extends Stream implements Serializable{
	/**Variables*/
	private String name;
	private String password;
	private boolean found = false;

	/**
	 * Constructor
	 */
	public Auth(){

	}

	/**
	 * Returns the name used by the user
	 * 
	 * @return String with the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the user name
	 * 
	 * @param name String with the name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the password used by the user
	 * 
	 * @return String with the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the user password
	 * 
	 * @param password String with the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns the result of the user authentication
	 * @return Boolean that indicates the result of the authentication
	 */
	public boolean getFound() {
		return found;
	}

	/**
	 * Sets the result of the user authentication
	 * 
	 * @param found Boolean which indicates if user was found in login or not
	 */
	public void setFound(boolean found) {
		this.found = found;
	}

}
