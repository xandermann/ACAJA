package gui_conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import files.SettingsFile;

public class ConversionPanel extends JFrame{
	 //private JFrame window;
	 private ConversionModel model;
	 
	 public ConversionPanel(ConversionModel m) {
		this.model = m;
	 }
	 
	 
	 
	 private JMenu drawFileMenu() {
		JMenu itemsFiles = new JMenu("Fichier");
		
		JMenuItem importFile = new JMenuItem("Importer un fichier");
		JMenuItem importFolder = new JMenuItem("Importer un dossier");
		JMenuItem clearLibrary = new JMenuItem("Vider la bibliothèque");
		JMenuItem quit = new JMenuItem("Quitter");
		
		itemsFiles.add(importFile);
		itemsFiles.add(importFolder);
		itemsFiles.add(clearLibrary);
		itemsFiles.add(quit);
	
		return itemsFiles;	 
	 }
	 
	 private JMenu drawProfilesMenu() {
			JMenu menuProfiles = new JMenu("Profils");
			
			JMenuItem newProfile = new JMenuItem("Creer un profil");
			JMenuItem loadProfile = new JMenuItem("Charger un profil");
			JMenuItem deleteProfile = new JMenuItem("Supprimer un profil");
			
			menuProfiles.add(newProfile);
			menuProfiles.add(loadProfile);
			menuProfiles.add(deleteProfile);
		
		
			return menuProfiles;	  
		 }
		 
	 private JMenu drawOptionsMenu() {
			JMenu menuOptions = new JMenu("Options");
			
			JMenuItem outputFolder = new JMenuItem("Changer le repertoire de sortie");
			JMenuItem switchMode = new JMenuItem("Passer en mode traitement");
			
			menuOptions.add(outputFolder);
			menuOptions.add(switchMode);

			return menuOptions;	 
		 }
		 
	 private JMenu drawConvertMenu() {
			JMenu convert = new JMenu("Convertir");
			return convert;	 
	 }
		 
	 
	 
	 public void generateConversionWindow() {
		 this.setVisible(true);
		 this.setTitle("Acaja Conversion");
		 this.setSize(new Dimension(1000,600));
		 try {
			 this.setIconImage(ImageIO.read(new File("img/LogoAcaja.png")));
		 } catch (IOException e1) {
			 e1.printStackTrace();
			}
		 this.setLocation(100, 100);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 SummaryView sv = new SummaryView(this.model);
		 DefaultListModel list_content = new DefaultListModel();
		 for(SettingsFile f : this.model.getFiles()) {
				list_content.addElement(new ListEntry(f.getSourceFilename()));
		 }	
		 LibraryView lv = new LibraryView(this.model, list_content);
		 TabsView tv = new TabsView(this.model);
		 JPanel p = new JPanel();
		 p.setLayout(new BorderLayout());
		 JMenuBar menu = new JMenuBar();
		 menu.add(this.drawFileMenu());
		 menu.add(this.drawOptionsMenu());
		 menu.add(this.drawProfilesMenu());
		 menu.add(this.drawConvertMenu());
		 this.setJMenuBar(menu);
		 
		 p.add(sv,BorderLayout.NORTH);
		 p.add(tv,BorderLayout.CENTER);
		 
		 this.setLayout(new BorderLayout());
		 this.add(lv,BorderLayout.WEST);
		 this.add(p,BorderLayout.EAST);
		 
		 
		 this.repaint();
	 }
	 
	 
}
