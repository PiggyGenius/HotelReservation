import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class PanneauChambreAttribution extends JPanel {
    public PanneauChambreAttribution(String affiche,ControlAttribution attribution_controler){
	    JLabel labelproposition = new JLabel(affiche);
	    this.add(labelproposition);

	    JButton yes_button = new JButton("OUI");
	    JButton no_button = new JButton("NON");

	    this.add(yes_button);
	    this.add(no_button);

	    yes_button.addActionListener(attribution_controler);
	    no_button.addActionListener(attribution_controler);
    }
}
