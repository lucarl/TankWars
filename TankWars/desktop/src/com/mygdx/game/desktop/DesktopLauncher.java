package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.TankWars;

public class DesktopLauncher {
	public static void main (String[] arg) {
		TankWars tankWars = new TankWars();
		Controller controller = new Controller(tankWars);
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = 1000;
		config.height = 600;
		new LwjglApplication(controller, config);
	}
}
