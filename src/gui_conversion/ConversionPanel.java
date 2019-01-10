package gui_conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import files.SettingsFile;
import main.OpeningWindow;

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
					model.setCurrentFile(f.getName());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				
			
			}
		});
		
		JMenuItem importFolder = new JMenuItem("Importer un dossier");
		importFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArrayList<File> files = DataChoose.DirectoryChoose();
					for(File f : files) {
						model.add(f);
						model.setCurrentFile(f.getName());
					}
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
					//JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				
			
			}
		});
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
	 private JMenu drawConvertButton() {
		 	JMenu convert = new JMenu("Convertir"); 	
		 	JMenuItem convert2 = new JMenuItem("Convertir les fichiers");
		 	convert.add(convert2);
			convert2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//ici ouvrir la fenetre de chargement 
					JFrame chargement = new JFrame("Convertion de votre fichier");
					OpeningWindow.afficherLogo(chargement);
					chargement.setLayout(new FlowLayout());
					chargement.setSize(400, 150);
					chargement.setVisible(true);
					JLabel c = new JLabel("Convertion de votre fichier ...");
					chargement.add(c,BorderLayout.CENTER);
					//recuperation des dimensions de l'ecran
					Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
					//on positionne la fenetre au centre de l'ecran
					chargement.setLocation(((int)ecran.getWidth()-400)/2, ((int)ecran.getHeight()-150)/2);
					
					/*Thread t = new Thread();
					while(model.convert()) {
						c.setText("Convertion de votre fichier .");
						t.sleep(20);
						c.setText("Convertion de votre fichier ..");
						t.sleep(20);
						c.setText("Convertion de votre fichier ...");
						t.sleep(20);
					}*/
					model.convert();
				    chargement.dispose();
				} 		
			});
			return convert;	 
	 }
		 
	 
	 /** Methode pour generer la fenetre de conversion et l'afficher
	  * 
	  */
	 public void generateConversionWindow() {
		 this.setVisible(true);
		 this.setResizable(false);
		 this.setTitle("Acaja Conversion");
		 this.setSize(new Dimension(1000,600));
		 OpeningWindow.afficherLogo(this);
		//recuperation des dimensions de l'ecran
		Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
		//on positionne la fenetre au centre de l'ecran
		this.setLocation(((int)ecran.getWidth()-1000)/2, ((int)ecran.getHeight()-600)/2);
				
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
		 menu.add(this.drawConvertButton());
		 this.setJMenuBar(menu);
		 this.model.addObserver(lv);
		 this.model.addObserver(sv);
		 p.add(sv,BorderLayout.NORTH);
		 p.add(tv,BorderLayout.CENTER);
		 
		 this.setLayout(new BorderLayout());
		 this.add(lv,BorderLayout.WEST);
		 this.add(p,BorderLayout.EAST);
		 
		 
		 this.repaint();
	 }
}
