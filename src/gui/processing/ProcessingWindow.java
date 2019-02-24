package gui.processing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;

import gui.WindowTools;
import gui.style.StylizedJMenuBar;

public class ProcessingWindow extends JFrame {

	public ProcessingWindow() {
		createJMenu();
		this.setVisible(true);
		this.setBackground(Color.lightGray);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// WindowTools.showLogo(this);
		WindowTools.executeWindow(this);

		ProcessingView p = new ProcessingView();

		this.setSize(1000, 625);
		// this.setResizable(false);
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

	public static void main(String[] args) {
		ProcessingWindow f = new ProcessingWindow();
		f.repaint();
	}
}
