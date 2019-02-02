package main_pack;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import gui.conversion.ConversionModel;
import gui.conversion.ConversionPanel;
import tools.Resources;
import tools.WindowTools;
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
		JFrame frame = new JFrame("Acaja - un logiciel à la portée de tous.");
		
		frame.setSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);
		
		frame.setContentPane(new JPanel() {
		    public void paintComponent(Graphics g) {
		        try {
					g.drawImage(ImageIO.read(Resources.ACAJA_LOGO),80,60,null);
				} catch (IOException ioe) {}
		      }
		});

		WindowTools.showLogo(frame);
		WindowTools.focusWindow(frame);
		
		frame.setBackground(Color.gray);
		WindowTools.showWindow(frame);
		
	    try {
	    	Thread.sleep(3000);
	    } catch (InterruptedException ie) {}

	    frame.dispose(); 
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE  - FENETRE CHOIX DU MODE D'UTILISATION DU LOGICIEL. ]
	 * 
	 * TODO commentaire a faire. 
	 */
	private static void generateChoiceModeWindow() {
		JFrame frame = new JFrame("Acaja");
		
		JButton convertbutton = new JButton("Conversion");
		convertbutton.setPreferredSize(new Dimension(100, 40));
		convertbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				ConversionPanel.generateConversionWindow();
				frame.dispose();
			}
		});
		
		
		JButton processingButton = new JButton("Traitement");
		processingButton.setPreferredSize(new Dimension(100, 40));
		
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(WIDTH, HEIGHT));
		
		WindowTools.showLogo(frame);
		WindowTools.focusWindow(frame);
		
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
		
		frame.add(centerPanel,BorderLayout.CENTER);
		frame.add(northPanel,BorderLayout.NORTH);
		frame.add(southPanel,BorderLayout.SOUTH);
		frame.add(eastPanel,BorderLayout.EAST);
		frame.add(westPanel,BorderLayout.WEST);
		
		centerPanel.setBackground(Color.gray);
		northPanel.setBackground(Color.gray);
		southPanel.setBackground(Color.gray);
		eastPanel.setBackground(Color.gray);
		westPanel.setBackground(Color.gray);
		
		WindowTools.showWindow(frame);
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
