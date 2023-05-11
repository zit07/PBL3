package datdocantin.Model;

public class AccountModel {
	private Integer ID_account;
	private String sdt, pass, type_User;

    public AccountModel() {
    }
    
    public AccountModel(Integer ID_account, String sdt, String pass, String type_User) {
    	this.ID_account = ID_account;
    	this.sdt = sdt;
    	this.pass = pass;
    	this.type_User = type_User;
    }
    
	public int getID_account() {
		return ID_account;
	}

	public void setID_account(Integer iD_account) {
		ID_account = iD_account;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getType_User() {
		return type_User;
	}

	public void setType_User(String type_User) {
		this.type_User = type_User;
	}
}
