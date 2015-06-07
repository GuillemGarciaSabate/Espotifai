package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The class DataBaseAccess manages the information from the DataBase.
 * It allows to do different operations related with users, songs, PlayLists...
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class DataBaseAccess {
	/**Constants*/
	private static final String DB_URL = "jdbc:mysql://localhost:3306/spotifydb";
	private static final String USER = "root";
	private static final String PASS = "";
	
	/**
	 * This method inserts a new user to the database
	 * 
	 * @param sLog String with the name of the user to insert on the database
	 * @param sPsw String with the password of the user to insert on the database
	 * @return Boolean that indicates if the operation has went well
	 */
	public boolean insertUser(String sLog, String sPsw) {
		
		boolean bDone = false;
		DateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String sDate = dfFormat.format(date);
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT COUNT(*) FROM user WHERE login = '"+sLog+"'";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	    	if (rs.getInt(1) == 0) {
	    		bDone = true;
	    		sql = "INSERT INTO user(login,password,date_register,last_access)" +
						" VALUES ('"+sLog+"','"+sPsw+"','"+sDate+"','"+sDate+"')";
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
	    	}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
					return bDone;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * This method gets the URL location of the song in Server
	 * 
	 * @param sNameSong is the name of the song to find
	 * @return String with the path of the location of where is the song
	 */
	public String getURLMusic(String sNameSong){
		String result = "";
		PreparedStatement stmt = null;
    	ResultSet rs = null;
    	Connection conn = null;
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT URL FROM song WHERE name_song = '"+sNameSong+"'";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
        	try {
        		if (rs.next()) {
        			result = rs.getString("URL");
        		}
				if (conn != null && stmt != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
 		}
    	return result;
    }
	
	/**
	 * This method gets all the favorite songs of a user
	 * 
	 * @param name that is the name of the user from we want to get the favorite songs
	 * @return ArrayList with the name of all the favorites songs of the user
	 */
	public ArrayList<String[]> getMusicsFromFavorites(String name){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] newline;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    ResultSet rs2 = null;
	    Connection conn = null;
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT id_user FROM user WHERE login = '"+name+"'";
			stmt = conn.prepareStatement(sql);
		    rs = (ResultSet) stmt.executeQuery(sql);
		    rs.next();
		    int iduser = rs.getInt(1);
		    sql = "SELECT id_song FROM usersonglist WHERE id_user = "+iduser+" AND name_list <> 'partylist'";
			stmt = conn.prepareStatement(sql);
		    rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				newline = new String[5];
				//iLine.add(rs.getInt(1));
				int pos = rs.getInt(1);
				sql = "SELECT * FROM song WHERE id_song <> 0 AND id_song = "+pos+"";
				stmt = conn.prepareStatement(sql);
			    rs2 = (ResultSet) stmt.executeQuery(sql);
			    while(rs2.next()) {
				    newline[0] = rs2.getString("name_song");
				    newline[1] = rs2.getString("genre");
				    newline[2] = rs2.getString("album");
				    newline[3] = rs2.getString("artist");
				    newline[4] = "Delete";
				    result.add(newline);
			    }
			}
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
	        try {
				if (conn != null && stmt != null) {
					conn.close();
					return result;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 	} return result;
    }
	
	/**
	 * This method makes a request to the system to get from it all the available songs
	 * 
	 * @return ArrayList of String[] that contains the information of all the songs in the system
	 */
	public ArrayList<String[]> getMusics(){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] line = null;
		PreparedStatement stmt = null;
    	ResultSet rs = null;
    	Connection conn = null;
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT * FROM song";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
        	try {
        		while (rs.next()) {
        			line = new String[5];
        			line[0] = rs.getString("name_song");
        			line[1] = rs.getString("genre");
        			line[2] = rs.getString("album");
        			line[3] = rs.getString("artist");
        			line[4] = "Delete";
        			result.add(line);
        		}
				if (conn != null && stmt != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
 		}
    	return result;
    }

	/**
	 * This method make a request to the DB to get all the registered users from the system
	 * 
	 * @return ArrayList of String[] that contains all the attributes from the user
	 */
	public ArrayList<String[]> getUsers() {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] line = null;
		PreparedStatement stmt = null;
    	ResultSet rs = null;
    	Connection conn = null;
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT * FROM user";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
        	try {
        		while (rs.next()) {
        			line = new String[6];
        			line[0] = rs.getString("login");
        			line[1] = rs.getString("date_register");
        			line[2] = rs.getString("last_access");
        			line[3] = rs.getString("num_songlists");
        			line[4] = rs.getString("num_songs");
        			line[5] = "Delete";
        			result.add(line);
        		}
				if (conn != null && stmt != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
 		}
    	return result;
	}
	
	/**
	 * This method is responsible of checking if a user has logged correctly or not
	 * 
	 * @param sUser String with the name of the user who wants to log in 
	 * @param sPsw String with the password from the user
	 * @return Boolean that returns if the log in was successful or not
	 */
	public boolean getAuth(String sUser, String sPsw) {
		boolean bDone = false;
		PreparedStatement stmt = null;
    	ResultSet rs = null;
    	Connection conn = null;
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT COUNT(*) FROM user WHERE login = '"+sUser+"' AND password = '"+sPsw+"'";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	    	if (rs.getInt(1) == 1) {
	    		bDone = true;
	    		DateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy");
	    		Date date = new Date();
	    		String sDate = dfFormat.format(date);
	    		sql = "UPDATE user SET last_access = '"+sDate+"' " +
	    				"WHERE login = '"+sUser+"' AND password = '"+sPsw+"'";
	    		stmt = conn.prepareStatement(sql);
	    		stmt.executeUpdate();
	    	}
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
        	try {
				if (conn != null && stmt != null) {
					conn.close();
					return bDone;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
 		}
    	return false;
	}

	/**
	 * Function that adds a music to the favorites table. Not used in the program
	 * @param sName String with the name of the song
	 * @param sGenre String with the genre of the song
	 * @param sAlbum String with the album of the song
	 * @param sArtist String with the artist of the song
	 * @param sURL String with the URL of the song
	 * @return Boolean that confirms if the insert was successful or not
	 */
	public boolean insertMusicToFavorites(String sName, String sGenre, String sAlbum,String sArtist, String sURL) {
		boolean bDone = false;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT COUNT(*) FROM favorites WHERE name_song = '"+sName+"'";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
			rs.next();
			if (rs.getInt(1) == 0) {
				sql = "INSERT INTO favorites(name_song,genre,album,artist,URL)" +
						" VALUES ('"+sName+"','"+sGenre+"','"+sAlbum+"','"+sArtist+"','"+sURL+"')";
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate(sql);
				bDone = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
					return bDone;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return false;
	}
	
	/**
	 * This method should insert a song into the DB, so every user can download it
	 * 
	 * @param sName String with the song name
	 * @param sGenre String with the gender of the song
	 * @param sAlbum String with the album of the song
	 * @param sArtist String with the artist of the song
	 * @param sURL String with the URL of the file
	 * @return Boolean that indicates if insertMusic was successful or not
	 */
	public boolean insertMusic(String sName, String sGenre, String sAlbum,String sArtist, String sURL) {
		boolean bDone = false;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT COUNT(*) FROM song WHERE name_song = '"+sName+"'";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
			rs.next();
			if (rs.getInt(1) == 0) {
				sql = "INSERT INTO song(name_song,genre,album,artist,URL)" +
						" VALUES ('"+sName+"','"+sGenre+"','"+sAlbum+"','"+sArtist+"','"+sURL+"')";
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate(sql);
				bDone = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
					return bDone;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return false;
	}

	/**
	 * This method deletes a song from the database
	 * 
	 * @param sName String with the name of the song we want to delete
	 */
	public void deleteMusic(String sName) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT id_song FROM song WHERE name_song = '"+sName+"'";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
			rs.next();
			int idsong = rs.getInt(1);
			sql = "DELETE FROM song WHERE name_song = '"+sName+"'";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			sql = "DELETE FROM usersonglist WHERE id_song = "+idsong;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			sql = "UPDATE user u SET num_songs = ("
					+"SELECT COUNT(*) FROM usersonglist usl WHERE usl.id_user = u.id_user "
					+"AND name_list <> 'partylist' AND id_song <> 0)";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	/**
	 * This method delete a user from the database
	 * 
	 * @param sLogin String with the name of the user to be deleted
	 */
	public void deleteUser(String sLogin) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT id_user FROM user WHERE login = '"+sLogin+"'";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
			rs.next();
			int iduser = rs.getInt(1);
			sql = "DELETE FROM user WHERE login = '"+sLogin+"'";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			sql = "DELETE FROM usersonglist WHERE id_user = "+iduser;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	/**
	 * This method counts all the music available on the database
	 * 
	 * @return Integer with the number of songs
	 */
	public int getCountMusic() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int iTotal = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT COUNT(*) FROM song";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
			rs.next();
			iTotal = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
					return iTotal;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} return 0;
	}

	/**
	 * This method is used to check which are the five most played songs.
	 * The result of the request is saved on the given arrays in the parameter.
	 * 
	 * @param iSongs Integer[] in which we put the number of reproductions of the top 5 songs
	 * @param sSongs String[] in which we put the name of the songs in the top 5
	 */
	public void getTop5(int[] iSongs, String[] sSongs) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int iTotal = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT COUNT(*) FROM song";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
			rs.next();
			iTotal = rs.getInt(1);
			if (iTotal > 5) iTotal = 5;
			sql = "SELECT name_song,num_plays FROM song ORDER BY num_plays DESC LIMIT 5";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
			for (int i = 0; i < iTotal; i++) {
				rs.next();
				iSongs[i] = rs.getInt("num_plays");
				sSongs[i] = rs.getString("name_song");
				if (sSongs[i].length() > 5) sSongs[i] = sSongs[i].substring(0,5)+"...";
			}
			for (int j = iTotal; j < 5; j++) {
				rs.next();
				iSongs[j] = 0;
				sSongs[j] = "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method receives the name of a user and his new PlayList and adds it to the database
	 * 
	 * @param uName String with the name of the user who wants to do the action
	 * @param listName String with the name of the list which you want to add
	 * 
	 * @return Boolean that confirms if the operation was successful or not
	 */
	public boolean insertNewPlayList(String uName, String listName) {
		boolean result = false;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT id_user FROM User WHERE login = '"+uName+"'";
			stmt = conn.prepareStatement(sql);
			    rs = (ResultSet) stmt.executeQuery(sql);
			rs.next();
			int iduser = rs.getInt(1);
				
			sql = "SELECT COUNT(*) FROM UserSongList WHERE name_list = '"+listName+"' AND id_user = '"+iduser+"'";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
			rs.next();
			int iTotal = rs.getInt(1);
				
			if(iTotal == 0){
				sql = "INSERT INTO UserSongList(name_list,id_user,id_song) VALUES ('"+listName+"','"+iduser+"',0)";
				stmt = conn.prepareStatement(sql);
			    stmt.executeUpdate(sql);
			    if (!listName.equals("partylist")){
			    	sql = "UPDATE user SET num_songlists = num_songlists+1 WHERE id_user = "+iduser;
			    	stmt = conn.prepareStatement(sql);
				    stmt.executeUpdate(sql);
			    }
			    result = true;
			    	
			}
		} catch (SQLException ex) {
		    ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
					return result;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} return result;
	}

	/**
	 * This method receives the name of a user and shall return an ArrayList
	 * containing the name from all his playLists.
	 * 
	 * @param name String with the name of the user that you want to get the PlayLists
	 * @return ArrayList of String that contains the name of every PlayLists of this user
	 */
	public ArrayList<String> getPlayLists(String name) {
		ArrayList<String> result = new ArrayList<String>();	
		String line = "";
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
		
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT id_user FROM User WHERE login = '"+name+"'";
			stmt = conn.prepareStatement(sql);
		    rs = (ResultSet) stmt.executeQuery(sql);
			rs.next();
			int iduser = rs.getInt(1);
			sql = "SELECT name_list FROM UserSongList WHERE id_user = '"+iduser+"'";
			stmt = conn.prepareStatement(sql);
		    rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
	        	line = rs.getString("name_list");
	        	result.add(line);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
	        try {
				if (conn != null && stmt != null) {
					conn.close();
					return result;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 	} return result;
	}
	
	/**
	 * This method receives the name of a PlayList and shall return an ArrayList 
	 * containing the name from all his songs
	 * 
	 * @param name String with the name of the list which you want to get the songs from
	 * @return ArrayList of String[] this attribute contains every song from the PlayList
	 */
	public ArrayList<String[]> getPlayListsDetails(String name) {
	 	ArrayList<String[]> result = new ArrayList<String[]>();
		String[] line = null;
		ArrayList<Integer> id_song = new ArrayList<Integer>();
		int iLine;
		PreparedStatement stmt = null;
    	ResultSet rs = null;
    	Connection conn = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT id_song FROM usersonglist WHERE name_list = '"+name+"'";
			stmt = conn.prepareStatement(sql);
		    rs = (ResultSet) stmt.executeQuery(sql);
		    while (rs.next()) {
	        	iLine = rs.getInt("id_song");
	        	
	        	id_song.add(iLine);
	        }  
		    for(int i = 0; i < id_song.size(); i++){
		    	sql = "SELECT * FROM song WHERE id_song = "+id_song.get(i);
				stmt = conn.prepareStatement(sql);
			    rs = (ResultSet) stmt.executeQuery(sql);
			    while (rs.next()) {
        			line = new String[4];
        			line[0] = rs.getString("name_song");
        			line[1] = rs.getString("genre");
        			line[2] = rs.getString("album");
        			line[3] = rs.getString("artist");
        			if(name.equals("partylist")){
            			line = new String[5];
            			line[0] = rs.getString("name_song");
            			line[1] = rs.getString("genre");
            			line[2] = rs.getString("album");
            			line[3] = rs.getString("artist");
        				line[4] = "Delete";
 
        			}
        			result.add(line);
        		}
			    
		    }
		    return result;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
        	try {
				if (conn != null && stmt != null) {
					conn.close();
					return result;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
 		}
    	return result;
	}

	/**
	 * This method is called when we need to add a new song into an existing PlayList
	 * 
	 * @param EP EditPlayList with all the information of the song and the name of the PlayList to add it
	 * @param uName String with the name of the user that adds a song to the PlayList
	 * @return Boolean that confirms if the process was successful or not
	 */
	public boolean addSongToPlaylist(String uName, EditPlayList EP) {
		boolean bDone = false;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT id_user FROM user WHERE login = '"+uName+"'";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	    	int identificadorUser = rs.getInt(1);
	    	
	    	String[] songAsArray = EP.getSong().split("%");
	    	sql = "SELECT id_song FROM song WHERE name_song = '"+songAsArray[0]+"'";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	    	int identificadorSong = rs.getInt(1);
	    	
	    	sql = "SELECT COUNT(*) FROM usersonglist WHERE id_song = '"+identificadorSong+"' AND id_user = '"+identificadorUser+"' AND name_list = '"+EP.getName()+"'";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	    	int found = rs.getInt(1);
	    	
	    	if(EP.getName().equals("partylist") || found == 0){
	    		sql = "INSERT INTO usersonglist (name_list, id_user, id_song)" + 
	  	    		  "VALUES ('"+EP.getName()+"', '"+identificadorUser+"', '"+identificadorSong+"')";
	  			stmt = conn.prepareStatement(sql);
	  	    	stmt.executeUpdate(sql);
	  	    	if (!EP.getName().equals("partylist")) {
	  	    		sql = "UPDATE user SET num_songs = num_songs+1 WHERE id_user = "+identificadorUser;
	  	  			stmt = conn.prepareStatement(sql);
	  	  	    	stmt.executeUpdate(sql);
	  	    	}
	  	    	return true;
	    	}
	    	return false;
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
					return bDone;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * This method receives the name of a song and check if this one exists on the DB
	 * 
	 * @param songName String with the name of the song to check the existence
	 * @return Boolean that confirms if the song is found or not
	 */
	public boolean checkSongExistence(String songName) {
		boolean bDone = false;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String[] songAsArray = songName.split("%");
	    	String sql = "SELECT id_song FROM song WHERE name_song = '"+songAsArray[0]+"'";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	  
	    	int identificadorSong = rs.getInt(1);
	    	
	    	sql = "SELECT COUNT(*) FROM usersonglist WHERE id_song = '"+identificadorSong+"'";
			stmt = conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	    	int found = rs.getInt(1);
	    	if(found == 1){
	    		return true;
	    	}else{
	    		return false;
	    	}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
					return bDone;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * This method increases the number of reproductions attribute from the DB to a song
	 * every time some user plays this song
	 * 
	 * @param sNom String with the name of the played song
	 * @param sArtist String with the name of the artist
	 */
	public void increaseNumPlays(String sNom, String sArtist) {
		PreparedStatement stmt = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
	    	String sql = "UPDATE song SET num_plays = num_plays+1 WHERE name_song = '"+sNom
	    			+"' AND artist = '"+sArtist+"'";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is called when we want to delete a song from an existing PlayList
	 * 
	 * @param uName String with the name of the user to delete the song from the PlayList
	 * @param plName String with the name of the PlayList
	 * @param song String with the name of the song
	 * @return Boolean that confirms if the process was successful or not
	 */
	public boolean deleteSongFromPlayList (String uName, String plName, String song) {
		boolean bDone = false;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT id_user FROM user WHERE login = '"+uName+"'";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	    	int identificadorUser = rs.getInt(1);
	    	
	    	String[] songAsArray = song.split("%");
	    	sql = "SELECT id_song FROM song WHERE name_song = '"+songAsArray[0]+"'";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	  
	    	int identificadorSong = rs.getInt(1);
	    	
	    	sql = "DELETE FROM usersonglist WHERE name_list = '"+plName+"' AND id_song = '"+identificadorSong+"' AND id_user = '"+identificadorUser+"'";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			sql = "UPDATE user SET num_songs = num_songs-1 WHERE id_user = "+identificadorUser;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
	    	
	    	return true;
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
					return bDone;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * This method is done to delete a PlayList from a user when needed
	 * 
	 * @param uName String with the name of the user who wants to delete a PlayList
	 * @param sName String with the name of the PlayList to delete
	 */
	public void deletePlayList(String uName, String sName) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT id_user FROM user WHERE login = '"+uName+"'";
			stmt = conn.prepareStatement(sql);
	    	rs = (ResultSet) stmt.executeQuery(sql);
	    	rs.next();
	    	int identificadorUser = rs.getInt(1);
			sql = "DELETE FROM usersonglist WHERE name_list = '"+sName+"' AND id_user = '"+identificadorUser+"'";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			sql = "UPDATE user u SET num_songlists = (SELECT COUNT(DISTINCT name_list) "
					+ "FROM usersonglist usl WHERE name_list <> 'partylist' AND usl.id_user = u.id_user)";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			sql = "UPDATE user u SET num_songs = ("
					+"SELECT COUNT(*) FROM usersonglist usl WHERE usl.id_user = u.id_user "
					+"AND name_list <> 'partylist' AND id_song <> 0)";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql); 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	/**
	 * Function that deletes the first row from party list
	 */
	public void deletePartyListSong() {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "DELETE FROM usersonglist WHERE name_list = 'partylist' LIMIT 1";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql); 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && stmt != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
