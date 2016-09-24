import java.awt.*;
import javax.swing.*;

public class LeavingClientPanel extends JPanel {
    public LeavingClientPanel(LeavingClientControler leaving_client_controler,JTextField room_id){
        this.add(new JLabel("Rentrez le numéro de chambre : "));
        this.add(room_id);
        JButton submit_button = new JButton("Envoyer");
        submit_button.addActionListener(leaving_client_controler);
        this.add(submit_button);
    }
}
        
