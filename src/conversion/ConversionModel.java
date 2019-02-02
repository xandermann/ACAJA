package conversion;

import java.io.*;
import java.util.*;

import javax.swing.*;

import exceptions.IncorrectFileException;
import files.SettingsFile;
import tools.*;
import wrapper.UserRequests;

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
	//Liste des profils.
	private ArrayList<Profile> profiles;
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ CONSTRUCTEUR. ]
	 */
	public ConversionModel() {
		files = new ArrayList<SettingsFile>();
		profiles = new ArrayList<Profile>();
	}

	

	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ CHARGER LES PROFILS. ]
	 * 
	 * Les profils sont enregistres dans un dossier interne du programme et sont tous
	 * charges au demarrage. L'utilisateur pourra gerer les profils presents et en
	 * enregistrer de nouveaux. Creer une classe profile qui prendra tous les
	 * parametres a enregistrer
	 * TODO
	 */
	public void loadProfiles() {
		// Charger les fichiers profils dans le dossier des profils
		// Creer des objets de type profils en lisant les fichiers
		// Ajouter les fichiers dans la liste
	}

	
	/**
	 * [ AJOUTER UN PROFIL. ] 
	 * 
	 * Methode pour ajouter un nouveau profil. 
	 * TODO
	 * @param profile Profile		Le nouveau profil a ajouter.
	 */
	public void addProfile(Profile profile) {
		// Creer un nouveau fichier contenant les parametres du profils
		// Enregistrer le fichier dans le dossier des profils
		profiles.add(profile);
	}

	
	/**
	 * [ SUPPRIMER UN PROFIL. ]
	 * 
	 * Methode pour supprimer un profil. 
	 * TODO
	 * @param profile Profile 	Le profil a supprimer. 
	 */
	public void removeProfile(Profile profile) {
		if(profiles.contains(profile)) 
			profiles.remove(profile);
		else 
			JOptionPane.showMessageDialog(null, "Le profil a supprimer n'existe pas");
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
		ArrayList<File> oldFiles = new ArrayList<File>();
		for(SettingsFile sf : files) oldFiles.add(sf.getSourceFile());
		
		try {
			ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream(Resources.CONVERSION_OLD_IMPORTS));
			saver.writeObject(oldFiles);
			saver.close();
		} catch (IOException e) {}
	}
	

	/**
	 * [ CHARGER LES FICHIERS PRECEDEMMENT IMPORTES. ]
	 * 
	 * Methode qui recupere les fichiers recemments ouverts a l'ouverture. 
	 * 
	 * @return ArrayList<File> 		La liste des fichiers recement ouverts. 
	 */
	public ArrayList<File> loadOldImports() {
		ArrayList<File> oldFiles = null;
		try {
			ObjectInputStream loader = new ObjectInputStream(new FileInputStream(Resources.CONVERSION_OLD_IMPORTS));
			oldFiles = (ArrayList<File>) loader.readObject();
			loader.close();
	    } catch (ClassNotFoundException e) {} catch (IOException e) {}
		return oldFiles;
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
		for (SettingsFile f : files) filenameList.addElement(new ListEntry(f.getSourceFilename()));
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
	 * [ MODIFIER  UN FICHIER. ]
	 * 
	 * Methode qui permet de modifier un parametre du fichier
	 * actuellement selectionne
	 */
	public void modify(Integer typeSetting, Object setting) {
		currentFile.modify(typeSetting, setting);
	}
	
	
	/**
	 * [ CONVERTIR LES FICHEIRS MODIFIES. ]
	 * 
	 * Methode qui demarre la conversion des SettingsFile modifies. 
	 */
	public void convert() {
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
			if (f.getSourceFilename().equals(fileName)) 
				currentFile = f;
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
