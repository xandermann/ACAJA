package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import gui.conversion.ConversionWindow;
import gui.style.*;
import tools.*;

/**
 * [ CLASSE POUR LE LANCEMENT DES FENETRES D'OUVERTURE DU LOGICIEL. ]
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public final class OpeningWindow {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ CONSTANTES DE CLASSE INTERNES. ]
	 *
	 * Dimensions des fenetres de demarrage.
	 */
	private final static int WIDTH = 400;
	private final static int HEIGHT = 400;

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE - FENETRE DE PRESENTATION DU LOGICIEL. ]
	 * 
	 * Cette methode permet de generer la fenetre de presentation du logiciel.
	 */
	private static void generatePresentationWindow() {
		JFrame presentationWindow = new JFrame("Acaja - un logiciel à la portee de tous.");

		presentationWindow.setSize(new Dimension(WIDTH, HEIGHT));
		presentationWindow.setResizable(false);
		presentationWindow.setLocationRelativeTo(null);
		presentationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Logo en image de fond.
		presentationWindow.add(new JPanel(){
			public void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(ResourceConstants.ACAJA_LOGO), 80, 60, null);
				} catch (IOException ioe) {}
			}
		});

		WindowTools.showLogo(presentationWindow);
		WindowTools.executeWindow(presentationWindow);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}

		presentationWindow.dispose();
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE - FENETRE CHOIX DU MODE D'UTILISATION DU LOGICIEL. ]
	 * 
	 * Cette methode permet de generer la 
	 * fenetre du choix de mode d'utilisation du
	 * logiciel : conversion ou traitement.
	 */
	private static void generateChoiceModeWindow() {
		JFrame openingWindow = new JFrame("Acaja");

		StylizedJButton convertbutton = new StylizedJButton("Conversion");
		convertbutton.setPreferredSize(new Dimension(100, 40));
		convertbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConversionWindow.generateConversionWindow();
				openingWindow.dispose();
			}
		});

		StylizedJButton processingButton = new StylizedJButton("Traitement");
		processingButton.setPreferredSize(new Dimension(100, 40));

		openingWindow.setResizable(false);
		openingWindow.setLayout(new BorderLayout());
		openingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		openingWindow.setSize(new Dimension(WIDTH, HEIGHT));
		openingWindow.setLocationRelativeTo(null);

		WindowTools.showLogo(openingWindow);

		StylizedJPanel centerPanel = new StylizedJPanel(new GridLayout(2, 1, 0, 50));
		centerPanel.add(convertbutton);
		centerPanel.add(processingButton);

		StylizedJPanel northPanel = new StylizedJPanel();
		northPanel.setPreferredSize(new Dimension(WIDTH, 135));
		StylizedJPanel southPanel = new StylizedJPanel();
		southPanel.setPreferredSize(new Dimension(WIDTH, 135));
		StylizedJPanel eastPanel = new StylizedJPanel();
		eastPanel.setPreferredSize(new Dimension(100, HEIGHT));
		StylizedJPanel westPanel = new StylizedJPanel();
		westPanel.setPreferredSize(new Dimension(100, HEIGHT));

		openingWindow.add(centerPanel, BorderLayout.CENTER);
		openingWindow.add(northPanel, BorderLayout.NORTH);
		openingWindow.add(southPanel, BorderLayout.SOUTH);
		openingWindow.add(eastPanel, BorderLayout.EAST);
		openingWindow.add(westPanel, BorderLayout.WEST);

		centerPanel.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		northPanel.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		southPanel.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		eastPanel.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		westPanel.setBackground(StyleConfigurator.BACKGROUND_COLOR);

		WindowTools.executeWindow(openingWindow);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour lancer les fenetres de demarrage du logiciel ACAJA.
	 */
	public static void generateOpeningWindow() {
		generatePresentationWindow();
		generateChoiceModeWindow();
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
