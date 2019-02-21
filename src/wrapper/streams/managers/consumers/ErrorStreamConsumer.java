package wrapper.streams.managers.consumers;

import wrapper.streams.iterators.ProcessManager;
import wrapper.streams.iterators.StreamIterator;

public final class ErrorStreamConsumer implements StreamsConsumer {

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
