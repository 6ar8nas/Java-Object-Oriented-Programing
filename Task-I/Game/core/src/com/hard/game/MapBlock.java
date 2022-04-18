package com.hard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapBlock {
	Texture texture;
	boolean walkable = true;

	public MapBlock (String blockName)
	{
		texture = new Texture(Gdx.files.internal("graphics/"+ blockName + ".png"));
		
		if(blockName == "obstacle1" || blockName == "obstacle2" || blockName == "grass")
			walkable = false;
	}
	
	public void draw (SpriteBatch batch, int x, int y, int size) {
		batch.draw(texture, x, y, size, size);
	}
}
