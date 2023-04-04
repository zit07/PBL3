package datdocantin.Model;


public class KhachHangModel {
	private String IDKH, Hoten, Ngaysinh, Gioitinh, Chieucao, Cannang, Sodienthoai, Email, IDCantin, Monyeuthich, PIN;
	private byte[] avatar;

	public KhachHangModel() {
		// TODO Auto-generated constructor stub
	}
    
    public KhachHangModel(String IDKH, String Hoten, String Ngaysinh, String Gioitinh, String Chieucao, String Cannang, String Sodienthoai, String Email, String IDCantin, String Monyeuthich,String PIN,byte[] avatar) {
    	this.IDKH = IDKH;
    	this.Hoten = Hoten;
    	this.Ngaysinh = Ngaysinh;
    	this.Gioitinh = Gioitinh;
    	this.Chieucao = Chieucao;
    	this.Cannang = Cannang;
    	this.Sodienthoai = Sodienthoai;
    	this.Email = Email;
    	this.IDCantin = IDCantin;
    	this.Monyeuthich = Monyeuthich;
    	this.PIN = PIN;
    	this.avatar = avatar;
    }
    
	public String getIDKH() {
		return IDKH;
	}

	public void setIDKH(String iDKH) {
		IDKH = iDKH;
	}

	public String getHoten() {
		return Hoten;
	}

	public void setHoten(String hoten) {
		Hoten = hoten;
	}

	public String getNgaysinh() {
		return Ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		Ngaysinh = ngaysinh;
	}

	public String getGioitinh() {
		return Gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		Gioitinh = gioitinh;
	}

	public String getChieucao() {
		return Chieucao;
	}

	public void setChieucao(String chieucao) {
		Chieucao = chieucao;
	}

	public String getCannang() {
		return Cannang;
	}

	public void setCannang(String cannang) {
		Cannang = cannang;
	}

	public String getSodienthoai() {
		return Sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		Sodienthoai = sodienthoai;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getIDCantin() {
		return IDCantin;
	}

	public void setIDCantin(String IDCantin) {
		this.IDCantin = IDCantin;
	}
	
    public String getMonyeuthich() {
		return Monyeuthich;
	}

	public void setMonyeuthich(String monyeuthich) {
		Monyeuthich = monyeuthich;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String PIN) {
		this.PIN = PIN;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
}

