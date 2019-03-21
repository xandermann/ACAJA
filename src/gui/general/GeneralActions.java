package gui.general;

import java.io.File;
import java.util.ArrayList;

import gui.JFileChooserManager;
import gui.alerts.Alert;
import gui.conversion.ConversionModel;
import gui.conversion.ConversionWindow;

public final class GeneralActions {
	public static void input() {
		try {
			   File f = JFileChooserManager.chooseFile();
			   GeneralContext.$M.add(f);
				if(GeneralContext.$M instanceof ConversionModel && GeneralContext.$M.getCurrentFile() == null)
					((ConversionWindow) GeneralContext.$W).redrawFirstTime();
			   GeneralContext.$M.setCurrentFile(
					   GeneralContext.$M.getFiles().get(GeneralContext.$M.getFiles().size()-1));
				Alert.shortAlert(Alert.SUCCESS, "Import realise avec succes.");
	    } catch (Exception e) {
				Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
	    }
	}
	
	public static void inputs() {
		try {
			ArrayList<File> files = JFileChooserManager.chooseDirectoryAndListSonFiles();
			for (File f : files) {
				GeneralContext.$M.add(f);
				if(GeneralContext.$M instanceof ConversionModel && GeneralContext.$M.getCurrentFile() == null)
					((ConversionWindow) GeneralContext.$W).redrawFirstTime();
				GeneralContext.$M.setCurrentFile(GeneralContext.$M.getFiles().get(GeneralContext.$M.getFiles().size()-1));
			}
			Alert.shortAlert(Alert.SUCCESS, "Import realise avec succes.");
		} catch (Exception e) {
			Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
		}
	}
}
