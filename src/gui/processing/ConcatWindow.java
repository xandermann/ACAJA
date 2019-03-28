package gui.processing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.files.ProcessingFile;
import gui.JFileChooserManager;
import gui.WindowTools;
import gui.general.GeneralModel;
import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;

public class ConcatWindow extends JFrame {
	
	private static ArrayList<ProcessingFile> listOfFile;

	public ConcatWindow(ProcessingModel m) {
		listOfFile = new ArrayList<ProcessingFile>();
		WindowTools.executeWindow(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.lightGray);
		WindowTools.showLogo(this);
		this.setTitle("Assembleur de vidéo");
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		ConcatPanel cp = new ConcatPanel();
		this.add(cp);
		if(m.getCurrentFile() != null)
			listOfFile.add(m.getCurrentFile());
		drawMenu();
	}
	
	public void drawMenu() {
		StylizedJMenuBar jm = new StylizedJMenuBar();
		JMenu fileMenu = new JMenu("Fichier");
		jm.add(fileMenu);
		
		
		
		StylizedJMenuItem importfile = new StylizedJMenuItem("Importer une video");
		fileMenu.add(importfile);
		importfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ProcessingFile p = null;
				try {
					p = new ProcessingFile(JFileChooserManager.chooseFile());
				} catch (IncorrectFileException | UnfindableResourceException e) {e.printStackTrace();}
				listOfFile.add(p);
				repaint();
			}
		});
		this.setJMenuBar(jm);
	}

	public static ArrayList<ProcessingFile> getListOfFile() {
		return listOfFile;
	}

}
