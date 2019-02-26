package gui.conversion;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import exceptions.*;
import files.FileInformation;
import gui.FileChooser;
import gui.WindowTools;
import gui.conversion.model.ConversionModel;
import gui.conversion.views_controllers.*;
import gui.processing.ProcessingWindow;
import gui.style.*;
import threads.*;

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
		model.loadOldImports();
		empty_workspace = new JLabel("Pour commencer, ajoutez un fichier audio ou video via le menu.");
		empty_workspace.setHorizontalAlignment(JLabel.CENTER);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	private void redrawFirstTime() {
		StylizedJPanel dataView = new StylizedJPanel();
		dataView.setLayout(new BoxLayout(dataView, BoxLayout.Y_AXIS));
		SummaryView sv = new SummaryView(model);
		TabsView tv = new TabsView(model);
		dataView.add(sv);
		dataView.add(tv);
		sv.setBackground(Color.LIGHT_GRAY);
		dataView.setBackground(Color.LIGHT_GRAY);
		add(dataView, BorderLayout.EAST);
		model.addObserver(sv);
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
			@Override
			public void actionPerformed(ActionEvent ae) {
					try {
						File f = FileChooser.chooseFile();
						model.add(f);
						if (model.getCurrentFile() == null) redrawFirstTime();
						model.setCurrentFile(f.getName());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
			}
		});
		

		StylizedJMenuItem importFolder = new StylizedJMenuItem("Importer un dossier");
		importFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					ArrayList<File> files = FileChooser.chooseDirectoryAndListSonFiles();
					for (File f : files) {
						model.add(f);
						if(model.getCurrentFile() == null) redrawFirstTime();
						model.setCurrentFile(f.getName());
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
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
							model.setCurrentFile(file.getName());
						} catch(Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
						}
							
					}
				});
			}	
	    }
		
			
		StylizedJMenuItem clearLibrary = new StylizedJMenuItem("Vider la bibliotheque");
		clearLibrary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				model.clear();
			}
		});

		
		StylizedJMenuItem quit = new StylizedJMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				dispose();
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
					model.setDestinationFolder(FileChooser.chooseDirectory());
				} catch (ImportationException ie) {
					JOptionPane.showMessageDialog(null, ie.getMessage());
				}
			}
		});
		
		
		StylizedJMenuItem switchMode = new StylizedJMenuItem("Passer en mode traitement");
		switchMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ProcessingWindow.generateProcessingWindow();
				dispose();
			}
		});

		optionsMenu.add(exportFolder);
		optionsMenu.add(switchMode);

		return optionsMenu;
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	

	/**
	 * [ METHODE INTERNE POUR LA GESTION DE LA CONVERSION. ]
	 */
	private void convert() {
		if(RuntimeSpaceManager.manage()) {
			/**
			 * FENETRE D'ATTENTE. 
			 */
			StylizedJFrame waitWindow = new StylizedJFrame("Conversion de votre fichier");
			waitWindow.setLayout(new BorderLayout());
			waitWindow.setSize(400, 150);
			waitWindow.setResizable(false);
			waitWindow.setLocationRelativeTo(null);
			waitWindow.add(
					new JLabel("<html>" + 
								"<body>" + 
								"		Conversion du ou des fichier(s)." +
								"		<br>" +
								"		Veuillez patientez..." + 
								"</body>" + 
								"</html>", JLabel.CENTER),
					BorderLayout.CENTER);
			WindowTools.showLogo(waitWindow);
			/**
			 * DEBUT DE LA CONVERSION.
			 */
			model.startSave();
			/**
			 * LANCEMENT DE LA CONVERSION DANS UN AUTRE PROCESSUS.
			 */
			ThreadForSave.saveInNewThread(model);
			/**
			 * LANCEMENT ET GESTION DE LA FENETRE D'ATTENTE DANS UN AUTRE PROCESSUS.
			 */
			ThreadForWaitWindow.waitInNewThread(waitWindow);
		}
	}
	
	
	
	/**
	 * [ METHODE INTERNE POUR LA COSNTRUCTION DE LA FENETRE DE FINALISATION. ]
	 */
	private void generateConvertWindow() {
		StylizedJFrame outputWindow = new StylizedJFrame("Demarrer la conversion");
		outputWindow.setResizable(false);
		outputWindow.setSize(new Dimension(400, 200));
		outputWindow.setLocationRelativeTo(null);
		outputWindow.setBackground(StyleTheme.BACKGROUND_COLOR);
		JPanel window = new JPanel(new BorderLayout());
		
		JPanel title = new JPanel(new BorderLayout());
		
		JPanel outputPanel = new JPanel(new BorderLayout());
		JLabel info = new JLabel("<html><br>Choisissez le repertoire et la qualite en sortie : </html>",JLabel.CENTER);
		JLabel outputFolderLabel = new JLabel("<html><br>Repertoire de sortie : </html>", JLabel.CENTER);
		title.add(info,BorderLayout.CENTER);
		StylizedJButton outputFolderButton = new StylizedJButton("Parcourir");
		outputFolderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					model.setDestinationFolder(FileChooser.chooseDirectory());
				} catch (ImportationException ie) {
					JOptionPane.showMessageDialog(null, ie.getMessage());
				}
			}
		});
		
		JPanel browse = new JPanel();
		browse.add(outputFolderButton,BorderLayout.CENTER);
		browse.setPreferredSize(new Dimension(100,40));
		outputPanel.add(title,BorderLayout.NORTH);
		outputPanel.add(outputFolderLabel,BorderLayout.CENTER);
		outputPanel.add(browse,BorderLayout.SOUTH);
		window.add(outputPanel,BorderLayout.NORTH);
		
		
		JLabel quality = new JLabel("<html>Qualite : </html>");
		ButtonGroup qualityChoice = new ButtonGroup();
		
		JRadioButton qualityMedium = new JRadioButton("moyenne");
		JRadioButton qualityHigh = new JRadioButton("optimale");
		
		
		qualityChoice.add(qualityHigh);
		qualityChoice.add(qualityMedium);
		StylizedJButton buttonConvert = new StylizedJButton("Convertir");
		buttonConvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputWindow.dispose();
				convert();
			}
		});

		JPanel qualityPanel = new JPanel();
		qualityPanel.setSize(new Dimension(400,70));
		qualityPanel.add(quality);
		qualityPanel.add(qualityHigh);
		qualityPanel.add(qualityMedium);
		JPanel convert = new JPanel();
		convert.add(buttonConvert);
		convert.setPreferredSize(new Dimension(100,40));
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
				generateConvertWindow();
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
		conversionWindow.setTitle("Acaja - Mode Conversion");
		conversionWindow.setSize(new Dimension(600, 600));
		conversionWindow.setLocationRelativeTo(null);
		conversionWindow.setDefaultCloseOperation(StylizedJFrame.EXIT_ON_CLOSE);

		WindowTools.showLogo(conversionWindow);

		
		LibraryViewController lv = new LibraryViewController(conversionWindow.model, conversionWindow.model.getFilenames());
		TabsView tv = new TabsView(conversionWindow.model);

		
		StylizedJPanel p = new StylizedJPanel();
		p.setLayout(new BorderLayout());
		StylizedJMenuBar menu = new StylizedJMenuBar();
		
		
		menu.add(conversionWindow.drawFileMenu());
		menu.add(conversionWindow.drawOptionsMenu());
		menu.add(conversionWindow.drawConvertMenu());

		
		conversionWindow.setJMenuBar(menu);
		conversionWindow.model.addObserver(lv);
		p.add(tv, BorderLayout.CENTER);
		p.add(conversionWindow.empty_workspace);
		conversionWindow.setLayout(new BorderLayout());
		conversionWindow.add(lv, BorderLayout.WEST);
		conversionWindow.add(p, BorderLayout.CENTER);

		
		WindowTools.executeWindow(conversionWindow);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
}