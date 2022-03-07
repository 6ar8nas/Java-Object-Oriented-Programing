package com.hard.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hard.game.HardGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Hard Game";
		cfg.width = 1280;
		cfg.height = 720;
		cfg.resizable = false;
		cfg.x = -1;
		cfg.y = -1;
		new LwjglApplication(new HardGame(), cfg);
	}
}
