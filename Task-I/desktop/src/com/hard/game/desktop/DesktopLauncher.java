package com.hard.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hard.game.HardGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Hard Game";
		config.width = 1200;
		config.height = 800;
		config.resizable = false;
		config.x = -1;
		config.y = -1;
		new LwjglApplication(new HardGame(), config);
	}
}
