package datdocantin.Model;

public class CartModel {
	private Integer ID_cart,ID_khachhang,ID_monan,soluong;
	private String tenmon;
	private Double gia;
	private byte[] hinhanhchinh;
	
	public  CartModel() {
	}
	
	public  CartModel(Integer ID_cart, Integer ID_khachhang, Integer ID_monan, String tenmon, byte[] hinhanhchinh, Integer soluong, Double gia) {
		this.ID_cart = ID_cart;
		this.ID_khachhang = ID_khachhang;
		this.ID_monan = ID_monan;
		this.tenmon = tenmon;
		this.hinhanhchinh = hinhanhchinh;
		this.soluong = soluong;
		this.gia = gia;
	}

	public Integer getID_cart() {
		return ID_cart;
	}

	public void setID_cart(Integer iD_cart) {
		ID_cart = iD_cart;
	}

	public Integer getID_khachhang() {
		return ID_khachhang;
	}

	public void setID_khachhang(Integer iD_khachhang) {
		ID_khachhang = iD_khachhang;
	}

	public Integer getID_monan() {
		return ID_monan;
	}

	public void setID_monan(Integer iD_monan) {
		ID_monan = iD_monan;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
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

	public void setGia(Double gia) {
		this.gia = gia;
	}

	public byte[] getHinhanhchinh() {
		return hinhanhchinh;
	}

	public void setHinhanhchinh(byte[] hinhanhchinh) {
		this.hinhanhchinh = hinhanhchinh;
	}

	
	
}