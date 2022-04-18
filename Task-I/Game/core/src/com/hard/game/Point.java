package com.hard.game;

public class Point extends Entity {

	boolean claimable = true;
	
	public Point(int x, int y) {
		super("graphics/chest.png", x, y, 30);
	}
	
	public boolean checkCollision(Player player) {
		if(collidesWith(player) && claimable){
            claimable = false;
            return true;
        }
		return false;
    }
	
    private boolean collidesWith(Player player) {
    	return (player.x < x + size && player.x + player.size > x &&
    			player.y < y + size && player.y + player.size > y);
    }
}