package main;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * 
 * @author alex_DaSilva
 *
 */


/**
 *class permettant l'affichage de l'acceuil
 */
public class OpeningWindow {
	private final static int WIDTH = 750;
	private final static int HEIGHT = 500;
	private static JProgressBar bar;
	
	private static void generateLoardingWindow() {
		JFrame frame = new JFrame("Chargement d'Acaja");
		try {
			frame.setIconImage(ImageIO.read(new File("src/img/LogoAcaja.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		frame.setLocation(200, 200);
		frame.setSize(new Dimension(WIDTH/3,HEIGHT/4));
		frame.setVisible(true); 
		
		bar  = new JProgressBar();
		bar.setMaximum(100);
		bar.setMinimum(0);
		bar.setStringPainted(true);
		frame.add(bar);
		
	    for(int val = 0; val <= 100; val++){
	    	bar.setValue(val);
	    	try {
	    		Thread.sleep(10);
	    	} catch (InterruptedException e) {
	    		e.printStackTrace();
	    	}
	    }
	    frame.dispose(); 
	}
	
	private static void generateChoiceModeWindow() {
		JButton convertbutton = new JButton("Conversion");
		convertbutton.setPreferredSize(new Dimension(100, 50));
		JButton j2 = new JButton("Traitement");
		j2.setPreferredSize(new Dimension(100, 50));
		
		JFrame frame = new JFrame("Acaja");
		try {
			frame.setIconImage(ImageIO.read(new File("src/img/LogoAcaja.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frame.setLayout(new BorderLayout());
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(WIDTH,HEIGHT));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(2, 1,0,50));
		
		JPanel p2 = new JPanel();
		p2.setPreferredSize(new Dimension(WIDTH, 150));
		JPanel p3 = new JPanel();
		p3.setPreferredSize(new Dimension(WIDTH, 150));
		JPanel p4 = new JPanel();
		p4.setPreferredSize(new Dimension(150, HEIGHT));
		JPanel p5 = new JPanel();
		p5.setPreferredSize(new Dimension(150, HEIGHT));
		
		frame.add(p1,BorderLayout.CENTER);
		frame.add(p2,BorderLayout.NORTH);
		frame.add(p3,BorderLayout.SOUTH);
		frame.add(p4,BorderLayout.EAST);
		frame.add(p5,BorderLayout.WEST);
		p1.add(convertbutton);
		p1.add(j2);
		
		p1.setBackground(Color.gray);
		p2.setBackground(Color.gray);
		p3.setBackground(Color.gray);
		p4.setBackground(Color.gray);
		p5.setBackground(Color.gray);
		frame.setBackground(Color.gray);
		
		convertbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Prout sa marche pas encore");
				/////////////////////////////////////////////////////////////////////A Rajouter méthode pour afficher
			}
		});
		
		frame.setVisible(true);
		
	}
	
	public static void generateOpeningWindow() {
		generateLoardingWindow();
		generateChoiceModeWindow();
	}
}
