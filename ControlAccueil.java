import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ControlAccueil implements ActionListener
{
    private PanneauAccueil panel;
    private HotelRooms model;

    public ControlAccueil (HotelRooms mbd){
	    this.model = mbd;
    }

    public void actionPerformed(ActionEvent e){
	    String objectName=e.getActionCommand();
	    Container window =  this.panel.getParent();	

	    if(objectName.equals("Attribution de chambre")){
		    window.remove(this.panel);
		    String[][] liste = new String[100][7];
		    liste = this.model.getReservations("2014-09-26");

            if(liste[0][0].equals("0")){
                String message = "Aucune réservation aujourd'hui, retournez-vous coucher.";
                ControlRetour return_controler = new ControlRetour(this.model);
                PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);
                return_controler.setPan(return_panel);
                window.add(return_panel);
                window.validate();
            }

            else if(liste[0][0].equals("-1")){
                String message = "Maintenance de la base de donnée, retour au papier.";
                ControlRetour return_controler = new ControlRetour(this.model);
                PanneauRetourAccueil return_panel = new PanneauRetourAccueil(message,return_controler);
                return_controler.setPan(return_panel);
                window.add(return_panel);
                window.validate();
            }

            else {
                String[][] clients_information = new String[liste.length][2];
                String[][] checked_reservations = new String[liste.length][2];
                String[][] left_reservations = new String[liste.length][7];
                checked_reservations = this.model.getRoomHistory("2014-09-26");
                /* Pas de prise en compte de la valeur de retour -1(erreur), cela serait plus incapacitant que l'erreur elle même. */
                int j;

		        for(int i=0;i<liste.length;i++){
                    for(j=0;j<checked_reservations.length;j+=1){
                        if(liste[i][0].equals(checked_reservations[j][0]))
                            break;
                    }
                    if(j==checked_reservations.length){
                        for(int k=0;k<liste[i].length;k++)
                            left_reservations[i][k]=liste[i][k];
                    clients_information[i][0]=left_reservations[i][0];
                    clients_information[i][1]=left_reservations[i][5];
                    }
		        }

		        ControlListeClient client_list_controler = new ControlListeClient(this.model,clients_information);
		        PanneauListeClient client_list_panel = new PanneauListeClient(client_list_controler,left_reservations);
		        client_list_controler.setPan(client_list_panel);
		        window.add(client_list_panel);
		        window.validate();
            }
        }

        else if(objectName.equals("Statistiques générales")){
		    this.panel.add(new JLabel("???Autre???") );
	    }
        
        else if(objectName.equals("Départ de client")){
            window.remove(this.panel);
            JTextField string_room_id = new JTextField(2);
            LeavingClientControler leaving_client_controler = new LeavingClientControler(this.model,string_room_id);
            LeavingClientPanel leaving_client_panel = new LeavingClientPanel(leaving_client_controler,string_room_id);
            leaving_client_controler.setPan(leaving_client_panel);
            window.add(leaving_client_panel);
            window.validate();
        }
    }

    public void setPan(PanneauAccueil pan){
	    this.panel=pan;
    }
}
