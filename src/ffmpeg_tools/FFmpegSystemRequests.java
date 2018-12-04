package ffmpeg_tools;
import tools.*;

public class FFmpegSystemRequests extends FFmpegBasic{
	private String sourcePath;
	
	public FFmpegSystemRequests(String sourcePath) {
		if(sourcePath==null) 
			throw new NullPointerException("Le chemin vers le fichier sourcePath est null !");
		else if(sourcePath.equals(""))
				throw new NullPointerException("Le chemin vers le fichier sourcePath est vide !");
		this.sourcePath = sourcePath;
	}
	
	
	public String extractAnImage(int time) {
		execute(ffmpegPath+" -i "+sourcePath+
				" -vframes 1 -r 1 -ss "+Tools.convertTimeToString(time)+ " -t 00:00:01 "+
				temporaryDataPath+"temporary_image_"+IdSequence.newId()+".jpg");
		return temporaryDataPath+"temporary_image_"+IdSequence.currentId()+".jpg";
	}
}
