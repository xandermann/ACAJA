package wrapper.streams.managers.filters;

import wrapper.streams.iterators.*;
import wrapper.streams.managers.consumers.OutputStreamConsumer;

/**
 * [ CLASSE POUR LE FILTRAGE DES DONNEES CONTENUES DANS UN PROCESS. ]
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
	 * une donnee a extraire. Plus simplement cette methode permet de determiner 
	 * la fin de la chaine a extraire.
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
	 * Cette methode permet d'extraire une des metadonnees d'un 
	 * fichier video ou audio dont FFMPEG nous a fourni 
	 * une longue plage. 
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
	 * video dans la plage des metadonnees fournie par FFMPEG.
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
	 * video dans la plage des metadonnees fournie par FFMPEG 
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
	 * audio dans la plage des metadonnees fournie par FFMPEG 
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
	 * @param processToStudy		ProcessManager pour lequel
	 * 								on doit filtrer les donnees.
	 * 
	 * @return	String				Les metadonnees du fichier.
	 */
	public static String findMetadataOfMediaFile(ProcessManager processToStudy) {
		if(processToStudy == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");

		/**
		 *  EXTRACTION DES METADONNEES.
		 */
		OutputStreamConsumer.consume(processToStudy);
		StreamIterator iterator = processToStudy.errorStreamIterator();
		
		// On ne recupere que les donnees qui nous interesse 
		// d'ou la presence du booleen keepData. 
		boolean keepData = false; 
		
		String metadata = "", data = null;	
		
		while(iterator.hasNext()) {
			data = iterator.next();
			if(keepData == false && data.contains("Input")) keepData = true;			
			if(keepData == true) metadata += data + " ";
		}
		
		return metadata;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
