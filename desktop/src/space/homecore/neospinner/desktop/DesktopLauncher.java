package space.homecore.neospinner.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import space.homecore.neospinner.NeoSpinner;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//this disables vsync, so you get unlimited FPS
		//comment out to enable vsync
		config.useVsync(false);
		//maximize it
		config.setMaximized(true);
		
		//set the title
		config.setTitle("N E O  S P I N N E R");
		
		new Lwjgl3Application(new NeoSpinner(), config);
	}
}
