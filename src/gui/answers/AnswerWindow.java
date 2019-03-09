package gui.answers;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import gui.WindowTools;
import gui.answers.views.AnswerView;

/**
 * [ CLASSE FENETRE DE LA VUE D'UNE REPONSE. ]
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant la vue d'une reponse.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class AnswerWindow extends JFrame{
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 */
	public AnswerWindow(File f){
		if(f == null) throw new NullPointerException("Fichier null !");
		setTitle("Affichage de la reponse : "+f.getName()+".");
		setResizable(false);
		AnswerView av = new AnswerView(f);
		setContentPane(av);
		setSize(new Dimension(av.getWidth(), av.getHeight()));
		setLocationRelativeTo(null);
		WindowTools.executeWindow(this);
	}
}
