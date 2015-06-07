package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * The class Graphic is the panel where we will draw our graph
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Graphic extends JPanel {
	/**Variables*/
	private int iMaxNumber;
	private int[] iSongs;
	private String[] sSongs;
	
	/**Constructor*/
	public Graphic() {
		iSongs = new int[5];
		sSongs = new String[5];
	    this.setBackground(Color.WHITE);
	}
	
	/**
	 * Method that forces the panel to be painted when we want
	 */
	public void drawGrafic() {
	    repaint();
	}

	/**
	 * Function that paints the panel with the Graphics we give it
	 * @param g Graphics that the panel will have
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.drawLine(80, 50, 80, 380);
		g.drawString("Cançons",25,60);
		g.drawLine(80, 380, 420, 380);
		g.drawString("Nombre Reproduccions",200,425);
		
		if (iSongs[0] > 0) {
			g.setColor(Color.BLUE);
			g.fillRect(81, 95, ((iSongs[0]*340)/iMaxNumber), 30);
			g.setColor(Color.BLACK);
			g.drawString(sSongs[0],30,115);
			g.drawString(Integer.toString(iSongs[0]), ((iSongs[0]*340)/iMaxNumber)+85, 115);
		}
		
		if (iSongs[1] > 0) {
			g.setColor(Color.RED);
			g.fillRect(81, 155, ((iSongs[1]*340)/iMaxNumber), 30);
			g.setColor(Color.BLACK);
			g.drawString(sSongs[1],30,175);
			g.drawString(Integer.toString(iSongs[1]), ((iSongs[1]*340)/iMaxNumber)+85, 175);
		}
		
		if (iSongs[2] > 0) {
			g.setColor(Color.GREEN);
			g.fillRect(81, 215, ((iSongs[2]*340)/iMaxNumber), 30);
			g.setColor(Color.BLACK);
			g.drawString(sSongs[2],30,235);
			g.drawString(Integer.toString(iSongs[2]), ((iSongs[2]*340)/iMaxNumber)+85, 235);
		}
		
		if (iSongs[3] > 0) {
			g.setColor(Color.YELLOW);
			g.fillRect(81, 275, ((iSongs[3]*340)/iMaxNumber), 30);
			g.setColor(Color.BLACK);
			g.drawString(sSongs[3],30,295);
			g.drawString(Integer.toString(iSongs[3]), ((iSongs[3]*340)/iMaxNumber)+85, 295);
		}
		
		if (iSongs[4] > 0) {
			g.setColor(Color.BLACK);
			g.fillRect(81, 335, ((iSongs[4]*340)/iMaxNumber), 30);
			g.setColor(Color.BLACK);
			g.drawString(sSongs[4],30,355);
			g.drawString(Integer.toString(iSongs[4]), ((iSongs[4]*340)/iMaxNumber)+85, 355);
		}
		
		if (iMaxNumber >= 5) {
			g.drawString("0", 420/5*1-10, 400);
			g.drawString(Integer.toString(iMaxNumber/4*1), 420/5*2-10, 400);
			g.drawString(Integer.toString(iMaxNumber/4*2), 420/5*3-10, 400);
			g.drawString(Integer.toString(iMaxNumber/4*3), 420/5*4-10, 400);
		}
		g.drawString(Integer.toString(iMaxNumber), 420-10, 400);			
	}
	
	/**
	 * In case it's necessary to order the songs, use this function. Not used currently
	 */
	public void orderSongs() {
		boolean bFlag = true;
		int iTemp = 0;
		String sTemp = "";

		while (bFlag) {
			bFlag= false;
			for (int j = 0;  j < 4;  j++ ) {
				if (iSongs[j] < iSongs[j+1]) {
                	iTemp = iSongs[j];
                	sTemp = sSongs[j];
                	iSongs[j] = iSongs[j+1];
                	sSongs[j] = sSongs[j+1];
                	iSongs[j+1] = iTemp;
                	sSongs[j+1] = sTemp;
                	bFlag = true;
				}
			}
		} 
		iMaxNumber = iSongs[0];
	}

	/**
	 * Setter of the new info of the graph
	 * @param iSongs Integer[] with the number of reproductions of the top 5 songs
	 * @param sSongs String[] with the name of the top 5 songs
	 */
	public void updateGraph(int[] iSongs, String[] sSongs) {
		this.iSongs = iSongs;
		this.sSongs = sSongs;
		this.iMaxNumber = this.iSongs[0];
	}
	
}
