package ffmpeg_tools;
import java.io.File;
import java.util.Map;
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
	public void execute(Map<String, Object> exec) {
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
