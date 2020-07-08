package com.teksystems.tekcamp.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Board {

	public int width;
	public int height;
	public Block[][] blocks;
	
	public List<Coin> coins;
	public List<Ghost> ghosts;
	
	public Board(String path) {
		coins = new ArrayList<>();
		ghosts = new ArrayList<>();
		try {
			
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.height = map.getHeight();
			int[] pixels = new int[width*height];
			blocks = new Block[width][height];
			//I do not know what this is doing.
			map.getRGB(0,0,width,height,pixels,0,width);
			for(int x = 0; x<width; x++) {
				for (int y = 0; y<height; y++) {
					int val = pixels[x+(y*width)];
					if(val == 0xFF000000) {
						blocks[x][y] = new Block(x*32, y*32);
					}
					else if(val == 0xFF0000FF) {
						Controller.player.x = x*32;
						Controller.player.y = y*32;
					}
					else if(val == 0xFFFF0000) {
						ghosts.add(new Ghost(x*32,y*32));
						
					}
					else {
						coins.add(new Coin(x*32,y*32));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void tick() {
		for (Ghost ghost : ghosts) {
			ghost.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int x = 0; x<width; x++) {
			for (int y = 0; y<height; y++) {
				
				if(blocks[x][y] != null) {
					blocks[x][y].render(g);
					
				}
					
				
			}
		}
		
		for (Coin coin : coins) {
			coin.render(g);
		}
		for (Ghost ghost : ghosts) {
			ghost.render(g);
		}
	}
	
}
