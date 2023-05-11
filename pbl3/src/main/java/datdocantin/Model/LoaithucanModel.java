package datdocantin.Model;

public class LoaithucanModel {
	private Integer ID_loaithucan;
	private String loaithucan;
	
	public LoaithucanModel() {
	}
	
	public LoaithucanModel(Integer ID_loaithucan, String loaithucan) {
		this.ID_loaithucan = ID_loaithucan;
		this.loaithucan = loaithucan;
	}

	public Integer getID_loaithucan() {
		return ID_loaithucan;
	}

	public void setID_loaithucan(Integer iD_loaithucan) {
		ID_loaithucan = iD_loaithucan;
	}

	public String getLoaithucan() {
		return loaithucan;
	}

	public void setLoaithucan(String loaithucan) {
		this.loaithucan = loaithucan;
	}
	
	
}
