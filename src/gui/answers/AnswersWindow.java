package gui.answers;

import java.awt.*;
import javax.swing.*;
import gui.WindowTools;
import gui.answers.views.AnswersView;

/**
 * [ CLASSE FENETRE DE LA VUE DES REPONSES. ]
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant la vue des reponses.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class AnswersWindow extends JFrame{
	/**
	 * [ CONSTRUCTEUR VIDE. ]
	 */
	public AnswersWindow(){
		super("Historique des reponses de FFmpeg.");
		setResizable(false);
		AnswersView av = new AnswersView();
		setContentPane(av);
		setSize(new Dimension(av.getWidth(), av.getHeight()));
		setLocationRelativeTo(null);
		WindowTools.executeWindow(this);
	}
}