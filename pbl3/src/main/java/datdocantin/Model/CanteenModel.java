package datdocantin.Model;

public class CanteenModel {
	private String id,ten,sodienthoai,email,tinh,huyen,xa,PIN;
	byte[] avatar;
	public CanteenModel() {
	}
	public CanteenModel(String id, String ten,String sodienthoai,String email,String tinh,String huyen,String xa,String PIN, byte[] avatar) {
		this.id=id;
		this.ten=ten;
		this.sodienthoai=sodienthoai;
		this.email=email;
		this.tinh=tinh;
		this.huyen=huyen;
		this.xa=xa;
		this.PIN=PIN;
		this.avatar=avatar;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
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
	public String getTinh() {
		return tinh;
	}
	public void setTinh(String tinh) {
		this.tinh = tinh;
	}
	public String getHuyen() {
		return huyen;
	}
	public void setHuyen(String huyen) {
		this.huyen = huyen;
	}
	public String getXa() {
		return xa;
	}
	public void setXa(String xa) {
		this.xa = xa;
	}
	public String getPIN() {
		return PIN;
	}
	public void setPIN(String pIN) {
		PIN = pIN;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
}
