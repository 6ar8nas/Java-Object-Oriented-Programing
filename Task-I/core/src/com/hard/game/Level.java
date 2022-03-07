package com.hard.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Level {
	int freePlay = 0, freePlayTaken = 0;
	int obstaclesCount;
	ArrayList<Obstacle> obstacles = new ArrayList <>();
	
	int pointsCount;
	ArrayList<Point> points = new ArrayList <>();

	Map map;
	
    public Level (Player player)
	{
		FileHandle f = Gdx.files.internal("maps/level" + player.currentLevel + ".txt");
		Scanner readFile = new Scanner(f.read());
		
		map = new Map(player);
		
		obstaclesCount = readFile.nextInt();
		for(int i = 0; i < obstaclesCount; ++i)
		{
			int x, y, xSpeed, ySpeed;
			x = readFile.nextInt() + map.xMapStart;
			y = readFile.nextInt() + map.yMapStart;
			xSpeed = readFile.nextInt();
			ySpeed = readFile.nextInt();
			obstacles.add(new Obstacle(x, y, xSpeed, ySpeed));
		}
		
		pointsCount = readFile.nextInt();
		for(int i = 0; i < pointsCount; ++i)
		{
			int x, y;
			x = readFile.nextInt();
			y = readFile.nextInt();
			points.add(new Point(x + map.xMapStart, y + map.yMapStart));
		}
		readFile.close(); 
	
		if(player.currentLevel == 6)
		{
			++freePlay;
			Random r = new Random();
			points.add(new Point(map.xMapStart + r.nextInt(map.widthTiles * map.tileSize), map.yMapStart + r.nextInt(map.heightTiles  * map.tileSize)));
		}
	}
}
