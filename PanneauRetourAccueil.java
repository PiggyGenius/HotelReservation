import java.awt.*;
import javax.swing.*;

public class PanneauRetourAccueil extends JPanel {
    public PanneauRetourAccueil(String message,ControlRetour return_controler){
        this.add(new JLabel(message));
        JButton return_button = new JButton("Accueil");
        return_button.addActionListener(return_controler);
        this.add(return_button);
    }
}
