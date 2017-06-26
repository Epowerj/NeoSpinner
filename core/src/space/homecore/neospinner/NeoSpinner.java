package space.homecore.neospinner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import io.anuke.ucore.core.Draw;
import io.anuke.ucore.core.DrawContext;
import io.anuke.ucore.core.Graphics;
import io.anuke.ucore.util.Angles;
import io.anuke.ucore.util.Mathf;

public class NeoSpinner extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	float speed = 10f;
	float playerx, playery;
	float time;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		//Sets the vector graphics batch
		DrawContext.batch = batch;
		//set the drawcontext font
		DrawContext.font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
		
		//set player position to center of screen
		playerx = Gdx.graphics.getWidth()/2;
		playery = Gdx.graphics.getHeight()/2;
	}

	@Override
	public void render () {
		//input
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)){
			playery += speed*Mathf.delta();
		}
		
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
			playerx -= speed*Mathf.delta();
		}
		
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			playery -= speed*Mathf.delta();
		}
		
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			playerx += speed*Mathf.delta();
		}
		
		//increment counter (fps-independent)
		time += Mathf.delta();
		
		//Graphics.clear(color) clears the screen
		//Hue.fromHSB() creates a color from Hue, Saturation and Brightness values
			//Graphics.clear(Hue.fromHSB(time/90f, 1f, 1f)); //rainbow background
		Graphics.clear(Color.BLACK); //static black background
		
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
		Draw.spikes(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2f, 60f, 20f, 10, -time);
		
		int sides = 3;
		
		for(int i = 0; i < sides; i ++){
			Angles.translation(i*360f/sides+time, 100);
			Draw.circle(Gdx.graphics.getWidth()/2f + Angles.vector.x, Gdx.graphics.getHeight()/2f + Angles.vector.y, 60);
		}
		
		batch.setColor(Color.WHITE);
		
		Draw.text(Gdx.graphics.getFramesPerSecond() + " FPS", 0, Gdx.graphics.getHeight(), Align.left);
		
		batch.end();
	}
	
	@Override
	public void resize(int width, int height){
		//this resizes the batch so the image doesn't stretch
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
