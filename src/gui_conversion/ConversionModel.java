package gui_conversion;

import java.io.*;
import java.util.*;
import files.*;

public class ConversionModel {

	private SettingsFile currentFile;
	private ArrayList<SettingsFile> files;
	
	/** Constructeur ConversionModel
	 * 
	 */
	public ConversionModel() {	}
	
	/*Les profils seront enregistres dans le dossier du programme
	 *et seront tous charges au demarrage. L'utilisateur pourra gerer les profils
	 *presents et en enregistrer de nouveaux.
	 *Creer une classe profile qui prendra tous les parametres a enregistrer
	 */
	/**
	 * Methode qui charge les profils precedemment enregistres
	 * 
	 */
	// a modifier public void loadProfiles() {}
	public void loadProfile(File profileFile) { }
	/**
	 * Methode qui enregistre un nouveau profil parmi les profils
	 * 
	 * @param profile Profile : nouveau profil a enregistrer
	 */
	// a modifier public void saveProfile(Profile profile) {}
	public void saveProfile(String profile) { }
	/**
	 * Methode qui supprime un des profils existants
	 * 
	 * @param profile Profile : profil a supprimer
	 */
	// a modifier public void removeProfile(Profile profile) {}
	public void removeProfile(File profileFile) { }

	/**
	 * Methode qui sauvegarde les fichiers recemments ouverts a la fermeture
	 */
	public void saveImports() {}
	/** 
	 * Methode qui recupere les fichiers recemments ouverts a l'ouverture
	 * @return liste des fichiers recemments ouverts
	 */
	public ArrayList<File> loadOldImports() {return null;}
	
	/**
	 * Methode qui retourne le fichier actuellement selectionne par l'utilisateur
	 * @return currentFile SettingsFile : fichier actuellement selectionne pour la modification par l'utilisateur
	 */
	public SettingsFile getCurrentFile() {
		return currentFile;
	}

	/**
	 * Methode qui modifie le fichier actuellement selectionne par l'utilisateur
	 * @param currentFile SettingsFile : fichier maintenant selectionne pour la modification par l'utilisateur
	 */
	public void setCurrentFile(SettingsFile currentFile) {
		this.currentFile = currentFile;
	}
	
	/**
	 * Methode qui ajoute un fichier a la bibliotheque
	 * @param file SettingsFile : fichier a ajouter a la bibliotheque
	 */
	public void add(SettingsFile file) { }
	
	/**
	 * Methode qui supprime un fichier de la bibliotheque
	 * @param file SettingsFile ! fichier a supprimer de la bibliotheque
	 */
	public void remove(SettingsFile file) {	}
	
	
	/**
	 * Methode qui demarre la conversion des SettingsFile modifies
	 */
	public void convert() { }
	
	/**
	 * Methode qui vide la bibliotheque
	 */
	public void clear() { }
	
	/**
	 * Methode qui permet de modifier un reglage du fichier actuellement selectionne
	 */
	public void modify(String parametre, Object valeur) { }

	
	/**
	 * Methode qui retourne la liste des fichiers actuellement dans la bibliotheque
	 */
	public ArrayList<SettingsFile> getFiles() {
		return files;
	}

	
	
	
}
