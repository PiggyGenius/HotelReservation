
Regarder le Main.java pour avoir une idée plus concrète, ne pas oublier de
java -cp ... Le mysql-connector est dans le dossier mysql-connector


1) Initialiser le modèle : 
    HotelRooms modele = new HotelRooms();
        La connection a notre BDD est établit et le Statement est créé.

2) Obtenir une chambre propre et non occupée : 
    modele.getRoom("Twin_Beds");
        Il faut simplement mêttre en argument le type de lit(On peut voir les
        types possibles dans l'énumération créée dans Create_Tables.sql, Table
        Room. Cela renvoit un int qui est l'id de la chambre.

3) Obtenir la liste des chambres propres et non occupées :
    modele.getRooms("Single_Bed");
        Cela permet d'obtenir la liste des chambres propres et non occupés
        répondant à l'attribut sous la forme d'un tableau de Int.

4) Obtenir la liste des réservations un jour t : 
    modele.getReservations("2014-09-26");
        Cela permet d'obtenir la liste des critères de réservation sous la
        forme d'un tableau bi-dimensionnel.
        Les critères Obtenus sont les suivants : 
        Reservation i :
            ClientID,Nom,Prenom,Ville,NbrePersonnes,Designation
        Reservation i+1 :
            ClientID,Nom,Prenom,Ville,NbrePersonnes,Designation
        ...

5) Changer le statut d'une chambre en occupé : 
    modele.setRoomOccupied();
        Cela sera fait à l'arrivé d'un client, cette méthode change l'attribut
        occupied d'une chambre à 1.

6) Changer le statu d'une chambre en non occupé : 
    modele.setRoomUnoccupied();
        Cela sera fait au départ du client, cette méthode change l'attribut de
        occupied à 0 et de clean à 0 pour indiquer que la chambre n'est plus
        propre.
