package wrapper.streams.managers.filters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JOptionPane;

import exceptions.UnfindableResourceException;
import resources.NamesSpaceManager;
import resources.ResourceConstants;
import wrapper.streams.iterators.*;
import wrapper.streams.managers.consumers.OutputStreamConsumer;

/**
 * [ CLASSE POUR LE FILTRAGE DES METADONNEES PORTANT SUR UN FICHIER. ]
 * 
 * Cette classe propose des methodes pour recuperer les metadonnees
 * qui nous interesse 
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class MetadataFilter implements DataStreamsFilter {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Cette methode permet de connaitre l'index du caractere situe apres
	 * une metadonnee a extraire. Elle permet de determiner la fin de
	 *  la chaine a extraire.
	 */
	private static int findEndIndexMetadata(String metadata, int fromIndex) {
		if(metadata == null) throw new NullPointerException("Metadata null !");
		if(fromIndex<0) throw new IllegalArgumentException("Index negatif !");
		int indexSpace = metadata.indexOf(' ', fromIndex);
		int indexComa = metadata.indexOf(',', fromIndex);
		return indexSpace<indexComa || indexComa==-1 ? indexSpace : indexComa;	
	}
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Cette methode permet d'extraire une des metadonnees d'un  fichier video ou audio 
	 * dont FFmpeg nous a fourni ne longue plage. 
	 */
	private static String findMetadata(String metadata, String start, int indexMetadata) {
		if(metadata == null) throw new NullPointerException("Metadata null !");
		if(start == null) throw new NullPointerException("Start null !");
		if(indexMetadata<0) throw new IllegalArgumentException("Index negatif !");
		
		int indexStart = metadata.indexOf(start) + start.length();
		if(indexMetadata != 0) {
			int indexEvolve = indexStart;
			
			for(int i=0; i<indexMetadata; i++) 
				indexEvolve = metadata.indexOf(',', indexEvolve)+1;
			
			indexEvolve++;
			
			return  metadata.substring(indexEvolve,findEndIndexMetadata(metadata, indexEvolve));
		}else
			return  metadata.substring(indexStart,findEndIndexMetadata(metadata, indexStart));
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	

	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour extraire la duree d'un fichier 
	 * video dans la plage des metadonnees fournie par FFmpeg.
	 */
	public static String findDuration(String metadata) {
		if(metadata == null) throw new NullPointerException("Metadata null !");
		return metadata.substring(
				metadata.indexOf("Duration: ") + "Duration: ".length(), 
				metadata.indexOf("Duration: ") + metadata.substring(metadata.indexOf("Duration: ")).indexOf(","));
	}
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour extraire une metadonnee d'un fichier 
	 * video dans la plage des metadonnees fournie par FFmpeg 
	 * a partir d'un index. 
	 */
	public static String findVideoMetadata(String metadata, int indexMetadata) {
		if(metadata == null) throw new NullPointerException("Metadata null !");
		if(indexMetadata<0) throw new IllegalArgumentException("Index negatif !");
		return findMetadata(metadata, "Video: ", indexMetadata);
	}

	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour extraire une metadonnee d'un fichier 
	 * audio dans la plage des metadonnees fournie par FFmpeg 
	 * a partir d'un index. 
	 */
	public static String findAudioMetadata(String metadata, int indexMetadata) {
		if(metadata == null) throw new NullPointerException("Metadata null !");
		if(indexMetadata<0) throw new IllegalArgumentException("Index negatif !");
		return findMetadata(metadata, "Audio: ", indexMetadata);
	}


	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE DE CLASSE POUR EXTRAIRE LES METADONNEES D'UN FICHIER. ]
	 * 
	 * @param processToStudy					ProcessManager contenant les flux a filter.
	 * 
	 * @return	String							Les metadonnees du fichier.
	 * 
	 * @throws UnfindableResourceException 		Exception sur les ressources introuvables. 
	 */
	public static String findAllMetadata(ProcessManager processToStudy) throws UnfindableResourceException {
		if(processToStudy == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");

		/**
		 *  EXTRACTION DES METADONNEES.
		 */
		StreamIterator iterator = processToStudy.errorStreamIterator();
		String metadata = "";
				
		try {
			Writer saver = new BufferedWriter(new FileWriter(NamesSpaceManager.err()));
			boolean keepData = false; 
			String data = null;	
			while(iterator.hasNext()) {
				saver.write((data = iterator.next())+"\n");
				if(keepData == false && data.contains("Input")) keepData = true;		
				if(keepData == true) metadata += data + " ";
			}
			saver.close();
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ioe.getMessage());
		}
		
		OutputStreamConsumer.consume(processToStudy);
		return metadata;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
