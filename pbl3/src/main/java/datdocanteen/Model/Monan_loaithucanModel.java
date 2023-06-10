package datdocanteen.Model;

public class Monan_loaithucanModel {
	private int ID_monan_loaithucan, ID_monan, ID_loaithucan;
	
	public Monan_loaithucanModel() {}
	
	public Monan_loaithucanModel(int ID_monan_loaithucan, int ID_monan, int ID_loaithucan) {
		this.ID_monan_loaithucan = ID_monan_loaithucan;
		this.ID_monan = ID_monan;
		this.ID_loaithucan = ID_loaithucan;
	}

	public int getID_monan_loaithucan() {
		return ID_monan_loaithucan;
	}

	public void setID_monan_loaithucan(int iD_monan_loaithucan) {
		ID_monan_loaithucan = iD_monan_loaithucan;
	}

	public int getID_monan() {
		return ID_monan;
	}

	public void setID_monan(int iD_monan) {
		ID_monan = iD_monan;
	}

	public int getID_loaithucan() {
		return ID_loaithucan;
	}

	public void setID_loaithucan(int iD_loaithucan) {
		ID_loaithucan = iD_loaithucan;
	}
}
