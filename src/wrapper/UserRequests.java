package wrapper;

import java.io.*;

import java.util.*;

import files.*;


/**
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class UserRequests extends FFmpegRuntime {	
	//=======================================================================================================================
	//=======================================================================================================================	
	
	/**
	 * [ ATTRIBUT DE CLASSE VOLATILE. ]
	 * 
	 * ( workIsOnGoing => en anglais " travail est en cours ". )
	 * 
	 * Ce booleen volatilepermet de savoir quand un processus fils 
	 * executant FFMPEG est en cours ou non. 
	 * 
	 * Ce booleen est vloatile car il est modifie par plusieurs 
	 * processus ( Thread / Runnable ), et le mot-cle "volatile"
	 * permet de le faire savoir au compilateur JAVAC. 
	 */
	private static volatile boolean workIsOnGoing = false;
	
	
	//=======================================================================================================================
	//=======================================================================================================================	
	
	
	/**
	 * [ METHODE DE CLASSE POUR DECLARER LE LANCEMENT DE FFMPEG. ]
	 * 
	 * Cette methode permet de declarer que FFMPEG vient d'etre lancee ou 
	 * va bientot etre lancee en processus fils par JAVA. 
	 */
	public static void startToWork() {
		workIsOnGoing = true;
	}
	
	/**
	 * [ METHODE DE CLASSE POUR DECLARER LA FIN D'EXCEUTION DE FFMPEG. ]
	 * 
	 * Cette methode permet declarer que le processus fils dans lequel 
	 * s'executait FFMPEG vient de mourir ou va bientot mourir. 
	 */
	private static void workIsOver() {
		workIsOnGoing = false;
	}
	
	/**
	 * [ GETTER - METHODE DE CLASSE ACCESSEUR POUR ACCEDER A WORKISONGOING. ]
	 * 
	 * Cette methode est un accesseur a la valeur du booleen workIsOnGoing,
	 * Celui-ci permet de connaitre l'evolution de la " besogne " de FFMPEG.
	 * 
	 * @return boolean 		La valeur de workIsOnGoing. 
	 */
	public static boolean workIsOnGoing() {
		return workIsOnGoing;
	}
	
	
	
   //=======================================================================================================================
   //=======================================================================================================================	
	
	
	/**
	 * [ METHODE DE CLASSE POUR LA CONSOMMATION DES FLUX DE REPONSES DE FFMPEG. ]
	 * 
	 * Cette methode permet la consommation des flux de reponses ( = de sorties ) de 
	 * FFMPEG dans un fil d'execution separe du processus courant qui le lance. 
	 * 
	 * On utilise entre autre ici un Thread pour lancer la consommation des
	 * flux de sortie de FFMPEG dans un nouveau fil d'execution. 
	 * 
	 * @param processToBeConsume		ProcessManager, un outil pour gerer 
	 * 									le Process contenat les flux a consommer 
	 * 									dans un nouveau processus. 
	 */
	public static void consumeStreams(ProcessManager processToBeConsume) {
		new Thread() {
			public void run() {
				startToWork();
				StreamsConsumer.consumeStreams(processToBeConsume);
				workIsOver();
			}
		}.start();
	}
	
	
	
   //=======================================================================================================================
   //=======================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour appliquer des modifications sur un ficheir media. 
	 * 
	 * @param file 		La fichier sur lequel il faut realiser les modifications. 
	 */
	public static void execute(SelectableFile file) {
		if(file instanceof SettingsFile) {
			HashMap<Setting, Object> ffmpegRequests = ((SettingsFile) file).getRequests();
			for(Setting requestKey : ffmpegRequests.keySet()) {
					if(requestKey == Setting.VIDEO_CODEC) {
						String oldExtension =  
								file.getSourceFile().getPath().split("[.]")
								[file.getSourceFile().getPath().split("[.]").length-1];
						String fileName = file.getSourceFile().getPath();
						String newFileName = 
								fileName.substring(0, fileName.lastIndexOf(oldExtension)-1) +
								ffmpegRequests.get(Setting.VIDEO_CODEC);
						
						/**
						 * REQUETE DE CONVERSION SOUMISE A FFMPEG.
						 */
						ProcessManager conversionProcess = execute(fileName+" "+newFileName);
							
						/**
						 * CONSOMMATION DES FLUX DE SORTIE.
						 */
						consumeStreams(conversionProcess);
					}
			}
		}
	}
	
	


	//=======================================================================================================================
	//=======================================================================================================================	
	
	
	
	/**
	 * TODO
	 * @param file
	 * @param l
	 * @return
	 */
	public static File extractImage(File file, long l) {
		return file; // TODO
	}
	
	
	
	 //=======================================================================================================================
	 //=======================================================================================================================	
}
