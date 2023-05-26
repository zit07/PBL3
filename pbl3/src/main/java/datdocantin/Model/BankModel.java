package datdocantin.Model;

public class BankModel {
	private int ID_bank_info;
	private String tennganhang, stk, hovaten;
	private byte[] maQR;
	
	public BankModel() {}
	
	public BankModel(int ID_bank_info, String tennganhang, String stk, String hovaten, byte[] maQR) {
		this.ID_bank_info = ID_bank_info;
		this.tennganhang = tennganhang;
		this.stk = stk;
		this.hovaten = hovaten;
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
}
