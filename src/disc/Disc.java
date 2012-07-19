package disc;

import kdm.Variables;

import java.sql.ResultSet;
import java.sql.SQLException;

import CC_Library.CC_H2;
import CC_Library.CC_Test;

public class Disc {
	int intDiscID = 0;
	String strDisc = "";
	String strTrack = "";
	String strTitle = "";
	String strArtist = "";
	String strFormat = "";

	public Disc() {

	}

	public Disc(int intID) throws SQLException {
		setIntDiscID(intID);
		CC_H2 db = new CC_H2(Variables.getStrConn(), Variables.getStrUser(),
				Variables.getStrPassword(), CC_H2.Embedded());
		db.Connect();
		ResultSet rs = db.query("Select * FROM tblDiscs WHERE DISC_ID = "
				+ intID);
		while (rs.next()) {
			setIntDiscID(rs.getInt("Disc_ID"));
			setStrDisc(rs.getString("Disc_Disc"));
			setStrTrack(rs.getString("Disc_Track"));
			setStrTitle(rs.getString("Disc_Title"));
			setStrArtist(rs.getString("Disc_Artist"));
			setStrFormat(rs.getString("Disc_Format"));
		}
		db.Disconnect();
		db.ClosePool();
	}

	public void addDisc() throws SQLException {
		CC_H2 db = new CC_H2(Variables.getStrConn(), Variables.getStrUser(),
				Variables.getStrPassword(), CC_H2.Embedded());
		db.Connect();
		String strSQL = "";
		strSQL += "INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ("
				+ "'"
				+ this.getStrDisc()
				+ "',"
				+ this.getStrTrack()
				+ ",'"
				+ this.getStrTitle()
				+ "','"
				+ this.getStrArtist()
				+ "','"
				+ this.getStrFormat() + "');";
		db.executeQuery(strSQL);
		db.Disconnect();
		db.ClosePool();
	}

	public void deleteDisc() throws SQLException {
		CC_H2 db = new CC_H2(Variables.getStrConn(), Variables.getStrUser(),
				Variables.getStrPassword(), CC_H2.Embedded());
		db.Connect();
		db.executeQuery("DELETE FROM tblDiscs WHERE DISC_ID = "
				+ this.getIntDiscID());
		db.Disconnect();
		db.ClosePool();
	}

	public void updateDisc() throws SQLException {
		CC_H2 db = new CC_H2(Variables.getStrConn(), Variables.getStrUser(),
				Variables.getStrPassword(), CC_H2.Embedded());
		db.Connect();
		if (!this.getStrDisc().isEmpty()) {
			db.executeQuery("UPDATE tblDiscs SET Disc_Disc ='"
					+ this.getStrDisc() + "' WHERE Disc_Id = " + this.getIntDiscID()
					+ ";");
		}
		
		if (!this.getStrTrack().isEmpty()) {
			db.executeQuery("UPDATE tblDiscs SET Disc_Track ='"
					+ this.getStrTrack() + "' WHERE Disc_Id = " + this.getIntDiscID()
					+ ";");
		}
		
		if (!this.getStrTitle().isEmpty()) {
			db.executeQuery("UPDATE tblDiscs SET Disc_Title ='"
					+ this.getStrTitle() + "' WHERE Disc_Id = " + this.getIntDiscID()
					+ ";");
		}
		
		if (!this.getStrArtist().isEmpty()) {
			db.executeQuery("UPDATE tblDiscs SET Disc_Artist ='"
					+ this.getStrArtist() + "' WHERE Disc_Id = " + this.getIntDiscID()
					+ ";");
		}
		
		if (!this.getStrFormat().isEmpty()) {
			db.executeQuery("UPDATE tblDiscs SET Disc_Format ='"
					+ this.getStrFormat() + "' WHERE Disc_Id = " + this.getIntDiscID()
					+ ";");
		}
		
		db.Disconnect();
		db.ClosePool();
	}

	public int getIntDiscID() {
		return intDiscID;
	}

	public void setIntDiscID(int intDiscID) {
		this.intDiscID = intDiscID;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public String getStrArtist() {
		return strArtist;
	}

	public void setStrArtist(String strArtist) {
		this.strArtist = strArtist;
	}

	public String getStrDisc() {
		return strDisc;
	}

	public void setStrDisc(String strDisc) {
		this.strDisc = strDisc;
	}

	public String getStrTrack() {
		return strTrack;
	}

	public void setStrTrack(String strTrack) {
		this.strTrack = strTrack;
	}

	public String getStrFormat() {
		return strFormat;
	}

	public void setStrFormat(String strFormat) {
		this.strFormat = strFormat;
	}
}
