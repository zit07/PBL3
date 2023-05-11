package datdocantin.Model;

public class LichsutimkiemModel {
	private Integer ID_lichsutimkiem, ID_nguoidung;
	String noidung;
	public LichsutimkiemModel() {}
	
	public LichsutimkiemModel(Integer ID_lichsutimkiem, Integer ID_nguoidung, String noidung) {
		this.ID_lichsutimkiem = ID_lichsutimkiem;
		this.ID_nguoidung = ID_nguoidung;
		this.noidung = noidung;
	}

	public int getID_lichsutimkiem() {
		return ID_lichsutimkiem;
	}

	public void setID_lichsutimkiem(Integer iD_lichsutimkiem) {
		ID_lichsutimkiem = iD_lichsutimkiem;
	}

	public int getID_nguoidung() {
		return ID_nguoidung;
	}

	public void setID_nguoidung(Integer iD_nguoidung) {
		ID_nguoidung = iD_nguoidung;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
}
