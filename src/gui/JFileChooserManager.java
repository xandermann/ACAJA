package gui;

import java.io.File;
import java.util.ArrayList;
import gui.style.StylizedJFileChooser;

public final class JFileChooserManager {
	// =======================================================================================================================
	// =======================================================================================================================

	public static File chooseFile() {
		StylizedJFileChooser jfc = new StylizedJFileChooser("Parcourir");
		int chooserStatus = jfc.showOpenDialog(null);
		File importFile = jfc.getSelectedFile();
		return importFile;
	}

	// =======================================================================================================================
	// =======================================================================================================================

	public static File chooseDirectory() {
		StylizedJFileChooser jdc = new StylizedJFileChooser("Parcourir");
		jdc.setFileSelectionMode(StylizedJFileChooser.DIRECTORIES_ONLY);
		jdc.showOpenDialog(null);
		File importDirectory = jdc.getSelectedFile();
		if (importDirectory==null || !importDirectory.isDirectory())
			throw new IllegalArgumentException("Attendu un repertoire !");
		return importDirectory;
	}

	// =======================================================================================================================
	// =======================================================================================================================

	public static ArrayList<File> chooseDirectoryAndListSonFiles() {
		ArrayList<File> sonFiles = new ArrayList<File>();
		File importDirectory = JFileChooserManager.chooseDirectory();
		for (File f : importDirectory.listFiles())
			sonFiles.add(f);
		return sonFiles;
	}

	// =======================================================================================================================
	// =======================================================================================================================
}
