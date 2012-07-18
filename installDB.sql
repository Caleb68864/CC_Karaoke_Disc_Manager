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