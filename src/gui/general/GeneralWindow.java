package gui.general;

import java.awt.*;
import javax.swing.*;
import gui.WindowTools;
import gui.style.StylizedJFrame;

/**
 * [ CLASSE FENETRE DE LA VUE DES REPONSES. ]
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant la vue des reponses.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public abstract class GeneralWindow extends StylizedJFrame{
	/**
	 * [ CONSTRUCTEUR VIDE. ]
	 */
	public GeneralWindow(String title, JPanel view){
		super(title);
		setResizable(false);
		setContentPane(view);
		setSize(new Dimension(view.getWidth(), view.getHeight()));
		setLocationRelativeTo(null);
		WindowTools.showLogo(this);
		WindowTools.executeWindow(this);
	}
}
