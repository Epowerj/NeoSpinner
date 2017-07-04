package space.homecore.neospinner;

import com.badlogic.gdx.graphics.Color;

import io.anuke.ucore.core.Draw;
import io.anuke.ucore.util.Angles;
import io.anuke.ucore.util.Mathf;

public class Spinner extends Entity{
	Color color = Color.GREEN;
	float thickness = 6f;
	float length = 125;
	float sideradius = 40;
	float radius = 40f;
	int sides = 3;
	
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
		Draw.circle(x, y, radius);
		Draw.circle(x, y, radius-17f);
		
		for(int i = 0; i < sides; i ++){
			drawSide(i*360f/sides+rotation);
		}
	}
	
	void drawSide(float angle){
		
		for(int i = 0; i < 2; i ++){
			//basically this makes sign 1, then -1, to draw both curves on the side of the spinner
			int sign = Mathf.sign(i == 0);
			
			//end coords
			Angles.translation(angle, length);
			float endx = Angles.vector.x, endy = Angles.vector.y;
			
			//sides of the end circle
			Angles.translation(angle+90*sign, sideradius);
			float rightx = endx + Angles.vector.x, righty = endy + Angles.vector.y;
			
			//middle of the curve
			float middlex = Angles.vector.x/2 + endx/2, middley = Angles.vector.y/2 + endy/2;
			
			//start of the curve, on the main circle
			Angles.translation(angle+(360f/sides)/2f*sign, radius);
			float rightdx = Angles.vector.x, rightdy = Angles.vector.y;
			
			//draw the curve
			Draw.curve(rightx+x, righty+y, middlex+x, middley+y, middlex+x, middley+y, rightdx+x, rightdy+y, 10);
		}
		
		//draw the actual ends of the side
		Angles.translation(angle, length);
		float endx = Angles.vector.x, endy = Angles.vector.y;
		
		Draw.circle(x + endx, y + endy, sideradius);
		Draw.circle(x + endx, y + endy, sideradius/1.6f);
	}
	
	@Override
	public void update() {
		time += Mathf.delta();
		rotation += Mathf.delta()*spinspeed;
		
		spinspeed *= 1f-Mathf.delta()*friction;

	}
}
