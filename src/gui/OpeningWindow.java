package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gui.conversion.ConversionWindow;
import gui.processing.ProcessingWindow;
import gui.style.StyleTheme;
import gui.style.StylizedJButton;
import gui.style.StylizedJPanel;
import resources.ResourcesManager;

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
	public final static int WIDTH = 400;
	public final static int HEIGHT = 400;

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ METHODE INTERNE DE CLASSE - FENETRE DE PRESENTATION DU LOGICIEL. ]
	 * 
	 * Cette methode permet de generer la fenetre de presentation du logiciel.
	 */
	private static void generateLoadingWindow() {
		ResourcesManager.clearResources();
		
		JFrame presentationWindow = new JFrame("Acaja - un logiciel a la portee de tous.");

		presentationWindow.setSize(new Dimension(WIDTH, HEIGHT));
		presentationWindow.setResizable(false);
		presentationWindow.setLocationRelativeTo(null);
		presentationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//
		// Design de l'OpeningWindow, ajout du panel
		LoadingView loadingView = new LoadingView();
		presentationWindow.add(loadingView);
		//
		//

		WindowTools.showLogo(presentationWindow);
		WindowTools.executeWindow(presentationWindow);

		// Menipulation de la barre de chargement
		try {
			Thread.sleep(1000);

			loadingView.setPercentage(36);

			Thread.sleep(1000);

			loadingView.setPercentage(80);

			Thread.sleep(500);

			loadingView.setPercentage(100);

			Thread.sleep(500);

		} catch (InterruptedException e) {

		}

		presentationWindow.dispose();
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ METHODE INTERNE DE CLASSE - FENETRE CHOIX DU MODE D'UTILISATION DU
	 * LOGICIEL. ]
	 * 
	 * Cette methode permet de generer la fenetre du choix de mode d'utilisation du
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
				NotificationView.alert(NotificationView.INFO, "Ceci est la fenetre pour realiser<br>des conversions.", 4000);
				openingWindow.dispose();
			}
		});

		StylizedJButton processingButton = new StylizedJButton("Traitement");
		processingButton.setPreferredSize(new Dimension(100, 40));
		processingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ProcessingWindow.generateProcessingWindow();
				NotificationView.alert(NotificationView.INFO, "Ceci est la fenetre pour realiser<br>des traitements.", 4000);
				openingWindow.dispose();
			}
		});

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

		centerPanel.setBackground(StyleTheme.BACKGROUND_COLOR);
		northPanel.setBackground(StyleTheme.BACKGROUND_COLOR);
		southPanel.setBackground(StyleTheme.BACKGROUND_COLOR);
		eastPanel.setBackground(StyleTheme.BACKGROUND_COLOR);
		westPanel.setBackground(StyleTheme.BACKGROUND_COLOR);

		WindowTools.executeWindow(openingWindow);
		
		NotificationView.alert(NotificationView.INFO, 
				"Bienvenue dans ACAJA.<br>Ici vous pouvez choisir un mode de travail.", 4000);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour lancer les fenetres de demarrage du logiciel ACAJA.
	 */
	public static void generateOpeningWindow() {
		generateLoadingWindow();
		generateChoiceModeWindow();
	}
	
	

	//=======================================================================================================================
	//=======================================================================================================================
}