package com.hard.game;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	boolean editMode = true;
	boolean holdingBlock = false;
	int blockHeld;
	
    int widthTiles, heightTiles;
    int xMapStart, yMapStart;
    
    int tileSize = 50;

    int[][] mapLayout;
    int[][] extraLayout;
  
    MapBlock[] blockArray;
    
	int xIndex = 0;
	int yIndex = 0;
	
    float camZoom;
    
    public Map (Player player) {
		FileHandle f = Gdx.files.internal("maps/map" + player.currentLevel + ".txt");
		Scanner readFile = new Scanner(f.read());
		
		xMapStart = readFile.nextInt();
		yMapStart = readFile.nextInt();
		widthTiles = readFile.nextInt();
		heightTiles = readFile.nextInt();
		
		camZoom = findCamZoom(player.currentLevel);
		
		player.x = readFile.nextInt() + xMapStart;
		player.y = readFile.nextInt() + yMapStart;
		
		mapLayout = new int[widthTiles][heightTiles];
		extraLayout = new int[widthTiles][heightTiles];
		
		blockArray = new MapBlock[5];
		blockArray[0] = new MapBlock("grass");
		blockArray[1] = new MapBlock("pavement1");
		blockArray[2] = new MapBlock("pavement2");
		blockArray[3] = new MapBlock("obstacle1");
		blockArray[4] = new MapBlock("obstacle2");
		
		for(int i = 0; i < heightTiles; ++i)
		{
			for(int j = 0; j < widthTiles; ++j)
			{
				mapLayout[j][i] = readFile.nextInt();
				extraLayout[j][i] = 0;
			}
		}
	}
    
    public void render(SpriteBatch batch, OrthographicCamera camera) {
    	if(Gdx.input.isKeyJustPressed(Input.Keys.E))
    	{
    		editMode = false;
    	}
		for(int i = heightTiles - 1; i >= 0; --i)
		{
			for(int j = widthTiles - 1; j >= 0; --j)
			{
				blockArray[mapLayout[j][i]].draw(batch, xMapStart + j * tileSize, yMapStart + i * tileSize, tileSize);
				if(extraLayout[j][i] != 0)
					blockArray[extraLayout[j][i]].draw(batch, xMapStart + j * tileSize, yMapStart + i * tileSize, tileSize);
			}
		}
    }
    
    public boolean isWalkable(int x, int y) {
    	if(editMode) 
    		return false;
		int xTemp = (x - xMapStart) / tileSize;
		int yTemp = (y - yMapStart) / tileSize;
		if(extraLayout[xTemp][yTemp] != 0)
			return false;
		return blockArray[mapLayout[xTemp][yTemp]].walkable;
    }
    
    public void edit(SpriteBatch batch, int currentLevel) {
    	blockArray[3].draw(batch, -50, 200, tileSize);
    	blockArray[4].draw(batch, -50, 150, tileSize);
    	
    	int mouseX = Gdx.graphics.getWidth()-Gdx.input.getX();
    	int mouseY = Gdx.graphics.getHeight()-Gdx.input.getY();  	

    	if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
    		blockHeld = selection(mouseX, mouseY, currentLevel);
    		if(blockHeld != 0)
    		{
    	    	holdingBlock = true;
    		}
        }
    	if(holdingBlock) {
    		blockArray[blockHeld].draw(batch, xMapStart + yIndex * tileSize, yMapStart + xIndex * tileSize, tileSize);
    		updateSelection();
    		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
    		{
    			holdingBlock = false;
    			extraLayout[yIndex][xIndex] = blockHeld;
    			blockHeld = yIndex = xIndex = 0;
    		}
    	}
    }
    public int selection(int mouseX, int mouseY, int currentLevel) {
    	mouseX = Gdx.graphics.getWidth()- mouseX;
    	mouseY = Gdx.graphics.getHeight() - mouseY;
    	switch(currentLevel) {
    	case 1:
    	{
    		if(mouseX >= 120 && mouseX <= 160)
    		{
    			if(mouseY >= 480 && mouseY < 520)
    			{
    				return 3;
    			}
    			if(mouseY >= 520 && mouseY < 560)
    			{
    				return 4;
    			}
    		}
    		return 0;
    	}
    	case 2:
    	{
    		if(mouseX >= 206 && mouseX <= 239)
    		{
    			if(mouseY >= 526 && mouseY < 558)
    			{
    				return 3;
    			}
    			if(mouseY >= 558 && mouseY < 590)
    			{
    				return 4;
    			}
    		}
    		return 0;
    	}
    	case 3:
    	{
    		if(mouseX >= 211 && mouseX <= 238)
    		{
    			if(mouseY >= 588 && mouseY < 614)
    			{
    				return 3;
    			}
    			if(mouseY >= 614 && mouseY < 643)
    			{
    				return 4;
    			}
    		}
    		return 0;
    	}
    	case 4:
    	{
    		if(mouseX >= 261 && mouseX <= 284)
    		{
    			if(mouseY >= 582 && mouseY < 602)
    			{
    				return 3;
    			}
    			if(mouseY >= 602 && mouseY < 627)
    			{
    				return 4;
    			}
    		}
    		return 0;
    	}
    	case 5:
    	{
    		if(mouseX >= 218 && mouseX <= 238)
    		{
    			if(mouseY >= 581 && mouseY < 603)
    			{
    				return 3;
    			}
    			if(mouseY >= 603 && mouseY < 626)
    			{
    				return 4;
    			}
    		}
    		return 0;
    	}
    	case 6:
    	{
    		if(mouseX >= 173 && mouseX <= 196)
    		{
    			if(mouseY >= 581 && mouseY < 602)
    			{
    				return 3;
    			}
    			if(mouseY >= 602 && mouseY < 625)
    			{
    				return 4;
    			}
    		}
    		return 0;
    	}
    	}
    	return 0;
    }
    
    public float findCamZoom(int currentLevel) {
    	float[] arr = {5/2f, 3f, 7/2f, 9/2f, 9/2f, 9/2f};
    	return arr[currentLevel - 1];
    }
    
    public void updateSelection() {
    	if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && xIndex < heightTiles){
    		++xIndex;
    	}
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && xIndex > 0){
			--xIndex;
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && yIndex > 0){
			--yIndex;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && yIndex < widthTiles){
			++yIndex;
		}
    }
}
