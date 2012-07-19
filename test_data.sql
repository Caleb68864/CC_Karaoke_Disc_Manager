DROP TABLE IF EXISTS `tblDiscs`;

CREATE TABLE `tblDiscs` (
  `Disc_ID` INTEGER NOT NULL AUTO_INCREMENT, 
  `Disc_Disc` VARCHAR(255), 
  `Disc_Track` VARCHAR(255), 
  `Disc_Title` VARCHAR(255), 
  `Disc_Artist` VARCHAR(255), 
  `Disc_Format` VARCHAR(255), 
  PRIMARY KEY (`Disc_ID`)
);

COMMIT;

INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('1','1','Running Gun','Marty Robbins','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('1','2','El Paso','Marty Robbins','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('1','3','Strawberry Roan','Marty Robbins','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('1','4','Big Iron','Marty Robbins','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('1','5','Old Red','Marty Robbins','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('2','1','Friends In Low Places','George Straight','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('2','2','Write This Down','George Straight','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('2','3','Monster','Skillet','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('2','4','Voodoo','Godsmack','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('2','5','Intro The Night','Santanna','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('3','1','I Need A Hero','Bonnie Tyler','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('3','2','Comatose','Skillet','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('3','3','I Stand Alone','Godsmack','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('3','4','Running Blind','Godsmack','Disc');
INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ('3','5','Utah Carol','Marty Robbins','Disc');
