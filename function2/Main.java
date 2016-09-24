import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args){
        CleaningTeam modele = new CleaningTeam();
        int[][] result = new int[100][100];
        result=modele.getRoomsToBeCleaned();
        int i=0;
        for(i=0;i<result.length;i++){
            System.out.println("Debut de section : ");
            for(int j=0;j<result[i].length;j++){
                System.out.println("id : "+result[i][j]);
            }
            System.out.println("Fin de section : ");
        }
        int id_cleaner=1;
        System.out.println("\nListe des chambres pour le cleaner numéro : "+id_cleaner);
        int[] small_result = new int[50];
        small_result=modele.getRoomsToBeCleaned(id_cleaner);
        for(i=0;i<small_result.length;i++){
            System.out.println(small_result[i]);
        }
        modele.setRoomToCleaner(3,1);
        String[] testou = new String[2];
        testou=modele.getClientDeparture(73);
        System.out.println("Date d'arrivée : "+testou[0]+"\nDate de départ : "+testou[1]);
        int testa=modele.getClientDeparture(69,"2014-07-01");
        //Problème dans les dates, un IdClient réserve deux a deux moments différents et fu. Il faudrait rajouter l'IdReservation pour éviter ce probleme !!!
        System.out.println("Date de départ a ce jour : "+testa);
    }
}
