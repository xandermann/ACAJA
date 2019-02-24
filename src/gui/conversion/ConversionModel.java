package gui.conversion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import exceptions.IncorrectFileException;
import files.FileInformation;
import files.SettingType;
import files.SettingsFile;
import gui.Model;
import messages.MessageConstants;
import resources.ResourceConstants;
import wrapper.runtime.global.UserRequests;
// TODO : gerer la presence des fichiers systemes .DS_store, etc dans la methode loadOldImports();
// TODO : manage multiple filenames

public final class ConversionModel extends Model {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ ATTRIBUTS D'INSTANCE. ]
	 */
	
	//Fichier courant. 
	private SettingsFile currentFile;
	//Liste des fichiers siur lesquels on travaille. 
	private ArrayList<SettingsFile> files;
	//Tableau des fichiers precedemment importes.
	private FileInformation[] importedFiles;
	//Repertoire de destination des fichiers exportes.
	private File destinationFolder;
	
	
	//=======================================================================================================================
	//=======================================================================================================================



	/**
	 * [ CONSTRUCTEUR. ]
	 */
	public ConversionModel() {
		files = new ArrayList<SettingsFile>();
		// maximum 10 last files
		importedFiles = new FileInformation[10];
		destinationFolder = new File("/");
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ SAUVER LES FICHIERS IMPORTEES. ]
	 * 
	 * Methode qui sauvegarde les fichiers recemments ouverts a la fermeture
	 * de la fenetre. 
	 */
	public void saveImports() {
		try {
			if(createDirectories()) {
				for(int i = 0 ; i < importedFiles.length ; i ++) {
					File f = new File(ResourceConstants.CONVERSION_OLD_IMPORTS_PATH + importedFiles[i].getFileName() + ".acaja");
					if(!f.exists()) {
						FileOutputStream fos = new FileOutputStream(f);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(this.importedFiles[i]);
						oos.close();
						fos.close();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, MessageConstants.ERROR_SAVE_IMPORTS);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	
	/**
	 *  [ METHODE POUR CREER LES DOSSIERS DES IMPORTS SI ILS N'EXISTENT PAS ]
	 */
	private boolean createDirectories() {
		File dirSaves = new File(ResourceConstants.ALL_OLD_IMPORTS_PATH);
		File dirConversion = new File(ResourceConstants.CONVERSION_OLD_IMPORTS_PATH);
		File dirProcessing = new File(ResourceConstants.PROCESSING_OLD_IMPORTS_PATH);
		try {
			boolean res = true;
			if(!dirSaves.exists())
				 res = dirSaves.mkdir();
			if(!dirConversion.exists() && res != false)
				res = dirConversion.mkdir();
			if(!dirProcessing.exists() && res != false)
				res = dirProcessing.mkdir();
			return res;
		} catch (Exception e) {
			return false;
		}				
	}

	
	/**
	 * [ CHARGER LES FICHIERS PRECEDEMMENT IMPORTES. ]
	 * 
	 * Methode qui recupere les fichiers recemments ouverts a l'ouverture. 
	 */
	public void loadOldImports() {
		if(createDirectories()) {
			File[] files = new File(ResourceConstants.CONVERSION_OLD_IMPORTS_PATH).listFiles();	
			// tri des fichiers par date
			if(files != null) {
				Arrays.sort(files, new Comparator<File>(){
					public int compare(File f1, File f2){
						return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
					} 
				});
				for(int i = 0 ; i < files.length ; i++) {
					if(files[i].getName().contains(".acaja")) {
						try {	
							FileInputStream fis = new FileInputStream(files[i]);
							ObjectInputStream ois = new ObjectInputStream(fis);
							if(i <= 10)  // avoid array out of bounds
								this.importedFiles[i] = (FileInformation)ois.readObject();
							ois.close();
							fis.close();
						}  catch(SecurityException se) {
							JOptionPane.showMessageDialog(null, 
									"Vous n'avez pas les permissions pour lire le fichier d'import acaja " + files[i].getName() + " !");
						} catch(IOException ioe) {
							JOptionPane.showMessageDialog(null, 
									"Impossible d'acceder au fichier " + files[i].getName() + " : erreur d'entree/sortie !");
						} catch(Exception e) {
								JOptionPane.showMessageDialog(null, 
									"Erreur lors de l'acces aux fichiers precedemment importes : " + e.getMessage() + " !");
						}
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, MessageConstants.ERROR_SAVE_IMPORTS);
		}
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	

	/**
	 * [ METHODE POUR AFFICHER LES FILES. ]
	 * 
	 * Methode pour recuperer les noms des fichiers sous la forme
	 * d'une DefaultListModel afin de l'afficher dans la liste du ConversionPanel. 
	 * 
	 * @return DefaultListModel<ListEntry>		La liste des noms des fichiers. 
	 */
	public DefaultListModel<ListEntry> getFilenames() {
		DefaultListModel<ListEntry> filenameList = new DefaultListModel<ListEntry>();
		for (SettingsFile f : files) filenameList.addElement(new ListEntry(f.getSourceFileName()));
		return filenameList;
	}

	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/** 
	 * [ SUPPRIMER UN ANCIEN FICHIER D'IMPORTATION ]
	 */
	private boolean removeOldImport(String fileName) {
		try {
			File f = new File(ResourceConstants.CONVERSION_OLD_IMPORTS_PATH + fileName + ".acaja");
			if(!(f.exists())) return true;
			return f.delete();
		} catch(Exception e) {
			return false;
		}
	}
	
	
	/**
	 * [ AJOUTER UN FICHIER. ]
	 * 
	 * Methode qui ajoute un fichier a la bibliotheque
	 * 
	 * @param file SettingsFile 		Le fichier a ajouter a la bibliotheque
	 * 
	 * @throws IncorrectFileException 	Exception levee pour les fichers incorrects. 
	 */
	public void add(File file) throws IncorrectFileException {
		if(file.exists()) {
			files.add(new SettingsFile(file));
			sendChanges();
			FileInformation f = new FileInformation(file.getName(),file.getAbsolutePath());
			for(int i = 0; i < importedFiles.length; i++) {
				if(importedFiles[i] != null) {
					if(importedFiles[i].equals(f)) break;	
				}
			    if(importedFiles[i] == null) {
			        importedFiles[i] = f;
			        this.saveImports();
			        break;
			    } 
			    if(i==9) {
			    	if(removeOldImport(importedFiles[0].getFileName())) {
			    		for(int j = 1 ; j < importedFiles.length ; j ++) {
			    			importedFiles[j-1] = importedFiles[j];
			    		}
			    		importedFiles[9] = f;
			    		saveImports();	
			    	}
			    }
			}
		}else 
			JOptionPane.showMessageDialog(null, "Le fichier selectionne n'existe pas");
	}

	
	/**
	 * [ SUPPRIMER UN FICHIER. ]
	 * 
	 * Methode pour supprimer un fichier de la bibliotheque. 
	 * 
	 * @param file SettingsFile Le fichier a supprimer de la bibliotheque.
	 */
	public void remove(SettingsFile file) {
		if(this.files.contains(file)) {
			files.remove(file);
			currentFile = null;
			sendChanges();
		}else
			JOptionPane.showMessageDialog(null, "Le fichier a supprimer n'est pas present dans la bibliotheque");
	}
	
	
	/**
	 * [ VIDER LA BIBLIOTHEQUE. ]
	 * 
	 * Methode qui vide la bibliotheque. 
	 */
	public void clear() {
		files.clear();
		sendChanges();
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ MODIFIER LE FICHIER COURANT. ]
	 * 
	 * Methode qui permet de modifier un parametre du fichier
	 * actuellement selectionne
	 */
	public void modify(SettingType typeSeyting, String setting) {
		currentFile.modify(typeSeyting, setting);
	}
	
	
	/**
	 * MODIFIER LE FICHIER DE DESTINATION DU FICHEIR COURANT. 
	 */
	public void setDestination(String destinationFile) {
		currentFile.setDestinationFile(destinationFolder.getPath()+"\\"+destinationFile);
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ CONVERTIR LES FICHIERS MODIFIES. ]
	 * 
	 * Methode pour demarrer la conversion des SettingsFile modifies. 
	 */
	public void save() {
		for(SettingsFile sf : files) {
			if(sf.isModified()) UserRequests.execute(sf);		
		}
	}

	

	//=======================================================================================================================
	//=======================================================================================================================
	

	
	/**
	 * [ GETTER - OBTENIR LE FICHEIR COURANT. ]
	 * 
	 * Methode qui retourne le fichier actuellement selectionne par l'utilisateur
	 * 
	 * @return currentFile SettingsFile : fichier actuellement selectionne pour la
	 *         modification par l'utilisateur
	 */
	public SettingsFile getCurrentFile() {
		return currentFile;
	}

	
	/**
	 * [ SETTER - CHANGER DE FICHIER COURANT. ]
	 * 
	 * Methode pour modifier le fichier actuellement 
	 * selectionne par l'utilisateur
	 * 
	 * @param currentFile 		Le nom du fichier maintenant selectionne par l'utilisateur
	 */
	public void setCurrentFile(String fileName) {
		for (SettingsFile f : this.getFiles()) {
			if (f.getSourceFileName().equals(fileName)) currentFile = f;
		}
		sendChanges();
	}
	
	
	/**
	 * [ GETTER - OBTENIR LA LISTE DES FICHIERS. ]
	 * 
	 * Methode qui retourne la liste des fichiers actuellement dans la bibliotheque.
	 */
	public ArrayList<SettingsFile> getFiles() {
		return files;
	}
	
	
	/**
	 * [ GETTER - OBTENIR LE LE TABLEAU DES FICHEIRS PRECDEMMENT IMPORTES. ]
	 * 
	 * Methode qui retourne la liste des fichiers precdemment importes. 
	 */
	public FileInformation[] getOldImports() {
		return importedFiles;
	}


	/**
	 * [ SETTER - CHANGER DE REPERTOIRE DE DESTINATION. ]
	 * 
	 * Methode pour modifier le repertoire de destination.
	 * 
	 * @param destinationFolder 	Le nouveau repertoire de destination.
	 */
	public void setDestinationFolder(File destinationFolder) {
		this.destinationFolder = destinationFolder;
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
