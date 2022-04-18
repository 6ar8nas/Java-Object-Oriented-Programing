package com.hard.game;

public class Obstacle extends Entity {
	
	int xSpeed, ySpeed;

	public Obstacle(int x, int y, int xSpeed, int ySpeed) {
		super("graphics/monster.png", x, y, 40, 50);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public void update(Map map) {
		if(map.editMode)
			return;
		
		int xTemp = x + xSpeed;
		int yTemp = y + ySpeed;
		
		if(!map.isWalkable((xSpeed > 0) ? xTemp + size : xTemp, y))
		{
			xSpeed = -xSpeed;
		}
		if(!map.isWalkable(x, (ySpeed > 0) ? yTemp + size : yTemp))
		{
			ySpeed = -ySpeed;
		}
		
		x += xSpeed;
		y += ySpeed;
	}
	
    public boolean collidesWith(Player player) {
    	return (player.x < x + size && player.x + player.size > x &&
    			player.y < y + size && player.y + player.size > y);
    }
}
