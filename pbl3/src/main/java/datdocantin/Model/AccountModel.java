package datdocantin.Model;

public class AccountModel {
	private String id, sdt, pass, type_User;

    public AccountModel() {
    }
    
    public AccountModel(String id, String sdt, String pass, String type_User) {
    	this.id = id;
    	this.sdt = sdt;
    	this.pass = pass;
    	this.type_User = type_User;
    }

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
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
