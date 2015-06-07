package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * Class that creates a custom model for the tables used in Server
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CustomTableModel extends AbstractTableModel {
	/**Variables*/
	private String[] columnNames;
	private ArrayList<String[]> data;
	
	/**Constants*/
	private static final String[] sColumnUG = {"Username","Date of Register",
        "Last Access","Total Lists","Total Songs"," "};
	private static final String [] sColumnMG = {"Nom","Genere","Album","Artista"," "};
	private static final String [] sColumnPL = {"Nom","Genere","Album","Artista"};

	/**
	 * Constructor
	 * @param iTable Integer with the id of the table we want to create, either users or songs
	 * @param data ArrayList of String[] with the data to add in the tables
	 */
	public CustomTableModel(int iTable, ArrayList<String[]> data) {
		if (iTable == 0) {
			this.columnNames = sColumnUG;
		} else if (iTable == 1){
			this.columnNames = sColumnMG;
		} else {
			this.columnNames = sColumnPL;
		}
		this.data = data;
	}

	@Override
	public int getRowCount() {
		return data.size();

	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int iRow, int iColumn) {
		return data.get(iRow)[iColumn];
	}
	
	/**
	 * Function that adds a new row to the table
	 * @param sNewRow String[] with the information of the new row
	 */
	public void setNewData(String[] sNewRow) {
		data.add(sNewRow);
		fireTableRowsInserted(data.size()-1,data.size()-1);
	}
	
	@Override 
	public boolean isCellEditable(int row, int col) {
		if (col == getColumnCount()-1) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getColumnName(int index) {
	    return columnNames[index];
	}
	
	/**
	 * Function that removes a row from the table
	 * @param row Integer that indicates the row to be deleted 
	 */
	public void removeRow(int row) {
        data.remove(row);
        fireTableRowsDeleted(row,row);
    }

}
