package com.hard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

public class Player extends Entity {

	int speed = 5, deaths = 0, score = 0, highScore = 0;
	int currentLevel;
	
	Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("sounds/death.wav"));
	Sound pointSound = Gdx.audio.newSound(Gdx.files.internal("sounds/point.wav"));
	Sound levelSound = Gdx.audio.newSound(Gdx.files.internal("sounds/level.wav"));

	public Player (int levelNumber) {
		super("graphics/character.png", 0, 0, 25);
		currentLevel = levelNumber;
	}
	
	public void update(Map map) {	
		int xTemp = x, yTemp = y;
		if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
			yTemp += speed;
			direction = 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)){
			yTemp -= speed;	
			direction = 0;
		}
		
		if(map.isWalkable(x, (yTemp - y > 0) ? yTemp + size : yTemp)) {
			y = yTemp;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
			xTemp -= speed;
			direction = 2;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
			xTemp += speed;
			direction = 3;
		}
		
		if(map.isWalkable((xTemp - x > 0) ? xTemp + size : xTemp, y)) {
			x = xTemp;
		}
	}
	
	public void reset()	{
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
	
	public void score() {
		++score;
		if(highScore < score)
		{
			highScore = score;
		}
		pointSound.play();
		
		if(checkIfLevelUp()) {
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
	}
	
	private boolean checkIfLevelUp() {
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
		return levelUp;
	}
}
