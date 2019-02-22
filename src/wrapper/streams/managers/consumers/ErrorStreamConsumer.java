package wrapper.streams.managers.consumers;

import wrapper.streams.iterators.ProcessManager;
import wrapper.streams.iterators.StreamIterator;

/**
 * [ CLASSE POUR LA "CONSOMMATION" DU FLUX STDERR. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ErrorStreamConsumer implements StreamsConsumer {
	/**
	 * [ METHODE DE CLASSE : CONSOMMER STDERR. ]
	 * 
	 * @param processToBeConsume	Le ProcessManager contenant le fux STDERR a consommer.
	 */
	public static void consume(ProcessManager processToBeConsume) {
		if(processToBeConsume == null) 
			throw new NullPointerException("Le ProcessManager recu en parametre est null !");
		/**
		 * CONSOMMATION DU FLUX D'ERREURS DE FFMPEG. 
		 * 
		 * InputStream represente ici le flux d'erreur de l'application externe
		 * ( STDERR de l'application externe : FFMPEG. ).
		 */ 
		StreamIterator consumer = processToBeConsume.errorStreamIterator();
		while(consumer.hasNext()) consumer.next();
	}
}