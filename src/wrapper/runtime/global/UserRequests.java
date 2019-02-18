package wrapper.runtime.global;

import java.util.*;
import files.*;
import wrapper.runtime.details.FFmpegRuntime;
import wrapper.runtime.details.Request;
import wrapper.streams.WatchedConsumer;

/**
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class UserRequests{		
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour appliquer des modifications sur un ficheir media. 
	 * 
	 * @param file 		La fichier sur lequel il faut realiser les modifications. 
	 */
	public static void execute(SelectableFile file) {
		Request request = new Request(file.getFullName());
		if(file instanceof SettingsFile) {
			HashMap<SettingType, String> ffmpegRequests = ((SettingsFile) file).getRequests();
			for(SettingType requestKey : ffmpegRequests.keySet()) {
					if(requestKey == SettingType.VIDEO_CODEC) {
						String oldExtension = file.getSourceFile().getPath().split("[.]")[file.getSourceFile().getPath().split("[.]").length-1];
						String fileName = file.getSourceFile().getPath();
						String newFileName = fileName.substring(0, fileName.lastIndexOf(oldExtension)-1) + ffmpegRequests.get(SettingType.VIDEO_CODEC);
						//WatchedConsumer.consumeStreams(FFmpegRuntime.execute(new String[]{"-i", fileName, newFileName}));
					}
			}
		}
	}
	
	
	//==================================================================================================================================================
}
