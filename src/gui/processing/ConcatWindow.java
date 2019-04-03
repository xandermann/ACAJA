package gui.processing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.ProcessingType;
import files.files.ProcessingFile;
import gui.JFileChooserManager;
import gui.WindowTools;
import gui.general.Context;
import gui.general.Focus;
import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;

public class ConcatWindow extends JFrame {
		
	private static final long serialVersionUID = -125816618351479903L;
	private static ArrayList<ProcessingFile> listOfFile;

	public ConcatWindow() {
		
		Context.$F.add(this);
		/**
		 * RENDRE LE FOCUS A L'EVENEMENT PARENT.
		 */
		addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				Context.$F.remove(this);
				new Focus();
			}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
		
		
		listOfFile = new ArrayList<ProcessingFile>();
		WindowTools.executeWindow(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.lightGray);
		WindowTools.showLogo(this);
		this.setTitle("Assembleur de video");
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		this.setLayout(new GridLayout(2,1));
		
		ConcatPanel cp = new ConcatPanel();
		this.add(cp);
		this.add(createJPanel());
		if(((ProcessingModel)Context.$M).getCurrentFile() != null)
			listOfFile.add(((ProcessingModel)Context.$M).getCurrentFile());
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
		
		StylizedJMenuItem importFolder = new StylizedJMenuItem("Importer un dossier");
		fileMenu.add(importFolder);
		importFolder.setToolTipText("Ici vous pouvez ajouter plusieurs fichiers dans la bibliotheque.");
		importFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ArrayList<File> f = JFileChooserManager.chooseDirectoryAndListSonFiles();
				for(File i : f) {
					ProcessingFile p = null;
					try {
						p = new ProcessingFile(i);
					} catch (IncorrectFileException | UnfindableResourceException e) {e.printStackTrace();}
					listOfFile.add(p);
					repaint();
				}
			}
		});
		
		this.setJMenuBar(jm);
	}

	public static ArrayList<ProcessingFile> getListOfFile() {
		return listOfFile;
	}
	
	public JPanel createJPanel() {
		JPanel j = new JPanel();
		JButton valider = new JButton("Assembler les videos");
		valider.setSize(100, 50);
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!listOfFile.isEmpty()) {
					ProcessingFile f1 = listOfFile.get(0);
					listOfFile.remove(0);
					((ProcessingModel)Context.$M).setCurrentFile(f1);
					String s = "";
					for(ProcessingFile f : listOfFile) {
						s = s +" "+f.getSourceFileFullName();
					}
					((ProcessingModel)Context.$M).getCurrentFile().modify(ProcessingType.ADDED, s);
					
					((ProcessingModel)Context.$M).setDestinationFolder(JFileChooserManager.chooseDirectory());
					((ProcessingModel)Context.$M).getCurrentFile().setDestinationPath(((ProcessingModel)Context.$M).getDestinationFolder());
					((ProcessingModel)Context.$M).getCurrentFile().setDestinationName("Concat"+System.currentTimeMillis());
					((ProcessingModel)Context.$M).getCurrentFile().setFileExtension(((ProcessingModel)Context.$M).getCurrentFile().getSourceFileExtension());
					try {
						((ProcessingModel)Context.$M).save();
						((ProcessingModel)Context.$M).getCurrentFile().cancelAll();
					} catch (UnfindableResourceException e1) {
						e1.printStackTrace();
					}
					
				}
			}
				
			
		});
		j.add(valider);
		return j;
	}

}
