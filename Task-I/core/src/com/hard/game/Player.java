package com.hard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

public class Player {
	
	int speed = 5, size = 25;

	int x, y, xStart, yStart;
	int deaths, score, highScore, currentLevel;
	
	Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("death.wav"));
	Sound pointSound = Gdx.audio.newSound(Gdx.files.internal("point.wav"));
	Sound levelSound = Gdx.audio.newSound(Gdx.files.internal("level.wav"));

	
	public Player (int xStart, int yStart, int deaths, int score, int levelNumber) {
		this.x = xStart;
		this.y = yStart;
		this.xStart = xStart;
		this.yStart = yStart;
		this.deaths = deaths;
		this.score = score;
		this.currentLevel = levelNumber;
	}
	
	public void update(Level level) {
		 if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
			 if (x <= level.minWidth) {
				 x = level.minWidth;
			 }
			 else
				 x -= speed;
		 }
		 if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
			 if (x + size >= level.maxWidth) {
				 x = level.maxWidth - size;
			 }
			 else
				 x += speed;
		 }
		 if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
			 if (y + size >= level.maxHeight) {
				 y = level.maxHeight - size;
			 }
			 else
				 y += speed;
		 }
		 if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)){
			 if (y <= level.minHeight) {
				  y = level.minHeight;
			 }
			 else
				 y -= speed;	
		 }
	}
	
	public void draw(ShapeRenderer shape) {
		shape.setColor(0, 0, 0, 1);
        shape.rect(x, y, size, size);
		shape.setColor(1, 0, 0, 1);
        shape.rect(x+2, y+2, size-4, size-4);
    }
	
	public void reset()
	{
		deathSound.play();
        try
		{
		    Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		++deaths;
		score = 0;
		currentLevel = 1;
	}
	public void score()
	{
		++score;
		if(highScore < score)
		{
			highScore = score;
		}
		pointSound.play();
		checkIfLevelUp();
	}
	
	public boolean checkIfLevelUp()
	{
		boolean levelUp = false;
		switch(currentLevel)
		{
		case 1:
		{
			if(score == 2)
			{
				levelUp = true;
				break;
			}
		}
		case 2:
		{
			if(score == 4)
			{
				levelUp = true;
				break;
			}
		}
		case 3:
		{
			if(score == 7)
			{
				levelUp = true;
				break;
			}
		}
		case 4:
		{
			if(score == 10)
			{
				levelUp = true;
				break;
			}
		}
		case 5:
		{
			if(score == 15)
			{
				levelUp = true;
				break;
			}
		}
		}
		if(levelUp)
		{
			++currentLevel;
			levelSound.play();
			try
			{
			    Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
        }
		return levelUp;
	}
}
