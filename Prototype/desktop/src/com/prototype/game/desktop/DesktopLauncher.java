package com.prototype.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.prototype.FlappyBirdDemo.FlappyBird;
import com.prototype.MVC_Demo.Controller.Controller;
import com.prototype.game.MenuDemo;
import com.prototype.screenManagerDemo.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyBird.WIDTH;
		config.height = FlappyBird.HEIGHT;
		config.title = FlappyBird.TITLE;
		new LwjglApplication(new MenuDemo(), config);
	}
}
