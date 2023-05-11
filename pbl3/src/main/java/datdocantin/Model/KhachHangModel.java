package datdocantin.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KhachHangModel {
    private Integer ID_khachhang, ID_canteen, PIN;
    private String hoten, gioitinh, sodienthoai, email, yeuthich;
    private LocalDate ngaysinh;
    private Double chieucao, cannang;
    private byte[] avatar;

	public KhachHangModel() {
		// TODO Auto-generated constructor stub
	}
    
    public KhachHangModel(Integer ID_khachhang, String hoten, LocalDate ngaysinh, String gioitinh, Double chieucao, Double cannang, String sodienthoai, String email, Integer ID_canteen, String yeuthich, Integer PIN,byte[] avatar) {
    	this.ID_khachhang = ID_khachhang;
    	this.hoten = hoten;
    	this.ngaysinh = ngaysinh;
    	this.gioitinh = gioitinh;
    	this.chieucao = chieucao;
    	this.cannang = cannang;
    	this.sodienthoai = sodienthoai;
    	this.email = email;
    	this.ID_canteen = ID_canteen;
    	this.yeuthich = yeuthich;
    	this.PIN = PIN;
    	this.avatar = avatar;
    }
    
	public int getID_khachhang() {
		return ID_khachhang;
	}

	public void setID_khachhang(Integer iD_khachhang) {
		ID_khachhang = iD_khachhang;
	}

	public int getID_canteen() {
		return ID_canteen;
	}

	public void setID_canteen(Integer iD_canteen) {
		ID_canteen = iD_canteen;
	}

	public int getPIN() {
		return PIN;
	}

	public void setPIN(Integer pIN) {
		PIN = pIN;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getYeuthich() {
		return yeuthich;
	}

	public void setYeuthich(String yeuthich) {
		this.yeuthich = yeuthich;
	}
 
	public Date getNgaysinh() {
		if (ngaysinh != null) {
			return Date.valueOf(ngaysinh);
	    } else {
	        return null;
	    }
	}
	
//	public LocalDate getNgaysinhLocalDate() {
//		return ngaysinh;
//	}

	public void setNgaysinh(LocalDate ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public double getChieucao() {
		return chieucao;
	}

	public void setChieucao(Double chieucao) {
		this.chieucao = chieucao;
	}

	public double getCannang() {
		return cannang;
	}

	public void setCannang(Double cannang) {
		this.cannang = cannang;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}


	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("30/04/1975", formatter);
        System.out.println(date);
	}
	
}

