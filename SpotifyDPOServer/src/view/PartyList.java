package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.ButtonsController;
import controller.RowController;

import model.CustomTableModel;
import model.TableColumnAdjuster;


/**
 * Class responsible of creating the panel of PartyList,
 * which also contains the functions of getter, setter,
 * update and assign controllers to its elements
 * Clase responsable de crear el panel de Party List, 
 * la cual tambien contiene las funciones de getter, setter, 
 * actualizar y de asignar controladores a sus elementos
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PartyList extends JPanel {
	/**Variables*/
	private CustomTableModel ctmTable;
	private JTable jtTable;
	private JButton jbDeletePL;

	/**
	 * Constructor
	 * @param iTable Integer that indicates which table we want to generate, either user or songs
	 * @param dataPL ArrayList with the data to add to our table
	 */
	public PartyList(int iTable, ArrayList<String[]> dataPL) {
		this.setLayout(new BorderLayout());
		ctmTable = new CustomTableModel(iTable, dataPL);
		jtTable = new JTable(ctmTable);
		JScrollPane jspTable = new JScrollPane(jtTable);
		jbDeletePL = new JButton("Delete");
		JPanel jpButton = new JPanel(new FlowLayout());
		jpButton.add(jbDeletePL);
		JPanel jpTable = new JPanel(new GridLayout(1,1));
		jpTable.add(jspTable);
		
		jtTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(jtTable);
		tca.adjustColumns();
		
		jtTable.setPreferredScrollableViewportSize(jtTable.getPreferredSize());
		jtTable.setFillsViewportHeight(true);
	
		this.add(jpTable,BorderLayout.CENTER);
		this.add(jpButton,BorderLayout.PAGE_END);
	}
	
	/**
	 * Method that adds Controllers to our table and buttons
	 * @param rc RowController that we will add to our table
	 * @param bc ButtonsController to add to our buttons
	 */
	public void setControllers(RowController rc, ButtonsController bc) {
		jtTable.addMouseListener(rc);
		jbDeletePL.addActionListener(bc);
		jbDeletePL.setActionCommand("DELETEPL");
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

	/**
	 * Returns the dimensions of the table
	 * @return Dimension with the dimensions of the table
	 */
	public Dimension getTableSize() {
		return jtTable.getPreferredSize();
	}
	
	/**
	 * Deletes the first row in the table. User for Party List only
	 */
	public void deleteFirstRow() {
		if (jtTable.getRowCount() > 0) ((CustomTableModel)jtTable.getModel()).removeRow(0);
	}
}
