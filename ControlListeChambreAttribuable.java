import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ControlListeChambreAttribuable implements ActionListener {
    private PanneauListeChambreAttribuable room_list_panel;
    private HotelRooms model;
    private int client_id;

    public ControlListeChambreAttribuable (HotelRooms mbd,int client_id_init){
	    model=mbd;
	    client_id=client_id_init;
    }

    public void actionPerformed(ActionEvent e){
	    int room_id = Integer.parseInt(e.getActionCommand());
	    String date="2014-09-26";

	    model.setRoom(room_id,date,this.client_id);

	    Container window = this.room_list_panel.getParent();
	    window.remove(this.room_list_panel);

        String message = ("Le client n°"+client_id+" a été attribué à la chambre n°"+room_id+".");
        ControlRetour return_controler = new ControlRetour(this.model);
        PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);
        return_controler.setPan(return_panel);

	    window.add(return_panel);
	    window.validate();
    }

    public void setPan(PanneauListeChambreAttribuable pan){
	    this.room_list_panel=pan;
    }
}
