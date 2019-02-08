package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.conversion.ConversionWindow;
import style.JButton;
import style.Style;
import tools.ResourceConstants;
import tools.WindowTools;

/**
 * [ CLASSE POUR LE LANCEMENT DES FENETRES D'OUVERTURE DU LOGICIEL. ]
 * 
 * TODO commentaire a faire.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public final class OpeningWindow {

	/**
	 * [ CONSTANTES DE CLASSE INTERNES. ]
	 *
	 * Dimensions des fenetres de demarrage.
	 */
	private final static int WIDTH = 400;
	private final static int HEIGHT = 400;

	/**
	 * [ METHODE INTERNE DE CLASSE - FENETRE DE PRESENTATION DU LOGICIEL. ]
	 * 
	 * Cette methode permet de generer la fenetre de presentation du logiciel.
	 */
	private static void generateLoadingWindow() {
		JFrame loadingWindow = new JFrame("Acaja - un logiciel à la portee de tous.");

		loadingWindow.setSize(new Dimension(WIDTH, HEIGHT));
		loadingWindow.setResizable(false);
		loadingWindow.setLocationRelativeTo(null);
		loadingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingWindow.setVisible(true);

		// Panel de la fenetre
		JPanel panel = new JPanel();
		panel.setBackground(Style.BACKGROUND_PRIMARY);
		panel.setLayout(new BorderLayout());

		// Ajout barre de progression
		LoadingWindowPanel loadingWindowPanel = new LoadingWindowPanel();
		loadingWindowPanel.setProgress(10);
		panel.add(loadingWindowPanel, BorderLayout.CENTER);

		// Ajout image
		panel.add(new JPanel() {
			public void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(ResourceConstants.ACAJA_LOGO), 80, 60, null);
				} catch (IOException ioe) {
				}
			}
		}, BorderLayout.NORTH);

		WindowTools.showLogo(loadingWindow);
		WindowTools.executeWindow(loadingWindow);

		// Ajoute le panel a la fenetre
		loadingWindow.setContentPane(panel);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		loadingWindowPanel.setProgress(35);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		loadingWindowPanel.setProgress(100);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		loadingWindow.dispose();

	}

	/**
	 * [ METHODE INTERNE DE CLASSE - FENETRE CHOIX DU MODE D'UTILISATION DU
	 * LOGICIEL. ]
	 * 
	 * Cette methode permet de generer la fenetre du choix de mode d'utilisation du
	 * logiciel : conversion ou traitement.
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

		openingWindow.add(centerPanel, BorderLayout.CENTER);
		openingWindow.add(northPanel, BorderLayout.NORTH);
		openingWindow.add(southPanel, BorderLayout.SOUTH);
		openingWindow.add(eastPanel, BorderLayout.EAST);
		openingWindow.add(westPanel, BorderLayout.WEST);

		centerPanel.setBackground(Style.BACKGROUND_PRIMARY);
		northPanel.setBackground(Style.BACKGROUND_PRIMARY);
		southPanel.setBackground(Style.BACKGROUND_PRIMARY);
		eastPanel.setBackground(Style.BACKGROUND_PRIMARY);
		westPanel.setBackground(Style.BACKGROUND_PRIMARY);

		WindowTools.executeWindow(openingWindow);
	}

	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour lancer les fenetres de demarrage du logiciel ACAJA.
	 */
	public static void generateOpeningWindow() {
		generateLoadingWindow();
		generateChoiceModeWindow();
	}
}
