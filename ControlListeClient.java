import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ControlListeClient implements ActionListener {
    private JPanel client_list_panel;
    private HotelRooms model;
    private static String[][] clients_information;

    public ControlListeClient (HotelRooms mbd,String[][] clients_information_init){
	    this.model = mbd;
	    this.clients_information = new String[clients_information_init.length][2];
	    this.clients_information = clients_information_init;
    }

    public void actionPerformed(ActionEvent e){
	    int client_id = Integer.parseInt(e.getActionCommand());
	    String str_client_id = e.getActionCommand();
	    String date = "2014-09-26";
	
	    Container window =  this.client_list_panel.getParent();

	    window.remove(this.client_list_panel);
	    int array_value=0;
	    for(array_value=0;this.clients_information[array_value][0]!=str_client_id;array_value+=1){
	        ;
	    }

	    String type=clients_information[array_value][1];
	    System.out.println(type);

	    if(type.equals("Un lit double")){
	        type="Twin_Beds";
	    }
	    else if(type.equals("Deux lits simples")){
	        type="Single_Beds";
	    }
	    else if(type.equals("Un lit simple")){
	        type="Single_Bed";
	    }
	    /* Cas -1 */

	    System.out.println(type);
	    int room_id = this.model.getRoom(type);

        if(room_id==0){
            String message = "Aucune chambre disponible, proposez le couloir.";
            ControlRetour return_controler = new ControlRetour(this.model);
            PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);

            return_controler.setPan(return_panel);
            window.add(return_panel);
            window.validate();
        }

        else if(room_id==-1){
            String message = "Maintenance de la base de donnée, faîtes le tour des chambres.";
            ControlRetour return_controler = new ControlRetour(this.model);
            PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);

            return_controler.setPan(return_panel);
            window.add(return_panel);
            window.validate();
        }
        else {
	        String message = "Voulez vous attribuer la chambre "+Integer.toString(room_id)+" au client "+Integer.toString(client_id);
	        ControlAttribution attribution_controler = new ControlAttribution(this.model,client_id,type,room_id);
	        PanneauChambreAttribution attribution_panel = new PanneauChambreAttribution(message,attribution_controler);
	        attribution_controler.setPan(attribution_panel);

	        window.add(attribution_panel);
	        window.validate();
        }
    }

    public void setPan(PanneauListeClient pan){
	    this.client_list_panel=pan;
    }
}
