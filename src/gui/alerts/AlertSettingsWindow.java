package gui.alerts;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import gui.WindowTools;
import gui.answers.views.AnswerView;

/**
 * [ CLASSE FENETRE DE LA VUE DE CHOIX DES PARAMETRES DES ALERTES. ]
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant la vue de choix des parametres des alertes.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class AlertSettingsWindow extends JFrame{
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 */
	public AlertSettingsWindow(){
		setTitle("Choix des parametres des notifications.");
		setResizable(false);
		AlertSettingsView asv = new AlertSettingsView();
		setContentPane(asv);
		setSize(new Dimension(asv.getWidth(), asv.getHeight()));
		setLocationRelativeTo(null);
		WindowTools.showLogo(this);
		WindowTools.executeWindow(this);
	}
}
