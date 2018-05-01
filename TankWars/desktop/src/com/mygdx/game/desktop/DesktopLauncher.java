package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.TankWars;

public class DesktopLauncher {
	public static void main (String[] arg) {
		TankWars tankWars = new TankWars(3);
		Controller controller = new Controller(tankWars);
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Tank Wars";
		config.foregroundFPS = 60;
		config.width = Controller.GAME_WIDTH;
		config.height = Controller.GAME_HEIGHT;
		new LwjglApplication(controller, config);
	}
}
