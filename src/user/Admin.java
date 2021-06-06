package user;

public class Admin extends User {
	
	private final boolean isAdmin = true;

	public Admin(String name, long phone) {
		super(name, phone);
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
}
