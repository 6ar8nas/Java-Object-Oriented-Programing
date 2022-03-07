package com.hard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Camera {
	OrthographicCamera cam;
	
	public Camera() {
		cam = new OrthographicCamera(1280, 720);
	    cam.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}
	
	public void update(SpriteBatch batch) {
		cam.update();
	    batch.setProjectionMatrix(cam.combined);
	}
	
	public void posUpdate(SpriteBatch batch, Player player, Level level) {
		if(level.map.editMode) {
			cam.zoom = level.map.findCamZoom(player.currentLevel);
			int[] coord = {600, 400, 600, 500, 700, 650, 800, 750, 900, 750, 1000, 750};
			cam.position.x = coord[(player.currentLevel-1)*2];
			cam.position.y = coord[(player.currentLevel-1)*2 + 1];
			level.map.edit(batch, player.currentLevel);
		}
		else 
		{
			cam.position.x = player.x;
			cam.position.y = player.y;
			cam.zoom = 3/2f;
		}
	}
}
