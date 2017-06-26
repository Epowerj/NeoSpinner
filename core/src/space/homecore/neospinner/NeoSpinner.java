package space.homecore.neospinner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.anuke.ucore.core.Draw;
import io.anuke.ucore.core.DrawContext;
import io.anuke.ucore.core.Graphics;
import io.anuke.ucore.graphics.Hue;

public class NeoSpinner extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	float speed = 3f;
	float playerx, playery;
	float time;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		//Sets the vector graphics batch
		DrawContext.batch = batch;
		
		//set player position to center of screen
		playerx = Gdx.graphics.getWidth()/2;
		playery = Gdx.graphics.getHeight()/2;
	}

	@Override
	public void render () {
		//input
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)){
			playery += speed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
			playerx -= speed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			playery -= speed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			playerx += speed;
		}
		
		//increment counter
		time ++;
		
		//Graphics.clear(color) clears the screen
		//Hue.fromHSB() creates a color from Hue, Saturation and Brightness values
		Graphics.clear(Hue.fromHSB(time/90f, 1f, 1f));
		
		batch.begin();
		
		batch.draw(img, //texture 
				playerx - img.getWidth()/2, //x 
				playery - img.getHeight()/2, //y
				img.getWidth()/2, img.getHeight()/2, //origin x and y
				img.getWidth(), img.getHeight(), //width and height
				1f, 1f, //scale x and y 
				time, //rotation 
				0, 0, //src x and y (if you want to draw part of the image)
				img.getWidth(), img.getHeight(), //src Height and Width (same as above)
				false, false); //flip x and y (bools)
		
		//set drawing color
		batch.setColor(Color.GREEN);
		
		//sets line thickness to 5
		Draw.thickness(5f);
		
		//draw a circle
		Draw.circle(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, 20f);
		//draw 3-sided regular polygon
		Draw.polygon(3, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2f, 50f);
		//draw spikes around everything
		Draw.spikes(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2f, 60f, 20f, 10);
		
		batch.setColor(Color.WHITE);
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
