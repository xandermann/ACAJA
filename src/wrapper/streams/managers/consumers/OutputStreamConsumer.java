package wrapper.streams.managers.consumers;

import wrapper.streams.iterators.ProcessManager;
import wrapper.streams.iterators.StreamIterator;

public class OutputStreamConsumer implements StreamsConsumer {
	
	public static void consume(ProcessManager processToBeConsume) {
		if(processToBeConsume == null) 
			throw new NullPointerException("Le ProcessManager recu en parametre est null !");
		/**
		 * CONSOMMATION DU FLUX DE SORTIE DE FFMPEG.
		 *  
		 * InputStream represente le flux de sortie de l'application externe 
		 * ( STDOUT de l'application externe : FFMPEG. ).
		 */
		StreamIterator consumer = processToBeConsume.outputStreamIterator();
		while(consumer.hasNext()) consumer.next();
	}
	
}
