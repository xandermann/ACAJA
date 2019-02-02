package gui.conversion;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import exceptions.*;
import main_pack.OpeningWindow;
import tools.WindowTools;

public final class ConversionWindow extends JFrame{
	 private ConversionModel model;
	 private JLabel start;
	 
	 
	 
	 /** Constructeur de la fenetre ConversionPanel
	  * 
	  */
	 private ConversionWindow() {
		 model = new ConversionModel();
		 start = new JLabel("Pour commencer, ajoutez un fichier via le menu");
		 start.setHorizontalAlignment(JLabel.CENTER);
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
					if(model.getCurrentFile() == null) {
						JPanel dataView = new JPanel();
						dataView.setLayout(new BoxLayout(dataView, BoxLayout.Y_AXIS));
						SummaryView sv = new SummaryView(model);
						TabsView tv = new TabsView(model);
						dataView.add(sv);
						dataView.add(tv);
						add(dataView,BorderLayout.EAST);
						model.addObserver(sv);
						start.setVisible(false);
					}
					model.setCurrentFile(f.getName());
				} catch (Exception e){
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
					JOptionPane.showMessageDialog(null, e.getMessage());
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
				dispose();
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
		 	JMenuItem convert2 = new JMenuItem("Convertir les fichiers");
		 	convert.add(convert2);
			convert2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame waitWindow = new JFrame("Convertion de votre fichier");

					waitWindow.setLayout(new BorderLayout());
					waitWindow.setSize(400, 150);
					waitWindow.setLocationRelativeTo(null);
					waitWindow.add(new JLabel("Conversion de votre fichier ..."),BorderLayout.CENTER);
					
					WindowTools.showLogo(waitWindow);
					WindowTools.executeWindow(waitWindow);			
					
					model.convert();				
				} 		
			});
			return convert;	 
	 }
		 
	 
	 
	 
	 /** Methode pour generer la fenetre de conversion et l'afficher
	  * 
	  */

	 public static void generateConversionWindow() {
		 ConversionWindow cw = new ConversionWindow();
		 
		 cw.setResizable(false);
		 cw.setTitle("Acaja Conversion");
		 cw.setSize(new Dimension(1000,600));
		 cw.setLocationRelativeTo(null);
		 cw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 cw.addWindowListener(new ConversionWindowController(cw.model));
		 
		 WindowTools.showLogo(cw);
		
		 SummaryView sv = new SummaryView(cw.model);
		 LibraryViewController lv = new LibraryViewController(cw.model, cw.model.getFilenames());
		 TabsView tv = new TabsView(cw.model);
		 
		 JPanel p = new JPanel();
		 p.setLayout(new BorderLayout());
		 JMenuBar menu = new JMenuBar();
		 menu.add(cw.drawFileMenu());
		 menu.add(cw.drawOptionsMenu());
		 menu.add(cw.drawProfilesMenu());
		 menu.add(cw.drawConvertMenu());
		 
		 cw.setJMenuBar(menu);
		 cw.model.addObserver(lv);
		 cw.model.addObserver(sv);
		 p.add(sv,BorderLayout.NORTH);
		 p.add(tv,BorderLayout.CENTER); 
		 p.add(cw.start);
		 cw.setLayout(new BorderLayout());
		 cw.add(lv,BorderLayout.WEST);
		 cw.add(p,BorderLayout.CENTER);
		 
		 WindowTools.executeWindow(cw);
	 }
}