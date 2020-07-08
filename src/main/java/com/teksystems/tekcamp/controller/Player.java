package com.teksystems.tekcamp.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	public boolean right,left,up,down;
	private int speed = 3;
	
	public Player(int x, int y) {
		setBounds(x,y,32,32);
	}
	
	public void tick() {
		if(right && canMove(x+speed,y)) x+=speed;
		if(left && canMove(x-speed,y)) x-=speed;
		if(up && canMove(x,y-speed)) y-=speed;
		if(down && canMove(x,y+speed)) y+=speed;
		
		for(int i = 0; i<Controller.boards.coins.size(); i++) {
			if(this.intersects(Controller.boards.coins.get(i))) {
				Controller.boards.coins.remove(i);
				break;
			}
		};
		
		if(Controller.boards.coins.size() == 0) {
			
		}
		
	}
	
	private boolean canMove(int nextX, int nextY) {
		
		Rectangle bounds = new Rectangle(nextX,nextY,width,height);
		
		for(int x = 0; x<Controller.boards.blocks.length; x++) {
			for(int y = 0; y<Controller.boards.blocks[0].length; y++) {
				if(Controller.boards.blocks[x][y] != null) {
					if(bounds.intersects(Controller.boards.blocks[x][y])) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}
	
}
