DELIMITER /
CREATE TRIGGER Room_INTO_RoomToBeCleaned
AFTER UPDATE ON Room
FOR EACH ROW
BEGIN
    IF NEW.Clean = 0 THEN
        INSERT INTO RoomToBeCleaned(IdRoom,IdCleaner)
        VALUES(NEW.IdRoom,NULL);
    END IF;
    IF NEW.Clean = 1 THEN
        DELETE FROM RoomToBeCleaned WHERE IdRoom=NEW.IdRoom;
    END IF;
END; /
DELIMITER ;

DELIMITER /
CREATE TRIGGER SET_IdCleaner_ON_RoomToBeCleaned
BEFORE INSERT ON RoomToBeCleaned
FOR EACH ROW
BEGIN
    IF NEW.IdCleaner IS NULL THEN
        SET NEW.IdCleaner = (SELECT IdCleaner FROM CleaningTeam ORDER BY RoomsAssigned ASC LIMIT 1);
    END IF;
END; /
DELIMITER ;
