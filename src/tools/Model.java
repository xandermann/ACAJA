package tools;

import java.util.Observable;

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
}
