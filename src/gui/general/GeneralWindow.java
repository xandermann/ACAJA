package gui.general;

import java.awt.*;
import javax.swing.*;
import gui.WindowTools;
import gui.style.StylizedJFrame;

/**
 * [ CLASSE FENETRE D'UNE VUE. ]
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant une vue.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public abstract class GeneralWindow extends StylizedJFrame{
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 */
	public GeneralWindow(String title, JPanel view){
		super(title);
		if(view == null) throw new NullPointerException("view null !");
		setResizable(false);
		setContentPane(view);
		setSize(new Dimension(view.getWidth(), view.getHeight()));
		setLocationRelativeTo(null);
		WindowTools.showLogo(this);
		WindowTools.executeWindow(this);
	}
}
