package wrapper;

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
	 * Cette methode permet d'extraire une des caracteristiques d'un fichier video ou audio
	 * dont FFMPEG nous a fourni une longue plage d'informations. 
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
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Methode pour extraire la cararteristique d'un fichier 
	 * video dans la plage d'informations fournie par FFMPEG 
	 * a partir d'in index. 
	 */
	private static String findVideoSetting(String informations, int indexSetting) {
		return findSetting(informations, "Video: ", indexSetting);
	}

	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Methode pour extraire la caracteristique d'un fichier 
	 * audio dans la plage d'informations fournie par FFMPEG 
	 * a partir d'in index. 
	 */
	private static String findAudioSetting(String informations, int indexSetting) {
		return findSetting(informations, "Audio: ", indexSetting);
	}


	
	//=======================================================================================================================
	//=======================================================================================================================
}
