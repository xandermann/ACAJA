package Accueil;


import java.awt.*;

import javax.swing.*;

public class OpeningWindow {
	private static int x = 750;
	private static int y = 500;
	public OpeningWindow() {
		
	}
	
	private static JFrame generateLoardingWindow() {
		JFrame frame = new JFrame("Chargement");
		frame.setLocation(100, 100);
		frame.setSize(new Dimension(x,y));
		//frame.pack();
		frame.setVisible(true);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		frame.dispose();
		return frame;
	}
	
	private static JFrame generateChoiceModeWindow() {
		JFrame frame = new JFrame("Chargement");
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(x,y));
		frame.setLayout(new GridLayout(2,1));
		
		JButton j1 = new JButton("Conversion");
		j1.setPreferredSize(new Dimension(100, 50));
		JButton j2 = new JButton("Traitement");
		j2.setPreferredSize(new Dimension(100, 50));
		
		frame.add(j1);
		frame.add(j2);
		
		frame.setVisible(true);
		
		return frame;
	}
	
	public static void generateOpeningWindow() {
		generateLoardingWindow();
		generateChoiceModeWindow();
	}
}
