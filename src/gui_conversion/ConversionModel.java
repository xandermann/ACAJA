package gui_conversion;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import exceptions.IncorrectFileException;
import ffmpeg_tools.UserRequests;
import files.SettingsFile;

public final class ConversionModel extends Observable {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	private SettingsFile currentFile;
	private ArrayList<SettingsFile> files;
	//nouvel attribut rajoute pour les profils charges
	private ArrayList<Profile> profiles;
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * Constructeur ConversionModel
	 * 
	 */
	public ConversionModel() {
		this.files = new ArrayList<SettingsFile>();
		this.profiles = new ArrayList<Profile>();
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/*
	 * Les profils seront enregistres dans le dossier du programme et seront tous
	 * charges au demarrage. L'utilisateur pourra gerer les profils presents et en
	 * enregistrer de nouveaux. Creer une classe profile qui prendra tous les
	 * parametres a enregistrer
	 */
	/**
	 * Methode qui charge les profils precedemment enregistres
	 * 
	 */
	// a modifier public void loadProfiles() {}
	// public void loadProfile(File profileFile) { }
	public void loadProfiles() {
		// TODO
		// Charger les fichiers profils dans le dossier des profils
		// Creer des objets de type profils en lisant les fichiers*
		// Ajouter les fichiers dans la liste

	}

	/**
	 * Methode qui enregistre un nouveau profil parmi les profils
	 * 
	 * @param profile Profile : nouveau profil a enregistrer
	 */
	// a modifier public void saveProfile(Profile profile) {}
	// anciennement public void saveProfile(String profile) { }
	public void saveProfile(Profile profile) {
		// Creer un nouveau fichier contenant les parametres du profils
		// Enregistrer le fichier dans le dossier des profils

		this.profiles.add(profile);
	}

	/**
	 * Methode qui supprime un des profils existants
	 * 
	 * @param profile Profile : profil a supprimer
	 */
	// a modifier public void removeProfile(Profile profile) {}
	// anciennement public void removeProfile(File profileFile) { }
	public void removeProfile(Profile profile) {
		if (this.profiles.contains(profile)) 
			this.profiles.remove(profile);
		else 
			JOptionPane.showMessageDialog(null, "Le profil a supprimer n'existe pas");
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * Methode qui sauvegarde les fichiers recemments ouverts a la fermeture
	 */
	public void saveImports() {
	}

	/**
	 * Methode qui recupere les fichiers recemments ouverts a l'ouverture
	 * 
	 * @return liste des fichiers recemments ouverts
	 */
	public ArrayList<File> loadOldImports() {
		// TODO
		return null;
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * Methode qui retourne le fichier actuellement selectionne par l'utilisateur
	 * 
	 * @return currentFile SettingsFile : fichier actuellement selectionne pour la
	 *         modification par l'utilisateur
	 */
	public SettingsFile getCurrentFile() {
		return this.currentFile;
	}

	/**
	 * Methode qui modifie le fichier actuellement selectionne par l'utilisateur
	 * 
	 * @param currentFile String : nom du fichier maintenant selectionne par
	 *                    l'utilisateur
	 */
	public void setCurrentFile(String fileName) {
		for (SettingsFile f : this.getFiles()) {
			if (f.getSourceFilename().contentEquals(fileName)) 
				this.currentFile = f;
		}
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Methode qui ajoute un fichier a la bibliotheque
	 * 
	 * @param file SettingsFile : fichier a ajouter a la bibliotheque
	 * 
	 * @throws IncorrectFileException 
	 */
	public void add(File file) throws IncorrectFileException {
		if(file.exists()) {
			this.files.add(new SettingsFile(file));
			this.setChanged();
			this.notifyObservers();
		}else 
			JOptionPane.showMessageDialog(null, "Le fichier selectionne n'existe pas");
		
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	

	/**
	 * Methode qui recupere les noms des fichiers et les retourne sous la forme
	 * d'une liste de modeles afin de l'afficher dans la liste du ConversionPanel
	 * 
	 * Par la suite, cette methode recuperera egalement les types des fichiers pour
	 * afficher leur icone
	 * 
	 * @return DefaultListModel liste des noms des fichiers
	 */
	public DefaultListModel getFilenames() {
		DefaultListModel filenameList = new DefaultListModel();
		for (SettingsFile f : this.getFiles()) 
			filenameList.addElement(new ListEntry(f.getSourceFilename()));
		return filenameList;
	}

	/**
	 * Methode qui supprime un fichier de la bibliotheque
	 * 
	 * @param file SettingsFile ! fichier a supprimer de la bibliotheque
	 */
	public void remove(SettingsFile file) {
		if(this.files.contains(file)) {
			this.files.remove(file);
			this.currentFile = null;
			this.setChanged();
			this.notifyObservers();
		}else
			JOptionPane.showMessageDialog(null, "Le fichier a supprimer n'est pas present dans la bibliotheque");
	}
	
	/**
	 * Methode qui vide la bibliotheque
	 */
	public void clear() {
		this.files.clear();
		this.setChanged();
		this.notifyObservers();
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * Methode qui permet de modifier un reglage du fichier actuellement selectionne
	 */
	public void modify(Integer typeSetting, Object setting) {
		this.currentFile.modify(typeSetting, setting);
	}
	
	/**
	 * Methode qui demarre la conversion des SettingsFile modifies
	 */
	public void convert() {
		for(SettingsFile sf : this.files) {
			if(sf.isModified()) UserRequests.execute(sf);		
		}
	}

	

	//=======================================================================================================================
	//=======================================================================================================================
	

	/**
	 * Methode qui retourne la liste des fichiers actuellement dans la bibliotheque
	 */
	public ArrayList<SettingsFile> getFiles() {
		return files;
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
