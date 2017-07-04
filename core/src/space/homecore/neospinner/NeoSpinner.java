package space.homecore.neospinner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import io.anuke.ucore.core.Draw;
import io.anuke.ucore.core.DrawContext;
import io.anuke.ucore.core.Graphics;
import io.anuke.ucore.util.Mathf;

public class NeoSpinner extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	float speed = 10f;
	float time;
	
	static Array<Entity> entities = new Array<Entity>();
	
	Spinner player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		//Sets the vector graphics batch
		DrawContext.batch = batch;
		//set the drawcontext font
		DrawContext.font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
		
		player = new Spinner(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}

	@Override
	public void render () {
		//increment counter (fps-independent)
		time += Mathf.delta();
		
		//input
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)){
			player.y += speed*Mathf.delta();
		}
		
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
			player.x -= speed*Mathf.delta();
		}
		
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			player.y -= speed*Mathf.delta();
		}
		
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			player.x += speed*Mathf.delta();
		}
		
		//for holding
		//if(Gdx.input.isKeyPressed(Keys.SPACE)) {
		//	player.spinspeed += 0.1*Mathf.delta();
		//}
		
		//for spamming
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			player.spinspeed += 1;
		}
		
		batch.begin();
		
		//update all the entities
		for(Entity e: entities) {
			e.update();
			e.draw();
		}
		
		//Graphics.clear(color) clears the screen
		Graphics.clear(Color.BLACK); //static black background	
		
		//draw fps
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
