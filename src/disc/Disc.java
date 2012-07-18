package disc;

public class Disc {
	String strTitle = "";
	String strArtist = "";
	String strDisc = "";
	int intTrack = 0;
	
	Disc(){
		strTitle = "";
		strArtist = "";
		strDisc = "";
		intTrack = 0;
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

	public int getIntTrack() {
		return intTrack;
	}

	public void setIntTrack(int intTrack) {
		this.intTrack = intTrack;
	}
}
