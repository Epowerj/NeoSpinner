package space.homecore.neospinner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
		
		//set player position to center of screen
		playerx = Gdx.graphics.getWidth()/2;
		playery = Gdx.graphics.getHeight()/2;
	}

	@Override
	public void render () {
		//input
		if(Gdx.input.isKeyPressed(Keys.W)){
			playery += speed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.A)){
			playerx -= speed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.S)){
			playery -= speed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)){
			playerx += speed;
		}
		
		//increment counter
		time ++;
		
		//Graphics.clear(color) clears the screen
		//Hue.fromHSB() creates a color from Hue, Saturation and Brightness values
		Graphics.clear(Hue.fromHSB(time/90f, 1f, 1f));
		
		batch.begin();
		
		batch.draw(img, playerx - img.getWidth()/2, playery - img.getHeight()/2, img.getWidth()/2, img.getHeight()/2,
				img.getWidth(), img.getHeight(), 1f, 1f, time, 0, 0, img.getWidth(), img.getHeight(), false, false);
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
