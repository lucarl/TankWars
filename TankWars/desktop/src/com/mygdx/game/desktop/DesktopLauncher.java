package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Application.TITLE;
		config.width = Application.GAME_WIDTH;
		config.height = Application.GAME_HEIGHT;
		config.foregroundFPS = 60;
		new LwjglApplication(new Application(), config);
	}
}
