package com.hard.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Obstacle {
	int size = 40;

    int x, y;
	int xSpeed, ySpeed;
	int minWidth, maxWidth, minHeight, maxHeight;
	
	
	public Obstacle (int x, int y, int xSpeed, int ySpeed, 
			int minWidth, int maxWidth, int minHeight, int maxHeight) {
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.minWidth = minWidth;
		this.maxWidth = maxWidth;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}
	
	public void update() {
		x += xSpeed;
		y += ySpeed;
		if (x < minWidth || x + size > maxWidth) {
            xSpeed = -xSpeed;
        }
        if (y < minHeight || y + size > maxHeight) {
            ySpeed = -ySpeed;
        }
	}
	
	public void draw(ShapeRenderer shape) {
		shape.setColor(0, 0, 0, 1);
        shape.rect(x, y, size, size);
		shape.setColor(0, 0, 246/255f, 1);
        shape.rect(x+2, y+2, size-4, size-4);
    }
	
    public boolean collidesWith(Player player) {
    	return (player.x < x + size && player.x + player.size > x &&
    			player.y < y + size && player.y + player.size > y);
    }	
}
