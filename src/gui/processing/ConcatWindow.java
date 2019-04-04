package gui.processing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.ProcessingType;
import files.files.ProcessingFile;
import gui.JFileChooserManager;
import gui.WindowTools;
import gui.alerts.Alert;
import gui.general.Context;
import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;

public class ConcatWindow extends JFrame {
	private static final long serialVersionUID = -125816618351479903L;
	private ArrayList<ProcessingFile> listOfFile;
	private JPanel view;
	
	
	
	
	public ConcatWindow() {
		listOfFile = new ArrayList<ProcessingFile>();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.lightGray);
		this.setTitle("Assembleur de video");
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.add(view=(new ConcatPanel(listOfFile)), BorderLayout.CENTER);
		this.add(createJPanel(), BorderLayout.SOUTH);
		drawMenu();
		WindowTools.showLogo(this);
		WindowTools.executeWindow(this);
	}
	
	
	
	
	public void drawMenu() {
		StylizedJMenuBar jm = new StylizedJMenuBar();
		JMenu fileMenu = new JMenu("Fichier");
		jm.add(fileMenu);
		
		StylizedJMenuItem importfile = new StylizedJMenuItem("Importer une video");
		fileMenu.add(importfile);
		importfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					listOfFile.add(new ProcessingFile(JFileChooserManager.chooseFile()));
					view.repaint();
				} catch (Exception e) {
					Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
				}
			}
		});
		
		StylizedJMenuItem importFolder = new StylizedJMenuItem("Importer un dossier");
		fileMenu.add(importFolder);
		importFolder.setToolTipText("Ici vous pouvez ajouter plusieurs fichiers dans la bibliotheque.");
		importFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ArrayList<File> f = JFileChooserManager.chooseDirectoryAndListSonFiles();
				for(File i : f) {
					try {
						listOfFile.add(new ProcessingFile(i));
						view.repaint();
					} catch (Exception e) {
						Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
					}
				}
			}
		});
		
		this.setJMenuBar(jm);
	}

	
	
	
	
	public JPanel createJPanel() {
		JPanel j = new JPanel();
		JButton valider = new JButton("Assembler les videos");
		valider.setSize(100, 50);
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!listOfFile.isEmpty() && listOfFile.size()>=2) {
					ProcessingModel pm = new ProcessingModel();

					pm.setCurrentFile(listOfFile.get(0));
					String s = "";
					for(int i=1; i<listOfFile.size(); i++) 
						s = s + "|" + listOfFile.get(i).getSourceFileFullName();
					
					pm.getCurrentFile().modify(ProcessingType.ADDED, s);
					pm.getCurrentFile().setDestinationName("video_concatenee_"+System.currentTimeMillis());
	
					try {
						((ProcessingModel)Context.$M).save();
					} catch (UnfindableResourceException e1) {
						Alert.shortAlert(Alert.FAILURE, "La concatenation a echouee !");
					}
					
				}else
					Alert.shortAlert(Alert.INFO, "Le nombre de videos est insuffisant.");
			}			
		});
		j.add(valider);
		return j;
	}
}
