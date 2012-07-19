package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CC_Library.CC_Test;

import ui.Main;
import disc.Disc;

public class Queries {
	public static ArrayList<Disc> getDiscList() throws SQLException {
		String query = "Select Disc_ID FROM tblDiscs";
		ArrayList<Disc> alDiscs = new ArrayList<Disc>();
		try {
			for (Integer i : runDbIntQuery(query, "Disc_ID")) {
				alDiscs.add(new Disc(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alDiscs;
	}

	public static ArrayList<Disc> searchDiscList(String strDisc,
			String strTrack, String strTitle, String strArtist)
			throws SQLException {

		ArrayList<String> alValues = new ArrayList<String>();
		String[][] arValues = new String[4][3];

		arValues[0][0] = "Disc_Disc";
		arValues[0][1] = strDisc;
		arValues[1][0] = "Disc_Track";
		arValues[1][1] = strTrack;
		arValues[2][0] = "Disc_Title";
		arValues[2][1] = strTitle;
		arValues[3][0] = "Disc_Artist";
		arValues[3][1] = strArtist;

		String strWhere = "";

		for (int j = 0; j < arValues.length; j++) {
			if (!arValues[j][1].isEmpty()) {
				alValues.add("UPPER(" + arValues[j][0] + ")" + " Like UPPER('%"
						+ arValues[j][1] + "%')");
			}
		}

		for (int i = 0; i < alValues.size(); i++) {
			strWhere += alValues.get(i).toString();
			if (i != alValues.size() - 1 && alValues.size() > 1) {
				strWhere += " AND ";
			}
		}

		if (!strWhere.isEmpty()) {
			String strTemp = "";
			strTemp = " WHERE " + strWhere;
			strWhere = strTemp;
		}

		String query = "Select Disc_ID FROM tblDiscs" + strWhere + ";";
		//CC_Test.MsgBox(query);
		ArrayList<Disc> alDiscs = new ArrayList<Disc>();
		try {
			for (Integer i : runDbIntQuery(query, "Disc_ID")) {
				alDiscs.add(new Disc(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alDiscs;
	}

	public static ArrayList<Integer> runDbIntQuery(String query, String field)
			throws Exception {
		Main.getDB().Connect();
		ResultSet rs = Main.getDB().query(query);
		ArrayList<Integer> lst = new ArrayList<Integer>();
		while (rs.next()) {
			lst.add(rs.getInt(field));
		}
		Main.getDB().Disconnect();
		return lst;
	}

	public static ArrayList<String> runDbStrQuery(String query, String field)
			throws Exception {

		Main.getDB().Connect();
		ResultSet rs = Main.getDB().query(query);
		ArrayList<String> lst = new ArrayList<String>();
		while (rs.next()) {
			lst.add(rs.getString(field));
		}
		Main.getDB().Disconnect();
		return lst;
	}
}