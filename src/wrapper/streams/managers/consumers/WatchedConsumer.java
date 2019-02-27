package wrapper.streams.managers.consumers;

import wrapper.streams.iterators.ProcessManager;

/**
 * [ CLASSE POUR UNE "CONSOMMATION SURVEILLEE" DE TOUS LES FLUX DE SORTIES. ]
 * 
 * Cette classe permet 2 chsoes essentielles :
 * 	- consommation de tous les flux de sorties ;
 * 	- information sur l'evolution de la consommation des flux.
 * 
 * 3 etats sont possibles :
 * 	- a commencé ;
 * 	- en cours ;
 * 	- a terminé. 
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class WatchedConsumer extends AllStreamsConsumer {
	//==================================================================================================================================================
	
	
	/**
	 * [ ATTRIBUT DE CLASSE VOLATILE. ]
	 * 
	 * ( workIsOnGoing => en anglais " travail est en cours ". )
	 * 
	 * Ce booleen volatilepermet de savoir quand un processus fils 
	 * executant FFmpeg est en cours ou non. 
	 * 
	 * Ce booleen est volatile car il est modifie par plusieurs 
	 * processus ( Thread / Runnable ), et le mot-cle "volatile"
	 * permet de le faire savoir au compilateur JAVAC. 
	 */
	private static volatile boolean workIsOnGoing = false;
	
	
	//==================================================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE POUR DECLARER LE LANCEMENT DE FFmpeg. ]
	 * 
	 * Cette methode permet de declarer que FFmpeg vient d'etre lancee ou 
	 * va bientot etre lancee en processus fils par JAVA. 
	 */
	public static void startToWork() {
		workIsOnGoing = true;
	}
	
	/**
	 * [ METHODE DE CLASSE POUR DECLARER LA FIN D'EXCEUTION DE FFmpeg. ]
	 * 
	 * Cette methode permet declarer que le processus fils dans lequel 
	 * s'executait FFmpeg vient de mourir ou va bientot mourir. 
	 */
	private static void workIsOver() {
		workIsOnGoing = false;
	}
	
	/**
	 * [ GETTER - METHODE DE CLASSE ACCESSEUR POUR ACCEDER A WORKISONGOING. ]
	 * 
	 * Cette methode est un accesseur a la valeur du booleen workIsOnGoing,
	 * Celui-ci permet de connaitre l'evolution de la " besogne " de FFmpeg.
	 * 
	 * @return boolean 		La valeur de workIsOnGoing. 
	 */
	public static boolean workIsOnGoing() {
		return workIsOnGoing;
	}
	

    //==================================================================================================================================================
	

	/**
	 * [ METHODE DE CLASSE POUR LA CONSOMMATION DES FLUX DE REPONSES DE FFmpeg. ]
	 * 
	 * Cette methode permet la consommation des flux de reponses ( = de sorties ) de 
	 * FFmpeg  
	 * 
	 * @param processToBeConsume		ProcessManager, un outil pour gerer 
	 * 									le Process contenant les flux a consommer. 
	 */
	public static void consume(ProcessManager processToBeConsume) {
		if(processToBeConsume == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");
		startToWork();
		AllStreamsConsumer.consume(processToBeConsume);
		workIsOver();
	}
	

   //==================================================================================================================================================
}
