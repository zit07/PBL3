package datdocantin.Model;

public class AccountModel {
	private String ID_Account, sdt, pass, type_User;

    public AccountModel() {
    }
    
    public AccountModel(String ID_Account, String sdt, String pass, String type_User) {
    	this.ID_Account = ID_Account;
    	this.sdt = sdt;
    	this.pass = pass;
    	this.type_User = type_User;
    }

	public String getID_Account() {
		return ID_Account;
	}

	public void setID_Account(String iD_Account) {
		ID_Account = iD_Account;
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
