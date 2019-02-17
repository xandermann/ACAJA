package wrapper.streams;

import javax.swing.JOptionPane;

import wrapper.runtime.details.ProcessManager;
import wrapper.runtime.global.MessageConstants;

/**
 * [ CLASSE POUR LA "CONSOMMATION" DES FLUX DE REPONSES DE FFMPEG. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class StreamsConsumer implements StreamsManager{
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
	 * d'instructions en java qui ralentiraient la machine realisant les taches de FFMPEG et 
	 * de JAVA parallelement.
	 * 
	 * @param processToBeConsume			ProcessManager contenant les flux a consommer.
	 */
	public static void consumeStreams(ProcessManager processToBeConsume) {
		if(processToBeConsume == null)
			throw new NullPointerException("Le flux recu en parametre est null !");
		
		/**
		 * CONSOMMATION DU FLUX D'ERREURS DE FFMPEG. 
		 * 
		 * InputStream represente ici le flux d'erreur de l'application externe
		 * ( STDERR de l'application externe : FFMPEG. ).
		 */ 
		StreamIterator consumer = processToBeConsume.errorStreamIterator();
		while(consumer.hasNext()) consumer.next();
		
		
		/**
		 * CONSOMMATION DU FLUX DE SORTIE DE FFMPEG.
		 *  
		 * InputStream represente le flux de sortie de l'application externe 
		 * ( STDOUT de l'application externe : FFMPEG. ).
		 */
		consumer = processToBeConsume.outputStreamIterator();
		while(consumer.hasNext()) consumer.next();
		
		
		 try {
			 /**
			  * PAR SIMPLE DEONTOLOGIE ON OBLIGE JAVA 
			  * A ATTENDRE LA MORT DU PROCESSUS FILS : FFMPEG.
			  */
			 processToBeConsume.getManagedProcess().waitFor();
		} catch (InterruptedException e) {
			 JOptionPane.showMessageDialog(null, MessageConstants.FALIURE_ACTIVE_WAIT_FOR);
		}
	}
	
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================	
}
