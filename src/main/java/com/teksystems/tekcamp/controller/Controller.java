package com.teksystems.tekcamp.controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import com.teksystems.tekcamp.Menu.Menu;


public class Controller extends Canvas implements Runnable, KeyListener {
	//Canvas is a blank rectangular area where we can draw or trap input events from the user.
	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private static final int WIDTH = 1280,HEIGHT = 960;
	public static final String TITLE = "PACMAN";
	public static Board boards;
	private Thread thread;
	public static SpriteSheet spriteSheet;

	int choice = 1;
	
	public static Player player;
	
	
	
	public Controller() {
		Dimension dimension = new Dimension(Controller.WIDTH,Controller.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		addKeyListener(this);
		player = new Player(Controller.WIDTH/2,Controller.HEIGHT/2);
		boards = new Board("/Image/pacman1.png");
		spriteSheet = new SpriteSheet("/Image/caracter1.png");
		
		new Texture();
	}

	public synchronized void start() {
		if(isRunning) {
			return;
		}
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
	
public synchronized void stop() {
		if(!isRunning) {
			return;
		}
			isRunning = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
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
		if(choice == -1) {
		player.render(g);
		boards.render(g);
		}else {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 200, 200);
			g.setFont(new Font(Font.DIALOG,Font.BOLD,19));
			g.drawString("chupa rola", 500, 500);
		}
		g.dispose();
		bs.show();
		
	}




	private void tick() {
		if(choice == -1) {
		player.tick();
		boards.tick();
		}
	}
	
	
	
	
	@Override
	public void run() {
		//So you dont have to click on the window to move the player
		requestFocus();
		//This function below is to set the time we want our tread to run.
		Long lastTime = System.nanoTime();
		double delta = 0;
		//targetTick is the time we want our game to run.
		double targetTick = 40.0;
		double interval =   1000000000/targetTick;
		int framespersecond = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += ( now-lastTime) / interval;
			lastTime = now;
			while(delta >= 1) {
				tick();
				render();
				framespersecond++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				framespersecond = 0;
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
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
			player.left = false;
			player.up = false;
			player.down = false;
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
			player.right = false;
			player.up = false;
			player.down = false;
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
			player.left = false;
			player.right = false;
			player.down = false;
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
			player.left = false;
			player.up = false;
			player.right = false;
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
		
	}




	

}
