package entity;

public abstract class Entity {

	public static int size;
	
	private String name, description;
	private int id;
	
	private double quantity;
	
	public Entity(String name, String description) {
		this.name = name;
		this.description = description;
		this.id = Entity.size++;
	}

	public int getId() {
		return id;
	}
	
	public String getEntityInfo() {
		return "#"+id+" "+name+" ("+description+") "+quantity;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}


	public abstract String getDetails();

	public double getQuantity() {
		return quantity;
	}
	
	public void addQuantity(double added) {
		quantity+=added;
	}
	
	public void removeQuantity(double removed) {
		quantity+=removed;
	}
	
	@Override
	public String toString() {
		return getEntityInfo()+" | "+getDetails();
	}
}
