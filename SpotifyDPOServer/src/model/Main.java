package model;

import javax.swing.SwingUtilities;

import network.Server;

import controller.ButtonsController;
import controller.MenuController;
import controller.RowController;
import controller.SliderController;

import view.GraphDialog;
import view.WindowServer;

/**
 * Class with the main routine of the program
 * 
 * @author Guillem Garcia
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
public class Main {
	/**
	 * Main Function
	 * @param args String[] with the starting arguments. Not used
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				DataBaseAccess dba = new DataBaseAccess();
				WindowServer ws = new WindowServer();
				GraphDialog gd = new GraphDialog();
				
				SliderController sc = new SliderController();
				ButtonsController bc = new ButtonsController(ws,dba,gd);
				MenuController mc = new MenuController(ws, dba);
				RowController rc = new RowController(dba);
				PlayMusic pm = new PlayMusic(bc,sc,rc);
				
				bc.setPlayMusic(pm);
				bc.setControllers(rc);
				rc.setPlayMusic(pm);
				sc.setPlayMusic(pm);
				ws.setControllers(mc, rc, bc);
				ws.setVisible(true);
				
				Server server = new Server(ws,bc);
				server.startServer();
			}
			
		});
	}
}
