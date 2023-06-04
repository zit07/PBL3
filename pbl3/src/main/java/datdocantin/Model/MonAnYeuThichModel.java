package datdocantin.Model;

public class MonAnYeuThichModel {
   public int ID_monanyeuthich;
   public int ID_khachhang;
   public int ID_monan;
   
public MonAnYeuThichModel(int id_monanyeuthich, int iD_khachhang, int iD_monan) {
	super();
	ID_monanyeuthich =id_monanyeuthich;
	ID_khachhang = iD_khachhang;
	ID_monan = iD_monan;
}
public int getID_monanyeuthich() {
	return ID_monanyeuthich;
}
public void setID_monanyeuthich(int iD_monanyeuthich) {
	ID_monanyeuthich = iD_monanyeuthich;
}
public int getID_khachhang() {
	return ID_khachhang;
}
public void setID_khachhang(int iD_khachhang) {
	ID_khachhang = iD_khachhang;
}
public int getID_monan() {
	return ID_monan;
}
public void setID_monan(int iD_monan) {
	ID_monan = iD_monan;
}
@Override
public String toString() {
	return "MonAnYeuThich [ID_monanyeuthich=" + ID_monanyeuthich + ", ID_khachhang=" + ID_khachhang + ", ID_monan="
			+ ID_monan + "]";
}
   
}
