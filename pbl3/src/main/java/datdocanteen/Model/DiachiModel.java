package datdocanteen.Model;

public class DiachiModel {
	private Integer ID_diachi, tinh, huyen, xa;
	
	public DiachiModel() {}
	public DiachiModel(Integer ID_diachi, Integer tinh, Integer huyen, Integer xa) {
		this.ID_diachi = ID_diachi;
		this.tinh = tinh;
		this.huyen = huyen;
		this.xa = xa;
	}
	
	public int getID_diachi() {
		return ID_diachi;
	}
	public void setID_diachi(Integer iD_diachi) {
		ID_diachi = iD_diachi;
	}
	public int getTinh() {
		return tinh;
	}
	public void setTinh(Integer tinh) {
		this.tinh = tinh;
	}
	public int getHuyen() {
		return huyen;
	}
	public void setHuyen(Integer huyen) {
		this.huyen = huyen;
	}
	public int getXa() {
		return xa;
	}
	public void setXa(Integer xa) {
		this.xa = xa;
	}
	
}
