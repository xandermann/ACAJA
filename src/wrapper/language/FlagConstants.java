package wrapper.language;

/**
 * [ CLASSE POUR ACCEDER AUX FLAGS ACCEPTES DANS LES REQUETES FFMPEG. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public interface FlagConstants {
	public final static String FLAG_INPUT = "-i";
	
	public final static String[] FLAG_ACCEPT_ABSOLUTE_PATH = {"-safe", "0"};
	
	public final static String FLAG_SEPARATOR = ":";
	public final static String S = FLAG_SEPARATOR;
	
	public final static String FLAG_MAP = "-map";
	
	public final static String FLAG_FILTER = "-c";
	public final static String FLAG_FILTER_AUDIO = FLAG_FILTER+FLAG_SEPARATOR+"a";
	public final static String FLAG_FILTER_VIDEO = FLAG_FILTER+FLAG_SEPARATOR+"v";
	public final static String FLAG_COPY = "copy";
	public final static String FLAG_FILTER_ALL_COPY = FLAG_FILTER+FLAG_SEPARATOR+FLAG_COPY;
	
	public final static String FLAG_SUPPORTED_CODECS = "-codecs";
	
	public final static String FLAG_QUALITY = "-crf";
	public final static String FLAG_SAME_QUALITY = "-qscale";
	
	public final static String FLAG_VIDEO_CODEC = "-vcodec";
	public final static String FLAG_VIDEO_BITRATE = "-b"+FLAG_SEPARATOR+"v";
	public final static String FLAG_FRAMERATE = "-r";
	public final static String FLAG_RESOLUTION = "-s";
	
	public final static String FLAG_AUDIO_CODEC = "-acodec";
	public final static String FLAG_AUDIO_BITRATE = "-b"+FLAG_SEPARATOR+"a";
	public final static String FLAG_SAMPLING_RATE =  "-ar";
	public final static String FLAG_NUMBER_AUDIO_CHANNELS = "-ac";
	
	public final static String FLAG_MODIFY  = "-vf";
	
	public final static String[] FLAG_RESIZE = {FLAG_MODIFY, "scale="};
	public final static String[] FLAG_CROP = {"-filter:v","crop="};
	public final static String[] FLAG_PERIOD = {"-ss", "-t"};
	public final static String[] FLAG_ROTATE = {FLAG_MODIFY, "transpose=1", "transpose=2", "transpose=2,transpose=2","transpose=0", "transpose=3"};
	public final static String[] FLAG_BLUR = {"-filter_complex", 
											  "[0:v]crop=", ",boxblur=10[fg];[0:v][fg]overlay=", "[v]",
											  FLAG_MAP, "[v]", FLAG_MAP, "0"+S+"a", 
										      FLAG_FILTER_VIDEO, "libx264", FLAG_FILTER_AUDIO, FLAG_COPY, 
										      "-movflags", "+faststart"};
	
	
	public final static String[] FLAG_CONCAT = {FLAG_ACCEPT_ABSOLUTE_PATH[0], FLAG_ACCEPT_ABSOLUTE_PATH[1],
												"-f", "concat", FLAG_INPUT, FLAG_FILTER, FLAG_COPY};
	
	public final static String[] FLAG_REMOVE_SOUND = {FLAG_FILTER, FLAG_COPY, "-an"};
	public final static String[] FLAG_ADD_SOUND = {FLAG_INPUT, FLAG_FILTER, FLAG_COPY, FLAG_MAP, "0"+S+"0", FLAG_MAP, "0"+S+"1"};
}
