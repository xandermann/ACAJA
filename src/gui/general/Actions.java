package gui.general;

import java.io.File;
import java.util.ArrayList;
import gui.JFileChooserManager;
import gui.alerts.Alert;
import gui.conversion.ConversionModel;
import gui.conversion.ConversionWindow;

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
}
