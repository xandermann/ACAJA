package main;


import java.awt.*;

import javax.swing.*;

public class OpeningWindow {
	private final static int WIDTH = 750;
	private final static int HEIGHT = 500;
	
	private static void generateLoardingWindow() {
		JFrame frame = new JFrame("Chargement");
		frame.setLocation(100, 100);
		frame.setSize(new Dimension(WIDTH,HEIGHT));
		//frame.pack();
		frame.setVisible(true);
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		frame.dispose();
	}
	
	private static void generateChoiceModeWindow() {
		JButton j1 = new JButton("Conversion");
		j1.setPreferredSize(new Dimension(100, 50));
		JButton j2 = new JButton("Traitement");
		j2.setPreferredSize(new Dimension(100, 50));
		
		JFrame frame = new JFrame("Chargement");
		
		frame.add(j1);
		frame.add(j2);
		
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(WIDTH,HEIGHT));
		frame.setLayout(new GridLayout(2,1));
		frame.setVisible(true);
	}
	
	public static void generateOpeningWindow() {
		generateLoardingWindow();
		generateChoiceModeWindow();
	}
}
