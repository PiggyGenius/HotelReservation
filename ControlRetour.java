import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ControlRetour implements ActionListener {
    private JPanel panel;
    private HotelRooms model;

    public ControlRetour (HotelRooms mbd){
	    this.model=mbd;
    }

    public void actionPerformed(ActionEvent e){
	    Container fenetre = this.panel.getParent();
	    fenetre.remove(this.panel);
	    ControlAccueil controler = new ControlAccueil(model);
	    PanneauAccueil panel_controler = new PanneauAccueil(controler);
	    controler.setPan(panel_controler);
	
	    fenetre.add(panel_controler);
	    fenetre.validate();
    }

    public void setPan(PanneauRetourAccueil return_panel){
        this.panel=return_panel;
    }
}
