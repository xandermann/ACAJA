package wrapper.language;

public final class FlagConstants {
	public final static String FLAG_SUPPORTED_CODECS = "-codecs";
	
	public final static String[] FLAG_BEST_QUALITY_CONVERSION = {"-crf", "0"};
	public final static String[] FLAG_WORSE_QUALITY_CONVERSION = {"-crf", "51"};
	
	public final static String FLAG_VIDEO_CODEC = "-vcodec";
	public final static String FLAG_VIDEO_BITRATE = "-b";
	public final static String FLAG_FRAMERATE = "-r";
	public final static String FLAG_RESOLUTION = "-s";
	
	public final static String FLAG_AUDIO_CODEC = "-acodec";
	public final static String FLAG_AUDIO_BITRATE = "-ab";
	public final static String FLAG_SAMPLING_RATE =  "-ar";
	public final static String FLAG_NUMBER_AUDIO_CHANNELS = "-ac";
	
	public final static String[] FLAG_RESIZE = {"-vf ", "scale=", ":"};
	public final static String[] FLAG_CROP = {"-filter:v", "\"crop=", ":", "\""};
	public final static String[] FLAG_PERIOD = {"-ss", "-t"};
	public final static String[] FLAG_ROTATE = {"-vf", "transpose=1"};
}
