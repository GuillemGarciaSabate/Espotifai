package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import controller.BottonsController;

@SuppressWarnings("serial")
/**
 * Class that generates the Login panel to allow log in the program
 * 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 * @version 1.0
 * @since 14/05/2015
 *
 */
public class Login extends JPanelBackground {
	/**Swing and AWT Components*/
	private JERoundTextField jertfUser;
	private JERoundPasswordField jerpfPassword;
	private JButton jbLogin;
	private JButton jbRegister;
	
	/**Constants*/
	private static final String sURLBG = "./img/MusicPanel.jpg";
	private static final String sURLBOTO1 = "./img/BOTOBLAU.png";
	private static final String sURLBOTO2 = "./img/BOTOVERD.png";

	/**
	 * Constructor
	 */
	public Login() {
		this.setBackground(sURLBG);
		this.setLayout(new GridLayout(5,1));
		
		this.addTitleToPanel();
		
		this.addUserToPanel();
		
		this.addPasswordToPanel();
		
		this.addRegisterOptionToPanel();
		
		this.addLoginButtonToPanel();	
	}	

	/**
	 * Function that adds a title to this JPanelBackground
	 */
	private void addTitleToPanel() {
		JPanel jpP1 = new JPanel();
		
		JLabel jlTitle = new JLabel("E S P O T Y F A I");
		jlTitle.setFont(new java.awt.Font("Microsoft Yi Baiti",0, 22));
		jlTitle.setForeground(new Color(250,250,250));
		
		jpP1.add(jlTitle);
		jpP1.setOpaque(false);
		
		this.add(jpP1);
	}
	
	/**
	 * Function that adds the User section to this JPanelBackground
	 */
	private void addUserToPanel() {
		JPanel jpP2 = new JPanel (new FlowLayout());
		
		JLabel jlUser = new JLabel ("Name of User");
		jlUser.setFont(new java.awt.Font("Century Gothic", 0, 12));
		jlUser.setForeground(new Color(200,200,200));
		
		jertfUser = new JERoundTextField();
		
		jpP2.add(jlUser);
		jpP2.add(jertfUser);
		jpP2.setOpaque(false);
		
		this.add(jpP2);
	}
	
	/**
	 * Function that adds the Password section to this JPanelBackground
	 */
	private void addPasswordToPanel() {
		JPanel jpP3 = new JPanel (new FlowLayout());
		
		JLabel jlPassword = new JLabel ("        Password");
		jlPassword.setFont(new java.awt.Font("Century Gothic", 0, 12));
		jlPassword.setForeground(new Color(200,200,200));
		jerpfPassword = new JERoundPasswordField();
		
		jpP3.add(jlPassword);
		jpP3.add(jerpfPassword);
		jpP3.setOpaque(false);
		
		this.add(jpP3);
	}
	
	/**
	 * Function that adds a button that allows the user to access the register dialog
	 */
	private void addRegisterOptionToPanel() {
		JPanel jpP4 = new JPanel (new BorderLayout());
		
		jbRegister = new JButton("Sign up for free!!");

		jbRegister.setFont(new java.awt.Font("Century Gothic", 0, 14));
		jbRegister.setText("Sign up for free!!");
		jbRegister.setForeground(new Color(0,125,100));
		jbRegister.setBorderPainted(false);
		jbRegister.setContentAreaFilled(false);
		jbRegister.setFocusable(false);
		
		jpP4.add(jbRegister);
		jpP4.setOpaque(false);
		
		this.add(jpP4);
	}
	
	/**
	 * Function that adds the Login Button to this JPanelBackground
	 */
	private void addLoginButtonToPanel() {
		JPanel jpP5 = new JPanel (new FlowLayout());
		ImageIcon iiBoto1 = new ImageIcon(sURLBOTO1);
		ImageIcon iiBoto2 = new ImageIcon(sURLBOTO2);
		
		jbLogin = new JButton ();
		jbLogin.setBounds(100, 100, 100, 100);
		jbLogin.setIcon(iiBoto1);
		jbLogin.setRolloverIcon(iiBoto2);
		jbLogin.setBorderPainted(false);
		jbLogin.setContentAreaFilled(false);
		jbLogin.setFocusable(false);
		
		jpP5.add(jbLogin,BorderLayout.CENTER);
		jpP5.setOpaque(false);
		
		this.add(jpP5);
	}
	
	/**
	 * Function that adds the BottonsController ActionListener to the buttons and sets their action command
	 * @param bc ActionListener used for reading button presses
	 */
	public void setControllers(BottonsController bc) {
		jbLogin.addActionListener(bc);
		jbLogin.setActionCommand("log in");
		jbRegister.addActionListener(bc);
		jbRegister.setActionCommand("sign in");
	}
	
	/**
	 * Getter of User field
	 * @return Returns the content in the JERoundTextField User
	 */
	public String getUser() {
		return jertfUser.getText();
	}
	
	/**
	 * Getter of Password field
	 * @return Returns the content in the JERoundPasswordField Password
	 */
	public String getPassword() {
		return String.valueOf(jerpfPassword.getPassword());
	}
}