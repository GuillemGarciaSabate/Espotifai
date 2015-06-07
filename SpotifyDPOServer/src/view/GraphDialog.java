package view;

import javax.swing.JDialog;

/**
 * The class GraphDialog generates the dialog with the graph and updates it when needed
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GraphDialog extends JDialog {
	/**Variables*/
	private Graphic gDibuix;
	
	/**Constructor*/
	public GraphDialog() {
		
		gDibuix = new Graphic();
		this.add(gDibuix);
		gDibuix.drawGrafic();
		
		this.setResizable(false);
		this.setTitle("Estadistiques Partida");
		this.setSize(500,500);
		this.setVisible(false);
		
	}

	/**Draws the graphic to the panel*/
	public void drawGraphic() {
		gDibuix.drawGrafic();
	}

	/**
	 * Updates the info of the graph with the entry parameters
	 * @param iSongs Integer[] with the number of reproductions of the top 5 songs
	 * @param sSongs String[] with the name of the top 5 songs
	 */
	public void updateGraph(int[] iSongs, String[] sSongs) {
		gDibuix.updateGraph(iSongs,sSongs);
	}

}

