package datdocanteen.Model;

public class LoaithucanModel {
	private Integer ID_loaithucan, ID_canteen;
	private String loaithucan;
	 
	public LoaithucanModel() {
	}
	
	public LoaithucanModel(int ID_loaithucan, int ID_canteen, String loaithucan) {
		this.ID_loaithucan = ID_loaithucan;
		this.ID_canteen = ID_canteen;
		this.loaithucan = loaithucan;
	}

	public Integer getID_loaithucan() {
		return ID_loaithucan;
	}

	public void setID_loaithucan(Integer iD_loaithucan) {
		ID_loaithucan = iD_loaithucan;
	}

	public Integer getID_canteen() {
		return ID_canteen;
	}

	public void setID_canteen(Integer iD_canteen) {
		ID_canteen = iD_canteen;
	}

	public String getLoaithucan() {
		return loaithucan;
	}

	public void setLoaithucan(String loaithucan) {
		this.loaithucan = loaithucan;
	}
	
	
}
