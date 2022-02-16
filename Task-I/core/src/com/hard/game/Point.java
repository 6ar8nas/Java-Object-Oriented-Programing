package com.hard.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Point {

	int size = 20;
	boolean claimable = true;

	int x, y;
	
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(ShapeRenderer shape) {
		shape.setColor(0, 0, 0, 1);
        shape.rect(x, y, size, size);
		shape.setColor(250/255f, 250/255f, 0, 1);
        shape.rect(x+2, y+2, size-4, size-4);
    }
	
	public boolean checkCollision(Player player) {
		if(collidesWith(player)){
            claimable = false;
            player.score();
            return true;
        }
		return false;
    }
	
    private boolean collidesWith(Player player) {
    	return (player.x < x + size && player.x + player.size > x &&
    			player.y < y + size && player.y + player.size > y);
    }
}
