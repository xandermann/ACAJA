package gui_conversion;

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
		if(importFile == null || chooserStatus != JFileChooser.APPROVE_OPTION) {
			throw new ImportationException("Merci de selectionner un fichier");
		} 
		
		return importFile;
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public static ArrayList<File> DirectoryChoose() throws Exception {
		ArrayList<File> files = new ArrayList<File>();
		
		JFileChooser jdc = new JFileChooser("Parcourir");
		jdc.showOpenDialog(null);
		jdc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jdc.setAcceptAllFileFilterUsed(true);

		File importDirectory = jdc.getCurrentDirectory();
		if(!importDirectory.isDirectory() || importDirectory == null)
			throw new Exception("Merci de selectionner un dossier");
		else{
			for(File f : importDirectory.listFiles()) 
				files.add(f);
		}
		return files;
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
