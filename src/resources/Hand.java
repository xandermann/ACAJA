package resources;

/**
 * [ MAIN / VERROU / PRIVATISEUR DE RESSOURCE. ]
 * 
 * Une main sur une ressource peut etre prise et rendu
 * depuis l'interieur comme depuis l'exterieur de la classe.
 * Une main est un genre de verrou, pris en compte par tous 
 * les processus du fil d'execution du logiciel. D'ou 
 * l'utilisation d'un booleen volatile.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class Hand {
	/**
	 * [ MAIN PRISE SUR UNE RESSOURCE. ]
	 * Par defaut la main n'est pas prise.
	 */
	private volatile boolean took = false;

	
	/**
	 * [ PRENDRE LA MAIN. ]
	 */
	public void take() {
		took = true;
	}
	
	
	/**
	 * [ RENDRE LA MAIN. ]
	 */
	public void render() {
		took = false;
	}
	
	
	/**
	 * [ LA MAIN EST-ELLE PRISE ? ]
	 * 
	 * @return True si la main est prise.
	 */
	public boolean took() {
		return took;
	}
}
