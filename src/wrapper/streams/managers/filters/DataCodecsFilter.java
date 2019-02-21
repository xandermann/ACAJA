package wrapper.streams.managers.filters;

import java.util.*;
import wrapper.streams.iterators.*;
import wrapper.streams.managers.consumers.*;

public final class DataCodecsFilter implements DataStreamsFilter {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	private static String[] findCodecs(String typeCodec, ProcessManager processToStudy) {
		if(typeCodec == null) throw new NullPointerException("Le typeCodec recu en parametre est null !");
		if(processToStudy == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");

		/**
		 *  EXTRACTION DES CODECS.
		 */
		ErrorStreamConsumer.consume(processToStudy);
		StreamIterator iterator = processToStudy.outputStreamIterator();
		
		// On ne recupere que les donnees qui nous interesse 
		// d'ou la presence du booleen keepData. 
		boolean keepData = false; 
		List<String> dataCodecs = new ArrayList<String>();
		String data = null;	
		while(iterator.hasNext()) {
			data = iterator.next();
			if(keepData==false && data.contains("-------")) {
				keepData = true;
				data = iterator.next();
			}		
			if(keepData==true) {
				String[] filteredData = data.split(" ");
				if(filteredData[1].contains(typeCodec)) dataCodecs.add(filteredData[2]);
			}
		}

		return dataCodecs.toArray(new String[dataCodecs.size()]);
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public static String[] findVideoCodecs(ProcessManager processToStudy) {
		if(processToStudy == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");
		return findCodecs("V", processToStudy);
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public static String[] findAudioCodecs(ProcessManager processToStudy) {
		if(processToStudy == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");
		return findCodecs("A", processToStudy);
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
