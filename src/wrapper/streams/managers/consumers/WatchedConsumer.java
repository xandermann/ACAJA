package wrapper.streams.managers.consumers;

import exceptions.UnfindableResourceException;
import resources.Hand;
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
	/**
	 * [ MAIN SUR LA RESSOURCE : LA CONSOMMATION DES FLUX. ]
	 *  
	 * Ce consommateur possede une main, qu'on peut prendre et rendre
	 * depuis l'interieur comme depuis l'exterieur de la classe.
	 * Une main est un genre de verrou, pris en compte par tous 
	 * les processus du fil d'execution du logiciel.
	 * 
	 * Cette main permet de modeliser les 3 etats de la consommation.
	 */
	public final static Hand hand = new Hand();
	

	/**
	 * [ METHODE DE CLASSE POUR LA CONSOMMATION DES FLUX DE REPONSES DE FFmpeg. ]
	 * 
	 * Cette methode permet la consommation des flux de reponses ( = de sorties ) de 
	 * FFmpeg  
	 * 
	 * @param processToBeConsume				ProcessManager, un outil pour gerer 
	 * 											le Process contenant les flux a consommer. 
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void consume(ProcessManager processToBeConsume) throws UnfindableResourceException {
		if(processToBeConsume == null) throw new NullPointerException("Le ProcessManager recu en parametre est null !");
		hand.take();
		AllStreamsConsumer.consume(processToBeConsume);
		hand.render();
	}
}
