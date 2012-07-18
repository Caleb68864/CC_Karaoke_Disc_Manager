package kdm;

public class Variables {
	private static String strConn = "L:\\eclipse\\workspace\\CC_Karaoke_Disc_Manager\\DB\\kdm_h2db";
	private static String strUser = "Admin";
	private static String strPassword = "";
	private static String strAppName = "CC Karaoke Disc Manager";

	public static String getStrConn() {
		return strConn;
	}

	public static String getStrAppName() {
		return strAppName;
	}

	public static void setStrConn(String strConn) {
		Variables.strConn = strConn;
	}

	public static void setStrAppName(String strAppName) {
		Variables.strAppName = strAppName;
	}

	public static String getStrPassword() {
		return strPassword;
	}

	public static String getStrUser() {
		return strUser;
	}
}
