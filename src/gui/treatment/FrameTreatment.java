package gui.treatment;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;
import gui.WindowTools;

public class FrameTreatment extends JFrame{
	
	public FrameTreatment() {
		createJMenu();
		this.setVisible(true);
		this.setBackground(Color.lightGray);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//WindowTools.showLogo(this);
		WindowTools.executeWindow(this);
		
		PanelTreatment p = new PanelTreatment();
		
		
		this.setSize(1000, 625);
		this.setResizable(false);
		this.add(p);
	}
	
	private void createJMenu() {
		StylizedJMenuBar jm = new StylizedJMenuBar();
		JMenu fileMenu = new JMenu("Fichier");
		jm.add(fileMenu);
		JMenu libraryMenu = new JMenu("Bibliothèque");
		jm.add(libraryMenu);
		JMenu videoMenu = new JMenu("Vidéo");
		jm.add(videoMenu);
		this.setJMenuBar(jm);
		
	}
	
	public static void main(String[] args) {
		FrameTreatment f = new FrameTreatment();
	}
}
