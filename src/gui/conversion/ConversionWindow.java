package gui.conversion;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import exceptions.*;
import files.FileInformation;
import files.enumerations.SettingType;
import files.files.SettingsFile;
import gui.JFileChooserManager;
import gui.WindowTools;
import gui.alerts.Alert;
import gui.alerts.ASWindow;
import gui.answers.AnswersWindow;
import gui.conversion.views.*;
import gui.processing.ProcessingWindow;
import gui.style.*;
import resources.ResourcesManager;
import threads.*;
import wrapper.language.ValueConstants;

//TODO : CODE OPTIMISATION

public final class ConversionWindow extends StylizedJFrame {
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ ATTRRIBUTS D'INSTANCE. ]
	 */
	private ConversionModel model;
	private JLabel empty_workspace;
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ CONSTRUCTEUR INTERNE - ENCAPSULE. ]
	 */
	private ConversionWindow() {
		super();
		model = new ConversionModel();
		try {
			model.loadOldImports();
		} catch (UnfindableResourceException ure) {
			JOptionPane.showMessageDialog(null, ure.getMessage());
		}
		empty_workspace = new JLabel(
				"<html>" + 
					"<head>" +
						"<style>" +
							"#content{" +
								"text-align: center; " +
							"}" +
							"#0 {" +
								"font-size: 10px;" +
							"}" +
							"#1 {" +
								"color: #0000FF;" +
								"font-size: 20px;" +
							"}" +
						"</style>" +
					"</head>" +
					"<body>" +
						"<div id=content>" +
							"<p id=0>" + 
								"Pour commencer,"+
								"<br>"+
								"ajoutez un fichier audio ou video via le menu." +
							"</p>" +
							"<p id=1>" + 
								"<br>"+
								"CTRL + A." +
							"</p>" + 
						"</div>"+
					"</body>" + 
				"</html>", JLabel.CENTER);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	private void redrawFirstTime() {
		StylizedJPanel dataView = new StylizedJPanel();
		dataView.requestFocus();
		dataView.setLayout(new BoxLayout(dataView, BoxLayout.Y_AXIS));
		
		SummaryView sv = new SummaryView(model);
		TabsView tv = new TabsView(model);
		LibraryView lv = new LibraryView(model);
		
		dataView.add(sv);
		dataView.add(tv);
		
		sv.setBackground(Color.LIGHT_GRAY);
		dataView.setBackground(Color.LIGHT_GRAY);
		add(lv, BorderLayout.WEST);
		add(dataView, BorderLayout.EAST);
		
		model.addObserver(sv);
		model.addObserver(lv);
		empty_workspace.setVisible(false);
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE INTERNE POUR LA CONSTRUCTION DU MENU DES FICHIERS. ]
	 * 
	 * @return JMenu Le menu des fichiers.
	 */
	private JMenu drawFileMenu() {
		JMenu filesMenu = new JMenu("Fichier");

		
		StylizedJMenuItem importFile = new StylizedJMenuItem("Importer un fichier");
		importFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
					try {
						File f = JFileChooserManager.chooseFile();
						model.add(f);
						if (model.getCurrentFile() == null) redrawFirstTime();
						model.setCurrentFile(model.getFiles().get(model.getFiles().size()-1));
						Alert.shortAlert(Alert.SUCCESS, "Import realise avec succes.");
					} catch (Exception e) {
						Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
					}
			}
		});
		

		StylizedJMenuItem importFolder = new StylizedJMenuItem("Importer un dossier");
		importFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					ArrayList<File> files = JFileChooserManager.chooseDirectoryAndListSonFiles();
					for (File f : files) {
						model.add(f);
						if(model.getCurrentFile() == null) redrawFirstTime();
						model.setCurrentFile(model.getFiles().get(model.getFiles().size()-1));
					}
					Alert.shortAlert(Alert.SUCCESS, "Import realise avec succes.");
				} catch (Exception e) {
					Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
				}
			}
		});
		
		
		JMenu recentFiles = new JMenu("Fichiers recemments importes");
		FileInformation[] files = model.getOldImports();
		for(FileInformation f : files) {
			if(f != null) {
				StylizedJMenuItem itemFile = new StylizedJMenuItem(f.getFileName());
				recentFiles.add(itemFile);
				itemFile.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						try {
							File file = new File(f.getPath());
							model.add(file);
							if (model.getCurrentFile() == null) redrawFirstTime();
							model.setCurrentFile(model.getFiles().get(model.getFiles().size()-1));
							Alert.shortAlert(Alert.SUCCESS, "Import realise avec succes.");
						} catch(Exception e) {
							Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
						}
					}
				});
			}	
	    }
		
			
		StylizedJMenuItem clearLibrary = new StylizedJMenuItem("Vider la bibliotheque");
		clearLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				model.clear();
			}
		});

		
		StylizedJMenuItem quit = new StylizedJMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ResourcesManager.clearResources();
				System.exit(0);		
			}
		});

		
		filesMenu.add(importFile);
		filesMenu.add(importFolder);
		filesMenu.add(recentFiles);
		filesMenu.add(clearLibrary);
		filesMenu.add(quit);

		return filesMenu;
	}


	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ METHODE INTERNE POUR LA CONSTRUCTION DU MENU DES OPTIONS. ]
	 * 
	 * @return JMenu Le menu des options.
	 */
	private JMenu drawOptionsMenu() {
		JMenu optionsMenu = new JMenu("Options");

		StylizedJMenuItem exportFolder = new StylizedJMenuItem("Choisir le repertoire de sortie");
		exportFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					model.setDestinationFolder(JFileChooserManager.chooseDirectory());
					Alert.shortAlert(Alert.SUCCESS, "Chemin de destination des fichiers <br>enregistre.");
				} catch (IllegalArgumentException iae) {}
			}
		});
		
		
		StylizedJMenuItem switchMode = new StylizedJMenuItem("Passer en mode traitement");
		switchMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {			
				ProcessingWindow.generateProcessingWindow();
				Alert.shortAlert(Alert.SUCCESS, "Changement de mode realise <br>avec succes.");
				dispose();
			}
		});

		StylizedJMenuItem answsers = new StylizedJMenuItem("Etudier les reponses de ffmpeg");
		answsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {		
				new AnswersWindow();
				Alert.longAlert(Alert.INFO, "Ceci est l'historique des reponses <br>de FFmpeg.");
			}
		});
		
		
		StylizedJMenuItem settings = new StylizedJMenuItem("Gerer les parametres des notifications");
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {		
				new ASWindow();
				Alert.longAlert(Alert.INFO, "Ceci est la fenetre de <br>gestion des parametres.");
			}
		});
		
		optionsMenu.add(exportFolder);
		optionsMenu.add(switchMode);
		optionsMenu.add(answsers);
		optionsMenu.add(settings);

		return optionsMenu;
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE POUR LA COSNTRUCTION DE LA FENETRE DE FINALISATION. ]
	 */
	private void drawConvertWindow() {
		StylizedJFrame outputWindow = new StylizedJFrame("Demarrer la conversion");
		outputWindow.setResizable(false);
		outputWindow.setSize(new Dimension(400, 280));
		outputWindow.setLocationRelativeTo(null);
		outputWindow.setBackground(StyleTheme.BACKGROUND_COLOR);
		
		JPanel window = new JPanel(new BorderLayout());	
		
		JPanel outputPanel = new JPanel(new BorderLayout());
		
		JPanel title = new JPanel(new BorderLayout());
		JPanel output = new JPanel(new BorderLayout());
		output.setPreferredSize(new Dimension(100,60));
		JPanel browse = new JPanel();
		browse.setPreferredSize(new Dimension(100,60));
		
		title.add(new JLabel("<html><br> REPERTOIRE DE SORTIE ET QUALITE ? </html>",JLabel.CENTER),BorderLayout.CENTER);
		
		JLabel outputFolder = model.getDestinationFolder() != null ?
		new JLabel(model.getDestinationFolder().getAbsolutePath(), JLabel.CENTER)
		: new JLabel("Auncun reperoire de sortie selectionne.", JLabel.CENTER);
		
		JPanel folder  = new JPanel(new BorderLayout());
		output.add(new JLabel("<html><br>REPERTOIRE DE SORTIE : </html>", JLabel.CENTER),BorderLayout.NORTH);
		output.add(outputFolder,BorderLayout.CENTER);
		
		
		StylizedJButton outputFolderButton = new StylizedJButton("Parcourir");
		browse.add(outputFolderButton,BorderLayout.CENTER);
		outputFolderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					model.setDestinationFolder(JFileChooserManager.chooseDirectory());
					outputFolder.setText(model.getDestinationFolder().getAbsolutePath());
				} catch (IllegalArgumentException iae) {}
			}
		});
		
		outputPanel.add(title,BorderLayout.NORTH);
		outputPanel.add(output,BorderLayout.CENTER);
		outputPanel.add(browse,BorderLayout.SOUTH);
		
		window.add(outputPanel,BorderLayout.NORTH);
		
		
		JLabel quality = new JLabel("<html>QUALITE : </html>", JLabel.CENTER);
		ButtonGroup qualityChoice = new ButtonGroup();
		JRadioButton qualityBad = new JRadioButton("basse");
		JRadioButton qualityMedium = new JRadioButton("moyenne");
		JRadioButton qualityHigh = new JRadioButton("optimale");
		qualityChoice.add(qualityBad);
		qualityChoice.add(qualityHigh);
		qualityChoice.add(qualityMedium);
		qualityBad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(SettingsFile f : model.getFiles()) f.modify(SettingType.QUALITY, ValueConstants.WORSE_QUALITY);
			}
		});
		qualityMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(SettingsFile f : model.getFiles()) f.modify(SettingType.QUALITY, ValueConstants.AVERAGE_QUALITY);
			}
		});
		qualityHigh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(SettingsFile f : model.getFiles()) f.modify(SettingType.QUALITY, ValueConstants.BEST_QUALITY);
			}
		});
		qualityMedium.setSelected(true);
		for(SettingsFile f : model.getFiles()) f.modify(SettingType.QUALITY, ValueConstants.AVERAGE_QUALITY);
		

		
		StylizedJButton buttonConvert = new StylizedJButton("Convertir");
		buttonConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(model.getDestinationFolder() != null) {
					outputWindow.dispose();
					try {
						model.save();
					} catch (UnfindableResourceException ure) {
						Alert.longAlert(Alert.FAILURE, ure.getMessage());
					}
				}
			}
		});

		JPanel qualityPanel = new JPanel(new BorderLayout());
		qualityPanel.setSize(new Dimension(400,40));
		qualityPanel.add(quality, BorderLayout.NORTH);
		JPanel qualityTypesPanel = new JPanel();
		qualityTypesPanel.add(qualityBad);
		qualityTypesPanel.add(qualityMedium);
		qualityTypesPanel.add(qualityHigh);
		qualityPanel.add(qualityTypesPanel, BorderLayout.CENTER);
		JPanel convert = new JPanel();
		convert.add(buttonConvert);
		convert.setPreferredSize(new Dimension(100,60));
		window.add(qualityPanel,BorderLayout.CENTER);
		window.add(convert,BorderLayout.SOUTH);
		outputWindow.add(window);
		WindowTools.executeWindow(outputWindow);
	}
	
	
	
	
	/**
	 * [ METHODE INTERNE POUR LA CONSTRUCTION DU MENU DE CONVERSION. ]
	 * 
	 * @return JMenu Le menu de la conversion.
	 */
	private JMenu drawConvertMenu() {
		JMenu convert = new JMenu("Convertir");
		StylizedJMenuItem convertItem = new StylizedJMenuItem("Convertir les fichiers");
		convert.add(convertItem);
		convertItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(RuntimeSpaceManager.manage() && model.isModified()) {
					drawConvertWindow();
					Alert.longAlert(
							Alert.INFO, 
							"Ceci est la fenetre de choix des parametres<br>d'export des fichiers à convertir.");
				}else
					Alert.longAlert(
							Alert.FAILURE, 
							"Aucun fichier modifie a convertir trouves<br>OU autre conversion deja en cours !");
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
		
		conversionWindow.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				ResourcesManager.clearResources();
			}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
		conversionWindow.setResizable(false);
		conversionWindow.setTitle("Acaja - Mode Conversion");
		conversionWindow.setSize(new Dimension(600, 600));
		conversionWindow.setLocationRelativeTo(null);
		conversionWindow.setDefaultCloseOperation(StylizedJFrame.EXIT_ON_CLOSE);

		WindowTools.showLogo(conversionWindow);

		
		StylizedJPanel p = new StylizedJPanel();
		p.setLayout(new BorderLayout());
		StylizedJMenuBar menu = new StylizedJMenuBar();
		
		
		menu.add(conversionWindow.drawFileMenu());
		menu.add(conversionWindow.drawOptionsMenu());
		menu.add(conversionWindow.drawConvertMenu());

		
		conversionWindow.setJMenuBar(menu);
		
		p.add(conversionWindow.empty_workspace);
		conversionWindow.setLayout(new BorderLayout());
		conversionWindow.add(p, BorderLayout.CENTER);
		conversionWindow.addKeyListener(new ConversionKeyboardController(conversionWindow.model));
		
		WindowTools.executeWindow(conversionWindow);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
}