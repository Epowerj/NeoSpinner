package space.homecore.neospinner;

public abstract class Entity {
	float x;
	float y;
	
	public abstract void draw();
	
	public abstract void update();
	
	public Entity() {
		x = 0;
		y = 0;
		
		//add this entity to the list
		NeoSpinner.entities.add(this);
	}
	public Entity(float settoX, float settoY) {
		x = settoX;
		y = settoY;
		
		//add this entity to the list
		NeoSpinner.entities.add(this);
	}
}
