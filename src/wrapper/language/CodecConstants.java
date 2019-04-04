package wrapper.language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ CLASSE POUR ACCEDER AUX CODECS SUPPORTES PAR FFMPEG. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class CodecConstants {
	public final static String[] ALL_EXTENSIONS = {".3g2",".3gp",".asf",".avi",".flv",".m4v",".mov",".mkv",".mp2",".mp4",".mpeg",".mpg",".ogg",".webm",".wmv"};
	public final static String[] ALL_SUPPORTED_VIDEO_CODECS =  { "dirac" ,"flv1", "h264","mjpeg", "mpeg1video", "mpeg2video", "mpeg4", "theora", "vp8", "vp9", "wmv1", "wmv2" };
	//"h263",
	public final static String[] ALL_SUPPORTED_AUDIO_CODECS = { "aac", "ac3", "alac", "flac", "mp1", "mp2", "mp3", "opus", "vorbis", "wavpack", "wmalossless", "wmapro", "wmav1", "wmav2"};
	public final static Map<String, Map<String,List<String>>> CORRESPONDING_EXTENSION;
	static {
		Map<String, Map<String,List<String>>> initialize_extensions = new HashMap<String,Map<String,List<String>>>();
		Map<String,List<String>> compatible_codecs = new HashMap<String,List<String>>();
		List<String> audioCodecList = new ArrayList<String>();
		for(String extension : ALL_EXTENSIONS) {
			switch(extension){
			case ".3g2":
				//audio
				audioCodecList.add("aac");
				
				//mpeg4 
				compatible_codecs.put("mpeg4",audioCodecList);

				//h264
				compatible_codecs.put("h264", audioCodecList);
				break;
			case ".3gp":
				//audio
				audioCodecList.add("aac");

				//mpeg4 
				compatible_codecs.put("mpeg4",audioCodecList);

				//h264
				compatible_codecs.put("h264", audioCodecList);
				break;
			//case ".aaf": break;
			case ".asf":
				//multi
				audioCodecList.addAll(Arrays.asList(ALL_SUPPORTED_AUDIO_CODECS));
				audioCodecList.remove("wmv3");
				for(String videoCodec : ALL_SUPPORTED_VIDEO_CODECS)
						compatible_codecs.put(videoCodec, audioCodecList);
				break;
			case ".avi":
				//multi
				audioCodecList.addAll(Arrays.asList(ALL_SUPPORTED_AUDIO_CODECS));
				for(String videoCodec : ALL_SUPPORTED_VIDEO_CODECS)
						compatible_codecs.put(videoCodec, audioCodecList);
				break;
			case ".flv":
				//audio
				audioCodecList.add("aac");
				audioCodecList.add("mp3");
				
				//h264
				compatible_codecs.put("h264", audioCodecList);
				break;
			case ".m4v":
				//Try others audio, multiple unknown (1)
				//audio
				audioCodecList.add("aac");
				audioCodecList.add("mp3");
				
				//h264
				compatible_codecs.put("h264",audioCodecList);
				//mpeg4
				compatible_codecs.put("mpeg4",audioCodecList);
				//mpeg2
				compatible_codecs.put("mpeg2video",audioCodecList);
				//mpeg1
				compatible_codecs.put("mpeg1video",audioCodecList);
				break;
			case ".mov":
				//audio
				audioCodecList.add("aac");
				audioCodecList.add("mp3");
				
				//mjpeg
				compatible_codecs.put("mjpeg",audioCodecList);
				//mpeg1video
				compatible_codecs.put("mpeg1video",audioCodecList);
				break;
			case ".mkv":
				//multi
				audioCodecList.addAll(Arrays.asList(ALL_SUPPORTED_AUDIO_CODECS));
				for(String videoCodec : ALL_SUPPORTED_VIDEO_CODECS)
						compatible_codecs.put(videoCodec, audioCodecList);
				break;
			case ".mp2":
				//audio
				audioCodecList.add("mp1");
				audioCodecList.add("mp2");
				audioCodecList.add("mp3");
				
				//mpeg1
				compatible_codecs.put("mpeg1video",audioCodecList);
				break;
			case ".mp4":
				//Try others audio, multiple unknown (2)
				//audio
				audioCodecList.add("aac");
				audioCodecList.add("mp3");
				
				
				//h264
				compatible_codecs.put("h264",audioCodecList);

				//mpeg4
				compatible_codecs.put("mpeg4",audioCodecList);

				//mpeg2
				compatible_codecs.put("mpeg2video",audioCodecList);

				//mpeg1
				compatible_codecs.put("mpeg1video",audioCodecList);
				break;
			case ".mpeg":
				//audio
				audioCodecList.add("mp1");
				audioCodecList.add("mp2");
				audioCodecList.add("mp3");
				audioCodecList.add("aac");
				
				//mpeg1
				compatible_codecs.put("mpeg1video",audioCodecList);
				break;
			case ".mpg":
				//audio
				audioCodecList.add("mp1");
				audioCodecList.add("mp2");
				audioCodecList.add("mp3");
				
				//mpeg1
				compatible_codecs.put("mpeg1video",audioCodecList);
				break;
			case ".ogg":
				//audio
				audioCodecList.add("flac");
				audioCodecList.add("vorbis");
				
				//theora
				compatible_codecs.put("theora",audioCodecList);
				//dirac
				compatible_codecs.put("dirac",audioCodecList);
				break;
			//case ".svi": break;
			case ".webm":
				//audio
				audioCodecList.add("opus");
				audioCodecList.add("vorbis");
				
				//vp8
				compatible_codecs.put("vp8",audioCodecList);
				//vp9
				compatible_codecs.put("vp9",audioCodecList);
				//av1
				compatible_codecs.put("av1",audioCodecList);
				break;
			case ".wmv":
				//audio
				audioCodecList.add("wmav1");
				audioCodecList.add("wmav2");
				
				//wmv1
				compatible_codecs.put("wmv1",audioCodecList);
				//wmv2
				compatible_codecs.put("wmv2",audioCodecList);
				//wmv3
				compatible_codecs.put("wmv3",audioCodecList);
				//wmv3image
				compatible_codecs.put("wmv3image",audioCodecList);
				break;
				
			}
			initialize_extensions.put(extension,compatible_codecs);
			compatible_codecs = new HashMap<String,List<String>>();
			audioCodecList = new ArrayList<String>();
			audioCodecList.clear();
		}
		CORRESPONDING_EXTENSION = Collections.unmodifiableMap(initialize_extensions);
		
	}
}
