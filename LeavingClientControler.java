import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LeavingClientControler implements ActionListener {
    private LeavingClientPanel leaving_client_panel;
    private HotelRooms model;
    private JTextField string_room_id;

    public LeavingClientControler(HotelRooms model_init,JTextField room_id_init){
        this.model=model_init;
        this.string_room_id=room_id_init;
    }

    public void actionPerformed(ActionEvent e){
        int room_id = Integer.parseInt(this.string_room_id.getText());

        Container window = this.leaving_client_panel.getParent();
        window.remove(this.leaving_client_panel);

        if(room_id>0 && room_id<101){
            this.model.setRoomUnoccupied(room_id);

            String message = ("La chambre n°"+room_id+" est maintenant innoccupé.");
            ControlRetour return_controler = new ControlRetour(this.model);
            PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);
            return_controler.setPan(return_panel);
            window.add(return_panel);
            window.validate();
        }

        else {
            String message = ("Aucune Chambre ne correspond à ce numéro, vous vous êtes trompé d'hotel.");
            ControlRetour return_controler = new ControlRetour(this.model);
            PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);
            return_controler.setPan(return_panel);
            window.add(return_panel);
            window.validate();
        }
    }

    public void setPan(LeavingClientPanel leaving_client_panel_init){
        this.leaving_client_panel=leaving_client_panel_init;
    }
}
