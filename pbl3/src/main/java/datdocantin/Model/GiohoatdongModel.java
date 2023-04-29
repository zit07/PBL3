package datdocantin.Model;

public class GiohoatdongModel {
	private String id, idcanteen, thu, giomocua, giodongcua;
	
	public GiohoatdongModel() {}
	public GiohoatdongModel(String id, String idcanteen, String thu, String giomocua, String giodongcua) {
		this.id = id;
		this.idcanteen = idcanteen;
		this.thu = thu;
		this.giomocua = giomocua;
		this.giodongcua = giodongcua;
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
	public String getThu() {
		return thu;
	}
	public void setThu(String thu) {
		this.thu = thu;
	}
	public String getGiomocua() {
		return giomocua;
	}
	public void setGiomocua(String giomocua) {
		this.giomocua = giomocua;
	}
	public String getGiodongcua() {
		return giodongcua;
	}
	public void setGiodongcua(String giodongcua) {
		this.giodongcua = giodongcua;
	}
	
}
