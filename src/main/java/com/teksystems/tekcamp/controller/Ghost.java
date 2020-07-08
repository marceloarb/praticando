package com.teksystems.tekcamp.controller;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ghost extends Rectangle{


	private static final long serialVersionUID = 1L;
	
	private int random = 0,smart = 1,findPath = 2;
	
	private int state = random;
	private int right = 0, left = 1, up = 2, down = 3;
	private int dir = -1;
	private int time = 0;
	
	private int targetTime = 60*10;
	
	private int speed = 3;
	
	public Random randomGen;

	private int lastDir = -1;
	public Ghost(int x , int y) {
		randomGen = new Random();
		setBounds(x,y,30,30);
		dir = randomGen.nextInt(4);
	}
	
	public void tick() {
		if(state == random) {
			if(dir == right) {
				if(canMove(x+speed,y)) {
					x+=speed;
				}
				else {
					dir = randomGen.nextInt(4);
				}
			}
			else if(dir == left) {
				if(canMove(x-speed,y)) {
					x-=speed;
				}
				else {
					dir = randomGen.nextInt(4);
				}
			}
			else if(dir == up) {
				if(canMove(x,y-speed)) {
					y-=speed;
				}
				else {
					dir = randomGen.nextInt(4);
				}
			}
			else if(dir == down) {
				if(canMove(x,y+speed)) {
					y+=speed;
				}
				else {
					dir = randomGen.nextInt(4);
				}
			}
			
			time++;
			if(time == targetTime) {
				state = smart;
				time = 0;
			}
			
		}
		else if(state == smart) {
			//Follow the player
			
			boolean move = false;
			if(x<Controller.player.x) {
				if(canMove(x+speed,y)) {
					x+=speed;
					lastDir = right;
				}
			}
			if(x>Controller.player.x) {
				if(canMove(x-speed,y)) {
					x-=speed;
					lastDir = left;
				}
			}
			if(y<Controller.player.y) {
				if(canMove(x,y+speed)) {
					y+=speed;
					lastDir = down;
				}
			}
			if(y>Controller.player.y) {
				if(canMove(x,y-speed)) {
					y-=speed;
					lastDir = up;
				}
			}
			
			if(x == Controller.player.x && y == Controller.player.y) move = true;
			if(!move) {
				state = findPath;
			}
			
			else if(state == findPath) {
				if(lastDir == right) {
					if(y < Controller.player.y) {
						if(canMove(x,y+speed)) {
							y+=speed;
							state = smart;
						}
						
					}
					else {
						if(canMove(x,y-speed)) {
							y-=speed;
							state = smart;
						}
					}
					if(canMove(x+speed,y)) {
						x+=speed;
					}
					
				}
				else if(lastDir == left) {
					if(y < Controller.player.y) {
						if(canMove(x,y+speed)) {
							y+=speed;
							state = smart;
						}
						
					}
					else {
						if(canMove(x,y-speed)) {
							y-=speed;
							state = smart;
						}
					}
					if(canMove(x-speed,y)) {
						x-=speed;
					}
									
				}
				else if(lastDir == up) {
					if(x < Controller.player.x) {
						if(canMove(x+speed,y)) {
							x+=speed;
							state = smart;
						}
						
					}
					else {
						if(canMove(x-speed,y)) {
							x-=speed;
							state = smart;
						}
					}
					if(canMove(x,y-speed)) {
						y-=speed;
					}
					
				}
				else if(lastDir == down) {
					if(x < Controller.player.x) {
						if(canMove(x+speed,y)) {
							x+=speed;
							state = smart;
						}
						
					}
					else {
						if(canMove(x-speed,y)) {
							x-=speed;
							state = smart;
						}
					}
					if(canMove(x,y+speed)) {
						y+=speed;
					}
				}
				
			}
			
//			time++;
//			if(time == targetTime * 2) {
//				state = smart;
//				time = 0;
//			}
			
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
		SpriteSheet sheet = Controller.spriteSheet;
		g.drawImage(Texture.ghost, x, y,32,32, null);
		
	}
	
}
