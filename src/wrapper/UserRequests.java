package wrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import files.SelectableFile;
import files.SettingsFile;
/**
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class UserRequests extends FFmpegRuntime {	
	public static volatile boolean workIsInOnGoing = true;
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour appliquer des modifications sur un ficheir media. 
	 * 
	 * @param file 		La fichier sur lequel il faut realiser les modifications. 
	 */
	public static void execute(SelectableFile file) {
		workIsInOnGoing = true;
		
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
						new Thread() {
							public void run() {
								try {
									BufferedReader consumer = new BufferedReader(new InputStreamReader(conversionProcess.getErrorStream()));
									String line = "";
									try {
										while((line = consumer.readLine()) != null);
									} finally {
										consumer.close();
									}
									
									consumer = new BufferedReader(new InputStreamReader(conversionProcess.getInputStream()));
									try {
										while((line = consumer.readLine()) != null);
									} finally {
										consumer.close();
									}
								} catch(IOException ioe) {}
								 workIsInOnGoing = false;
							}
						}.start();
					}
			}
		}
	}
	
	

	/**
	 * TODO
	 * @param file
	 * @param l
	 * @return
	 */
	public static File extractImage(File file, long l) {
		return file; // TODO
	}
}
