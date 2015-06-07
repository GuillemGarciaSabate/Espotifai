package model;

import java.io.Serializable;

/**
 * The class SignUP contains the attributes if a user
 *  
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SignUP extends Stream implements Serializable{
	/**Variables*/
	private String email;
	private String name;
	private String pwd;
	private boolean done;
	
	/**Constructor*/
	public SignUP(){
		done = false;
	}
	
	/**
	 * This method gets if a user is inserted in  the database
	 * 
	 * @return done is a boolean that indicate if a user is inserted in  the database
	 */
	public boolean getDone() {
		return done;
	}

	/**
	 * This method sets if a user is inserted in the database
	 * 
	 * @param done is a boolean that indicate if a user is inserted to the database
	 */
	public void setDone(boolean done) {
		this.done = done;
	}

	/**
	 * This method gets the email of a user
	 * 
	 * @return email is the email of a user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method sets the email of a user
	 * 
	 * @param email is the email of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method gets the name of a user
	 * 
	 * @return name is the name of a user
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the name of a user
	 * 
	 * @param name is the name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the password of a user
	 * 
	 * @return pwd is the password of a user
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * This method sets the password of a user
	 * 
	 * @param pwd is the password of the user
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
