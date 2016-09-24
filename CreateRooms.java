import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CreateRooms {
    Statement stmt=null;
    Access connection=null;

    public CreateRooms(){
        this.connection = new Access();
        this.stmt=this.connection.getStatement();;
    }

    public void createRoom(String state){
        try {
            stmt.executeUpdate("INSERT INTO Room(State,IdRoom,Clean,Occupied) VALUES(\""+state+"\",0,1,0);");
        } catch(SQLException se){
            System.out.println(se.getMessage());
            System.exit(1);
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void createCleaners(){
        try {
            stmt.executeUpdate("INSERT INTO CleaningTeam(IdCleaner,IdCleaningRoom,Free,RoomsAssigned) VALUES(0,null,0,0);");
        } catch(SQLException se){
            System.out.println(se.getMessage());
            System.exit(1);
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
