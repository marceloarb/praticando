package com.teksystems.tekcamp.Menu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Scanner;

import com.teksystems.tekcamp.controller.Controller;

public class Menu extends Controller{
	
	boolean exit;
	Controller controller = new Controller();
	
	public Menu() {
		
	}
	
	public void run() {
		
		
		
		printHeader();
		while(!exit) {
			printMenu();
			int choice = getInput();
			performAction(choice);
		}
	}
	
	
	
	



	private void performAction(int choice) {
		switch(choice) {
		case 0:
			exit = true;
			break;
		case 1:
			choice = 1;
		case 2:
			choice = 2;
			default:
				
		}
		
	}


	public void printHeader() {
		System.out.println("|------------------------------------|");
		System.out.println("|             Pac-Man                |");
		System.out.println("|         Menu Application           |");
		System.out.println("|------------------------------------|");
	}
	
	
	public void printMenu() {
		System.out.println("1)PLAY!");
		System.out.println("2)Read how to play!");
		System.out.println("0)EXIT!");
	}
	
	private int getInput() {
		Scanner scanner = new Scanner(System.in);
		int choice = -1;
		while(choice < 0 || choice > 2) {
			try {
				choice = Integer.parseInt(scanner.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("INvalid selection. Please try again.");
			}
			
		}
		return choice;
		
	}
	
}
