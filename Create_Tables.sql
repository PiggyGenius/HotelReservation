/* TINYINT réserve un octet, suffisant pour stocké l'id chambre(100 chambres max)
 ou le boolean propre ou pas(pas de type boolean sur mysql)*/
CREATE TABLE Room (
State ENUM('Twin_Beds','Single_Bed','Single_Beds') NOT NULL,
IdRoom TINYINT(1) NOT NULL AUTO_INCREMENT,
Clean TINYINT(1) NOT NULL,
Occupied TINYINT(1) NOT NULL,
PRIMARY KEY(IdRoom)
);

CREATE TABLE RoomToBeCleaned(
IdRoom TINYINT(1) NOT NULL,
IdCleaner TINYINT(1),
PRIMARY KEY(IdRoom),
FOREIGN KEY(IdRoom) REFERENCES Room(IdRoom)
);

CREATE TABLE CleaningTeam(
IdCleaner TINYINT(1) NOT NULL AUTO_INCREMENT,
IdCleaningRoom TINYINT(1),
Free TINYINT(1) NOT NULL,
PRIMARY KEY(IdCleaner),
FOREIGN KEY(IdCleaningRoom) REFERENCES RoomToBeCleaned(IdRoom)
);

CREATE TABLE RoomHistory(
IdRoom TINYINT(1) NOT NULL,
DataDate DATE NOT NULL,
IdClient TINYINT(1),
PRIMARY KEY(IdRoom, DataDate)
);


/* You must create the cleaners before doing this 
ALTER TABLE RoomToBeCleaned ADD FOREIGN KEY(IdCleaner) REFERENCES CleaningTeam(IdCleaner);
*/
