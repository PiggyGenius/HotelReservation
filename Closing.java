import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Closing extends WindowAdapter {
    private HotelRooms model;

    public Closing(HotelRooms model_init){
        this.model=model_init;
    }

    public void windowClosing(WindowEvent e){
        this.model.closeConnection();
        System.exit(0);
    }
}
