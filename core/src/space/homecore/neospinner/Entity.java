package space.homecore.neospinner;

public abstract class Entity {
	float x;
	float y;
	
	public abstract void draw();
	
	public Entity() {
		x = 0;
		y = 0;
	}
	public Entity(float settoX, float settoY) {
		x = settoX;
		y = settoY;
	}
}
