package entity;

public class Material extends Entity {

	private double level1, level2, level3;

	public Material(String name, String description, double level1, double level2, double level3) {
		super(name, description);
		this.level1 = level1;
		this.level2 = level2;
		this.level3 = level3;
	}

	@Override
	public String getDetails() {
		return "Level 1: "+level1+" Level 2: "+level2+" Level 3: "+level3+" *This entity is material*";
	}

	public double getMaxAmount(int noPersons) {

		if (noPersons==1) return level1;
		if (noPersons>=2 && noPersons<=4) return level2;
		if (noPersons>=5) return level3;
		
		return 0;
	}
	
}
