package datdocantin.Model;

import java.util.Arrays;

public class CanteenModel {
	private Integer ID_canteen, ID_diachi,PIN;
	private String ten, sodienthoai, email;
	byte[] avatar;
	public CanteenModel() {
	}
	public CanteenModel(Integer ID_canteen, String ten,String sodienthoai,String email, Integer ID_diachi,Integer PIN, byte[] avatar ) {
		this.ID_canteen=ID_canteen;
		this.ten=ten;
		this.sodienthoai=sodienthoai;
		this.email=email;
		this.ID_diachi=ID_diachi;
		this.PIN=PIN; 
		this.avatar=avatar;
		//this.ID_bank_info=ID_bank_info;
	}
	
	@Override
	public String toString() {
		return "CanteenModel [ID_canteen=" + ID_canteen + ", ID_diachi=" + ID_diachi + ", PIN=" + PIN + ", ten=" + ten
				+ ", sodienthoai=" + sodienthoai + ", email=" + email + ", avatar=" + Arrays.toString(avatar) + "]";
	}
	public Integer getID_canteen() {
		return ID_canteen;
	}
	public void setID_canteen(Integer iD_canteen) {
		ID_canteen = iD_canteen;
	}
	public Integer getID_diachi() {
		return ID_diachi;
	}
	public void setID_diachi(Integer iD_diachi) {
		ID_diachi = iD_diachi;
	}
	public Integer getPIN() {
		return PIN;
	}
	public void setPIN(Integer pIN) {
		PIN = pIN;
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
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	/*
	 * public Integer getID_bank_info() { return ID_bank_info; } public void
	 * setID_bank_info(Integer iD_bank_info) { ID_bank_info = iD_bank_info; }
	 */
	
}
