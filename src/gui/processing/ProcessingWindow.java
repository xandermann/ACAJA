package gui.processing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import exceptions.ImportationException;
import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.files.SelectableFile;
import gui.JFileChooserManager;
import gui.WindowTools;
import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;
import wrapper.runtime.global.SystemRequests;

public class ProcessingWindow extends JFrame {
	
	private ModelARenomer model;

	
	private ProcessingWindow() {
		
		this.model = new ModelARenomer();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.createJMenu();
		this.setBackground(Color.lightGray);
		WindowTools.showLogo(this);
		this.setTitle("Traitement d'une vidéo");
		WindowTools.executeWindow(this);

		this.setSize(1000, 625);
		this.setLocationRelativeTo(null);
		ProcessingPan p = new ProcessingPan(model);

		this.setResizable(false);
		this.add(p);
		
	}

	
	private void createJMenu() {
		StylizedJMenuBar jm = new StylizedJMenuBar();
		JMenu fileMenu = new JMenu("Fichier");
		jm.add(fileMenu);
		JMenu libraryMenu = new JMenu("Bibliotheque");
		jm.add(libraryMenu);
		JMenu videoMenu = new JMenu("Video");
		jm.add(videoMenu);
		
		StylizedJMenuItem importFile = new StylizedJMenuItem("Importer un fichier");
		importFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					File f = JFileChooserManager.chooseFile();
					SelectableFile sf = null;
					try {
						sf = new SelectableFile(f);
					} catch (UnfindableResourceException e) {}
					model.setCurentFile(sf);
				} catch (IncorrectFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ImportationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		libraryMenu.add(importFile);

		this.setJMenuBar(jm);

	}
	
	
	public static void generateProcessingWindow() {
		new ProcessingWindow();
	}

	
	
}
