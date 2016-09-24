import java.awt.*;
import javax.swing.*;


public class Main {
    public static void main(String[] args){
        HotelRooms model = new HotelRooms();
        Fenetre window = new Fenetre(model);
		window.setVisible(true);             
    }
}
