package com.teksystems.tekcamp.controller;

import java.awt.image.BufferedImage;

public class Texture {
	
	public static BufferedImage[] player;
	public static BufferedImage ghost;
	
	public Texture() {
		player = new BufferedImage[2];
		player[0] = Controller.spriteSheet.getSprite(16, 0);
		player[1] = Controller.spriteSheet.getSprite(32, 0);
		ghost = Controller.spriteSheet.getSprite(0, 0);
		
	}

}
