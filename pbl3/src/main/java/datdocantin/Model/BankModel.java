package datdocantin.Model;

import java.util.Arrays;

public class BankModel {
	private int ID_bank_info,ID_canteen;
	private String tennganhang, stk, hovaten;
	private byte[] maQR;
	
	public BankModel() {}
	
	public BankModel(int ID_bank_info, String tennganhang, String stk, String hovaten,int ID_canteen, byte[] maQR) {
		this.ID_bank_info = ID_bank_info;
		this.tennganhang = tennganhang;
		this.stk = stk;
		this.hovaten = hovaten;
		this.ID_canteen=ID_canteen;
		this.maQR = maQR;
	}

	public int getID_bank_info() {
		return ID_bank_info;
	}

	public void setID_bank_info(int iD_bank_info) {
		ID_bank_info = iD_bank_info;
	}

	public String getTennganhang() {
		return tennganhang;
	}

	public void setTennganhang(String tennganhang) {
		this.tennganhang = tennganhang;
	}

	public String getStk() {
		return stk;
	}

	public void setStk(String stk) {
		this.stk = stk;
	}

	public String getHovaten() {
		return hovaten;
	}

	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	public byte[] getMaQR() {
		return maQR;
	}

	public void setMaQR(byte[] maQR) {
		this.maQR = maQR;
	}

	
	@Override
	public String toString() {
		return "BankModel [ID_bank_info=" + ID_bank_info + ", ID_canteen=" + ID_canteen + ", tennganhang=" + tennganhang
				+ ", stk=" + stk + ", hovaten=" + hovaten + ", maQR=" + Arrays.toString(maQR) + "]";
	}

	public int getID_canteen() {
		return ID_canteen;
	}

	public void setID_canteen(int iD_canteen) {
		ID_canteen = iD_canteen;
	}

	
	
	
}
