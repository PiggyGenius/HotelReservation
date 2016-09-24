import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ControlAttribution implements ActionListener {
    private PanneauChambreAttribution attribution_panel;
    private HotelRooms model;
    private String type;
    private int room_id;
    private int client_id;

    public ControlAttribution (HotelRooms mbd,int client_id_init,String typeRoom, int room_id_init){
	    this.model=mbd;
	    this.type=typeRoom;
	    this.room_id=room_id_init;
	    this.client_id=client_id_init;
    }

    public void actionPerformed(ActionEvent e){
	    String objectName=e.getActionCommand();
	    Container window = this.attribution_panel.getParent();
        String date="2014-09-26";

	    if(objectName.equals("OUI")){
            window.remove(this.attribution_panel);
		    this.model.setRoom(this.room_id,date,this.client_id);
            String message = ("Le client n°"+this.client_id+" a été attribué à la chambre n°"+this.room_id);
            ControlRetour return_controler = new ControlRetour(this.model);
            PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);
            return_controler.setPan(return_panel);

            window.add(return_panel);
		    window.validate();
	    }
	    if(objectName.equals("NON")){
            window.remove(this.attribution_panel);
		    int list[] = new int[100];
    		list = model.getRooms(type);
		
            if(list[0]==0){
                String message = "Aucune chambre disponible, contentez-vous de celle proposé.";
                ControlRetour return_controler = new ControlRetour(this.model);
                PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);
                return_controler.setPan(return_panel);
                window.add(return_panel);
                window.validate();
            }

            else if(list[0]==0){
                String message = "Maintenance de la base de donnée, faîtes le tour des chambres.";
                ControlRetour return_controler = new ControlRetour(this.model);
                PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);
                return_controler.setPan(return_panel);
                window.add(return_panel);
                window.validate();
            }

            else {
    		    ControlListeChambreAttribuable room_controler = new ControlListeChambreAttribuable(model,this.client_id);
	    	    PanneauListeChambreAttribuable room_panel = new PanneauListeChambreAttribuable(list, room_controler);
		        room_controler.setPan(room_panel);

                window.add(room_panel);
		        window.validate();
	        }
        }
    }

    public void setPan(PanneauChambreAttribution room_panel){
	    this.attribution_panel = room_panel;
    }
}
