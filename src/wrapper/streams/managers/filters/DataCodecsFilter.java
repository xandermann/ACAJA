package wrapper.streams.managers.filters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import exceptions.UnfindableResourceException;
import resources.NamesSpaceManager;
import resources.ResourceConstants;
import wrapper.streams.iterators.*;
import wrapper.streams.managers.consumers.*;

/**
 * [ CLASSE POUR LE FILTRAGE DES DONNEES SUR LES CODECS. ]
 * 
 * Ce filtre permet de renvoyer dans des tableaux les codecs 
 * supportes par FFmpeg. 
 *  
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class DataCodecsFilter implements DataStreamsFilter {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE POUR LE FILTRAGE DES CODECS. ]
	 * 
	 * @param typeCodec							Les codecs recherches : 
	 * 											"A" => codecs audio. 
	 * 											"V" => codecs video. 
	 * 
	 * @param processToStudy					Le ProcessManager contenant les flux a filtrer. 
	 * 
	 * @return									Les codecs recherches. 
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	private static String[] findCodecs(String typeCodec, ProcessManager processToStudy) throws UnfindableResourceException {
		if(typeCodec == null) throw new NullPointerException("Le typeCodec recu en parametre est null !");
		if(!typeCodec.equals("A") && !typeCodec.equals("V")) throw new IllegalArgumentException("Type de codecs inconnu !");
		if(processToStudy == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");

		/**
		 *  EXTRACTION DES CODECS.
		 */
		StreamIterator iterator = processToStudy.outputStreamIterator();
		List<String> dataCodecs = new ArrayList<String>();
		
		try {
			Writer saver = new BufferedWriter(new FileWriter(NamesSpaceManager.out()));
			boolean keepData = false; 
			String data = null;	
			while(iterator.hasNext()) {
				saver.write((data = iterator.next())+"\n");
				if(keepData==false && data.contains("-------")) {
					keepData = true;
					data = iterator.next();
				}		
				if(keepData==true) {
					String[] filteredData = data.split(" ");
					if(filteredData[1].contains(typeCodec)) dataCodecs.add(filteredData[2]);
				}
			}
			saver.close();
		} catch (IOException e) {}

		ErrorStreamConsumer.consume(processToStudy);
		return dataCodecs.toArray(new String[dataCodecs.size()]);
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE POUR RECUPERER TOUS LES CODECS VIDEO SUPPORTES PAR FFmpeg. ]
	 * 
	 * @param processToStudy					Le ProcessManager contenant les flux a filtrer. 
	 * 
	 * @return									Les codecs video supportes par FFmpeg.
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static String[] findVideoCodecs(ProcessManager processToStudy) throws UnfindableResourceException {
		if(processToStudy == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");
		return findCodecs("V", processToStudy);
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE POUR RECUPERER TOUS LES CODECS AUDIO SUPPORTES PAR FFmpeg. ]
	 * 
	 * @param processToStudy					Le ProcessManager contenant les flux a filtrer. 
	 * 
	 * @return									Les codecs audio supportes par FFmpeg
	 * 
	 * @throws UnfindableResourceException 		Exception sur les ressources introuvables. 
	 */
	public static String[] findAudioCodecs(ProcessManager processToStudy) throws UnfindableResourceException {
		if(processToStudy == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");
		return findCodecs("A", processToStudy);
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
