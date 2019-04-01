package wrapper.streams.managers.filters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

/**
 * [ CLASSE POUR LE FILTRAGE DES DONNEES EN REPONSE DES COMMANDES DE L'UTILISATEUR. ]
 * 
 * Ce filtre permet d'etudier le resultat d'une commande
 * et de le notifier a l'utilisateur.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class FilterForFeedback extends Errors implements DataStreamsFilter {
	public static boolean successed(File asFilter, File concerned) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(asFilter));
			String line = null;
			Boolean isConcerned = false;
			while((line=reader.readLine()) != null) {
				if(line.contains(concerned.getAbsolutePath()))	
					isConcerned = true;
				else {
					if(isConcerned && !track(line)) {
						reader.close();
						return false;
					}
				}
			}
			reader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
		return true;
	}
}
	
