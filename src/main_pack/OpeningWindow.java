package main_pack;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import gui.conversion.*;
import tools.*;

/**
 * [ CLASSE POUR LE LANCEMENT DES FENETRES D'OUVERTURE DU LOGICIEL. ]
 * 
 * TODO commentaire a faire. 
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class OpeningWindow {
	//=======================================================================================================================
	//=======================================================================================================================
	
	/**
	 *[ CONSTANTES DE CLASSE. ]
	 *
	 *TODO commentaire a faire. 
	 */
	private final static int WIDTH = 400;
	private final static int HEIGHT = 400;
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE - FENETRE DE PRESENTATION DU LOGICIEL. ]
	 * 
	 * TODO commentaire a faire. 
	 */
	private static void generateLoadingWindow() {
		JFrame loadingWindow = new JFrame("Acaja - un logiciel à la portée de tous.");
		
		loadingWindow.setSize(new Dimension(WIDTH, HEIGHT));
		loadingWindow.setResizable(false);
		loadingWindow.setLocationRelativeTo(null);
		
		loadingWindow.setContentPane(new JPanel() {
		    public void paintComponent(Graphics g) {
		        try {
					g.drawImage(ImageIO.read(Resources.ACAJA_LOGO),80,60,null);
				} catch (IOException ioe) {}
		      }
		});

		WindowTools.showLogo(loadingWindow);
		
		loadingWindow.setBackground(Color.gray);
		WindowTools.executeWindow(loadingWindow);
		
	    try {
	    	Thread.sleep(3000);
	    } catch (InterruptedException ie) {}

	    loadingWindow.dispose(); 
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE  - FENETRE CHOIX DU MODE D'UTILISATION DU LOGICIEL. ]
	 * 
	 * TODO commentaire a faire. 
	 */
	private static void generateChoiceModeWindow() {
		JFrame openingWindow = new JFrame("Acaja");
		
		JButton convertbutton = new JButton("Conversion");
		convertbutton.setPreferredSize(new Dimension(100, 40));
		convertbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				ConversionWindow.generateConversionWindow();
				openingWindow.dispose();
			}
		});
		
		
		JButton processingButton = new JButton("Traitement");
		processingButton.setPreferredSize(new Dimension(100, 40));
		
		openingWindow.setResizable(false);
		openingWindow.setLayout(new BorderLayout());
		openingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		openingWindow.setSize(new Dimension(WIDTH, HEIGHT));
		openingWindow.setLocationRelativeTo(null);
		
		WindowTools.showLogo(openingWindow);

		JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 50));
		centerPanel.add(convertbutton);
		centerPanel.add(processingButton);
		
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(WIDTH, 135));
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(WIDTH, 135));
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(100, HEIGHT));
		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(100, HEIGHT));
		
		openingWindow.add(centerPanel,BorderLayout.CENTER);
		openingWindow.add(northPanel,BorderLayout.NORTH);
		openingWindow.add(southPanel,BorderLayout.SOUTH);
		openingWindow.add(eastPanel,BorderLayout.EAST);
		openingWindow.add(westPanel,BorderLayout.WEST);
		
		centerPanel.setBackground(Color.gray);
		northPanel.setBackground(Color.gray);
		southPanel.setBackground(Color.gray);
		eastPanel.setBackground(Color.gray);
		westPanel.setBackground(Color.gray);
		
		WindowTools.executeWindow(openingWindow);
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * TODO commentaire a faire. 
	 */
	public static void generateOpeningWindow() {
		generateLoadingWindow();
		generateChoiceModeWindow();
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
