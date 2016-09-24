import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Access  {
    Connection conn=null;
    Statement stmt=null;

    public Access(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String DB_URL="jdbc:mysql://dwarves.iut-fbleau.fr/carre";
            String USER="carre";
            String PASSWORD="carre";
            conn=DriverManager.getConnection(DB_URL,USER,PASSWORD);
        } catch(SQLException se){
            System.out.println(se.getMessage());
            System.exit(1);
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    public Statement getStatement(){
        try {
            this.stmt=conn.createStatement();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return this.stmt;
    }
    public void closeConnection(){
        try {
            this.stmt.close();
            this.conn.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
