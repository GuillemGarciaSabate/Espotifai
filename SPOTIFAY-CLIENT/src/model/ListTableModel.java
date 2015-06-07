package model;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 * The class ListTableModel manage the column and row clicked by a user in the graphical interface
 *  
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */

@SuppressWarnings("serial")
public class ListTableModel extends DefaultTableModel {
	
	private final String[] columnNames = {"ID","Image","Songs","genre","ADD","PLAY","DELETE"};
	
	
	
	private final Class<?>[] ColumnTypes = {
		
			javax.swing.JLabel.class,
			javax.swing.JLabel.class,
			javax.swing.JLabel.class,
			javax.swing.JLabel.class,
			javax.swing.JButton.class,
			javax.swing.JButton.class,
			javax.swing.JButton.class,
	
	};
	
	private List list;
	/**
	 * Constructor
	 * 
	 * @param list is the List
	 */

	public ListTableModel(List list){
		
		this.list = list;
	}
	/**
	 * This method gets the number of columns
	 * 
	 * @return columnNames.length is the number of columns
	 */
	public int getColumnCount() {
		return columnNames.length;
	}
	/**
	 * This method gets the number of rows
	 * 
	 * @return list.getSongs().size() is the number of rows
	 */
	@Override
	public int getRowCount() {
		if(list == null){
			return 0;
		} else {
			return list.getSongs().size();
		}
		
	}
	/**
	 * This method gets the name of a column
	 * 
	 * @return columnNames[col] is the name of a column
	 */

	public String getColumnName(int col) {

		return columnNames[col];
	}
	/**
	 * This method gets a column value
	 * 
	 * @return columnNames[col] is the column returned
	 */
	public Class<?> getColumnClass(int col){
		return ColumnTypes[col];
		
	}
	/**
	 * This method it's done to return the value at some specific cell
	 * @param row it's the row
	 * @param col it's the column
	 * @return Object it's the value of the clicked cell
	 */
	public Object getValueAt(int row,int col){

		JButton Delete = new JButton();
		JButton play = new JButton();
		JButton Add = new JButton();

		switch(col)	{
		case 0 :	 
					return row;
		case 1 :
					return new ImageIcon("./img/DefaultPicture.png");
		case 2 :
			 		return list.getSongs().get(row).getName() +"%"+ list.getSongs().get(row).getAlbum() +"%"+ list.getSongs().get(row).getArtist();
		case 3 : 
					return list.getSongs().get(row).getGender();
		case 4 :
					return Add;
		case 5 :			
					return play;
			
		case 6 : 	return Delete;
		
		
		default : return null;
		}

	}
	/**This method sets a value into a cell
	 * @param nomlist it's the object to set in that cell
	 * @param row it's the row
	 * @param column it's the column
	 */
	@SuppressWarnings("unused")
	public void setValueAt(Object nomlist,int row, int column){
		JButton play = new JButton();
		JButton Delete = new JButton();
		JButton add = new JButton();

		switch (column) {
		case 0 : row ++;
				
		case 1 :
		
		case 2 :
			list.getSongs().get(row).setName((String)nomlist);
			
		case 3 : 
			list.getSongs().get(row).setGender((String)nomlist);
		
		case 4 :
				Delete = ((JButton)nomlist);
		case 5 :
				play = ((JButton)nomlist);
		case 6 :
				add = ((JButton)nomlist);
		
			
		}
	}
	/**
	 * This method check if a Cell it's editable
	 * @param row it's the row
	 * @param col it's the column
	 * @return false
	 */
	public boolean isCellEditable (int row,int col){
		return false;
	}

	
}



