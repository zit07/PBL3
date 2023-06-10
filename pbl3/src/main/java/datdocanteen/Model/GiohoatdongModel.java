package datdocanteen.Model;

public class GiohoatdongModel {
	private Integer ID_giohoatdong, ID_canteen, thu;
	private String giomocua, giodongcua;
	
	public GiohoatdongModel() {}
	public GiohoatdongModel(Integer ID_giohoatdong, Integer ID_canteen, Integer thu, String giomocua, String giodongcua) {
		this.ID_giohoatdong = ID_giohoatdong;
		this.ID_canteen = ID_canteen;
		this.thu = thu;
		this.giomocua = giomocua;
		this.giodongcua = giodongcua;
	}
	public int getID_giohoatdong() {
		return ID_giohoatdong;
	}
	public void setID_giohoatdong(Integer iD_giohoatdong) {
		ID_giohoatdong = iD_giohoatdong;
	}
	public int getID_canteen() {
		return ID_canteen;
	}
	public void setID_canteen(Integer iD_canteen) {
		ID_canteen = iD_canteen;
	}
	public int getThu() {
		return thu;
	}
	public void setThu(Integer thu) {
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
