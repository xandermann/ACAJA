package ffmpeg_tools;
import java.io.File;
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
	public void execute(SelectableFile file) {
		if(file instanceof SettingsFile) {
			HashMap<Integer, Object> ffmpegRequests = ((SettingsFile) file).getOldSettings();
			HashMap<Integer, Object> newSettings = ((SettingsFile) file).getOldSettings();
			for(Integer requestKey : ffmpegRequests.keySet()) {
				if(ffmpegRequests.get(requestKey) instanceof String) {
					if(ffmpegRequests.get(requestKey).equals("codec video")) {
						String oldExtension =  
								file.getSourceFile().getName().split("[.]")
								[file.getSourceFile().getName().split("[.]").length-1];
						String fileName = file.getSourceFile().getName();
						String newFileName = fileName.substring(0, fileName.lastIndexOf(oldExtension)-1)+newSettings.get("codec video");
						super.execute(fileName+" "+newFileName);
					}
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
	public File extractImage(File file, long l) {
		return file; // TODO
	}

}
