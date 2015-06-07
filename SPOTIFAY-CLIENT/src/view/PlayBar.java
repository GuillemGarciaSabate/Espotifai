package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.BottonsController;

/**
 * Class that bottom play section
 * 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class PlayBar extends JPanelBackground {
	/**Swing and AWT Components*/
	private JPanel jpButtons;
	private JButton jbPlay;
	private JButton jbBack;
	private JButton jbNext;
	
	/**Constants*/
	private static final String sURLBACK = "./img/Lists4.png";
	private static final String sURLPB = "./img/PlayButton.png";
	private static final String sURLPB2 = "./img/PlayButton2.png";
	private static final String sURLPB3 = "./img/PlayButton3.png";
	private static final String sURLPB4 = "./img/PlayButton4.png";
	private static final String sURLBB = "./img/ButtonBack.png";
	private static final String sURLBB2 = "./img/ButtonBack2.png";
	private static final String sURLBN = "./img/ButtonNext.png";
	private static final String sURLBN2 = "./img/ButtonNext2.png";
	
	/**
	 * Constructor
	 */
	public PlayBar(){
		this.setBackground(sURLBACK);
		this.setLayout(new BorderLayout());
		
		jpButtons = new JPanel(new FlowLayout());
		jpButtons.setOpaque(false);
		jbPlay = new JButton();
		jbBack = new JButton();
		jbNext = new JButton();
		
		ImageIcon imgPlay = new ImageIcon(sURLPB);
		ImageIcon imgPlay2 = new ImageIcon(sURLPB2);
		ImageIcon imgPlay3 = new ImageIcon(sURLPB3);
		ImageIcon imgPlay4 = new ImageIcon(sURLPB4);
		
		ImageIcon imgBack = new ImageIcon(sURLBB);
		ImageIcon imgBack2 = new ImageIcon(sURLBB2);
		
		ImageIcon imgNext = new ImageIcon(sURLBN);
		ImageIcon imgNext2 = new ImageIcon(sURLBN2);
		
		jbPlay.setIcon(imgPlay);
		jbPlay.setRolloverIcon(imgPlay2);
		jbPlay.setContentAreaFilled(false);
		jbPlay.setFocusable(false);
		jbPlay.setBorderPainted(false);
		jbPlay.setSelectedIcon(imgPlay4);
		jbPlay.setRolloverSelectedIcon(imgPlay3);
		
		jbBack.setIcon(imgBack);
		jbBack.setRolloverIcon(imgBack2);
		jbBack.setContentAreaFilled(false);
		jbBack.setFocusable(false);
		jbBack.setBorderPainted(false);
		
		jbNext.setIcon(imgNext);
		jbNext.setRolloverIcon(imgNext2);
		jbNext.setContentAreaFilled(false);
		jbNext.setFocusable(false);
		jbNext.setBorderPainted(false);
		
		jpButtons.add(jbBack);
		jpButtons.add(jbPlay);
		jpButtons.add(jbNext);
		this.add(jpButtons,BorderLayout.WEST);
		
	}
	
	public JButton getPlay(){
		return jbPlay;
	}

	/**
	 * Function that sets listeners to the needed buttons
	 * @param controlador Controller that works as listener for buttons
	 */
	public void setControllers(BottonsController controlador) {
		jbPlay.addActionListener(controlador);
		jbPlay.setActionCommand("FINALPLAY");
	}

}
