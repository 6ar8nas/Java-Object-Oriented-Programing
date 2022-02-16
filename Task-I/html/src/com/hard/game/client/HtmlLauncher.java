package com.hard.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.hard.game.HardGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
        	return new GwtApplicationConfiguration(1200, 800);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new HardGame();
        }
}