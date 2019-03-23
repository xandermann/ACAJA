package gui.general;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import gui.JFileChooserManager;
import gui.alerts.ASWindow;
import gui.alerts.Alert;
import gui.answers.AnswersWindow;
import gui.conversion.ConversionModel;
import gui.conversion.ConversionWindow;
import gui.processing.ProcessingWindow;
import resources.ResourcesManager;
import threads.RuntimeSpaceManager;

/**
 * [ REALISER DES ACTIONS GENERIQUES. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class Actions {
	/**
	 * [ IMPORTER 1 FICHIER. ]
	 */
	public static void input() {
		try {
		    File f = JFileChooserManager.chooseFile();
			
			Context.$M.add(f);
		   
			if(Context.$M instanceof ConversionModel && Context.$M.getCurrentFile() == null)
				((ConversionWindow) Context.$W).redrawFirstTime();
			
		   Context.$M.setCurrentFile(Context.$M.getFiles().get(Context.$M.getFiles().size()-1));
			
		   Alert.shortAlert(Alert.SUCCESS, "Import realise avec succes.");
		} catch (Exception e) {
			Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
		}	    
	}
	
	
	/**
	 * [ IMPORTER 1 DOSSIER. ]
	 */
	public static void inputs() {
		try {
			ArrayList<File> files = JFileChooserManager.chooseDirectoryAndListSonFiles();
			
			for (File f : files) {
				Context.$M.add(f);
				
				if(Context.$M instanceof ConversionModel && Context.$M.getCurrentFile() == null)
					((ConversionWindow) Context.$W).redrawFirstTime();
				
				Context.$M.setCurrentFile(Context.$M.getFiles().get(Context.$M.getFiles().size()-1));
			}
			
			Alert.shortAlert(Alert.SUCCESS, "Import realise avec succes.");
		} catch (Exception e) {
			Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
		}
	}
	
	
	/**
	 * [ CHOISIR REPERTOIRE DE SORTIE. ]
	 */
	public static void output() {
		try {
			((ConversionModel) Context.$M).setDestinationFolder(JFileChooserManager.chooseDirectory());
			Alert.shortAlert(Alert.SUCCESS, "Chemin de destination des fichiers <br>enregistre.");
		}catch(IllegalArgumentException iae){
			Alert.shortAlert(Alert.FAILURE, "Echec du choix du repertoire de sortie.");
		}
	}
	
	
	/**
	 * [ CHANGER DE MODE. ]
	 */
	public static void switchMode() {
		Context.$W.dispose();
		if(Context.$W instanceof ConversionWindow) new ProcessingWindow(); else new ConversionWindow();
		Alert.shortAlert(Alert.SUCCESS, "Changement de mode realise <br>avec succes.");
	}
	
	
	/**
	 * [ INSPECTER LES REÖNSES DE FFMPEG. ]
	 */
	public static void inspect() {
		new AnswersWindow();
		Alert.longAlert(Alert.INFO, "Ceci est l'historique des reponses <br>de FFmpeg.");
	}
	
	
	/**
	 * [ MODIFIER LES PARAMETRES DE NOTIFICATION. ]
	 */
	public static void set() {
		new ASWindow();
		Alert.longAlert(Alert.INFO, "Ceci est la fenetre de <br>gestion des parametres.");
	}
	
	
	/**
	 * [ QUITTER. ]
	 */
	public static void quit() {
		ResourcesManager.clearResources();
		System.exit(0);
	}
	
	
	/**
	 * [ SAUVER / CONVERTIR / EXPORTER. ]
	 */
	public static void save() {
		if(Context.$W instanceof ConversionWindow) {
			if(RuntimeSpaceManager.manage() && Context.$M.isModified()) {
				((ConversionWindow) Context.$W).drawConvertWindow();
				Alert.longAlert(
						Alert.INFO, 
						"Ceci est la fenetre de choix des parametres<br>d'export des fichiers à convertir.");
			}else
				Alert.longAlert(
						Alert.FAILURE, 
						"Aucun fichier modifie a convertir trouves<br>OU autre conversion deja en cours !");
		}
	}
}
