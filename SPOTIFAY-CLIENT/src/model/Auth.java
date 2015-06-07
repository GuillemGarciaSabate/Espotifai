package model;

import java.io.Serializable;
/**
 * This class contains the attributes filled by the user when he is at
 * loggin, it also has a boolean attribute which turns to true if the user
 * is found on the system.
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 * 
 */
@SuppressWarnings("serial")
public class Auth extends Stream implements Serializable{
	
	private String name;
	private String password;
	private boolean found = false;

	public Auth(){
		
	}
	/**
	 * Returns the name used by the user
	 * @return String the name of the user 
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the user name
	 * @param name user name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the password used by the user
	 * @return String the password of the user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Set the user password
	 * @param password user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Returns the result of the user authentification
	 * @return found return boolean if the user is found
	 */
	public boolean getFound() {
		return found;
	}
	/**
	 * Sets the result of the user authentification
	 * @param found a boolean which indicate the result
	 * of the operation
	 */
	public void setFound(boolean found) {
		this.found = found;
	}
	
	

}

