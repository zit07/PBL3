package datdocanteen.Model;

import java.sql.Date;
import java.time.LocalDate;

public class DoanhthuModel {
	private int ID_doanhthu, ID_canteen, soluongdaban;
	private LocalDate ngay;
	private double tongdoanhthu;
	
	public DoanhthuModel() {}
	
	public DoanhthuModel(int ID_doanhthu, int ID_canteen, LocalDate ngay, int soluongdaban, double tongdoanhthu) {
		this.ID_doanhthu = ID_doanhthu;
		this.ID_canteen = ID_canteen;
		this.ngay = ngay;
		this.soluongdaban = soluongdaban;
		this.tongdoanhthu = tongdoanhthu;
	}

	public int getID_doanhthu() {
		return ID_doanhthu;
	}

	public void setID_doanhthu(int iD_doanhthu) {
		ID_doanhthu = iD_doanhthu;
	}

	public int getID_canteen() {
		return ID_canteen;
	}

	public void setID_canteen(int iD_canteen) {
		ID_canteen = iD_canteen;
	}

	public int getSoluongdaban() {
		return soluongdaban;
	}

	public void setSoluongdaban(int soluongdaban) {
		this.soluongdaban = soluongdaban;
	}

	public Date getNgay() {
		return Date.valueOf(ngay);
	}

	public void setNgay(LocalDate ngay) {
		this.ngay = ngay;
	}

	public double getTongdoanhthu() {
		return tongdoanhthu;
	}

	public void setTongdoanhthu(double tongdoanhthu) {
		this.tongdoanhthu = tongdoanhthu;
	}
	
	
}
