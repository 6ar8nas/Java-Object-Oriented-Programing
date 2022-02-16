package com.hard.game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Level {

	int freePlay = 0, freePlayTaken = 0; 
    
	int obstaclesCount;	
	ArrayList<Obstacle> obstacles = new ArrayList <>();
	
	int pointsCount;
	ArrayList<Point> points = new ArrayList <>();

    int minWidth, minHeight, maxHeight, maxWidth;

	public Level (Player player)
	{
		FileHandle f = Gdx.files.internal("level" + player.currentLevel + ".txt");
		Scanner readFile = new Scanner(f.read());
		
		minWidth = readFile.nextInt();
		maxWidth = readFile.nextInt();
		minHeight = readFile.nextInt();
		maxHeight = readFile.nextInt();
		
		player.x = readFile.nextInt() + minWidth;
		player.y = readFile.nextInt() + minHeight;
		
		obstaclesCount = readFile.nextInt();
		for(int i = 0; i < obstaclesCount; ++i)
		{
			int x, y, xSpeed, ySpeed;
			x = readFile.nextInt();
			y = readFile.nextInt();
			xSpeed = readFile.nextInt();
			ySpeed = readFile.nextInt();
			obstacles.add(new Obstacle(x + minWidth, y + minHeight, xSpeed, ySpeed, minWidth, maxWidth, minHeight, maxHeight));
		}
		pointsCount = readFile.nextInt();
		for(int i = 0; i < pointsCount; ++i)
		{
			int x, y;
			x = readFile.nextInt();
			y = readFile.nextInt();
			points.add(new Point(minWidth + x, minHeight + y));
		}
		readFile.close(); 
	
		if(player.currentLevel == 6)
		{
			++freePlay;
			Random r = new Random();
			points.add(new Point(minWidth + r.nextInt(maxWidth-minWidth), minHeight + r.nextInt(maxHeight-minHeight)));
		}
	}
	
	public void draw(ShapeRenderer shape) {
		for(int i = minWidth, iIndex = 0; i < maxWidth; i = i + 50, ++iIndex)
		{
			for(int j = minHeight, jIndex = 0; j < maxHeight; j = j + 50, ++jIndex)
			{
				if((iIndex + jIndex) % 2 == 0)
				{
					shape.setColor(247/255f, 247/255f, 1, 1);
				}
				else
				{
					shape.setColor(221/255f, 221/255f, 1, 1);
				}
				shape.rect(i, j, 50, 50);

			}
		}
	}
}
