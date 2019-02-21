package wrapper.streams;

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
		return metadata.substring(
				metadata.indexOf("Duration: ") + "Duration: ".length(), 
				metadata.indexOf("Duration: ") + metadata.substring(metadata.indexOf("Duration: ")).indexOf(",")
				);
	}
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour extraire une metadonnee d'un fichier 
	 * video dans la plage des metadonnees fournie par FFMPEG 
	 * a partir d'un index. 
	 */
	public static String findVideoMetadata(String metadata, int indexMetadata) {
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
		if(processToStudy == null)
			throw new NullPointerException("Le ProcessManager recu en parametre est null !");

		/**
		 * QUELQUES FAITS A CONNAITRE POUR COMPRENDRE LA LIGNE DE CODE CI-DESSOUS.
		 * 
		 * "Il est temps de vous racontez une petite histoire" :
		 * Les pogrammeurs de FFMPEG savent que les temps de traitement des requetes
		 * sur FFMPEG peuvent prendre un temps assez long. Et il faut savoir que 
		 * le traitement d'une requete renvoie son resultat par le biais des flux de sortie 
		 * ( = STDOUT ). De ce fait les pogrammeurs de FFMPEG ont decide que certains messages 
		 * de reussite comme d'echec d'une requete passeraient par les flux 
		 * d'erreurs ( =  STDERR ). Mais pourquoi ce choix n'est-ce-pas ? Pour la bonne 
		 * raison qu'envoyer certains messages par STDERR permet d'eviter que 
		 * les traitements des requetes qui passent par STDOUT ne soit freiner par les 
		 * messages de reussites de requetes si ils passaient pas STDOUT. 
		 * 
		 * C'est ainsi qu'on se retrouve en 2019 a devoir extraire les messages ne correpondant 
		 * pas a des cas d'erreur, a partir de STDERR et pas a partir de STDOUT, comme ca l'est  
		 * pourtant habituellement par convention. 
		 * 
		 * Ainsi on ne s'interesse ici qu'au flux d'erreurs qui du coup n'en est pas un avec FFMPEG,
		 * mais plutot un flux des messages tous confondus. 
		 */
		StreamIterator extractor = processToStudy.errorStreamIterator();
		
		/**
		 *  EXTRACTION DES METADONNEES.
		 */
		
		//On ne recupere que les donnees qui nous interesse
		//d'ou la presence du booleen keepData. 
		boolean keepData = false;
		
		String metadata = "", data = null;	
		
		while(extractor.hasNext()) {
			data = extractor.next();

			if( keepData == false && data.contains("Input") ) keepData = true;
			
			if( keepData == true ) metadata += data + " ";
		}
		
		//On ferme le flux.
		extractor.endReading();
		
		return metadata;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
