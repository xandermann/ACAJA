package wrapper.streams;

import java.io.*;
import java.util.Iterator;
import javax.swing.JOptionPane;


/**
 * [ SUPERCLASSE ABSTRAITE DES ITERATEURS SUR LES FLUX. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public abstract class StreamIterator implements Iterator<String>{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 *  [ ATTRIBUTS D'INSTANCE. ]
	 */
	/**
	 * FLUX A LIRE.
	 */
	private BufferedReader streamToRead;
	/**
	 * DERNIERE LIGNE LUE.
	 */
	private String lastLineRead;
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE POUR LIRE UNE LIGNE DANS LE FLUX. ]
	 * 
	 * Cette methode permet de lire une nouvelle ligne dans le flux.
	 * Si il n y a plus de ligne a lire, le flux est ferme.
	 * Si le flux n'est pas trouve a la premiere ligne lue,
	 * une boite de dialogue affiche un message d'erreur.
	 */
	private void readNextLine() {
		try {
			/**
			 * ETUDE DE LA LIGNE SUIVANTE.
			 * 
			 * On lit la ligne suivante, et on ferme le flux 
			 * si il n'y a pas de ligne suivante.
			 */
			if((lastLineRead = streamToRead.readLine()) == null) streamToRead.close();
		} catch (IOException e) {			
			/**
			 * EXCEPTIONS, PAS DE FLUX TROUVES. 
			 */
			JOptionPane.showMessageDialog(null, MessageConstants.FALIURE_UNFINDABLE_STREAMS);
		}
	}
	

	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param stream		Le flux (STDOUT ou STDERR) a gerer. 
	 */
	public StreamIterator(InputStream streamToRead) {
		if(streamToRead == null)
			throw new NullPointerException("Le flux recu en parametre est null !");
		this.streamToRead = new BufferedReader(new InputStreamReader(streamToRead));
		readNextLine();
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	

	@Override
	public boolean hasNext() {
		return lastLineRead != null;
	}

	
	
	
	@Override
	public String next() {
		String oldLineRead = lastLineRead;
		/**
		 * CHARGEMENT PREVISIONNEL DE LA PROCHAINE LIGNE A RETOURNER.
		 */
		readNextLine();
		return oldLineRead;
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE POUR ARRETER MANUELLEMENT LA LECTURE DU FLUX. ]
	 */
	public void endReading() {
		/**
		 * ARRET DE LA LECTRURE ET CONSOMMATION DU RESTE DU FLUX. 
		 * 
		 * On consomme le reste du flux 
		 * pour eviter un interblocage.
		 * 
		 * Au dernier appel de readNextLine() le flux est close.
		 */
		while(hasNext()) readNextLine();
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
