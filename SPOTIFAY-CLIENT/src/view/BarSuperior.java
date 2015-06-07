package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Class that creates the north side of the screen
 * 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class BarSuperior extends JPanelBackground {
	/**Swing and AWT Components*/
	private JButton jbDisconnect;
	private JLabel jlNameUser;
	private JPanel jpTitle;
	
	/**Constants*/
	private static final String sURLBACK = "./img/BarSuperior3.png";
	private static final String sURLBD1 = "./img/ButtonDisconnect.png";
	private static final String sURLBD2 = "./img/ButtonDisconnect2.png";
	private static final String sURLLOGO = "./img/logo.png";
	
	/**
	 * Constructor
	 */
	public BarSuperior(){
		this.CreateNorthPanel();
		this.PutObjectsInFrame();		
	}
	
	/**
	 * Creates the north Panel of BarSuperior
	 */
	private void CreateNorthPanel(){
		this.setBackground(sURLBACK);
		this.setLayout(new GridBagLayout());
		
		ImageIcon image1 = new ImageIcon(sURLBD1);
		ImageIcon image2 = new ImageIcon(sURLBD2);
		
		jbDisconnect = new JButton("Disconnect");
		jbDisconnect.setFont(new java.awt.Font("Apple SD Gothic Neo Thin",0, 12));
		jbDisconnect.setForeground(new Color(150,100,0));
		jbDisconnect.setIcon(image1);
		jbDisconnect.setHorizontalTextPosition(SwingConstants.CENTER);
		jbDisconnect.setVerticalTextPosition(SwingConstants.CENTER);
		jbDisconnect.setRolloverIcon(image2);
		jbDisconnect.setContentAreaFilled(false);
		jbDisconnect.setFocusable(false);
		jbDisconnect.setBorderPainted(false);
		
		jlNameUser = new JLabel("USER");

		ImageIcon logo = new ImageIcon(sURLLOGO);
		jpTitle = new JPanel(new FlowLayout());

		jpTitle.setOpaque(false);
		JLabel jlTitle = new JLabel("E S P O T Y F A I");
		jlTitle.setFont(new java.awt.Font("Microsoft Yi Baiti",0, 22));
		jlTitle.setForeground(new Color(250,250,250));
		JLabel jlLogo = new JLabel(logo);
		jlLogo.setOpaque(false);

		jpTitle.add(jlLogo);
		jpTitle.add(jlTitle);
	}
	
	/**
	 * Puts all Swing and AWT Components inside our panel
	 */
	private void PutObjectsInFrame(){
		GridBagConstraints constraints  = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.insets = new Insets(10,10,10,10);
		constraints.weighty = 0.0;
		this.add(jlNameUser,constraints);
		
		constraints.gridx = 1; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.insets = new Insets(0,450,0,0);
		constraints.weighty = 0.0;
		this.add(jpTitle,constraints);
		
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 5; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.insets = new Insets(0,400,0,0);
		constraints.weighty = 1.0;
		this.add(jbDisconnect,constraints);
	}

	/**
	 * Setter of JLabel with User Name
	 * @param sText Text with the name of the user
	 */
	public void setNameUser(String sText) {
		jlNameUser.setText(sText);
	}
}
