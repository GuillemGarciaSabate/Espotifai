package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.ButtonColumn;
import model.CustomTableModel;
import model.TableColumnAdjuster;

import controller.ButtonsController;
import controller.RowController;

/**
 * Class responsible for creating the panel of UserGestion,
 * which contains all the getter, setter, update and assing controllers
 * functions.
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class UserGestion extends JPanel {
	/**Variables*/
	private ButtonsController bc;
	/**Swing and AWT Components*/
	private JTable jtTable;
	private CustomTableModel ctmTable;
	
	/**
	 * Constructor
	 * @param iTable Integer that indicates which table we want to generate, either user or songs
	 * @param data ArrayList with the data to add to our table
	 */
	public UserGestion(int iTable, ArrayList<String[]> data) {
		ctmTable = new CustomTableModel(iTable, data);
		jtTable = new JTable(ctmTable);
		JScrollPane jspTable = new JScrollPane(jtTable);
		
		
		Action deleteUG = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        jtTable = (JTable)e.getSource();
		        int modelRow = Integer.valueOf(e.getActionCommand());
		        String sLogin = (String) ((CustomTableModel)jtTable.getModel()).getValueAt(modelRow,0);
		        ((CustomTableModel)jtTable.getModel()).removeRow(modelRow);
		        bc.deleteUserDB(sLogin);
		    }
		};
		
		ButtonColumn buttonColumn = new ButtonColumn(jtTable, deleteUG, 5);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		jtTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(jtTable);
		tca.adjustColumns();
		
		jtTable.setPreferredScrollableViewportSize(jtTable.getPreferredSize());
		jtTable.setFillsViewportHeight(true);
	
		this.setLayout(new GridLayout(1,1));
		this.add(jspTable);
	}
	
	/**
	 * Returns the dimensions of the table
	 * @return Dimension with the dimensions of the table
	 */
	public Dimension getTableSize() {
		return jtTable.getPreferredSize();
	}
	
	/**
	 * Method that adds Controllers to our table and buttons
	 * @param rc RowController that we will add to our table
	 * @param bc ButtonsController to add to our buttons
	 */
	public void setControllers(ButtonsController bc, RowController rc) {
		this.bc = bc;
		jtTable.addMouseListener(rc);
	}

	/**
	 * Inserts a new info to our table, and adjusts the table according to the new info
	 * @param sNewRow String[] with the new info to add to our table
	 */
	public void insertData(String[] sNewRow) {
		ctmTable.setNewData(sNewRow);
		jtTable.setPreferredScrollableViewportSize(jtTable.getPreferredSize());
		TableColumnAdjuster tca = new TableColumnAdjuster(jtTable);
		tca.adjustColumns();	
	}

}
