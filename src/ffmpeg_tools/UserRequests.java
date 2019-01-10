package ffmpeg_tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import files.SelectableFile;
import files.SettingsFile;
/**
 * 
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class UserRequests extends FFmpegRuntime {	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour appliquer des modifications sur un ficheir media. 
	 * 
	 * @param file 		La fichier sur lequel il faut realiser les modifications. 
	 */
	public static void execute(SelectableFile file) {
		if(file instanceof SettingsFile) {
			HashMap<Integer, Object> ffmpegRequests = ((SettingsFile) file).getSettings();
			HashMap<Integer, Object> newSettings = ((SettingsFile) file).getRequests();
			for(Integer requestKey : ffmpegRequests.keySet()) {
					if(requestKey == SettingsFile.VIDEO_CODEC) {
						String oldExtension =  
								file.getSourceFile().getPath().split("[.]")
								[file.getSourceFile().getPath().split("[.]").length-1];
						String fileName = file.getSourceFile().getPath();
						String newFileName = fileName.substring(0, fileName.lastIndexOf(oldExtension)-1)+
								newSettings.get(SettingsFile.VIDEO_CODEC);
						
						/**
						 * REQUETE DE CONVERSION SOUMISE A FFMPEG.
						 */
						Process conversion = execute(fileName+" "+newFileName);
						
						try {			 
							String information = null;	
							
							/**
							 * CONSOMMATION DU FLUX.
							 */
							try {
								//On "consomme" le flux des messages, pour ne pas bloquer le waitFor().
								BufferedReader br = new BufferedReader(new InputStreamReader(conversion .getErrorStream()));
								while( (information = br.readLine()) != null ) ;
								br.close();	
							} catch (IOException e) {}
							
							/**
							 * ATTENTE DE FIN DE LA CONVERSION.
							 */
							//On oblige JAVA a attendre la fin de l'execution de la requete par FFMPEG. 
							conversion.waitFor();
						} catch (InterruptedException e) {}
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
