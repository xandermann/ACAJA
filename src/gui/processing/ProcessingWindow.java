package gui.processing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenu;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.MediaFileType;
import files.enumerations.ProcessingType;
import files.files.ProcessingFile;
import gui.JFileChooserManager;
import gui.WindowTools;
import gui.alerts.Alert;
import gui.general.Actions;
import gui.general.Context;
import gui.general.GeneralKeyboardController;
import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;
import resources.ResourcesManager;

public class ProcessingWindow extends JFrame {
	
	private static final long serialVersionUID = -6495416785122055429L;
	private ProcessingModel model;

	
	public ProcessingWindow() {
		Context.$0();
		Context.$W = this;
		this.model = new ProcessingModel();
		
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
		addKeyListener(new GeneralKeyboardController());
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
		process.setToolTipText("Ici vous pouvez demarer le traitement de vos fichiers.");
		process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.save();
				} catch (UnfindableResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		StylizedJMenuItem quit = new StylizedJMenuItem("Quitter");
		quit.setToolTipText("Ici vous pouvez quitter le logiciel (ECHAP / CTRL + Q).");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Actions.quit();
			}
		});
		
		
		StylizedJMenuItem importVideo = new StylizedJMenuItem("Importer une video");
		importVideo.setToolTipText("Ici vous pouvez importer une video.");
		importVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					File f = JFileChooserManager.chooseFile();
					ProcessingFile pf = new ProcessingFile(f);
					if(!pf.getTypeFile().equals(MediaFileType.MEDIA_FILE_VIDEO)) {
						throw new IncorrectFileException("Type de fichier incorrect !");
					}
					model.setCurrentFile(pf);
					
				} catch (IncorrectFileException e) {
					// TODO Auto-generated catch block
					Alert.longAlert(Alert.FAILURE, "Type de fichier incorrect !");
				} catch (UnfindableResourceException e) {
					// TODO Auto-generated catch block
					Alert.longAlert(Alert.FAILURE, "Fichier introuvable !");
				}
			}
		});
		StylizedJMenuItem importImage = new StylizedJMenuItem("Importer une image");
		importImage.setToolTipText("Ici vous pouvez importez une image.");
		importImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
					File img = JFileChooserManager.chooseFile();
					String[] imageExtensions = { "png", "jpg", "jpeg", "bmp" };
					boolean isImage = false;
					for (String imgExt : imageExtensions) {
						if (img.getName().endsWith(imgExt)) {
							isImage = true;
						}
					}
					if(isImage) {
						model.addImage(img);
						LibraryView.setActualiser(true);
						repaint();
					} else {
						Alert.shortAlert(Alert.FAILURE, "Le fichier doit être une image");
					}
					
					
			}
			
		});
		StylizedJMenuItem concat = new StylizedJMenuItem("Concatener des videos  / sons");
		concat.setToolTipText("Ici vous pouvez concatener des videos ou des sons.");
		concat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				new ConcatWindow();
				Alert.longAlert(Alert.INFO, "Ceci est la fenetre pour concatener des videos.");
			}
		});
		StylizedJMenuItem removeSound = new StylizedJMenuItem("Retirer la bande son de la video");
		removeSound.setToolTipText("Ici vous pouvez retirer un son a la video en cours de traitement.");
		removeSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				model.modify(ProcessingType.REMOVED_SOUND, "");
				Alert.shortAlert(Alert.SUCCESS, "Suppression de la bande son de la video<br>prise en compte.");
			}
		});
		StylizedJMenuItem addSound = new StylizedJMenuItem("Ajouter une bande son a la video");
		addSound.setToolTipText("Ici vous pouvez ajouter un son a la video en cours de traitement.");
		addSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				model.modify(ProcessingType.ADDED_SOUND, JFileChooserManager.chooseFile().getAbsolutePath());
				Alert.shortAlert(Alert.SUCCESS, "Ajout de la bande son a la video<br>prise en compte.");
			}
		});
		
		
		
		StylizedJMenuItem answers = new StylizedJMenuItem("Inspecter les reponses de ffmpeg");
		answers.setToolTipText("Ici vous pouvez inspecter les reponses de ffmpeg (CTRL + I).");
		answers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {		
				Actions.inspect();
			}
		});
		StylizedJMenuItem procToConv = new StylizedJMenuItem("Passer en mode conversion");
		procToConv.setToolTipText("Ici vous pouvez choisir de passer en mode conversion (CTRL + C).");
		procToConv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Actions.switchMode();
			}
		});
		StylizedJMenuItem settings = new StylizedJMenuItem("Gerer les parametres des notifications");
		settings.setToolTipText("Ici vous pouvez gerer les parametres des notifications (CTRL + P).");
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {		
				Actions.set();
			}
		});
		

		fileMenu.add(process);
		fileMenu.add(quit);	
	
		libraryMenu.add(importVideo);
		libraryMenu.add(importImage);
		
		processingMenu.add(concat);
		processingMenu.add(addSound);
		processingMenu.add(removeSound);
		
		optionsMenu.add(answers);
		optionsMenu.add(procToConv);
		optionsMenu.add(settings);
		

		this.setJMenuBar(jm);
	}
}
