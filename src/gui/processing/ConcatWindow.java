package gui.processing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.OperationType;
import files.enumerations.ProcessingType;
import files.files.ProcessingFile;
import gui.JFileChooserManager;
import gui.WindowTools;
import gui.alerts.AlertWindow;
import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;
import threads.RuntimeSpaceManager;
import threads.ThreadForSave;
import threads.ThreadForWaitWindow;

public class ConcatWindow extends JFrame {
	
	private static ArrayList<ProcessingFile> listOfFile;
	private ProcessingModel model;

	public ConcatWindow(ProcessingModel m) {
		model = m;
		listOfFile = new ArrayList<ProcessingFile>();
		WindowTools.executeWindow(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.lightGray);
		WindowTools.showLogo(this);
		this.setTitle("Assembleur de vidéo");
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		this.setLayout(new GridLayout(2,1));
		
		ConcatPanel cp = new ConcatPanel();
		this.add(cp);
		this.add(createJPanel());
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
		
		StylizedJMenuItem importFolder = new StylizedJMenuItem("Importer un dossier");
		fileMenu.add(importFolder);
		importFolder.setToolTipText("Ici vous pouvez ajouter plusieurs fichiers dans la biblioth�que.");
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
		JButton valider = new JButton("Assembler les vidéos");
		valider.setSize(100, 50);
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!listOfFile.isEmpty()) {
					//model.getCurrentFile().cancelAll();
					ProcessingFile f1 = listOfFile.get(0);
					model.setCurrentFile(f1);
					String s = "";
					for(ProcessingFile f : listOfFile) {
						s = s +" "+f.getSourceFileFullName();
					}
					model.getCurrentFile().modify(ProcessingType.ADDED, s);
					
					model.setDestinationFolder(JFileChooserManager.chooseDirectory());
					model.getCurrentFile().setDestinationPath(model.getDestinationFolder());
					model.getCurrentFile().setDestinationName("Concat"+System.currentTimeMillis());
					model.getCurrentFile().setFileExtension(".mp4");
					try {
						model.save();
					} catch (UnfindableResourceException e1) {
						e1.printStackTrace();
					}
					System.out.println("fait");
					
				}
			}
				
			
		});
		j.add(valider);
		return j;
	}

}
