package com.mygdx.gamedemo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.gamedemo.EventInputDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//----window-size----//
		config.title = "Title";
		config.useGL30 = true;
		config.height = 800;
		config.width = 1200;
		new LwjglApplication(new EventInputDemo(), config);


	}
}
