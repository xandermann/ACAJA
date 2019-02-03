package gui;

import java.util.Observable;

import wrapper.UserRequests;
/**
 * [ SUPERCLASSE ABSTRAITE MODEL. ]
 * 
 * Cette classe abstraite declare les methodes communes aux 2 modeles :
 * ConversionModel et ProcessingModel.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public abstract class Model extends Observable{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE POUR "ENVOYER" AUX VUES LES DERNIERS CHANGEMENTS DU MODELE. ]
	 * 
	 * Methodes pour que les vues (Observeurs) soit "up to date" par rapport 
	 * aux derniers changements internes du modele (Observable).  
	 */
	public void sendChanges() {
		setChanged();
		notifyObservers();
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ PRECISER MANUELLEMENT QUE LA SAUVEGARDDE COMMENCE. ]
	 * 
	 * La sauvegarde correpond a la conversion des fichiers modifies 
	 * (ConversionModel) ou a l'export des fichiers traites (ProcessingPanel) 
	 * selon la selon la sous-classe.
	 * 
	 * Cette methode a pour but d'eviter des imprecisions (du a
	 * un Thread ayant commence en retard ou en avance son travail).
	 * Le booleen UserRequests.workIsOnGoing ne doit pas souffrir de ses 
	 * imprecisions, ce pourquoi avec cette methode on peut repreciser 
	 * la valeur du booleen. 
	 */
	public void startSave() {
		UserRequests.startToWork();
	}
	
	
	/**
	 * [ "SAUVER" LES FICHIERS MODIFIES. ]
	 * 
	 * Methode pour demarrer la conversion des SettingsFile modifies
	 * (ConversionModel) ou l'export des ProcessingFile traites (ProcessingPanel) 
	 * selon la sous-classe. 
	 * 
	 * Le code de la methode est diffreent selon la sous-classe, ce pourquoi
	 * on declare la methode pour l'instant en abstract.
	 */
	public abstract void save();
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
