package gui.conversion;

import java.io.*;
import java.util.*;
import javax.swing.*;
import exceptions.*;
import files.*;
import files.enumerations.OperationType;
import files.files.*;
import gui.alerts.*;
import gui.general.GeneralModel;
import resources.*;
import threads.*;


/**
 * [ MODELE POUR LA FENETRE DE CONVERSION. ]
 * 
 * TODO : gerer la presence des fichiers systemes .DS_store, etc dans la methode loadOldImports();
 * TODO : manage multiple filenames
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ConversionModel extends GeneralModel{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ ATTRIBUTS D'INSTANCE. ]
	 */
	
	//Fichier courant. 
	private SelectableFile currentFile;
	//Liste des fichiers siur lesquels on travaille. 
	private List<SelectableFile> files;
	//Tableau des fichiers precedemment importes.
	private FileInformation[] oldImportedFiles;
	//Repertoire de destination des fichiers exportes.
	private File destinationFolder;
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================



	/**
	 * [ CONSTRUCTEUR. ]
	 */
	public ConversionModel() {
		files = new ArrayList<SelectableFile>();
		oldImportedFiles = new FileInformation[10]; // maximum 10 last files
		destinationFolder = null;
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ SAUVER LES FICHIERS IMPORTEES. ]
	 * 
	 * Methode qui sauvegarde les fichiers recemments ouverts a la fermeture
	 * de la fenetre. 
	 * 
	 * @throws UnfindableResourceException		Exception sur les fichiers introuvables.
	 * @throws  
	 */	
	public void saveImports() throws UnfindableResourceException {
		ResourcesManager.secureConversionImports();
		for(int i = 0 ; i < oldImportedFiles.length ; i ++) {
			try {
				File f = new File(ResourcesManager.CONVERSION_OLD_IMPORTS_PATH + oldImportedFiles[i].getFileName() + ".acaja");
				if(!f.exists()) {
					FileOutputStream fos = new FileOutputStream(f);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(oldImportedFiles[i]);
					oos.close();
					fos.close();	
				}
			} catch (FileNotFoundException fde) {
			} catch (IOException ioe) {	
			} catch(Exception e) {}
		}
	}

	

	
	/**
	 * [ CHARGER LES FICHIERS PRECEDEMMENT IMPORTES. ]
	 * 
	 * Methode qui recupere les fichiers recemments ouverts a l'ouverture. 
	 * 
	 * @throws UnfindableResourceException 		Exception sur les fichiers introuvables.
	 */
	public void loadOldImports() throws UnfindableResourceException {
		ResourcesManager.secureConversionImports();
		File[] files = ResourcesManager.CONVERSION_OLD_IMPORTS.listFiles();	
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
						if(i <= 10) oldImportedFiles[i] = (FileInformation)ois.readObject(); 
						ois.close();
						fis.close();
					}  catch(SecurityException se) {
							Alert.longAlert(Alert.FAILURE, 
								"Vous n'avez pas les permissions pour lire<br>le fichier d'import acaja " 
								 + files[i].getName() + " !");
					} catch(IOException ioe) {
							Alert.longAlert(Alert.FAILURE, 
								"Impossible d'acceder au fichier " + files[i].getName() 
								 + " :<br>erreur d'entree/sortie !");
					} catch(Exception e) {
							Alert.longAlert(Alert.FAILURE,  
								"Erreur lors de l'acces aux fichiers<br>precedemment importes : " 
							     + e.getMessage() + " !");
					}
				}
			}
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/** 
	 * [ SUPPRIMER UN ANCIEN FICHIER D'IMPORTATION ]
	 */
	private boolean removeOldImport(String fileName) {
		try {
			File f = new File(ResourcesManager.CONVERSION_OLD_IMPORTS_PATH + fileName + ".acaja");
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
	 * @throws IncorrectFileException 			Exception levee pour les fichers incorrects. 
	 * 
	 * @throws UnfindableResourceException 		Exception sur les fichiers introuvables.
	 */
	public void add(File file) throws IncorrectFileException, UnfindableResourceException {
		if(file.exists()) {
			files.add(new SettingsFile(file));
			sendChanges();
			FileInformation f = new FileInformation(file.getName(),file.getAbsolutePath());
			for(int i = 0; i < oldImportedFiles.length; i++) {
				if(oldImportedFiles[i] != null) {
					if(oldImportedFiles[i].equals(f)) break;	
				}
			    if(oldImportedFiles[i] == null) {
			        oldImportedFiles[i] = f;
			        saveImports();
			        break;
			    } 
			    if(i==9) {
			    	if(removeOldImport(oldImportedFiles[0].getFileName())) {
			    		for(int j = 1 ; j < oldImportedFiles.length ; j ++) {
			    			oldImportedFiles[j-1] = oldImportedFiles[j];
			    		}
			    		oldImportedFiles[9] = f;
			    		saveImports();	
			    	}
			    }
			}
		}else 
			Alert.longAlert(Alert.FAILURE, Alert.ERROR_ABSENT_SELECTED_FILE);
	}

	
	/**
	 * [ SUPPRIMER UN FICHIER. ]
	 * 
	 * Methode pour supprimer un fichier de la bibliotheque. 
	 * 
	 * @param file Le fichier a supprimer de la bibliotheque.
	 */
	public void remove(SelectableFile file) {
		if(files.contains(file)) {
			files.remove(file); 
			currentFile = null;
			
			if(files.isEmpty()) 
				((ConversionWindow) ConversionContext.WINDOW).redrawEmpty();
			else
				currentFile = files.get(0);
			
			sendChanges();
			Alert.shortAlert(Alert.SUCCESS, "Suppression du fichier "+file.getSourceFileName()+" reussie.");
		}else
			Alert.longAlert(Alert.FAILURE, Alert.ERROR_UNFINDABLE_FILE_TO_REMOVE);
	}
	
	
	/**
	 * [ VIDER LA BIBLIOTHEQUE. ]
	 * 
	 * Methode qui vide la bibliotheque. 
	 */
	public void clear() {
		currentFile = null;
		files.clear();
		sendChanges();
		((ConversionWindow) ConversionContext.WINDOW).redrawEmpty();
		Alert.shortAlert(Alert.SUCCESS, "Bibliotheque videe avec succes.");
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	

	
	/**
	 * [ LE MODELE CONTIENT-IL DES FICHIERS QUI ON ETE MODIFIES ? ]
	 */
	public boolean isModified() {
		boolean isModified = false;
		for(SelectableFile f : files) {
			if(((Modifiable) f).isModified()) {
				isModified = true;
				if(f.getDestinationFileName().equals("") || f.getDestinationFileName().startsWith(".")) 
					return false;
			}
		}
		return isModified;
	}
	
	
	/**
	 * [ MODIFIER LE FICHIER COURANT. ]
	 * 
	 * Methode qui permet de modifier un parametre du fichier
	 * actuellement selectionne
	 */
	public void modify(OperationType typeSetting, String setting) {
		((Modifiable) currentFile).modify(typeSetting, setting);
	}
	
	
	/**
	 * MODIFIER LE FICHIER DE DESTINATION DU FICHIER COURANT. 
	 */
	public void setDestination(String destinationFile) {
		currentFile.setDestinationName(destinationFile);
	}
	
	/**
	 *  [ MODIFIER L'EXTENSION DU FICHIER COURANT ]
	 *  
	 * @param extensionFile extension du fichier
	 */
	public void setExtension(String extensionFile) {
		currentFile.setFileExtension(extensionFile);
	}
	
	/**
	 * [ CONVERTIR LES FICHIERS MODIFIES. ]
	 * 
	 * Methode pour demarrer la conversion des SettingsFile modifies. 
	 * 
	 * @throws UnfindableResourceException 		Exception sur les ressources introuvables. 
	 */
	public void save() throws UnfindableResourceException {
		for(SelectableFile f : files) {
			if(((Modifiable) f).isModified()) {
				new Thread() {
					public void run (){
						/**
						 * ATTENDRE QU'ON ME RENDE LA MAIN.
						 */
						while(RuntimeSpaceManager.hand.took());
						/**
						 * DEBUT DE LA CONVERSION :
						 * 
						 * Prendre la main sur l'espace d'execution.
						 * Prendre la main sur ffmpeg.
						 */
						RuntimeSpaceManager.hand.take();
						startSave();
						/**
						 * LANCEMENT DE LA CONVERSION DANS UN AUTRE PROCESSUS.
						 */
						ThreadForSave.saveInNewThread(f);
						/**
						 * LANCEMENT ET GESTION DE LA FENETRE D'ATTENTE DANS UN AUTRE PROCESSUS.
						 */
						ThreadForWaitWindow.waitInNewThread(
								new AlertWindow(
										"Conversion en evolution.",
										"Conversion du fichier "+f.getSourceFileName()+"<br>Veuillez patientez..."),
								f.getSourceFile());
					}
				}.start();
			}
		}
	}

	

	//=======================================================================================================================
	//=======================================================================================================================
	

	
	/**
	 * [ GETTER - OBTENIR LE FICHEIR COURANT. ]
	 * 
	 * Methode qui retourne le fichier actuellement selectionne par l'utilisateur
	 * 
	 * @return currentFile : fichier actuellement selectionne pour la
	 *         modification par l'utilisateur
	 */
	public SelectableFile getCurrentFile() {
		return currentFile;
	}

	
	/**
	 * [ SETTER - CHANGER DE FICHIER COURANT. ]
	 * 
	 * Methode pour modifier le fichier actuellement 
	 * selectionne par l'utilisateur
	 */
	public void setCurrentFile(SelectableFile file) {
		currentFile = (SettingsFile) file;
		sendChanges();
	}
	
	
	/**
	 * [ GETTER - OBTENIR LA LISTE DES FICHIERS. ]
	 * 
	 * Methode qui retourne la liste des fichiers actuellement dans la bibliotheque.
	 */
	public List<SelectableFile> getFiles() {
		return files;
	}
	
	
	/**
	 * [ GETTER - OBTENIR LE LE TABLEAU DES FICHEIRS PRECDEMMENT IMPORTES. ]
	 * 
	 * Methode qui retourne la liste des fichiers precdemment importes. 
	 */
	public FileInformation[] getOldImports() {
		return oldImportedFiles;
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
		for(SelectableFile f : files) {
			f.setDestinationPath(destinationFolder.getPath());
		}
	}
	
	
	/**
	 * [ GETTER - OBTENIR LE REPERTOIRE DE DESTINATION. ]
	 * 
	 * Methode pour obtenir le repertoire de destination.
	 */
	public File getDestinationFolder() {
		return destinationFolder;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
