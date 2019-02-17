package wrapper.language;

public final class ConversionFlagConstants {
	public final static String[] FLAG_BEST_QUALITY_CONVERSION = {"-crf", "0"};
	public final static String[] FLAG_WORSE_QUALITY_CONVERSION = {"-crf", "51"};
	
	public final static String FLAG_VIDEO_CODEC = " -vcodec ";
	public final static String FLAG_AUDIO_CODEC = " -acodec ";
	public final static String FLAG_FPS = " -r ";
	public final static String FLAG_SAMPLING_RATE =  " -ar ";
	public final static String FLAG_RESOLUTION = " -s ";
}
