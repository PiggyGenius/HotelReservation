import java.awt.*;
import javax.swing.*;

public class PanneauListeChambreAttribuable extends JPanel {
    public PanneauListeChambreAttribuable(int[] list,ControlListeChambreAttribuable room_controler){
	    this.setLayout(new GridLayout(list.length+1,2));
	    this.add(new JLabel("chambre id"));

	    for(int i=0;i<list.length;i++){
	        JButton bRoom = new JButton( Integer.toString(list[i]));
	        bRoom.addActionListener(room_controler);
	        add(bRoom);
	    }
    }
}
