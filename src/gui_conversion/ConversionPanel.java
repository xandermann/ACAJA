package gui_conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import files.SettingsFile;

public class ConversionPanel extends JFrame{
	
	
	 private ConversionModel model;
	 
	 /** Constructeur de la fenetre ConversionPanel
	  * 
	  * @param m Modele associe a la fenetre
	  */
	 public ConversionPanel(ConversionModel m) {
		this.model = m;
	 }
	 
	 
	 /** Methode privee qui cree le contenu du menu fichier
	  * 
	  * @return JMenu menu des fichiers et ses items
	  */
	 private JMenu drawFileMenu() {
		JMenu itemsFiles = new JMenu("Fichier");
		
		JMenuItem importFile = new JMenuItem("Importer un fichier");
		importFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					File f = DataChoose.FileChoose();
					model.add(f);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				
			
			}
		});
		
		JMenuItem importFolder = new JMenuItem("Importer un dossier");
		JMenuItem clearLibrary = new JMenuItem("Vider la bibliotheque");
		clearLibrary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.clear();
			}
		});
		JMenuItem quit = new JMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			} 
			
		});
		
		itemsFiles.add(importFile);
		itemsFiles.add(importFolder);
		itemsFiles.add(clearLibrary);
		itemsFiles.add(quit);
	
		return itemsFiles;	 
	 }
	 
	 
	 /** Methode privee qui cree le contenu du menu profils
	  * 
	  * @return JMenu menu des profils et ses items
	  */
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
	 
	 
	 /** Methode privee qui cree le contenu du menu options
	  * 	 
	  * @return JMenu menu des options et ses items
	  */
	 private JMenu drawOptionsMenu() {
			JMenu menuOptions = new JMenu("Options");
			
			JMenuItem outputFolder = new JMenuItem("Changer le repertoire de sortie");
			JMenuItem switchMode = new JMenuItem("Passer en mode traitement");
			
			menuOptions.add(outputFolder);
			menuOptions.add(switchMode);

			return menuOptions;	 
		 }
	
	 /** Methode privee qui cree le contenu du menu conversion
	  * 
	  * @return JMenu menu de conversion et ses items
	  */
	 private JMenu drawConvertMenu() {
			JMenu convert = new JMenu("Convertir");
			return convert;	 
	 }
		 
	 
	 /** Methode pour generer la fenetre de conversion et l'afficher
	  * 
	  */
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
		 DefaultListModel list_content = this.model.getFilenames();
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
		 this.model.addObserver(lv);
		 p.add(sv,BorderLayout.NORTH);
		 p.add(tv,BorderLayout.CENTER);
		 
		 this.setLayout(new BorderLayout());
		 this.add(lv,BorderLayout.WEST);
		 this.add(p,BorderLayout.EAST);
		 
		 
		 this.repaint();
	 }
	 
	 
}
