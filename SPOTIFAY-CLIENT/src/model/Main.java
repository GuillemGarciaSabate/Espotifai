package model;



import javax.swing.SwingUtilities;

import network.ServerCommunication;
import controller.BottonsController;
import view.WindowClient;
import controller.SliderController;
/**
 * The class Main is the starting point of our program. 
 * From here we will call all the methods to execute the program correctly
 *  
 * @author Guillem Garcia 
 * @author Jordi Badia
 * @author Daniel Mateu
 * @author Albert Trias
 * @author Adria Acero
 * @version 1.0
 */
	public class Main {
		public static void main(String[] args) {

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					// Creem la vista
					User user = new User();

					ListTableModel listablemodel = new ListTableModel(user.getFavorites());
					ListTableModel listablemodel2 = new ListTableModel(user.getSongs());
					ListTableModel listablemodel3 = new ListTableModel(user.getPartyList());
					
					PlaylistsTableModel listmodel = new PlaylistsTableModel(user);
					WindowClient wc = new WindowClient(listmodel,listablemodel,listablemodel2,listablemodel3);
					
					ServerCommunication SC = new ServerCommunication(wc);
					SC.startServerComunication();
					BottonsController bcontroller = new BottonsController(wc,user,SC);
					SliderController slider = new SliderController();
					SC.setController(bcontroller);
					
					PlayMusic pm = new PlayMusic(bcontroller,slider);
					bcontroller.setPm(pm);
					slider.setPlayMusic(pm);
					
					wc.registerController(bcontroller);
					// La fem visible
					wc.setVisible(true);
					wc.refreshTable();

					
					
				}
			});
		}


	}


