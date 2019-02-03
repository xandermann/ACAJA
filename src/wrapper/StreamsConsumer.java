package wrapper;

import java.io.*;

import javax.swing.JOptionPane;

/**
 * [ CLASSE POUR LA "CONSOMMATION" DES FLUX DE REPONSES DE FFMPEG. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class StreamsConsumer{
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
	 * @param processToBeConsume				Process a consommer.
	 */
	public static void consumeStreams(Process processToBeConsume) {
		try {
			/**
			 * CONSOMMATION DU FLUX D'ERREURS DE FFMPEG. 
			 * 
			 * InputStream representant ici le flux d'erreur de l'application externe
			 * ( STDERR de l'application externe : FFMPEG. ).
			 */ 
			BufferedReader consumer = new BufferedReader(new InputStreamReader(processToBeConsume.getErrorStream()));
			String line = "";
			try {
				while((line = consumer.readLine()) != null);
			} finally {
				consumer.close();
			}
			
			
			/**
			 * CONSOMMATION DU FLUX DE SORTIE DE FFMPEG.
			 *  
			 * InputStream representant le flux de sortie de l'application externe 
			 * ( STDOUT de l'application externe : FFMPEG. ).
			 */
			consumer = new BufferedReader(new InputStreamReader(processToBeConsume.getInputStream()));
			try {
				while((line = consumer.readLine()) != null);
			} finally {
				consumer.close();
			}
		} catch(IOException ioe) {}
		
		
		 try {
			 /**
			  * PAR SIMPLE DEONTOLOGIE ON OBLIGE JAVA 
			  * A ATTENDRE LA MORT DU PROCESSUS FILS : FFMPEG.
			  */
			 processToBeConsume.waitFor();
		} catch (InterruptedException e) {
			 JOptionPane.showMessageDialog(null, MessageConstants.FALIURE_ACTIVE_WAIT_FOR);
		}
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================	
}
