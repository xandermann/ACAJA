package mainpack;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import gui_conversion.ConversionModel;
import gui_conversion.ConversionPanel;
/**
 * [ CLASSE POUR LE LANCEMENT DES FENETRES D'OUVERTURE DU LOGICIEL. ]
 * 
 * TODO commentaire a faire. 
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public class OpeningWindow {
	//=======================================================================================================================
	//=======================================================================================================================
	
	/**
	 *[ CONSTANTES DE CLASSE. ]
	 *
	 *TODO commentaire a faire. 
	 */
	private final static int WIDTH = 750;
	private final static int HEIGHT = 500;
	private static JProgressBar bar;
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	/**
	 * [ METHODE INTERNE DE CLASSE - FENETRE DE PRESENTATION DU LOGICIEL. ]
	 * 
	 * TODO commentaire a faire. 
	 */
	private static void generateLoardingWindow() {
		JFrame frame = new JFrame("Chargement d'Acaja");
		OpeningWindow.afficherLogo(frame);
		
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
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE  - FENETRE CHOIX DU MODE D'UTILISATION DU LOGICIEL. ]
	 * 
	 * TODO commentaire a faire. 
	 */
	private static void generateChoiceModeWindow() {
		JButton convertbutton = new JButton("Conversion");
		convertbutton.setPreferredSize(new Dimension(100, 50));
		JButton j2 = new JButton("Traitement");
		j2.setPreferredSize(new Dimension(100, 50));
		
		JFrame frame = new JFrame("Acaja");
		frame.setResizable(false);
		
		OpeningWindow.afficherLogo(frame);
		frame.setLayout(new BorderLayout());
		
		//recuperation des dimensions de l'ecran
		Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
		//on positionne la fenetre au centre de l'ecran
		frame.setLocation(((int)ecran.getWidth()-750)/2, ((int)ecran.getHeight()-500)/2);
				
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
		
		frame.setVisible(true);
		convertbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				ConversionPanel.generateConversionWindow();
				frame.dispose();
			}
		});
		
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * TODO commentaire a faire. 
	 */
	public static void generateOpeningWindow() {
		//generateLoardingWindow();
		generateChoiceModeWindow();
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public static void afficherLogo(JFrame f) {
		try {
			f.setIconImage(ImageIO.read(new File("img/LogoAcaja.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
