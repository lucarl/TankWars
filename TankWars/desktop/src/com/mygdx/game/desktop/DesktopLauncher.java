package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.Demo;
import com.mygdx.game.model.TankWars;
import com.mygdx.game.view.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		TankWars tankWars = new TankWars();
		Controller controller = new Controller(tankWars);
		//BaseScreen baseScreen = new BaseScreen(controller, tankWars);
		




		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(controller, config);
	}
}
