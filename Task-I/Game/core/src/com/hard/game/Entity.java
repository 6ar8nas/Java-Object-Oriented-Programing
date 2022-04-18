package com.hard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Entity {
	int x, y;
	int xAxis, yAxis; 

	Sprite[] sprites;
	Texture textureMain;
	int tileSize = 32;

	int size, direction = 0;
	
	public Entity (String textureName, int x, int y, int size) {
		textureMain = new Texture(Gdx.files.internal(textureName));
		this.x = x;
		this.y = y;
		this.size = size;
		xAxis = textureMain.getWidth() / tileSize;
		yAxis = textureMain.getHeight() / tileSize;
				
		sprites = new Sprite[xAxis * yAxis];
		
		for(int i = 0; i < xAxis; ++i)
		{
			for(int j = 0; j < yAxis; ++j)
			{
				sprites[i*yAxis+j] = new Sprite(textureMain, i * tileSize, j * tileSize, tileSize, tileSize);
			}
		}
	}
	
	public Entity (String textureName, int x, int y, int size, int tileSize) {
		this.tileSize = tileSize;
		textureMain = new Texture(Gdx.files.internal(textureName));
		this.x = x;
		this.y = y;
		this.size = size;
		xAxis = textureMain.getWidth() / tileSize;
		yAxis = textureMain.getHeight() / tileSize;
				
		sprites = new Sprite[xAxis * yAxis];
		
		for(int i = 0; i < xAxis; ++i) 
		{
			for(int j = 0; j < yAxis; ++j) 
			{
				sprites[i*yAxis+j] = new Sprite(textureMain, i * tileSize, j * tileSize, tileSize, tileSize);
			}
		}	
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(sprites[direction], x, y, size, size);
	}
}
