package datdocantin.Model;

import java.sql.Date;
import java.time.LocalDate;

public class MonAnModel {
	private String tenmon,mota,thanhphan,huongvi;
	private Integer ID_monan,ID_canteen,ID_loaithucan,trangthai,daban,xoa;
	private Double giacu, giahientai;
	private LocalDate ngaytao;
	private byte[] hinhanhchinh;
	public MonAnModel() {}
	
	public MonAnModel(Integer ID_monan,Integer ID_canteen,String tenmon,String mota,String thanhphan,String huongvi,Integer ID_loaithucan,Double giacu,Double giahientai,LocalDate ngaytao,byte[] hinhanhchinh,Integer trangthai,Integer daban,Integer xoa){
		this.ID_monan = ID_monan;
		this.ID_canteen = ID_canteen;
		this.tenmon = tenmon;
		this.mota = mota;
		this.thanhphan = thanhphan;
		this.huongvi = huongvi;
		this.ID_loaithucan = ID_loaithucan;
		this.giacu = giacu;
		this.giahientai = giahientai;
		this.ngaytao = ngaytao;
		this.hinhanhchinh = hinhanhchinh;
		this.trangthai = trangthai;
		this.daban = daban;
		this.xoa = xoa;
	}

	public String getTenmon() {
		return tenmon;
	}

	public void setTenmon(String tenmon) {
		this.tenmon = tenmon;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getThanhphan() {
		return thanhphan;
	}

	public void setThanhphan(String thanhphan) {
		this.thanhphan = thanhphan;
	}

	public String getHuongvi() {
		return huongvi;
	}

	public void setHuongvi(String huongvi) {
		this.huongvi = huongvi;
	}

	public int getID_monan() {
		return ID_monan;
	}

	public void setID_monan(Integer iD_monan) {
		ID_monan = iD_monan;
	}

	public int getID_canteen() {
		return ID_canteen;
	}

	public void setID_canteen(Integer iD_canteen) {
		ID_canteen = iD_canteen;
	}

	public int getID_loaithucan() {
		return ID_loaithucan;
	}

	public void setID_loaithucan(Integer ID_loaithucan) {
		this.ID_loaithucan = ID_loaithucan;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Integer trangthai) {
		this.trangthai = trangthai;
	}

	public int getDaban() {
		return daban;
	}

	public void setDaban(Integer daban) {
		this.daban = daban;
	}

	public int getXoa() {
		return xoa;
	}

	public void setXoa(Integer xoa) {
		this.xoa = xoa;
	}

	public double getGiacu() {
		return giacu;
	}

	public void setGiacu(Double giacu) {
		this.giacu = giacu;
	}

	public double getGiahientai() {
		return giahientai;
	}

	public void setGiahientai(Double giahientai) {
		this.giahientai = giahientai;
	}

	public Date getNgaytao() {
		return Date.valueOf(ngaytao);
	}

	public void setNgaytao(LocalDate ngaytao) {
		this.ngaytao = ngaytao;
	}

	public byte[] getHinhanhchinh() {
		return hinhanhchinh;
	}

	public void setHinhanhchinh(byte[] hinhanhchinh) {
		this.hinhanhchinh = hinhanhchinh;
	}
	
}
