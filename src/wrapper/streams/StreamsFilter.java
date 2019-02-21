package wrapper.streams;

/**
 * [ CLASSE POUR LE FILTRAGE DES DONNEES CONTENUES DANS UN PROCESS. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class StreamsFilter implements StreamsManager {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Cette methode permet de connaitre l'index du caractere situe apres
	 * une donnee a extraire. Plus simplement cette methode determine la 
	 * l'index du caractere qui delime la fin de chaine a extraire.
	 */
	private static int findEndIndexSetting(String informations, int fromIndex) {
		int indexSpace = informations.indexOf(' ', fromIndex);
		int indexComa = informations.indexOf(',', fromIndex);
		return indexSpace<indexComa || indexComa==-1 ? indexSpace : indexComa;	
	}
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Cette methode permet d'extraire un des parametres d'un 
	 * fichier video ou audio dont FFMPEG nous a fourni 
	 * une longue plage d'informations. 
	 */
	private static String findSetting(String informations, String start, int indexSetting) {
		int indexStart = informations.indexOf(start) + start.length();
		if(indexSetting != 0) {
			int indexEvolve = indexStart;
			
			for(int i=0; i<indexSetting; i++) 
				indexEvolve = informations.indexOf(',', indexEvolve)+1;
			
			indexEvolve++;
			
			return  informations.substring(indexEvolve,findEndIndexSetting(informations, indexEvolve));
		}else
			return  informations.substring(indexStart,findEndIndexSetting(informations, indexStart));
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	

	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour extraire la duree. 
	 */
	public static String findDuration(String informations) {
		return informations.substring(
					informations.indexOf("Duration: ") + "Duration: ".length(), 
					informations.indexOf("Duration: ") + informations.substring(informations.indexOf("Duration: ")).indexOf(",")
				);
	}
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour extraire un parametre d'un fichier 
	 * video dans la plage d'informations fournie par FFMPEG 
	 * a partir d'un index. 
	 */
	public static String findVideoSetting(String informations, int indexSetting) {
		return findSetting(informations, "Video: ", indexSetting);
	}

	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour extraire un parametre d'un fichier 
	 * audio dans la plage d'informations fournie par FFMPEG 
	 * a partir d'in index. 
	 */
	public static String findAudioSetting(String informations, int indexSetting) {
		return findSetting(informations, "Audio: ", indexSetting);
	}


	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE DE CLASSE POUR EXTRAIRE LES INFORMATIONS SUR UN FICHIER. ]
	 * 
	 * @param processToStudy		ProcessManager pour lequel
	 * 								on doit filtrer les informations.
	 * 
	 * @return	String				Les donnees filtrees.
	 */
	public static String findInformationsOfMediaFile(ProcessManager processToStudy) {
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
		 *  EXTRACTION DES CARACTERISTIQUES.
		 */
		
		//On ne recupere que les donnees qui nous interesse
		//d'ou la presence du booleen keepInformations. 
		boolean keepInformations = false;
		
		String informations = "", information = null;	
		
		while(extractor.hasNext()) {
			information = extractor.next();

			if( keepInformations == false && information.contains("Input") ) 
				keepInformations = true;
			
			if( keepInformations == true ) 
				informations += information + " ";
		}
		
		//On ferme le flux.
		extractor.endReading();
		
		return informations;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
