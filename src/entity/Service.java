package entity;

public class Service extends Entity {
	
	public Service(String name, String description) {
		super(name, description);
	}

	@Override
	public String getDetails() {
		return "*This entity is service*";
	}

}
