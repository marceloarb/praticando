package com.teksystems.tekcamp.test;

import javax.swing.JFrame;

import com.teksystems.tekcamp.controller.Controller;

public class Test {
	public static void main(String[] args) {
		
		Controller controller = new Controller();
		JFrame frame = new JFrame();
		frame.setTitle(Controller.TITLE);
		frame.add(controller);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		controller.start();
		
	}

}
