package ffmpeg_tools;
import java.io.IOException;

public class FFmpegBasic {
	public final static String ffmpegPath = "..\\..\\ffmpeg\\bin\\";
	
	public final static String temporaryDataPath = "..\\..\\temporary_data\\";
	
	private final static Runtime run = Runtime.getRuntime();
	
	
	public static boolean execute(String ffmpegRequest){
		if(ffmpegRequest==null) 
			throw new NullPointerException("La requete FFmpeg est null !");
		else if(ffmpegRequest.equals(""))
				throw new NullPointerException("La requete FFmpeg est vide !");
		try{
			run.exec(ffmpegRequest);
			return true;
		}catch(IOException e){
			return false;
		}
	}
}
