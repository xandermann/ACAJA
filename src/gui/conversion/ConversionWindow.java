package gui.conversion;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import exceptions.*;
import gui.OpeningWindow;
import gui.ThreadForSave;
import gui.ThreadForWaitWindow;
import tools.WindowTools;
import wrapper.UserRequests;

public final class ConversionWindow extends JFrame{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ ATTRRIBUTS D'INSTANCE. ]
	 */
	 private ConversionModel model;
	 private JLabel start;
	 
	 
	 
	 //=======================================================================================================================
	 //=======================================================================================================================	 
	 
	 
	 
	 /**
	  * [ CONSTRUCTEUR INTERNE. ]
	  */
	 private ConversionWindow() {
		 model = new ConversionModel();
		 start = new JLabel("Pour commencer, ajoutez un fichier audio ou vidéo via le menu");
		 start.setHorizontalAlignment(JLabel.CENTER);
	 }
	 
	 
	 
	 //=======================================================================================================================
	 //=======================================================================================================================	 
	 
	 
	 
	 /** 
	  *  [ METHODE INTERNE POUR LA CONSTRUCTION DU MENU DES FICHIERS. ]
	  * 
	  * @return JMenu 		Le menu des fichiers. 
	  */
	 private JMenu drawFileMenu() {
		JMenu filesMenu = new JMenu("Fichier");
		
		JMenuItem importFile = new JMenuItem("Importer un fichier");
		importFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
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
			public void actionPerformed(ActionEvent ae) {
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
			public void actionPerformed(ActionEvent ae) {
				model.clear();
			}
		});
		
		
		JMenuItem quit = new JMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				dispose();
			} 		
		});
		
		
		filesMenu.add(importFile);
		filesMenu.add(importFolder);
		filesMenu.add(clearLibrary);
		filesMenu.add(quit);
	
		return filesMenu;	 
	 }
	 
	 
	 
	 //=======================================================================================================================
	 //=======================================================================================================================	 
	 
	 
	 
	 /** 
	  * [ METHODE INTERNE POUR LA CONSTRUCTION DU MENU DES PROFILS. ]
	  * 
	  * @return JMenu		Le menu des profils. 
	  */
	 private JMenu drawProfilesMenu() {
			JMenu profilesMenu = new JMenu("Profils");
			
			JMenuItem newProfile = new JMenuItem("Creer un profil");
			JMenuItem loadProfile = new JMenuItem("Charger un profil");
			JMenuItem deleteProfile = new JMenuItem("Supprimer un profil");
			
			profilesMenu.add(newProfile);
			profilesMenu.add(loadProfile);
			profilesMenu.add(deleteProfile);
				
			return profilesMenu;	  
	 }
	 
	 
	 
	 //=======================================================================================================================
	 //=======================================================================================================================	 
	 
	 
	 
	 /** 
	  * [ METHODE INTERNE POUR LA CONSTRUCTION DU MENU DES OPTIONS. ]
	  * 	 
	  * @return JMenu 		Le menu des options. 
	  */
	 private JMenu drawOptionsMenu() {
			JMenu optionsMenu = new JMenu("Options");
			
			JMenuItem outputFolder = new JMenuItem("Changer le repertoire de sortie");
			JMenuItem switchMode = new JMenuItem("Passer en mode traitement");
			
			optionsMenu.add(outputFolder);
			optionsMenu.add(switchMode);

			return optionsMenu;	 
	}
	 
	
	 
	 //=======================================================================================================================
	 //=======================================================================================================================	 
	 
	 
	 
	 /** 
	  * [ METHODE INTERNE POUR LA CONSTRUCTION DU MENU DE CONVERTION. ]
	  * 
	  * @return JMenu 		Le menu de la conversion.
	  */
	 private JMenu drawConvertMenu() {
		 	JMenu convert = new JMenu("Convertir"); 	
		 	JMenuItem convertItem = new JMenuItem("Convertir les fichiers");
		 	convert.add(convertItem);
		 	
		 	convertItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame waitWindow = new JFrame("Conversion de votre fichier");

					waitWindow.setLayout(new BorderLayout());
					waitWindow.setSize(400, 150);
					waitWindow.setLocationRelativeTo(null);
					waitWindow.add(new JLabel("<html> "
											+ "<body> "
											+ "		Conversion du ou des fichier(s)."
											+ "		<br>   "
											+ "		Veuillez patientez... "
											+ "</body> "
											+ "</html>", JLabel.CENTER), BorderLayout.CENTER);
					
					WindowTools.showLogo(waitWindow);			
					
					/**
					 * DEBUT DE LA CONVERSION.  
					 */
					model.startSave();
					
					/**
					 * LANCEMENT DE LA CONVERSION DANS 
					 * UN AUTRE PROCESSUS. 
					 */
					ThreadForSave.saveInNewThread(model);
					
					/**
					 * LANCEMENT ET GESTION DE LA FENETRE D'ATTENTE 
					 * DANS UN AUTRE PROCESSUS. 
					 */
					ThreadForWaitWindow.waitInNewThread(waitWindow);
				} 		
			});
			return convert;	 
	 }
		 
	 
	 
	 //=======================================================================================================================
	 //=======================================================================================================================	 
	 
	 
	 
	 /**
	  * [ METHODE DE CLASSE DE CONSTRUCTION ET DE GENERATION DE LA FENETRE DE CONVERSION. ]
	  */
	 public static void generateConversionWindow() {
		 ConversionWindow conversionWindow = new ConversionWindow();
		 
		 conversionWindow.setResizable(false);
		 conversionWindow.setTitle("Acaja Conversion");
		 conversionWindow.setSize(new Dimension(1000,600));
		 conversionWindow.setLocationRelativeTo(null);
		 conversionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 conversionWindow.addWindowListener(new ConversionWindowController(conversionWindow.model));
		 
		 WindowTools.showLogo(conversionWindow);
		
		 SummaryView sv = new SummaryView(conversionWindow.model);
		 LibraryViewController lv = new LibraryViewController(conversionWindow.model, conversionWindow.model.getFilenames());
		 TabsView tv = new TabsView(conversionWindow.model);
		 
		 JPanel p = new JPanel();
		 p.setLayout(new BorderLayout());
		 JMenuBar menu = new JMenuBar();
		 menu.add(conversionWindow.drawFileMenu());
		 menu.add(conversionWindow.drawOptionsMenu());
		 menu.add(conversionWindow.drawProfilesMenu());
		 menu.add(conversionWindow.drawConvertMenu());
		 
		 conversionWindow.setJMenuBar(menu);
		 conversionWindow.model.addObserver(lv);
		 conversionWindow.model.addObserver(sv);
		 p.add(sv,BorderLayout.NORTH);
		 p.add(tv,BorderLayout.CENTER); 
		 p.add(conversionWindow.start);
		 conversionWindow.setLayout(new BorderLayout());
		 conversionWindow.add(lv,BorderLayout.WEST);
		 conversionWindow.add(p,BorderLayout.CENTER);
		 
		 WindowTools.executeWindow(conversionWindow);
	 }
	 
	 
	 
	 //=======================================================================================================================
	 //=======================================================================================================================	 
}