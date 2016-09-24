import java.sql.*;

public class CleaningTeam {
    private static Statement stmt=null;
    private static Access connection=null;

    public CleaningTeam(){
        this.connection = new Access();
        this.stmt = this.connection.getStatement();
    }

    public void setRoomCleaned(int id){
        try {
            this.stmt.executeUpdate("UPDATE Room SET Clean=1 WHERE IdRoom="+id+";");
        } catch(SQLException se){
            System.out.println(se.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int[][] getRoomsToBeCleaned(){
        try {
            ResultSet rs=null;
            rs=this.stmt.executeQuery("SELECT COUNT(IdRoom) FROM RoomToBeCleaned;");
            rs.first();
            int result_number=rs.getInt(1);
            rs.close();
            if(result_number==0){
                int[][] result = new int[1][1];
                result[0][0]=-1;
                return result;
            }
            rs=null;
            rs=this.stmt.executeQuery("SELECT IdRoom,IdCleaner FROM RoomToBeCleaned;");
            int[][] result = new int[result_number][2];
            rs.first();
            for(int i=0;i<result_number;i++){
                for(int j=0;j<2;j++){
                    result[i][j]=rs.getInt(j+1);
                }
                rs.next();
            }
            rs.close();
            return result;
        } catch(SQLException se){
            System.out.println(se.getMessage());
            int[][] result = new int[1][1];
            result[0][0]=-1;
            return result;
        } catch(Exception e){
            System.out.println(e.getMessage());
            int[][] result = new int[1][1];
            result[0][0]=-1;
            return result;
        }
    }

    public int[] getRoomsToBeCleaned(int id_cleaner){
        try {
            ResultSet rs=null;
            rs=this.stmt.executeQuery("SELECT COUNT(IdRoom) FROM RoomToBeCleaned WHERE IdCleaner="+id_cleaner+";");
            rs.first();
            int result_number=rs.getInt(1);
            rs.close();
            if(result_number==0){
                int[] result = new int[1];
                result[0]=-1;
                return result;
            }
            rs=null;
            rs=this.stmt.executeQuery("SELECT IdRoom FROM RoomToBeCleaned WHERE IdCleaner="+id_cleaner+";");
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

    //Ne pas oublier qu'un trigger fais cela en automatique, cela correspond a assigner une chambre a un autre cleaner que celui assigné automatiquement !
    public void setRoomToCleaner(int id_cleaner,int id_room){
        try {
            this.stmt.executeUpdate("Update RoomToBeCleaned SET IdCleaner="+id_cleaner+" WHERE IdRoom="+id_room+";");
        } catch(SQLException se){
            System.out.println(se.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String[] getClientDeparture(int id_client){
        try {
            this.connection.closeConnection();
            AccessReservation connection_reservation = new AccessReservation();
            this.stmt=connection_reservation.getStatement();

            ResultSet rs = null;
            rs=this.stmt.executeQuery("SELECT DateArrivee,NbreNuits FROM Reservation WHERE ClientID="+id_client+" ORDER BY DateArrivee ASC LIMIT 1;");

            if(!rs.next()){
                rs.close();
                String[] result = new String[1];
                result[0]="-1";
                return result;
            }
            String[] result = new String[2];
            result[0] = rs.getString("DateArrivee");
            result[1] = rs.getString("NbreNuits");

            rs.close();
            connection_reservation.closeConnection();
            this.connection = new Access();
            this.stmt = this.connection.getStatement();
            return result;
        } catch(SQLException se){
            System.out.println(se.getMessage());
            String[] result = new String[1];
            result[0]="-1";
            return result;
        } catch(Exception e){
            System.out.println(e.getMessage());
            String[] result = new String[1];
            result[0]="-1";
            return result;
        }
    }
    public int getClientDeparture(int id_client,String date){
        try {
            this.connection.closeConnection();
            AccessReservation connection_reservation = new AccessReservation();
            this.stmt=connection_reservation.getStatement();

            ResultSet rs = null;
            rs=this.stmt.executeQuery("SELECT NbreNuits FROM Reservation WHERE ClientID="+id_client+" AND DateArrivee=\""+date+"\" LIMIT 1;");

            if(!rs.next()){
                rs.close();
                return -1;
            }

            int result=rs.getInt("NbreNuits");
            rs.close();
            connection_reservation.closeConnection();
            this.connection = new Access();
            this.stmt = this.connection.getStatement();
            return result;
        } catch(SQLException se){
            System.out.println(se.getMessage());
            return -1;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

}
