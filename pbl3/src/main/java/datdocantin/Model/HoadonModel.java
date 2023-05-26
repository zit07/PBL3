package datdocantin.Model;

import java.sql.Date;
import java.time.LocalDate;

public class HoadonModel{
	private Integer ID_hoadon, ID_canteen, ID_khachhang, xoa;
	private LocalDate ngaytao;
	private double tongtien;
	private String trangthai, madon;
	
	public HoadonModel() {}
	
	public HoadonModel(int ID_hoadon, int ID_canteen, int ID_khachhang, LocalDate ngaytao, double tongtien, String trangthai, String madon, Integer xoa) {
		this.ID_hoadon = ID_hoadon;
		this.ID_canteen = ID_canteen;
		this.ID_khachhang = ID_khachhang;
		this.ngaytao = ngaytao;
		this.tongtien = tongtien;
		this.trangthai = trangthai;
		this.madon = madon;
		this.xoa = xoa;
	}

	public int getID_hoadon() {
		return ID_hoadon;
	}

	public void setID_hoadon(int iD_hoadon) {
		ID_hoadon = iD_hoadon;
	}

	public int getID_canteen() {
		return ID_canteen;
	}

	public void setID_canteen(int iD_canteen) {
		ID_canteen = iD_canteen;
	}

	public int getID_khachhang() {
		return ID_khachhang;
	}
	
	public Date getNgaytao() {
		return Date.valueOf(ngaytao);
	}

	public void setNgaytao(LocalDate ngaytao) {
		this.ngaytao = ngaytao;
	}

	public void setID_khachhang(int iD_khachhang) {
		ID_khachhang = iD_khachhang;
	}

	public double getTongtien() {
		return tongtien;
	}

	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public String getMadon() {
		return madon;
	}

	public void setMadon(String madon) {
		this.madon = madon;
	}

	public int getXoa() {
		return xoa;
	}

	public void setXoa(Integer xoa) {
		this.xoa = xoa;
	}
	
	
	
}
