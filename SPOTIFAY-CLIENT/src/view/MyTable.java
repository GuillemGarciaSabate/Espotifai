package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.table.TableCellRenderer;

import model.PlaylistsTableModel;

/**
 * 
 * This class paints the table with the playlists of the user
 * 
 * @author Jordi Badia
 * @author Guillem Gracia
 * @author Albert Trias	
 * @author Daniel Mateu
 * @author Adria Acero
 *
 */
@SuppressWarnings("serial")
public class MyTable extends JTable{
	private  ImageIcon image1 = new ImageIcon("./img/FinalPlayList.png");
    private Border paddingBorder;
    private TableCellRenderer buttonRenderer;
    private  RolloverMouseAdapter RolloverAdapter;
    
    
    /**
     * Constructor
     * 
     * @param tablemodel is the information of all the playlists of a user
     */
    public MyTable(PlaylistsTableModel tablemodel){
    	paddingBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    	this.setModel(tablemodel);
 		this.setOpaque(false);
 		this.setFocusable(false);
 		this.setAutoCreateRowSorter(true);
 		this.setRowHeight(37);		
 		this.setPreferredSize(new Dimension(250,30));		
 		this.setRowSelectionAllowed(true);
 		RolloverAdapter = new  RolloverMouseAdapter(this);
 		buttonRenderer = new JTableButtonRenderer(RolloverAdapter);
        this.setDefaultRenderer(JButton.class, buttonRenderer);
        this.addMouseMotionListener(RolloverAdapter);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.setIntercellSpacing(new Dimension(0,0));
        this.setShowHorizontalLines(false);
        this.setShowVerticalLines(false);
    }
    
    /**
     * This methods refresh the table with the playlists of the user
     */
	public void refreshTable() {
		this.setPreferredSize(new Dimension(250,this.getHeight()+37));
		SwingUtilities.updateComponentTreeUI(this);
	}

	/**
	 * This is an inner class that paints a cell like if it was a button
	 *
	 */
    private static class JTableButtonRenderer extends JCheckBox implements TableCellRenderer,UIResource {        
    	private JButton button;
    	private  ImageIcon image1 = new ImageIcon("./img/FinalPlayList.png");
    	private ImageIcon image2 = new ImageIcon("./img/FinalPlayList2.png");
       	private ImageIcon image3 = new ImageIcon("./img/FinalPlayList3.png");
       	private  RolloverMouseAdapter Adapter;
       	
       	public JTableButtonRenderer(RolloverMouseAdapter Adapter){
       		super();
       		this.Adapter = Adapter;
         }
       	
       	@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       		
       		//System.out.println(row+","+column+","+hasFocus);
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
     * Gets the padding border
     * 
     * @return paddingBorder is the border
     */
	public Border getPaddingBorder() {
		return paddingBorder;
	}

	/**
	 * 
	 * Sets the padding border
	 * 
	 * @param paddingBorder is the border
	 */
	public void setPaddingBorder(Border paddingBorder) {
		this.paddingBorder = paddingBorder;
	}

	/**
	 * 
	 * Gets the button
	 * 
	 * @return buttonRenderer is the button
	 */
	public TableCellRenderer getButtonRenderer() {
		return buttonRenderer;
	}

	/**
	 * 
	 * Sets the button
	 * 
	 * @param buttonRenderer is the button
	 */
	public void setButtonRenderer(TableCellRenderer buttonRenderer) {
		this.buttonRenderer = buttonRenderer;
	}
	
	/**
	 * 
	 * This is an inner class
	 * The function of this class is to rollover a button
	 *
	 */
	static class RolloverMouseAdapter extends MouseAdapter {
        private int row = -1;
        private int column = -1;
        private JTable table;
        

        public RolloverMouseAdapter(JTable table) {
            this.table = table;
        }

        public boolean isRolloverCell(int row, int column) {
            
        	if(this.row == row && this.column == column){
            	return true;
            }
        	return false;
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
	 * 
	 * Gets an image
	 * 
	 * @return image1 is the image
	 */
	public ImageIcon getImage1() {
		return image1;
	}

	/**
	 * 
	 * Sets an image
	 * 
	 * @param image1 is the image
	 */
	public void setImage1(ImageIcon image1) {
		this.image1 = image1;
	}
}
