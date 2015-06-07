package view;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.lang.Object;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.UIResource;

import javax.swing.table.TableCellRenderer;



import model.ListTableModel;
/**
 * This class that extends of JTable and contains all logic system of the sellection cells and the image
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 * 
 */
@SuppressWarnings("serial")
public class MyTableSongs extends JTable{
	
    private int lastcolumn=-1;
	private int lastrow=-1;
	private int lastcolumn2=-1;
	private int lastrow2=-1;
	private Map <Integer, Set<Integer>> selectedCells = new HashMap<Integer, Set<Integer>>();
	private int PlayTable = -1;

	/**
	 * The constructor of the class 
	 * @param listablemodel Structure of the table songs
	 */
	public MyTableSongs(ListTableModel listablemodel){	

		this.setModel(listablemodel);
		this.setOpaque(false);
		this.setFocusable(false);
		this.setAutoCreateRowSorter(true);
		this.setCellSelectionEnabled(true);
		this.setRowHeight(55);
		this.getColumn("ID").setPreferredWidth(50);
		this.getColumn("Image").setPreferredWidth(55);
		this.getColumn("Songs").setPreferredWidth(500);
		this.getColumn("genre").setPreferredWidth(150);
		this.setGridColor(new Color(200,200,200));
		RolloverMouseAdapter rollover = new RolloverMouseAdapter(this);
		
		this.getColumn("ID").setCellRenderer(new JTableIDRenderer(rollover));
		this.getColumn("Image").setCellRenderer(new JTableImageRenderer(rollover));
		this.getColumn("Songs").setCellRenderer(new JTableSongRenderer(rollover));
		this.getColumn("genre").setCellRenderer(new JTableGenderRenderer(rollover));
		this.getColumn("ADD").setCellRenderer(new JTableButtonAddRenderer(rollover));
		this.getColumn("PLAY").setCellRenderer(new JTableButtonPlayRenderer(rollover));
		this.getColumn("DELETE").setCellRenderer(new JTableButtonDeleteRenderer(rollover));
		
		this.setRowSelectionAllowed(false);
		this.setColumnSelectionAllowed(false);
		this.setCellSelectionEnabled(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
		this.setIntercellSpacing(new Dimension(10,0));
		this.setShowHorizontalLines(true);
		this.setShowVerticalLines(false);
	}
	/**
	 * method that refresh the list of songs of the table
	 */
	public void refreshTable() {
		this.setPreferredSize(new Dimension(800,this.getHeight()+60));
		SwingUtilities.updateComponentTreeUI(this);

	}
	
	/**
	 * method that sets the vaulue of height of a row
	 * @param row number of the row
	 * @param rowHeight height to set in a row
	 */
	 @Override
	    public void setRowHeight(int row, int rowHeight) {
	        int oldRowHeight = getRowHeight(row);
	        super.setRowHeight(row, rowHeight);
	        // Fire the row changed
	        firePropertyChange("singleRowHeight", oldRowHeight, row);
	    }
	 	/**
	 	 * Looks the status of sellection cell of the columns 4, 5 and 6
	 	 * @param rowIndex number of the row we want to change
	 	 * @param columnIndex number of the column we want to change
	 	 * @param toggle boolean
	 	 * @param extend boolean
	 	 */
	    @Override
	    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
	
	    	if(columnIndex == 5){
	    		 ButtonPlay(rowIndex,columnIndex,toggle,extend);
	    	}else if(columnIndex== 4 || columnIndex == 6){
	    		ButtonADDTo(rowIndex,columnIndex,toggle,extend);
	    		}
	    		
	    }
	    
	    /**
	     * Change the status button play of column 5
	     * @param rowIndex number of the row we want to change
	 	 * @param columnIndex number of the column we want to change
	     * @param toggle boolean
	     * @param extend boolean
	     */
	    public void ButtonPlay(int rowIndex, int columnIndex, boolean toggle, boolean extend){
	    	if (isCellSelected(rowIndex, columnIndex) && !extend && selectedCells.containsKey(rowIndex)) {
    			selectedCells.get(rowIndex).remove(columnIndex);
    			lastcolumn = -1;
    			lastrow = -1;
    			PlayTable = -1;
    			ButtonADDTo(lastrow2,lastcolumn2,false,false);
    		} else {
    			if (!toggle && !extend) {
    				ButtonADDTo(lastrow2,lastcolumn2,false,false);
    				if(lastcolumn!=-1 && lastrow!=-1){
    					selectedCells.get(lastrow).remove(lastcolumn);
    				}
    				PlayTable = rowIndex;
    				lastcolumn = columnIndex;
    				lastrow = rowIndex;
    				addCellToSelection(rowIndex, columnIndex);
    				selectionModel.addSelectionInterval(rowIndex, rowIndex);
	    			selectedCells.get(rowIndex).add(columnIndex);
    			}
    		}
	    	SwingUtilities.updateComponentTreeUI(this);
	    }
	    
	    /**
	     * Change the status button play of column 6 or 4
	     * @param rowIndex number of the row we want to change
	 	 * @param columnIndex number of the column we want to change
	     * @param toggle boolean
	     * @param extend boolean
	     */
	    public void ButtonADDTo(int rowIndex,int columnIndex,boolean toggle,boolean extend){
	    	if (isCellSelected(rowIndex, columnIndex) && !extend && selectedCells.containsKey(rowIndex)) {
    			System.out.println("HOLA MOOON");
    
    			selectedCells.get(rowIndex).remove(columnIndex);
    			lastcolumn2 = -1;
    			lastrow2 = -1;
    		} else {
    			if (!toggle && !extend) {
    		
    				if(lastcolumn2!=-1 && lastrow2!=-1){
    					selectedCells.get(lastrow2).remove(lastcolumn2);
    				}
    				lastcolumn2 = columnIndex;
    				lastrow2 = rowIndex;
    				addCellToSelection(rowIndex, columnIndex);
    				selectionModel.addSelectionInterval(rowIndex, rowIndex);
	    			selectedCells.get(rowIndex).add(columnIndex);
    			}
    		}
	    	SwingUtilities.updateComponentTreeUI(this);
	    }

	    /**
	     * sets the cell that has been selected
	     * @param row Number of row
	     * @param column Number of column
	     */
	    private void addCellToSelection(int row, int column) {
	        Set<Integer> selectedColumns = selectedCells.get(row);
	        if (selectedColumns == null) {
	            selectedColumns = new TreeSet<Integer>();
	            selectedCells.put(row, selectedColumns);
	        }
	        selectedColumns.add(column);
	    }

	    /**
	     * add row selected
	     * @param index0 Integer from which row we start selecting
	     * @param index1 Integer from which row we end selecting
	     */
	    @Override
	    public void addRowSelectionInterval(int index0, int index1) {
	    	for (int i = index0; i <= index1; i++) {
	            selectedCells.remove(i);
	        }
	        selectedCells.remove(index0);
	        getColumnModel().getSelectionModel().addSelectionInterval(0, getColumnCount() - 1);
	        super.addRowSelectionInterval(index0, index1);
	    }
	    /**
	     * remove row selected
	     * @param index0 Integer from which row we start not selecting
	     * @param index1 Integer from which row we stop not selecting
	     */
	    @Override
	    public void removeRowSelectionInterval(int index0, int index1) {
	        for (int i = index0; i <= index1; i++) {
	            selectedCells.remove(i);
	        }
	        super.removeRowSelectionInterval(index0, index1);
	    }
	    /**
	     * method that  sellect all cells
	     */
	    @Override
	    public void selectAll() {
	        selectedCells.clear();
	        super.selectAll();
	    }
	    /**
	     * method that desellect all cells
	     */
	    @Override
	    public void clearSelection() {
	        if (selectedCells != null) {
	            selectedCells.clear();
	        }
	        super.clearSelection();
	    }
	    /**
	     * method that returns if the cell is sellected
	     * @param row Number of the row
	     * @param column Number of the column
	     */
	    @Override
	    public boolean isCellSelected(int row, int column) {
	        if (!getSelectionModel().isSelectedIndex(row)) {
	            return false;
	        }
	        if (getSelectionModel().isSelectedIndex(row) && selectedCells.get(row) == null) {
	            return true;
	        }
	        return selectedCells.get(row).contains(column);
	    }

	    
	   
	/**
	 * Inner Class that paints the cell of the table with image
	 */
	private static class JTableImageRenderer extends JCheckBox implements TableCellRenderer,UIResource {        


		private JLabel image;
		private  ImageIcon image1 = new ImageIcon("./img/DefaultPicture.png");


		public JTableImageRenderer(RolloverMouseAdapter rollover){
			super();
			image = new JLabel(image1);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){

			return image;
		}
		
	}
	/**
	 * Inner Class that paints the cell of the table with JLabel for name of the song
	 */
	
	private static class JTableSongRenderer extends JCheckBox implements TableCellRenderer,UIResource {        
		
		private JLabel Song;
		private JLabel Album;
		private JLabel Single;

		public JTableSongRenderer(RolloverMouseAdapter rollover){
			super();
			Song = new JLabel();
			Song.setFont(new java.awt.Font("Arial Rounded MT Bold",0, 14));
			Song.setHorizontalTextPosition(SwingConstants.CENTER);
			Song.setVerticalTextPosition(SwingConstants.CENTER);
			Song.setOpaque(false);
			
			Single = new JLabel();
			Single.setVerticalTextPosition(SwingConstants.CENTER);
			Single.setOpaque(false);
			Single.setLayout(new GridLayout(2,1));
			
			Album = new JLabel();
			Album.setFont(new java.awt.Font("Helvetica Neue UltraLight",0, 13));
			Album.setForeground(new Color(50,50,50));
			Album.setHorizontalTextPosition(SwingConstants.CENTER);
			Album.setVerticalTextPosition(SwingConstants.CENTER);
			Album.setOpaque(false);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			
			String[] aux;
			aux = ((String)value).split("%");
			Song.setText(aux[0]);
			Album.setText(aux[1]+" · "+aux[2]);
			Single.add(Song);
			Single.add(Album);
			return Single;
		}       	
	}

	/**
	 * Inner Class that paints the cell of the table with JLabel for gender
	 */
	private static class JTableGenderRenderer extends JCheckBox implements TableCellRenderer,UIResource {

		private JLabel genre;
		
		public JTableGenderRenderer(RolloverMouseAdapter rollover){
			genre = new JLabel();
			genre.setFont(new java.awt.Font("Lao Sangam MN",0, 15));
			genre.setHorizontalTextPosition(SwingConstants.CENTER);
			genre.setVerticalTextPosition(SwingConstants.CENTER);
			genre.setOpaque(false);
			
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row,int column) {
			
			genre.setText(((String)value));
			
			return genre;

			
		}
		
		
	}
	/**
	 * Inner Class that paints the cell of the table with JLabel with number of the song in the list
	 */
	private static class JTableIDRenderer implements TableCellRenderer,UIResource {        


		private JLabel ID;


		public JTableIDRenderer(RolloverMouseAdapter rollover){
			super();
			ID = new JLabel();
			ID.setFont(new java.awt.Font("Century Gothic",0, 15));
			ID.setHorizontalTextPosition(SwingConstants.CENTER);
			ID.setVerticalTextPosition(SwingConstants.CENTER);
			ID.setOpaque(false);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			ID.setText((String)value.toString());
			ID.setHorizontalTextPosition(SwingConstants.CENTER);
			ID.setVerticalTextPosition(SwingConstants.BOTTOM);
			return ID;


		}       	
	}
	/**
	 * Inner Class that paints the cell of the table with JButton for the button ADD
	 */
	private static class JTableButtonAddRenderer extends JCheckBox implements TableCellRenderer,UIResource {        


		private JButton button;
		private ImageIcon image1 = new ImageIcon("./img/ButtonAdd2.png");
		private ImageIcon image2 = new ImageIcon("./img/ButtonAdd3.png");
		private ImageIcon image3 = new ImageIcon("./img/ButtonAdd.png");
		private  RolloverMouseAdapter Adapter;
	
		public JTableButtonAddRenderer(RolloverMouseAdapter rollover){
			super();
			button = new JButton();
			Adapter = rollover;
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

			button = (JButton)value;

			button.getModel().setRollover(Adapter.isRolloverCell(row, column));
			button.setContentAreaFilled(false);
			button.setFocusable(false);
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setBorderPainted(false);
			button.setOpaque(false);
			button.setIcon(image1);

			button.setRolloverIcon(image2);
			button.setRolloverSelectedIcon(image3);

			if(isSelected){
				if(column < 5){
					button.setIcon(image3);
					return button;
				}else button.setIcon(image1);
				return button;
			}else if(Adapter.isRolloverCell(row, column)){
				button.setIcon(image2);
				return button;
			}else{
				button.setIcon(image1);
				return button;
			}


		}

	}
	/**
	 * Inner Class that paints the cell of the table with JButton for the Button PLAY
	 */
	private static class JTableButtonPlayRenderer extends JCheckBox implements TableCellRenderer,UIResource {        

	
		private JButton button;
		private  ImageIcon image1 = new ImageIcon("./img/ButtonPlaySong.png");	    	
		private  ImageIcon image2 = new ImageIcon("./img/ButtonPlaySong2.png");
		private  ImageIcon image3 = new ImageIcon("./img/ButtonPlaySong3.png");
		private  RolloverMouseAdapter Adapter;

		public JTableButtonPlayRenderer(RolloverMouseAdapter rollover){
			super();
			button = new JButton();
			Adapter = rollover;
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

			button = (JButton)value;
       		button.getModel().setRollover(Adapter.isRolloverCell(row, column));
       		button.setContentAreaFilled(false);
           	button.setFocusable(false);
    		button.setBorder(BorderFactory.createEmptyBorder());
    		button.setBorderPainted(false);
    		button.setOpaque(false);
            button.setIcon(image1);
            button.setRolloverIcon(image2);
            button.setRolloverSelectedIcon(image3);
       		
            if(isSelected){
            	button.setIcon(image3);
            	return button;
            }else if(Adapter.isRolloverCell(row, column)){
       			button.setIcon(image2);
       			return button;
       		}else{
       			button.setIcon(image1);
       			return button;
       		}
	
		}
	}

	/**
	 * Inner Class that paints the cell of the table with JButton for the Button DELETE
	 */
	private static class JTableButtonDeleteRenderer extends JCheckBox implements TableCellRenderer,UIResource {        
		private JButton button;
		private  ImageIcon image1 = new ImageIcon("./img/ButtonDelete.png");	    	
		private  ImageIcon image2 = new ImageIcon("./img/ButtonDelete2.png");
		@SuppressWarnings("unused")
		private  RolloverMouseAdapter Adapter;
		
		public JTableButtonDeleteRenderer(RolloverMouseAdapter rollover){
			button = new JButton();
			Adapter = rollover;
			}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
					
			button = (JButton)value;

			button.setContentAreaFilled(false);
			button.setFocusable(false);
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setBorderPainted(false);
			button.setOpaque(false);
			button.setIcon(image1);

			button.setRolloverIcon(image2);
			if(isSelected){
				button.setIcon(image2);
			}else{
				button.setIcon(image1);
			}
		return button;
		
		}
		
	}
	/**
	 * Inner Class that try to paint the cell when the mouse rollover into this
	 */
	static class RolloverMouseAdapter extends MouseAdapter {
		private int row = -1;
		private int column = -1;
		private JTable table;

		public RolloverMouseAdapter(JTable table) {
			this.table = table;
		}

		public boolean isRolloverCell(int row, int column) {
			return this.row == row && this.column == column;
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int lastRow = row;
			int lastColumn = column;

			row = table.rowAtPoint(e.getPoint());
			column = table.columnAtPoint(e.getPoint());

			if (row == lastRow && column == lastColumn)
				return;

			if (row >= 0 && column >= 0) {
				table.repaint(table.getCellRect(row, column, false));

			}
			if (lastRow >= 0 && lastColumn >= 0) {
				table.repaint(table.getCellRect(lastRow, lastColumn, false));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (row >= 0 && column >= 0) {
				table.repaint(table.getCellRect(row, column, false));
			}
			row = column = -1;
		}

	}
	/**
	 * value of the cell who has sellected
	 * @return PlayTable
	 */
	public int getPlayTable() {
		return PlayTable;
	}

	/**
	 * save the value of the row who has selected to reproduce
	 * @param playTable Value of the row that was selected to reproduce
	 */
	public void setPlayTable(int playTable) {
		PlayTable = playTable;
	}

	/**
	 * return the last column who has selected
	 * @return lastcolumn
	 */
	public int getLastcolumn() {
		return lastcolumn;
	}

	/**
	 * save the last column selected
	 * @param lastcolumn Last column to set
	 */
	public void setLastcolumn(int lastcolumn) {
		this.lastcolumn = lastcolumn;
	}
	/**
	 * return the last row who has selected
	 * @return lastrow
	 */
	public int getLastrow() {
		return lastrow;
	}

	/**
	 * save the last row selected
	 * @param lastrow Last row to set
	 */
	public void setLastrow(int lastrow) {
		this.lastrow = lastrow;
	}

	/**
	 * return the second last column sellected
	 * @return lastcolumn2
	 */
	public int getLastcolumn2() {
		return lastcolumn2;
	}

	/**
	 * save the second last column selected
	 * @param lastcolumn2 Last Second column to set
	 */
	public void setLastcolumn2(int lastcolumn2) {
		this.lastcolumn2 = lastcolumn2;
	}
	/**
	 * return the second last row who has selected
	 * @return lastrow2 Second Last Row to get
	 */
	public int getLastrow2() {
		return lastrow2;
	}
	/**
	 * save the second last row selected
	 * @param lastrow2 Second Last row to save
	 */
	public void setLastrow2(int lastrow2) {
		this.lastrow2 = lastrow2;
	}
}

	
	
