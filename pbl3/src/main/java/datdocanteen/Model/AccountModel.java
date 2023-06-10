package datdocanteen.Model;

public class AccountModel {
	private Integer ID_account, status_lock;
	private String sdt, pass, type_User;

    public AccountModel() {
    }
    
	public AccountModel(Integer iD_account, String sdt, String pass, String type_User, Integer status_lock) {
		super();
		ID_account = iD_account;
		this.sdt = sdt;
		this.pass = pass;
		this.type_User = type_User;
		this.status_lock = status_lock;
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

    public int getStatus_lock() {
		return status_lock;
	}

	public void setStatus_lock(Integer status_lock) {
		this.status_lock = status_lock;
	}
	
}
