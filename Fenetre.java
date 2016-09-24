import java.awt.*;
import javax.swing.*;


public class Fenetre extends JFrame {
    public Fenetre(HotelRooms model){
	    this.setSize(800,800);
	    this.setLocation(10,10);
	    this.setTitle("Accueil Hotel");
        this.addWindowListener(new Closing(model));
	    ControlAccueil controler = new ControlAccueil(model);
	    PanneauAccueil panel = new PanneauAccueil(controler);
	    controler.setPan(panel);
	    this.add(panel, BorderLayout.CENTER); 
    }
}
