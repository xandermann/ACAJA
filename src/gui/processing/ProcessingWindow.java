package gui.processing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;

import gui.WindowTools;
import gui.style.StylizedJMenuBar;

public class ProcessingWindow extends JFrame {

	
	private ProcessingWindow() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.createJMenu();
		this.setBackground(Color.lightGray);
		WindowTools.showLogo(this);
		this.setTitle("Traitement d'une vidéo");
		WindowTools.executeWindow(this);

		this.setSize(1000, 625);
		this.setLocationRelativeTo(null);
		ProcessingPan p = new ProcessingPan();

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
		this.setJMenuBar(jm);

	}
	
	
	public static void generateProcessingWindow() {
		new ProcessingWindow();
	}

	
	
}
