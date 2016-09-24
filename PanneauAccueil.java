import java.awt.*;
import javax.swing.*;

public class PanneauAccueil extends JPanel {
    public PanneauAccueil(ControlAccueil controler){
		JButton attribution_button = new JButton("Attribution de chambre");
		JButton statistic_button = new JButton("Statistiques générales");
        JButton leaving_button = new JButton("Départ de client");

        this.add(attribution_button, BorderLayout.NORTH);
		this.add(statistic_button, BorderLayout.CENTER);
        this.add(leaving_button, BorderLayout.SOUTH);

		attribution_button.addActionListener(controler);
		statistic_button.addActionListener(controler);
        leaving_button.addActionListener(controler);
    }
}
