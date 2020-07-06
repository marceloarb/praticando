package com.teksystems.tekcamp.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle{

	
	
	private static final long serialVersionUID = 1L;


	public Block(int x , int y) {
		setBounds(x,y,32,32);
	}
	
	
	public void render(Graphics g ) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
}
