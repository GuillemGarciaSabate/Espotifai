package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
 
@SuppressWarnings("serial")
/**
 * 
 * This class is used to paint the background of the panels
 * 
 * @author Jordi Badia
 * @author Guillem Gracia
 * @author Albert Trias	
 * @author Daniel Mateu
 * @author Adria Acero
 * 
 * @version 1.0
 *
 */
public class JPanelBackground extends JPanel {
 
	//This is the image to set in the background
	private Image background;
	public JPanelBackground(){}
	public JPanelBackground(Image background){
		
	}

	/**
	 * This method is executed every time that we repaint a component
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(),
                this);

		setOpaque(false);
	}
 
	// Metodo donde le pasaremos la direcci�n de la imagen a cargar.
	/**
	 * 
	 * This method sets an image to paint it in a component
	 * 
	 * @param imagePath is the image to paint
	 */
	public void setBackground(String imagePath) {
		this.background = new ImageIcon(imagePath).getImage();
		repaint();
	}
}
