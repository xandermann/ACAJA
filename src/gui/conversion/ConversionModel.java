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
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ CONSTRUCTEUR. ]
	 */
	public ConversionModel() {
		files = new ArrayList<SettingsFile>();
		// maximum 10 last files
		this.importedFiles = new FileInformation[10];
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
				for(int i = 0 ; i < this.importedFiles.length ; i ++) {
				File f = new File(ResourceConstants.CONVERSION_OLD_IMPORTS_PATH + this.importedFiles[i].getFileName() + ".acaja");
				if(!f.exists()) {
					FileOutputStream fos = new FileOutputStream(f);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(this.importedFiles[i]);
					oos.close();
					fos.close();
				}
			}
			} else {
				//System.out.println("Impossible de creer les dossiers de sauvegarde des imports");
				JOptionPane.showMessageDialog(null,"Impossible de creer les dossiers de sauvegarde des imports. Merci de verifier les permissions du repertoire d'installation d'acaja");
			}
			
		} catch(SecurityException se) {
			System.out.println(se.getMessage());
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
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
				
				
				// System.out.println(files.length);
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
						//System.out.println(se.getMessage());
						JOptionPane.showMessageDialog(null, "Vous n'avez pas les permissions pour lire le fichier d'import acaja " + files[i].getName());
					} catch(IOException ioe) {
						//System.out.println(ioe.getMessage());
						JOptionPane.showMessageDialog(null, "Impossible d'acceder au fichier " + files[i].getName() + " : erreur d'entree/sortie");
					} catch(Exception e) {
						//System.out.println(e.getMessage());
						JOptionPane.showMessageDialog(null, "Erreur lors de l'acces aux fichiers precedemment importes : " + e.getMessage());
					}
					}
					
					}
				}
			//	System.out.println("Loaded old imports...");
			/*	for(FileInformation f : this.importedFiles) {
					if(f != null) 
						System.out.println(f.getFileName());
				} debug ; */
			} else {
				JOptionPane.showMessageDialog(null,"Impossible de creer les dossiers de sauvegarde des imports. Merci de verifier les permissions du repertoire d'installation d'acaja");
			}
	}
	
	public FileInformation[] getOldImports() {
		return this.importedFiles;
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	

	/**
	 * [ METHODE POUR AFFICHER LES FILES. ]
	 * 
	 * Methode pour recuperer les noms des fichiers sous la forme
	 * d'une DefaultListModel afin de l'afficher dans la liste du ConversionPanel. 
	 * 
	 * TODO
	 * Par la suite, cette methode recuperera egalement les types des fichiers pour
	 * afficher leur icone. 
	 * 
	 * @return DefaultListModel<ListEntry>		La liste des noms des fichiers. 
	 */
	public DefaultListModel<ListEntry> getFilenames() {
		DefaultListModel<ListEntry> filenameList = new DefaultListModel<ListEntry>();
		for (SettingsFile f : files) filenameList.addElement(new ListEntry(f.getName()));
		return filenameList;
	}

	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	
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
		//	System.out.println("File exists ...");
			FileInformation f = new FileInformation(file.getName(),file.getAbsolutePath());
		
			for(int i = 0; i < this.importedFiles.length; i++) {
					
				if(this.importedFiles[i] != null) {
					if(this.importedFiles[i].equals(f)){
				//	System.out.println("Found match in file " + i + " : " + this.importedFiles[i].getFileName());
					break;	
					}
					
				}
			    if(this.importedFiles[i] == null) {
			    //	System.out.println("Found empty array field at " + i + " : adding " + f.getFileName());
			        this.importedFiles[i] = f;
			        this.saveImports();
			        break;
			    } 
			    
			    
			    
			    if(i==9) {
			  //  	System.out.println("Array is full ...");
			    	if(deleteFile(this.importedFiles[0].getFileName())) {
			    		//remove first occurence and moving all others
			    
			    	//	System.out.println("File at index 0 (" + this.importedFiles[0].getFileName() + ") removed.");
			    		for(int j = 1 ; j < this.importedFiles.length ; j ++) {
			    		//	System.out.println("Replacing " + this.importedFiles[j-1].getFileName() + " by " + this.importedFiles[j] + " in array ( " + (j-1) +" replaced by  " + j);
			    			this.importedFiles[j-1] = this.importedFiles[j];
			    		}
			    			
			    		//add new fileinformation at last position
			    		this.importedFiles[9] = f;
			    	//	System.out.println("Added " + f.getFileName() + " at position 9");
			    	this.saveImports();	
			    	}
			    }
			}
		}else 
			JOptionPane.showMessageDialog(null, "Le fichier selectionne n'existe pas");
		
		
		
	}

	
	/** 
	 * [ SUPPRIMER UN ANCIEN FICHIER D'IMPORTATION ]
	 */
	private boolean deleteFile(String fileName) {
		try {
			File f = new File(ResourceConstants.CONVERSION_OLD_IMPORTS_PATH + fileName + ".acaja");
			if(!(f.exists())) return true;
			return f.delete();
		} catch(Exception e) {
			return false;
		}
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
	 * [ MODIFIER  UN FICHIER. ]
	 * 
	 * Methode qui permet de modifier un parametre du fichier
	 * actuellement selectionne
	 */
	public void modify(SettingType typeSeyting, String setting) {
		currentFile.modify(typeSeyting, setting);
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
			if (f.getName().equals(fileName)) currentFile = f;
		}
		sendChanges();
	}
	
	
	/**
	 * [ GETTER - OBTENIR LA LISTE DES FICHIERS. ]
	 * 
	 * Methode qui retourne la liste des fichiers actuellement dans la bibliotheque
	 */
	public ArrayList<SettingsFile> getFiles() {
		return files;
	}
	

	
	//=======================================================================================================================
	//=======================================================================================================================
}
