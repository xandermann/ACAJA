package gui_conversion;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class DataChoose {
	
	private static JFileChooser jfc;
	
	public static File FileChoose() throws Exception {
		
		jfc = new JFileChooser("Parcourir");
		jfc.showOpenDialog(null);
		File importFile = jfc.getSelectedFile();
		if(!importFile.isFile() || importFile == null) {
			throw new Exception("Merci de selectionner un fichier");
		} 
		
		return importFile;
	}
	
	public static ArrayList<File> DirectoryChoose() {
		
		//Todo
		return null;
	}
}
