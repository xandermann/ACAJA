package ffmpeg_tools;
import java.io.File;
import java.util.*;
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
	 * TODO
	 */
	public UserRequests() {
	}

	/**
	 * TODO
	 * @param exec
	 */
	public void execute(HashMap<String, Object> ffmpegRequests) {
		for(String requestKey : ffmpegRequests.keySet()) {
			if(ffmpegRequests.get(requestKey) instanceof String) {
				if(ffmpegRequests.get(requestKey).equals("codec_video")) {
					
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
