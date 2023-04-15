package datdocantin.Model;

public class MonAnModel {
	private String id,idcantin,tenmon,mota,thanhphan,huongvi,loaithucan,giacu,giahientai,ngaytao,trangthai,daban;
	private byte[] hinhanhchinh;
	
	public MonAnModel() {}
	
	public MonAnModel(String id,String idcantin,String tenmon,String mota,String thanhphan,String huongvi,String loaithucan,String giacu,String giahientai,String ngaytao,byte[] hinhanhchinh,String trangthai,String daban){
		this.id = id;
		this.idcantin = idcantin;
		this.tenmon = tenmon;
		this.mota = mota;
		this.thanhphan = thanhphan;
		this.huongvi = huongvi;
		this.loaithucan = loaithucan;
		this.giacu = giacu;
		this.giahientai = giahientai;
		this.ngaytao = ngaytao;
		this.hinhanhchinh = hinhanhchinh;
		this.trangthai = trangthai;
		this.daban = daban;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdcantin() {
		return idcantin;
	}
	public void setIdcantin(String idcantin) {
		this.idcantin = idcantin;
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
	public String getLoaithucan() {
		return loaithucan;
	}
	public void setLoaithucan(String loaithucan) {
		this.loaithucan = loaithucan;
	}
	public String getGiacu() {
		return giacu;
	}
	public void setGiacu(String giacu) {
		this.giacu = giacu;
	}
	public String getGiahientai() {
		return giahientai;
	}
	public void setGiahientai(String giahientai) {
		this.giahientai = giahientai;
	}
	public String getNgaytao() {
		return ngaytao;
	}
	public void setNgaytao(String ngaytao) {
		this.ngaytao = ngaytao;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public byte[] getHinhanhchinh() {
		return hinhanhchinh;
	}
	public void setHinhanhchinh(byte[] hinhanhchinh) {
		this.hinhanhchinh = hinhanhchinh;
	}
	public String getDaban() {
		return daban;
	}
	public void setDaban(String daban) {
		this.daban = daban;
	}
}
