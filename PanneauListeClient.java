import java.awt.*;
import javax.swing.*;

public class PanneauListeClient extends JPanel {
    public PanneauListeClient(ControlListeClient client_list_controler,String[][] list){
		this.setLayout(new GridLayout(list.length+1,7));

		this.add(new JLabel("id"));
		this.add(new JLabel("nom "));
		this.add(new JLabel("prenom"));
		this.add(new JLabel("ville"));
		this.add(new JLabel("NbrePersonnes"));
		this.add(new JLabel("Designation"));
		this.add(new JLabel("NbreNuits"));

		for(int i=0;i<list.length;i++){
		    JButton select = new JButton(list[i][0]);
		    this.add(select);
		    select.addActionListener(client_list_controler);
		    for(int n=1;n<list[i].length;n++)
			    this.add(new JLabel(list[i][n]));
		}
	}
}
