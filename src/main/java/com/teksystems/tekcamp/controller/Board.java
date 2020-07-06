package com.teksystems.tekcamp.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Board {

	public int width;
	public int height;
	public Block[][] blocks;
	
	public Board(String path) {
		System.out.println(path);
		
		try {
			System.out.print("***********************************");
			
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			System.out.print("***********************************");
			this.width = map.getWidth();
			this.height = map.getHeight();
			int[] pixels = new int[width*height];
			blocks = new Block[height][width];
			map.getRGB(0,0,width,height,pixels,0,width);
			for(int x = 0; x<width; x++) {
				for (int y = 0; y<height; y++) {
					int val = pixels[x+(y*width)];
					if(val == 0xFF000000) {
						blocks[x][y] = new Block(x*32, y*32);
					}
				}
			}
		} catch (IOException e) {
			System.out.print("***********************************");
			System.out.print(e);
			e.printStackTrace();
			System.out.print("***********************************");
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
	}
	
}
