package datdocantin.Model;

public class LichsutimkiemModel {
	String id, idcanteen, noidung;
	public LichsutimkiemModel() {}
	
	public LichsutimkiemModel(String id, String idcaString, String noidung) {
		this.id = id;
		this.idcanteen = idcaString;
		this.noidung = noidung;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdcanteen() {
		return idcanteen;
	}

	public void setIdcanteen(String idcanteen) {
		this.idcanteen = idcanteen;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
}
