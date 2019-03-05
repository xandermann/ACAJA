package gui;

import java.io.File;
import java.util.ArrayList;

import exceptions.ImportationException;
import gui.style.StylizedJFileChooser;

public final class JFileChooserManager {
	//=======================================================================================================================
	//=======================================================================================================================

	
	public static File chooseFile() throws ImportationException {
		StylizedJFileChooser jfc = new StylizedJFileChooser("Parcourir");
		int chooserStatus = jfc.showOpenDialog(null);
		
		File importFile = jfc.getSelectedFile();
		if(importFile == null || chooserStatus != StylizedJFileChooser.APPROVE_OPTION) 
			throw new ImportationException(ImportationException.ABSENT_FILE);
		
		return importFile;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	public static File chooseDirectory() throws ImportationException {
		StylizedJFileChooser jdc = new StylizedJFileChooser("Parcourir");
		jdc.setFileSelectionMode(StylizedJFileChooser.DIRECTORIES_ONLY);
		jdc.showOpenDialog(null);

		File importDirectory = jdc.getSelectedFile();
		if(importDirectory == null || !importDirectory.isDirectory()) {
			throw new ImportationException(ImportationException.ABSENT_DIRECTORY);
		}
			
		
		return importDirectory;
	}
	

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	public static ArrayList<File> chooseDirectoryAndListSonFiles() throws ImportationException {
		ArrayList<File> sonFiles = new ArrayList<File>();	
		File importDirectory = JFileChooserManager.chooseDirectory();
		for(File f : importDirectory.listFiles()) sonFiles.add(f);	
		return sonFiles;
	}
	

	
	//=======================================================================================================================
	//=======================================================================================================================
}
