package gui.conversion;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import exceptions.ImportationException;

public final class DataChoose {
	//=======================================================================================================================
	//=======================================================================================================================

	
	public static File FileChoose() throws ImportationException {
		JFileChooser jfc = new JFileChooser("Parcourir");
		int chooserStatus = jfc.showOpenDialog(null);
		
		File importFile = jfc.getSelectedFile();
		if(importFile == null || chooserStatus != JFileChooser.APPROVE_OPTION) 
			throw new ImportationException(ImportationException.ABSENT_FILE);
		
		return importFile;
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public static ArrayList<File> DirectoryChoose() throws ImportationException {
		ArrayList<File> files = new ArrayList<File>();
		
		JFileChooser jdc = new JFileChooser("Parcourir");
		jdc.showOpenDialog(null);
		jdc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jdc.setAcceptAllFileFilterUsed(true);

		File importDirectory = jdc.getCurrentDirectory();
		if(!importDirectory.isDirectory() || importDirectory == null)
			throw new ImportationException(ImportationException.ABSENT_DIRECTORY);
		else{
			for(File f : importDirectory.listFiles()) files.add(f);
		}
		
		return files;
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
