package com.hard.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HardGame extends ApplicationAdapter {
	
	SpriteBatch batch;
	BitmapFont font;
	Player player;
	Level level;
	Camera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
		player = new Player(1);
		level = new Level(player);
		camera = new Camera();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update(batch);
		batch.begin();
		level.map.render(batch, camera.cam);
		camera.posUpdate(batch, player, level);
		player.update(level.map);
		player.render(batch);
		runGameEngine();
		renderInterface();
		batch.end();
	}
	
	public void runGameEngine() {
    	for (Point point : level.points) {
			if(point.claimable)
			{
				int levelBefore = player.currentLevel;
				point.render(batch);
				boolean pointClaimedCheck = point.checkCollision(player);
				if(pointClaimedCheck) {
					player.score();
				}
				
				if(levelBefore != player.currentLevel)
					level = new Level(player);
				
				if(pointClaimedCheck && player.currentLevel == 6)
					++level.freePlayTaken;
			}			
		}
		if(level.freePlay - 1 != level.freePlayTaken && player.currentLevel == 6)
		{
			Random r = new Random();
			level.points.add(new Point(level.map.xMapStart + r.nextInt(level.map.widthTiles * level.map.tileSize), level.map.yMapStart + r.nextInt(level.map.xMapStart + r.nextInt(level.map.heightTiles * level.map.tileSize))));
			++level.freePlay;
		}
		for (Obstacle obstacle : level.obstacles) {
			obstacle.update(level.map);
			obstacle.render(batch);
			if(obstacle.collidesWith(player))
			{
	            player.reset();
				player.currentLevel = 1;
	            level = new Level(player);
			}
		}
    }
	
	public void renderInterface() {
		SpriteBatch batch1 = new SpriteBatch();
		batch1.begin();
	    String lowerTextR, lowerTextL, upperTextL, upperTextR;
	    if(level.map.editMode && player.currentLevel == 1)
	    {
	    	String editHelp = "E to turn off edit mode. MsClick a block to select it. ArrKeys/Enter to Nav/Drop.";
			font.draw(batch1, editHelp, 10, 64);
	    }

		lowerTextR = "Deaths: " + player.deaths + ". Score: " + player.score + ".";
		lowerTextL = "FPS: " + Gdx.graphics.getFramesPerSecond() + ".";
		upperTextR = "Level: " + player.currentLevel + ".";
		upperTextL = "High score: " + player.highScore + ".";

		font.draw(batch1, lowerTextR, 10, 32);
		font.draw(batch1, upperTextR, 10, Gdx.graphics.getHeight() - 2);
		font.draw(batch1, lowerTextL, Gdx.graphics.getWidth()-lowerTextL.length()*14 - 2, 32);
		font.draw(batch1, upperTextL, Gdx.graphics.getWidth()-upperTextL.length()*14 - 2, Gdx.graphics.getHeight() - 2);
		batch1.end();
	}
}
