package disc;

import kdm.Variables;

import java.sql.ResultSet;
import java.sql.SQLException;

import CC_Library.CC_H2;

public class Disc {
	int intDiscID = 0;
	String strDisc = "";
	String strTrack = "";
	String strTitle = "";
	String strArtist = "";
	String strFormat = "";

	Disc(){
		this.intDiscID = 0;
		this.strDisc = "";
		this.strTrack = "";
		this.strTitle = "";
		this.strArtist = "";
		this.strFormat = "";
	}

	public Disc(int intID) throws SQLException{
		setIntDiscID(intID);
		this.strTitle = "";
		this.strArtist = "";
		this.strDisc = "";
		this.strTrack = "";
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
	
	public void addDisc(Disc d) throws SQLException{
		CC_H2 db = new CC_H2(Variables.getStrConn(), Variables.getStrUser(),
				Variables.getStrPassword(), CC_H2.Embedded());
		db.Connect();
		String strSQL = "";
		strSQL += "INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES (" +
				  "'" + d.getStrDisc() + "'," + d.getStrTrack() + ",'" + d.getStrTitle() + "','" + d.getStrArtist() + 
				  "','" + d.getStrFormat() + "');";
		db.executeQuery(strSQL);
		db.Disconnect();
		db.ClosePool();
	}
	
	public void deleteDisc(int intID) throws SQLException{
		CC_H2 db = new CC_H2(Variables.getStrConn(), Variables.getStrUser(),
				Variables.getStrPassword(), CC_H2.Embedded());
		db.Connect();
		db.executeQuery("DELETE * FROM tblDiscs WHERE DISC_ID = "
				+ intID);
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
