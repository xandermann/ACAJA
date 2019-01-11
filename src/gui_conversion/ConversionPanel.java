package gui_conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.OpeningWindow;

public class ConversionPanel extends JFrame{
	 private ConversionModel model;
	 
	 
	 
	 
	 /** Constructeur de la fenetre ConversionPanel
	  * 
	  */
	 private ConversionPanel() {
		this.model = new ConversionModel();
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
	 private JMenu drawConvertMenu() {
		 	JMenu convert = new JMenu("Convertir"); 	
		 	JMenuItem convert2 = new JMenuItem("Convertir les fichiers");
		 	convert.add(convert2);
			convert2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//ici ouvrir la fenetre de chargement 

					JFrame chargement = new JFrame("Convertion de votre fichier");
					OpeningWindow.afficherLogo(chargement);

					chargement.setLayout(new BorderLayout());
					chargement.setSize(400, 150);
					JLabel c = new JLabel("Conversion de votre fichier ...");
					chargement.add(c,BorderLayout.CENTER);
					//recuperation des dimensions de l'ecran
					Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
					//on positionne la fenetre au centre de l'ecran
					chargement.setLocation(((int)ecran.getWidth()-400)/2, ((int)ecran.getHeight()-150)/2);
					chargement.setVisible(true);					
					
					model.convert();
					
				    chargement.dispose();
				} 		
			});
			return convert;	 
	 }
		 
	 
	 
	 
	 /** Methode pour generer la fenetre de conversion et l'afficher
	  * 
	  */

	 public static void generateConversionWindow() {
		 ConversionPanel cp = new ConversionPanel();
		 
		 cp.setVisible(true);
		 cp.setResizable(false);
		 cp.setTitle("Acaja Conversion");
		 cp.setSize(new Dimension(1000,600));
		 OpeningWindow.afficherLogo(cp);
		 try {
			 cp.setIconImage(ImageIO.read(new File("img/LogoAcaja.png")));
		 } catch (IOException e1) {
			 e1.printStackTrace();
			}

		//recuperation des dimensions de l'ecran
		Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
		//on positionne la fenetre au centre de l'ecran
		cp.setLocation(((int)ecran.getWidth()-1000)/2, ((int)ecran.getHeight()-600)/2);
				
		cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 SummaryView sv = new SummaryView(cp.model);
		 DefaultListModel list_content = cp.model.getFilenames();
		 LibraryView lv = new LibraryView(cp.model, list_content);
		 TabsView tv = new TabsView(cp.model);
		 
		 JPanel p = new JPanel();
		 p.setLayout(new BorderLayout());
		 JMenuBar menu = new JMenuBar();
		 menu.add(cp.drawFileMenu());
		 menu.add(cp.drawOptionsMenu());
		 menu.add(cp.drawProfilesMenu());
		 menu.add(cp.drawConvertMenu());
		 cp.setJMenuBar(menu);
		 cp.model.addObserver(lv);
		 cp.model.addObserver(sv);
		 p.add(sv,BorderLayout.NORTH);
		 p.add(tv,BorderLayout.CENTER);
		
		 cp.setLayout(new BorderLayout());
		 cp.add(lv,BorderLayout.WEST);
		 cp.add(p,BorderLayout.EAST);
	 }
}
