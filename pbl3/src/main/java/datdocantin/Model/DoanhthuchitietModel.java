package datdocantin.Model;

public class DoanhthuchitietModel {
	private int ID_doangthuchitiet, ID_doanhthu, soluong;
	private String tenmon;
	private double gia;
	
	public DoanhthuchitietModel() {}
	
	public DoanhthuchitietModel(int ID_doangthuchitiet, int ID_doanhthu, String tenmon, int soluong, double gia) {
		this.ID_doangthuchitiet = ID_doangthuchitiet;
		this.ID_doanhthu = ID_doanhthu;
		this.tenmon = tenmon;
		this.soluong = soluong;
		this.gia = gia;
	}

	public int getID_doangthuchitiet() {
		return ID_doangthuchitiet;
	}

	public void setID_doangthuchitiet(int iD_doangthuchitiet) {
		ID_doangthuchitiet = iD_doangthuchitiet;
	}

	public int getID_doanhthu() {
		return ID_doanhthu;
	}

	public void setID_doanhthu(int iD_doanhthu) {
		ID_doanhthu = iD_doanhthu;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public String getTenmon() {
		return tenmon;
	}

	public void setTenmon(String tenmon) {
		this.tenmon = tenmon;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}
	
	
}
