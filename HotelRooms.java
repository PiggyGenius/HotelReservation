import java.sql.*;

public class HotelRooms {
    private static Statement stmt=null;
    private static Access connection=null;

    public HotelRooms(){
        this.connection = new Access();
        this.stmt = this.connection.getStatement();
    }

    public int getRoom(String state){
        try {
            ResultSet rs=null;
            rs=this.stmt.executeQuery("SELECT IdRoom FROM Room WHERE State=\""+state+"\" AND Clean=1 AND Occupied=0;");
            if(!rs.next()){
                rs.close();
                return 0;
            }
            int room_number=rs.getInt("IdRoom");
            rs.close();
            return room_number;
        } catch(SQLException se){
            System.out.println(se.getMessage());
            return -1;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int[] getRooms(String state){
        try {
            ResultSet rs=null;
            rs=this.stmt.executeQuery("SELECT COUNT(IdRoom) FROM Room WHERE State=\""+state+"\" AND CLEAN=1 AND Occupied=0;");
            rs.first();
            int result_number=rs.getInt(1);

            if(result_number==0){
                int[] result = new int[1];
                result[0]=0;
                rs.close();
                return result;
            }
            rs.close();
            rs=null;
            rs=this.stmt.executeQuery("SELECT IdRoom FROM Room WHERE State=\""+state+"\" AND Clean=1 AND Occupied=0;");
            int[] result = new int[result_number];
            rs.first();
            for(int i=0;i<result_number;i++){
                result[i]=rs.getInt("IdRoom");
                rs.next();
            }
            rs.close();
            return result;
        } catch(SQLException se){
            System.out.println(se.getMessage());
            int[] result = new int[1];
            result[0]=-1;
            return result;
        } catch(Exception e){
            System.out.println(e.getMessage());
            int[] result = new int[1];
            result[0]=-1;
            return result;
        }
    }

    public void setRoom(int id_room,String date,int id_client){
        try {
            this.stmt.executeUpdate("Update Room SET Occupied=1 WHERE IdRoom="+id_room+";");
            this.stmt.executeUpdate("INSERT INTO RoomHistory() VALUES("+id_room+",\""+date+"\","+id_client+");");
        } catch(SQLException se){
            System.out.println(se.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setRoomUnoccupied(int id_room){
        try {
            this.stmt.executeUpdate("Update Room SET Occupied=0, Clean=0 WHERE IdRoom="+id_room+";");
        } catch(SQLException se){
            System.out.println(se.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection(){
        this.connection.closeConnection();
    }

    public String[][] getReservations(String date){
        try {
            this.connection.closeConnection();
            AccessReservation connection_reservation = new AccessReservation();
            this.stmt=connection_reservation.getStatement();

            ResultSet rs = null;
            rs=this.stmt.executeQuery("SELECT COUNT(ClientID) FROM Client NATURAL JOIN Reservation NATURAL JOIN Categorie WHERE DateArrivee=\""+date+"\";");
            rs.first();
            int result_number=rs.getInt(1);
            rs.close();
            if(result_number==0){
                String[][] result = new String[1][1];
                result[0][0]="0";
                return result;
            }
            rs=null;
            rs=this.stmt.executeQuery("SELECT ClientID,Nom,Prenom,Ville,NbrePersonnes,Designation,NbreNuits FROM Client NATURAL JOIN Reservation NATURAL JOIN Categorie WHERE DateArrivee=\""+date+"\";");

            String[][] result = new String[result_number][7];
            rs.first();
            for(int i=0;i<result_number;i++){
                for(int j=0;j<7;j++){
                    result[i][j]=rs.getString(j+1);
                }
                rs.next();
            }
            rs.close();
            connection_reservation.closeConnection();
            this.connection = new Access();
            this.stmt = this.connection.getStatement();
            return result;
        } catch(SQLException se){
            System.out.println(se.getMessage());
            String[][] result = new String[1][1];
            result[0][0]="-1";
            return result;
        } catch(Exception e){
            System.out.println(e.getMessage());
            String[][] result = new String[1][1];
            result[0][0]="-1";
            return result;
        }
    }

    public String[][] getRoomHistory(String date){
        try {
            ResultSet rs = null;
            rs=this.stmt.executeQuery("SELECT COUNT(IdRoom) FROM RoomHistory WHERE DataDate=\""+date+"\";");
            rs.first();
            int result_number=rs.getInt(1);
            rs.close();
            if(result_number==0){
                String[][] result = new String[1][1];
                result[0][0]="0";
                return result;
            }
            rs=null;
            rs=this.stmt.executeQuery("SELECT IdClient,IdRoom FROM RoomHistory WHERE DataDate=\""+date+"\";");

            String[][] result = new String[result_number][2];
            rs.first();
            for(int i=0;i<result_number;i++){
                for(int j=0;j<2;j++){
                    result[i][j]=rs.getString(j+1);
                }
                rs.next();
            }
            rs.close();
            return result;
        } catch(SQLException se){
            System.out.println(se.getMessage());
            String[][] result = new String[1][1];
            result[0][0]="-1";
            return result;
        } catch(Exception e){
            System.out.println(e.getMessage());
            String[][] result = new String[1][1];
            result[0][0]="-1";
            return result;
        }
    }

    public int getHotelOccupation(String date){
        try {
            ResultSet rs = null;
            rs=this.stmt.executeQuery("SELECT COUNT(IdRoom) FROM RoomHistory WHERE DataDate=\""+date+"\";");
            rs.first();
            int result_number=rs.getInt(1);
            rs.close();
            return result_number;
        } catch(SQLException se){
            System.out.println(se.getMessage());
            return -1;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int getRoomInRoomHistory(String date,int id_room){
        try {
            ResultSet rs = null;
            rs=this.stmt.executeQuery("SELECT IdRoom FROM RoomHistory WHERE DataDate=\""+date+"\" AND IdRoom="+id_room+";");
            if(!rs.next()){
                rs.close();
                return 0;
            }
            rs.close();
            return 1;
        } catch(SQLException se){
            System.out.println(se.getMessage());
            return -1;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
