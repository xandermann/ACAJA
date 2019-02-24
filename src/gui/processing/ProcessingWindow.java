package gui.processing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;

import gui.WindowTools;
import gui.style.StylizedJMenuBar;

public class ProcessingWindow extends JFrame {

	
	private ProcessingWindow() {
		createJMenu();
		setBackground(Color.lightGray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// WindowTools.showLogo(this);
		WindowTools.executeWindow(this);

		ProcessingView p = new ProcessingView();

		setSize(1000, 625);
		// this.setResizable(false);
		add(p);
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

	
	public static void main(String[] args) {
		ProcessingWindow f = new ProcessingWindow();
		f.repaint();
	}
}
