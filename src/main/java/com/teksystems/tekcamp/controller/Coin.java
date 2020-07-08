package com.teksystems.tekcamp.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin extends Rectangle{
	
	
	private static final long serialVersionUID = 1L;


	public Coin(int x, int y) {
		setBounds(x+15,y+13,8,8);
	}
	
	
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
	}
}
