package gui.conversion;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import exceptions.*;
import files.FileInformation;
import files.enumerations.SettingType;
import files.files.*;
import gui.JFileChooserManager;
import gui.WindowTools;
import gui.alerts.Alert;
import gui.alerts.ASWindow;
import gui.answers.AnswersWindow;
import gui.conversion.views.*;
import gui.general.Actions;
import gui.general.Context;
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
	 * [ ATTRIBUTS D'INSTANCE. ]
	 */
	private JLabel empty;
	private LibraryView libraryView;
	private JPanel concernedFileView;
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ CONSTRUCTEUR INTERNE - ENCAPSULE. ]
	 */
	public ConversionWindow() {
		super();
		
		Context.$W = this;
		new ConversionModel();
		
		try {
			((ConversionModel) Context.$M).loadOldImports();
		} catch (UnfindableResourceException ure) {
			Alert.longAlert(Alert.FAILURE, ure.getMessage());
		}
		
		empty = new TwoTextsView("Pour commencer,<br>ajoutez un fichier audio ou video via le menu.", 10, "CTRL + A.", 20);
		
		
		addWindowListener(new WindowListener() {
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
		
		
		setResizable(false);
		setTitle("Acaja - Mode Conversion");
		setSize(new Dimension(600, 600));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(StylizedJFrame.EXIT_ON_CLOSE);

		WindowTools.showLogo(this);
		
		StylizedJPanel p = new StylizedJPanel();
		p.setLayout(new BorderLayout());
		StylizedJMenuBar menu = new StylizedJMenuBar();
		
		menu.add(drawFileMenu());
		menu.add(drawOptionsMenu());
		menu.add(drawConvertMenu());
		
		setJMenuBar(menu);
		
		p.add(empty);
		setLayout(new BorderLayout());
		add(p, BorderLayout.CENTER);
		addKeyListener(new ConversionKeyboardController());
		
		WindowTools.executeWindow(this);
	}

	
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	
	public void redrawEmpty() {
		if(libraryView != null) {
			remove(libraryView);
			remove(concernedFileView);
			
			add(empty);
			
			repaint();
			revalidate();
		}
	}
	
	
	
	public void redrawFirstTime() {
		remove(empty);
		
		concernedFileView = new StylizedJPanel();
		concernedFileView.setLayout(new BoxLayout(concernedFileView, BoxLayout.Y_AXIS));
		
		SummaryView sv = new SummaryView();
		TabsView tv = new TabsView();
		concernedFileView.add(sv);
		concernedFileView.add(tv);
		
		libraryView = new LibraryView();
		
		sv.setBackground(Color.LIGHT_GRAY);
		concernedFileView.setBackground(Color.LIGHT_GRAY);
		add(libraryView, BorderLayout.WEST);
		add(concernedFileView, BorderLayout.EAST);
		
		Context.$M.addObserver(sv);
		Context.$M.addObserver(libraryView);
		
		repaint();
		revalidate();
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
				Actions.input();
			}
		});
		

		StylizedJMenuItem importFolder = new StylizedJMenuItem("Importer un dossier");
		importFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Actions.inputs();
			}
		});
		
		
		JMenu recentFiles = new JMenu("Fichiers recemments importes");
		FileInformation[] files = ((ConversionModel) Context.$M).getOldImports();
		for(FileInformation f : files) {
			if(f != null) {
				StylizedJMenuItem itemFile = new StylizedJMenuItem(f.getFileName());
				recentFiles.add(itemFile);
				itemFile.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						try {
							File file = new File(f.getPath());
							Context.$M.add(file);
							System.out.println(1);
							if (Context.$M.getCurrentFile() == null) redrawFirstTime();
							System.out.println(2);
							Context.$M.setCurrentFile(Context.$M.getFiles().get(Context.$M.getFiles().size()-1));
							System.out.println(3);
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
				Context.$M.clear();
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
					((ConversionModel) Context.$M).setDestinationFolder(JFileChooserManager.chooseDirectory());
					Alert.shortAlert(Alert.SUCCESS, "Chemin de destination des fichiers <br>enregistre.");
				} catch (IllegalArgumentException iae) {}
			}
		});
		
		
		StylizedJMenuItem switchMode = new StylizedJMenuItem("Passer en mode traitement");
		switchMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {			
				new ProcessingWindow();
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
		
		JLabel outputFolder = ((ConversionModel) Context.$M).getDestinationFolder() != null ?
		new JLabel(((ConversionModel) Context.$M).getDestinationFolder().getAbsolutePath(), JLabel.CENTER)
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
					((ConversionModel) Context.$M).setDestinationFolder(JFileChooserManager.chooseDirectory());
					outputFolder.setText(((ConversionModel) Context.$M).getDestinationFolder().getAbsolutePath());
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
				for(SelectableFile f : Context.$M.getFiles()) ((SettingsFile) f).modify(SettingType.QUALITY, ValueConstants.WORSE_QUALITY);
			}
		});
		qualityMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(SelectableFile f : Context.$M.getFiles()) ((SettingsFile) f).modify(SettingType.QUALITY, ValueConstants.AVERAGE_QUALITY);
			}
		});
		qualityHigh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(SelectableFile f : Context.$M.getFiles()) ((SettingsFile) f).modify(SettingType.QUALITY, ValueConstants.BEST_QUALITY);
			}
		});
		qualityMedium.setSelected(true);
		for(SelectableFile f : Context.$M.getFiles()) ((SettingsFile) f).modify(SettingType.QUALITY, ValueConstants.AVERAGE_QUALITY);
		

		
		StylizedJButton buttonConvert = new StylizedJButton("Convertir");
		buttonConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((ConversionModel) Context.$M).getDestinationFolder() != null) {
					outputWindow.dispose();
					try {
						Context.$M.save();
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
				if(RuntimeSpaceManager.manage() && Context.$M.isModified()) {
					drawConvertWindow();
					Alert.longAlert(
							Alert.INFO, 
							"Ceci est la fenetre de choix des parametres<br>d'export des fichiers � convertir.");
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
}