package wrapper.streams.managers.consumers;

import exceptions.UnfindableResourceException;
import gui.alerts.Alert;
import wrapper.streams.iterators.*;

/**
 * [ CLASSE POUR LA "CONSOMMATION" DES FLUX DE REPONSES DE FFMPEG. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public class AllStreamsConsumer implements StreamsConsumer {
	//=======================================================================================================================
	//=======================================================================================================================	
	
	
	
	/**
	 * [ METHODE DE LA CLASSE POUR LA "CONSOMMATION" DES FLUX DE REPONSES DE FFMPEG. ]
	 * 
	 * Il est necessaire et imperatif pour la bonne execution d'une application 
	 * par l'intermediaire de JAVA de "consommer" les flux de reponses ( = de sortie ) 
	 * de l'application externe. 
	 * 
	 * Dans le cas ou les flux ne seraient pas consommes, il y a un potentiel risque
	 * eleve d'interblocage. Pour faire simple, l'application externe attendrait en 
	 * boucle qu'on consomme ses flux, et JAVA atenndrait en boucle que le processus
	 * fils ( = processus de l'application externe ) se termine.
	 * 
	 * Enfin le processus fils ( = processus de l'application externe ) doit obliger 
	 * son processus pere ( = JAVA ) a attendre afin d'eviter l'enchainement en parallele
	 * d'instructions en java qui ralentiraient la machine.
	 * 
	 * @param processToBeConsume				ProcessManager contenant les flux a consommer.
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void consume(ProcessManager processToBeConsume) throws UnfindableResourceException {
		if(processToBeConsume == null) 
			throw new NullPointerException("Le ProcessManager recu en parametre est null !");
		
		/**
		 * CONSOMMATION DU FLUX D'ERREURS DE FFMPEG. 
		 * 
		 * InputStream represente ici le flux d'erreur de l'application externe
		 * ( STDERR de l'application externe : FFMPEG. ).
		 */ 
		ErrorStreamConsumer.consume(processToBeConsume);
		
		
		/**
		 * CONSOMMATION DU FLUX DE SORTIE DE FFMPEG.
		 *  
		 * InputStream represente le flux de sortie de l'application externe 
		 * ( STDOUT de l'application externe : FFMPEG. ).
		 */
		OutputStreamConsumer.consume(processToBeConsume);
		
		
		try {
			 /**
			  * PAR SIMPLE DEONTOLOGIE ON OBLIGE JAVA 
			  * A ATTENDRE LA MORT DU PROCESSUS FILS : FFMPEG.
			  */
			 processToBeConsume.getManagedProcess().waitFor();
		} catch (InterruptedException e) {
			/**
			 * EXCEPTIONS, PAS DE FLUX TROUVES. 
			 */
			Alert.longAlert(Alert.FAILURE, Alert.ERROR_ACTIVE_WAIT_FOR);
		}
	}
	
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================	
}
