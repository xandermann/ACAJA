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
	
	
	private static volatile boolean workIsOnGoing = false;
	
	
	//=======================================================================================================================
	//=======================================================================================================================	
	
	
	
	public static void startToWork() {
		workIsOnGoing = true;
	}
	
	private static void workIsOver() {
		workIsOnGoing = false;
	}
	
	public static boolean workIsOnGoing() {
		return workIsOnGoing;
	}
	
	
	
   //=======================================================================================================================
   //=======================================================================================================================	
	
	
	
	public static void consumeStreams(Process processtoBeConsume) {
		new Thread() {
			public void run() {
				startToWork();
				StreamsConsumer.consumeStreams(processtoBeConsume);
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
			HashMap<Integer, Object> ffmpegRequests = ((SettingsFile) file).getRequests();
			for(Integer requestKey : ffmpegRequests.keySet()) {
					if(requestKey == SettingsFile.VIDEO_CODEC) {
						String oldExtension =  
								file.getSourceFile().getPath().split("[.]")
								[file.getSourceFile().getPath().split("[.]").length-1];
						String fileName = file.getSourceFile().getPath();
						String newFileName = 
								fileName.substring(0, fileName.lastIndexOf(oldExtension)-1) +
								ffmpegRequests.get(SettingsFile.VIDEO_CODEC);
						
						/**
						 * REQUETE DE CONVERSION SOUMISE A FFMPEG.
						 */
						final Process conversionProcess = execute(fileName+" "+newFileName);
							
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
