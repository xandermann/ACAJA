package gui.processing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.ProcessingType;
import files.files.ProcessingFile;
import files.files.SelectableFile;
import gui.JFileChooserManager;
import gui.WindowTools;
import gui.alerts.Alert;
import gui.conversion.ConversionWindow;
import gui.general.Actions;
import gui.general.Context;
import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;
import resources.ResourcesManager;
import wrapper.runtime.global.SystemRequests;

public class ProcessingWindow extends JFrame {
	
	private ProcessingModel model;

	
	public ProcessingWindow() {
		Context.$0();
		Context.$W = this;
		new ProcessingModel();
		
		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				ResourcesManager.clearResources();
			}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
		this.model = new ProcessingModel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.createJMenu();
		this.setBackground(Color.lightGray);
		WindowTools.showLogo(this);
		this.setTitle("Traitement d'une video");
		this.setSize(1000, 625);
		this.setLocationRelativeTo(null);
		ProcessingPan p = new ProcessingPan();
		this.setResizable(false);
		this.add(p);
		
		WindowTools.executeWindow(this);
	}

	
	private void createJMenu() {
		StylizedJMenuBar jm = new StylizedJMenuBar();
		JMenu fileMenu = new JMenu("Fichier");
		jm.add(fileMenu);
		JMenu libraryMenu = new JMenu("Bibliotheque");
		jm.add(libraryMenu);
		JMenu processingMenu = new JMenu("Traitement");
		jm.add(processingMenu);
		JMenu optionsMenu = new JMenu("Options");
		jm.add(optionsMenu);
		/*
		JMenu videoMenu = new JMenu("Video");
		jm.add(videoMenu);*/
		
		
		
		StylizedJMenuItem process = new StylizedJMenuItem("Demarrer le traitement");
		process.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.setMessage("Veuillez patienter ...");
					model.setDestinationFolder(JFileChooserManager.chooseDirectory());
					model.getCurrentFile().setDestinationPath(model.getDestinationFolder());
					model.getCurrentFile().setDestinationName("Traitement"+System.currentTimeMillis());
					model.getCurrentFile().setFileExtension(".mp4");
					model.save();
					model.setMessage("Traitement terminé !");
					System.out.println("fait");
				} catch (UnfindableResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		StylizedJMenuItem quit = new StylizedJMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Actions.quit();
			}
		});
		
		
		StylizedJMenuItem importVideo = new StylizedJMenuItem("Importer une video");
		importVideo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					File f = JFileChooserManager.chooseFile();
					model.setCurrentFile(new ProcessingFile(f));
				} catch (IncorrectFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnfindableResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		StylizedJMenuItem importImage = new StylizedJMenuItem("Importer une image");
		importImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					model.add(JFileChooserManager.chooseFile());
				} catch (IncorrectFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnfindableResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		StylizedJMenuItem concat = new StylizedJMenuItem("Concatener des videos  / sons");
		concat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				new ConcatWindow(model);
				Alert.longAlert(Alert.INFO, "Ceci est la fenetre pour concatener des videos.");
			}
		});
		StylizedJMenuItem removeSound = new StylizedJMenuItem("Retirer la bande son de la video");
		removeSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				model.modify(ProcessingType.REMOVE_SOUND, "");
				Alert.shortAlert(Alert.INFO, "Suppression de la bande son de la video<br>prise en compte.");
			}
		});
		StylizedJMenuItem addSound = new StylizedJMenuItem("Ajouter une bande son a la video");
		addSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
	
			}
		});
		
		
		
		StylizedJMenuItem answers = new StylizedJMenuItem("Inspecter les reponses de ffmpeg");
		answers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {		
				Actions.inspect();
;			}
		});
		StylizedJMenuItem procToConv = new StylizedJMenuItem("Passer en mode conversion");
		procToConv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Actions.switchMode();
			}
		});
		

		fileMenu.add(process);
		fileMenu.add(quit);	
	
		libraryMenu.add(importVideo);
		libraryMenu.add(importImage);
		
		optionsMenu.add(answers);
		optionsMenu.add(procToConv);
		
		processingMenu.add(concat);
		processingMenu.add(addSound);
		processingMenu.add(removeSound);

		this.setJMenuBar(jm);
	}
}
