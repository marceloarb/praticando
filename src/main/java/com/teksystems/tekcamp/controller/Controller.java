package com.teksystems.tekcamp.controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class Controller extends Canvas implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private static final int WIDTH = 1280,HEIGHT = 960;
	public static final String TITLE = "PACMAN";
	public static Board boards;
	private Thread thread;
	
	public static Player player;
	
	
	
	public Controller() {
		Dimension dimension = new Dimension(Controller.WIDTH,Controller.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		addKeyListener(this);
		player = new Player(Controller.WIDTH/2,Controller.HEIGHT/2);
		boards = new Board("/Image/pacman1.png");
	}

	public synchronized void start() {
		if(isRunning) {
			return;
		}
		else {
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}
		
	}
	
public synchronized void stop() {
		if(!isRunning) {
			return;
		}
		else {
			isRunning = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	
		
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(g);
		boards.render(g);
		g.dispose();
		bs.show();
		
	}




	private void tick() {
		
		player.tick();
		boards.tick();
	}
	
	
	
	
	@Override
	public void run() {
		//So you dont have to click on the window to move the player
		requestFocus();
		
		Long lastTime = System.nanoTime();
		double delta = 0;
		double targetTick = 40.0;
		double interval =   1000000000/targetTick;
		int fps = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += ( now-lastTime) / interval;
			lastTime = now;
			while(delta >= 1) {
				tick();
				render();
				fps++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				fps = 0;
				timer+=1000;
			}
			
		}
		stop();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
		
	}




	

}
