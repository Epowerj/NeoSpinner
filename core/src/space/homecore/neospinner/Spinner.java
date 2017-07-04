package space.homecore.neospinner;

import com.badlogic.gdx.graphics.Color;

import io.anuke.ucore.core.Draw;
import io.anuke.ucore.util.Angles;
import io.anuke.ucore.util.Mathf;

public class Spinner extends Entity{
	Color color = Color.GREEN;
	float thickness = 6f;
	
	float spinspeed = 0f;
	private float rotation = 0;
	float friction = 0.001f;
	
	private float time = 0;
	
	public Spinner(float startx, float starty) {
		x = startx;
		y = starty;
		
		//add this entity to the list
		NeoSpinner.entities.add(this);
		
		//set drawing color
		Draw.color(color);
				
		//sets line thickness to 5
		Draw.thickness(thickness);
	}

	@Override
	public void draw() {
		//draw a circle
		Draw.circle(x, y, 40f);
		Draw.circle(x, y, 30f);
		
		//draw 3-sided regular polygon
		//Draw.polygon(3, x, y, 50f);
		//draw spikes around everything
		//Draw.spikes(x, y, 60f, 20f, 10, -time);
		
		int sides = 3;
		
		for(int i = 0; i < sides; i ++){
			Angles.translation(i*360f/sides+rotation, 125);
			Draw.circle(x + Angles.vector.x, y + Angles.vector.y, 40);
		}
	}
	
	@Override
	public void update() {
		time += Mathf.delta();
		rotation += Mathf.delta()*spinspeed;
		
		spinspeed *= 1f-Mathf.delta()*friction;

	}
}
