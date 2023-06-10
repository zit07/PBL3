package datdocanteen.Model;

public class HoadonchitietModel {
	private int ID_hoadonchitiet, ID_hoadon, ID_monan, soluong;
	private double gia;
	private String tenmon;
	
	public HoadonchitietModel() {}
	
	public HoadonchitietModel(int ID_hoadonchitiet, int ID_hoadon, int ID_monan, String tenmon, int soluong, double gia) {
		this.ID_hoadonchitiet = ID_hoadonchitiet;
		this.ID_hoadon = ID_hoadon;
		this.ID_monan = ID_monan;
		this.tenmon = tenmon;
		this.soluong = soluong; 
		this.gia = gia;
	}

	public int getID_hoadonchitiet() {
		return ID_hoadonchitiet;
	}

	public void setID_hoadonchitiet(int iD_hoadonchitiet) {
		ID_hoadonchitiet = iD_hoadonchitiet;
	}

	public int getID_hoadon() {
		return ID_hoadon;
	}

	public void setID_hoadon(int iD_hoadon) {
		ID_hoadon = iD_hoadon;
	}
	
	public int getID_monan() {
		return ID_monan;
	}

	public void setID_monan(int iD_monan) {
		ID_monan = iD_monan;
	}

	public String getTenmon() {
		return tenmon;
	}

	public void setTenmon(String tenmon) {
		this.tenmon = tenmon;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}	
}
