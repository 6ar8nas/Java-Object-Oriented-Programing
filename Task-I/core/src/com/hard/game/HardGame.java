package com.hard.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;

public class HardGame extends ApplicationAdapter {
    
    SpriteBatch batch;
    BitmapFont font;
	ShapeRenderer shape;
	
    Player player;
	Level level;
    	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		shape = new ShapeRenderer();
		player = new Player(0, 0, 0, 0, 1);
		level = new Level(player);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
	    
		renderInterface();
		
		shape.begin(ShapeRenderer.ShapeType.Filled);
		
		level.draw(shape);

		player.update(level);
		player.draw(shape);
		
		for (Obstacle obstacle : level.obstacles) {
			obstacle.update();
			obstacle.draw(shape);
			if(obstacle.collidesWith(player))
			{
				player.currentLevel = 1;
	            level = new Level(player);
	            player.reset();
			}
		}
		for (Point point : level.points) {
			if(point.claimable)
			{
				int levelBefore = player.currentLevel;
				point.draw(shape);
				boolean pointClaimedCheck = point.checkCollision(player);
				
				if(levelBefore != player.currentLevel)
					level = new Level(player);
				
				if(pointClaimedCheck && player.currentLevel == 6)
					++level.freePlayTaken;
			}			
		}
		if(level.freePlay - 1 != level.freePlayTaken && player.currentLevel == 6)
		{
			Random r = new Random();
			level.points.add(new Point(level.minWidth + r.nextInt(level.maxWidth-level.minWidth), level.minHeight + r.nextInt(level.maxHeight-level.minHeight)));
			++level.freePlay;
		}
		shape.end();
	}
	
	public void renderInterface() {
	    String lowerTextR, lowerTextL, upperTextL, upperTextR;

		lowerTextR = "Deaths: " + player.deaths + ". Score: " + player.score + ".";
		lowerTextL = "FPS: " + Gdx.graphics.getFramesPerSecond() + ".";
		upperTextR = "Level: " + player.currentLevel + ".";
		upperTextL = "High score: " + player.highScore + ".";

		batch.begin();
		font.draw(batch, lowerTextR, 10, 32);
		font.draw(batch, upperTextR, 10, Gdx.graphics.getHeight() - 2);
		font.draw(batch, lowerTextL, Gdx.graphics.getWidth()-lowerTextL.length()*14 - 2, 32);
		font.draw(batch, upperTextL, Gdx.graphics.getWidth()-upperTextL.length()*14 - 2, Gdx.graphics.getHeight() - 2);
		batch.end();
	}
}
