package model;

import javax.swing.JButton;

import javax.swing.table.DefaultTableModel;
/**This class has to create the table that will display the playlists of every user
* @author Albert Trias Torroglosa
* @author Daniel Mateu Elizalde
* @author Guillem Garcia Sabater
* @author Jordi Badia Iglesias
* @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class PlaylistsTableModel extends DefaultTableModel{

	private final String[] columnNames = {"PLAYLISTS"};
	
	private final Class<?>[] ColumnTypes = {
			javax.swing.JButton.class
	};
	
	private User user;
	
	public PlaylistsTableModel(User user){
		this.user = user;
		//setValueAt((String)"CreatePlayList",0,1);
	}
	/**This method it's used to get the number of columns of the view
	 * @return int this is the number of columns
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	/**this method gets the number of rows
	 * @return int it's the number of rows
	 */
	@Override
	public int getRowCount() {
		if(user == null){
			return 0;
		} else {
			//System.out.println(user.getLists().size());
			return user.getLists().size();
		}
		
	}
	/**
	 * this gets the number of column checked
	 * @param col it's the column
	 * @return String it's the name of the column
	 */
	public String getColumnName(int col) {
		//System.out.println(columnNames[col]);

		return columnNames[col];
	}

	/**
	 * This method it's used to get the Class from the clicked column
	 * @param col number of the column
	 * @return the Class from this column
	 */
	public Class<?> getColumnClass(int col){
		//System.out.println(","+ col); 
		return ColumnTypes[col];		
	}
	/**
	 * This method it's used to get the value from the clicked position
	 * @param row the row
	 * @param col the column
	 * @return Object this is the object that you clicked
	 */
	public Object getValueAt(int row,int col){
		switch(col)	{
		case 0 :	//System.out.println(row +","+ col); 
					//System.out.println(user.getLists().get(row).getButton()); 
					return user.getLists().get(row).getButton(); 
						
			
		default : return null;
		}
	}
	/**
	 * sets an Object into a cell
	 * @param row it's the row
	 * @param column the column
	 * @param nomlist it's the object to set
	 */
	public void setValueAt(Object nomlist,int row, int column){
		switch (column) {
		case 0 : 
			
			user.updateNameList(row, (JButton)nomlist);
			//System.out.println("3"); 
			
		}
	}
	
	/**check if the clicked item it's a clickable element
	 * @param row the row
	 * @param col the column
	 * @return false
	 */
	public boolean isCellEditable (int row,int col){
		return false;
	}
}