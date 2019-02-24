package gui;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import exceptions.ImportationException;

public final class FilesManager {
	//=======================================================================================================================
	//=======================================================================================================================

	
	public static File chooseFile() throws ImportationException {
		JFileChooser jfc = new JFileChooser("Parcourir");
		int chooserStatus = jfc.showOpenDialog(null);
		
		File importFile = jfc.getSelectedFile();
		if(importFile == null || chooserStatus != JFileChooser.APPROVE_OPTION) 
			throw new ImportationException(ImportationException.ABSENT_FILE);
		
		return importFile;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	public static File chooseDirectory() throws ImportationException {
		JFileChooser jdc = new JFileChooser("Parcourir");
		jdc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jdc.showOpenDialog(null);

		File importDirectory = jdc.getSelectedFile();
		if(!importDirectory.isDirectory() || importDirectory == null) 
			throw new ImportationException(ImportationException.ABSENT_DIRECTORY);
		
		return importDirectory;
	}
	

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	public static ArrayList<File> chooseDirectoryAndListSonFiles() throws ImportationException {
		ArrayList<File> sonFiles = new ArrayList<File>();	
		File importDirectory = FilesManager.chooseDirectory();
		for(File f : importDirectory.listFiles()) sonFiles.add(f);	
		return sonFiles;
	}
	

	
	//=======================================================================================================================
	//=======================================================================================================================
}
