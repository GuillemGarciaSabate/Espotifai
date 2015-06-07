package view;

import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BottonsController;

/**
 * Class that generates the JDialog for Register
 * 
 * @author Albert Trias Torroglosa
 * @author Daniel Mateu Elizalde
 * @author Guillem Garcia Sabater
 * @author Jordi Badia Iglesias
 * @author Adria Acero Montes
 */
@SuppressWarnings("serial")
public class Register extends JDialog {
	/**Swing and AWT Components*/
	private JPanelBackground pPrin;
	private JPanel PPrin;

	private JPanel jpP1;

	private JPanel jpP2;
	private JERoundTextField jertfTextMail;
	private JERoundTextField jertfTextUser;
	private JERoundPasswordField jerpfPassword;
	private JERoundPasswordField jerpfPasswordAgain;
	
	private JPanel jpP3;
	private JLabel jlPasswordError;
	private JButton jbOK;
	
	/**Constants*/
	private static final String sURLBACK = "./img/RegisterMusic.jpg";
	private static final String sURLB4 = "./img/boto4.png";

	/**
	 * Constructor
	 * @param wc Pointer to the WindowClient
	 * @param type Value that locks window client until this dialog is closed
	 */
	public Register(WindowClient wc, Dialog.ModalityType type){
		super(wc,type);
			
		this.setMainPanel();
		this.setRegisterTitle();
		this.setOKButton();
		this.setFormRegister();
		this.addComponentsToPanel();
	}
	
	/**
	 * Places all elements to the main panel and does final configurations
	 */
	private void addComponentsToPanel() {
		PPrin.add(jpP1,BorderLayout.NORTH);
		PPrin.add(jpP2,BorderLayout.CENTER);
		PPrin.add(jpP3,BorderLayout.SOUTH);
		pPrin.add(PPrin,BorderLayout.CENTER);
		
		this.add(pPrin);
		this.setSize(600,450);
		this.setTitle("Espotyfai");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	/**
	 * Prepares the main panel where we will place everything
	 */
	private void setMainPanel() {
		pPrin = new JPanelBackground();
		pPrin.setBackground(sURLBACK);
		
		PPrin = new JPanel(new BorderLayout());
		PPrin.setBackground(null);
		PPrin.setOpaque(false);
	}

	/**
	 * Prepares the title Register
	 */
	private void setRegisterTitle() {
		JLabel jlTitle = new JLabel("R E G I S T E R");
		jlTitle.setFont(new java.awt.Font("Microsoft Yi Baiti", 0, 28));
		jlTitle.setForeground(new Color(250,250,250));
		jpP1 = new JPanel(new FlowLayout());
		jpP1.setBackground(null);
		jpP1.add(jlTitle,BorderLayout.CENTER);
		jpP1.setOpaque(false);
	}

	/**
	 * Prepares all Swing and AWT Components for the form and places them in a panel
	 */
	private void setFormRegister() {
		GridBagConstraints constraints = new GridBagConstraints();
		
		jpP2 = new JPanel(new GridBagLayout());
		
		/**Label of Mail*/
		JLabel jlMail = new JLabel ("                    Mail");
		jlMail.setFont(new java.awt.Font("Century Gothic", 0, 12));
		jlMail.setForeground(new Color(200,200,200));
		constraints.gridx = 0;
		constraints.gridy = 0; 
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(40,0,15,10);
		jpP2.add(jlMail,constraints);
		
		/**Mail Form*/
		jertfTextMail = new JERoundTextField();
		constraints.gridx = 2;
		constraints.gridy = 0; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1;
		jpP2.add(jertfTextMail,constraints);
		
		/**Label of Nickname*/
		JLabel jlNickName = new JLabel("        NickName");
		jlNickName.setFont(new java.awt.Font("Century Gothic", 0, 12));
		jlNickName.setForeground(new Color(200,200,200));
		constraints.gridx = 0; 
		constraints.gridy = 2; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.insets = new Insets(15,0,15,10);
		jpP2.add(jlNickName,constraints);
		
		/**Nickname Form*/
		jertfTextUser = new JERoundTextField();		
		constraints.gridx = 2; 
		constraints.gridy = 2; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1; 
		jpP2.add(jertfTextUser,constraints);
		
		/**Label of Password*/
		JLabel jlPassword = new JLabel ("Intro Password");
		jlPassword.setFont(new java.awt.Font("Century Gothic", 0, 12));
		jlPassword.setForeground(new Color(200,200,200));
		constraints.gridx = 0;
		constraints.gridy = 4; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		jpP2.add(jlPassword,constraints);
		
		/**Password Form*/
		jerpfPassword = new JERoundPasswordField();
		constraints.gridx = 2; 
		constraints.gridy = 4; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1; 
		jpP2.add(jerpfPassword,constraints);
		
		/**Label of Password Again*/
		JLabel jlPasswordAgain = new JLabel ("Repeat Password     ");
		jlPasswordAgain.setFont(new java.awt.Font("Century Gothic", 0, 12));
		jlPasswordAgain.setForeground(new Color(200,200,200));
		constraints.gridx = 0;
		constraints.gridy = 6; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.insets = new Insets(15,0,30,10);
		jpP2.add(jlPasswordAgain,constraints);	
		
		/**Password Again Form*/
		jerpfPasswordAgain = new JERoundPasswordField();
		constraints.gridx = 2; 
		constraints.gridy = 6; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1; 
		jpP2.add(jerpfPasswordAgain,constraints);
		
		jpP2.setOpaque(false);
	}

	/**
	 * Prepares the panel with the OK Button
	 */
	private void setOKButton() {
		ImageIcon dBoto = new ImageIcon(sURLB4);
		jbOK = new JButton();
		
		jbOK.setBounds(100, 100, 100, 100);
		jbOK.setForeground(new Color(0,125,100));
		jbOK.setIcon(dBoto);
		jbOK.setBorderPainted(false);
		jbOK.setContentAreaFilled(false);
		jbOK.setFocusable(false);
		
		jlPasswordError = new JLabel("Failed to enter passwords. Please write new password");
		jlPasswordError.setFont(new java.awt.Font("Century Gothic", 0, 12));
		jlPasswordError.setForeground(Color.RED);
		jlPasswordError.setVisible(false);
		
		jpP3 = new JPanel (new BorderLayout());
		jpP3.setBackground(null);
		jpP3.add(jlPasswordError,BorderLayout.EAST);
		jpP3.add(jbOK,BorderLayout.SOUTH);
		jpP3.setOpaque(false);
	}

	/**
	 * Getter of User
	 * @return String with the user value
	 */
	public String getUser() {
		return jertfTextUser.getText();
	}

	/**
	 * Getter of Password
	 * @return String with the password value
	 */
	public String getPassword() {
		return String.valueOf(jerpfPassword.getPassword());
	}
	
	/**
	 * Getter of Password Again
	 * @return String with the password again value
	 */
	public String getPasswordAgain() {
		return String.valueOf(jerpfPasswordAgain.getPassword());
	}

	/**
	 * Sets the listeners of the buttons using the controller
	 * @param controlador Controller that adds ActionListener to the button OK
	 */
	public void setControllers(BottonsController controlador) {
		jbOK.addActionListener(controlador);
		jbOK.setActionCommand("OK");
	}

	/**
	 * Show the error message in case of failed register
	 */
	public void showErrorMessage() {
		jlPasswordError.setVisible(true);
	}
	
}


